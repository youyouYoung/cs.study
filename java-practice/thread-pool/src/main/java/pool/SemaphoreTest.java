package pool;

import java.util.concurrent.*;

/**
 * 功能描述: TODO
 *
 * @author youyou
 * @date 2/9/20 2:17 PM
 */
public class SemaphoreTest {

    private static final int THREAD_COUNT = 30;

    private static ExecutorService threadPool = new ThreadPoolExecutor(5,5, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
//    private static ExecutorService threadPool = Executors
//            .newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(14);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            try {
                s.acquire();
            } catch (InterruptedException e) {

            }
            threadPool.execute(() -> {
                try {
                    System.out.println("save data, i: " + finalI);
                    s.release();
                } catch (Exception e) {
                }
            });
        }

        threadPool.shutdown();
    }
}