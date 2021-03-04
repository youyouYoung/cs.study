import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 功能描述: fork join 多线程框架使用
 *
 * @author youyou
 * @date 10/25/19 10:05 PM
 * @since 1.7+
 */
public class ForkJoinTask extends RecursiveTask<Long> {

    private long start;
    private long end;
    private static final long MAX = 1000000000L;
    private static final long THRESHOLD = 1000L;

    public ForkJoinTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 功能描述: 划分子任务
     *
     * @return TODO
     * @author youyou
     * @date 10/25/19 10:09 PM
     * @since 1.7+
     */
    @Override
    public Long compute() {

        if (end < start) {
            return 0L;
        }

        boolean needSplit = end - start > THRESHOLD;
        if (needSplit) {

            long middle = (end - start) / 2;
            ForkJoinTask leftForkJoinTask = new ForkJoinTask(start, middle);
            ForkJoinTask rightForkJoinTask = new ForkJoinTask(middle + 1, end);

            leftForkJoinTask.fork();
            rightForkJoinTask.fork();

            return leftForkJoinTask.join() + rightForkJoinTask.join();
        }

        long sum = 0L;
        for (long i = start; i < end; i++) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(new ForkJoinTask(1, MAX)));

        /*
        * 调用时发生异常:
        *
        * Exception in thread "ForkJoinPool-1-worker-1" java.lang.NoClassDefFoundError: Could not initialize class java.util.concurrent.locks.AbstractQueuedSynchronizer$Node
	at java.util.concurrent.locks.AbstractQueuedSynchronizer.acquire(AbstractQueuedSynchronizer.java:1198)
	at java.util.concurrent.locks.ReentrantLock$NonfairSync.lock(ReentrantLock.java:209)
	at java.util.concurrent.locks.ReentrantLock.lock(ReentrantLock.java:285)
	at java.util.concurrent.ForkJoinTask.recordExceptionalCompletion(ForkJoinTask.java:464)
	at java.util.concurrent.ForkJoinTask.setExceptionalCompletion(ForkJoinTask.java:491)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:291)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
        * */
    }
}
