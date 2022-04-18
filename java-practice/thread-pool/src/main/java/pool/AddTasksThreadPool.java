package pool;

import task.AddTask;

import java.util.concurrent.*;

/**
 * 功能描述: 处理累加任务的线程池
 *
 * @author youyou
 * @date 2/8/20 5:05 PM
 */
public class AddTasksThreadPool {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        version2();
        System.out.println("finish :" + (System.currentTimeMillis() - start));
    }

    /**
     * 功能描述: 第一版的线程池设计方案.
     *
     * 设计方案:
     * 设置5个线程去执行累加任务, 并且一次性将所有的任务都放进任务队列中.
     *
     * @author youyou
     * @date 2/8/20 5:06 PM
     */
    private static void version1() {
        int threadCount = 5;
        int taskQueueSize = AddTask.NUMBER_OF_TASK;
        ExecutorService pool = new ThreadPoolExecutor(threadCount, threadCount, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(taskQueueSize), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < AddTask.NUMBER_OF_TASK; i++) {
            pool.execute(new AddTask());
        }

        pool.shutdown();
    }

    /**
     * 功能描述: 第二版的线程池设计方案.
     *
     * 设计方案:
     * 创建一个容量为50的线程池, 5个线程/45容量的任务队列. 使用信号量控制进入任务队列的任务.
     * 当且仅当线程池的任务队列存在空位时, 将任务提给给线程池.
     *
     * 改进原因:
     * 线程池任务队列也应该作为一种资源被限制, 如果一次性将所有的任务塞入队列中可能会导致内存溢出的情况.
     *
     * @author youyou
     * @date 2/8/20 5:28 PM
     */
    private static void version2() {
        int capacityOfThread = 50;
        final Semaphore semaphore = new Semaphore(capacityOfThread);
        int threadCount = 5;
        ExecutorService pool = new ThreadPoolExecutor(threadCount, threadCount, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(capacityOfThread - threadCount), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 51; i++) {
            executeTask(semaphore, pool);
        }

        pool.shutdown();
    }

    private static void executeTask(Semaphore semaphore, ExecutorService pool) {
        try {
            // 获取信号量
            semaphore.acquire();
            pool.execute(() -> {
                new AddTask().run();
                semaphore.release();
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 功能描述: 第三版的线程池设计方案.
     * 是对第二版的改进, 在第二版中如果任务数过多时会出现java.util.concurrent.RejectedExecutionException异常.
     * 这是由于在线程池任务队列满的情况下仍然向线程池中添加任务导致的.
     *
     * 设计方案:
     * 信号量的引入是将线程池任务队列视作是一种资源, 只有当队列不满时才可以往线程池中添加任务.
     * 所以信号量的大小 = 线程池任务队列的大小
     *
     * 改进原因:
     * v2与v3的区别在于, 到底一个核心线程数=最大线程数=5 && 任务队列容量=45的线程池可容纳的任务数最多是多少.
     * 这里存在一个问题, 如果核心线程数 > 任务队列容量时那么线程池中的线程数永远没法达到核心线程数的大小
     *
     * @author youyou
     * @date 2/8/20 5:28 PM
     */
    private static void version3() {
        int capacityOfThread = 3;
        int threadCount = 1;
        final Semaphore semaphore = new Semaphore(capacityOfThread);
        ExecutorService pool = new ThreadPoolExecutor(threadCount, threadCount, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(capacityOfThread), new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < AddTask.NUMBER_OF_TASK; i++) {
            executeTask(semaphore, pool);
        }

        pool.shutdown();
    }


}
