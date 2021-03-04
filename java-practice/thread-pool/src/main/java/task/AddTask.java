package task;

/**
 * 功能描述: 模拟任务的实际工作内容, 这是是将一个数从1加到100.
 *
 * @author youyou
 * @date 2/8/20 4:59 PM
 */
public class AddTask implements Runnable {
    public static final int NUMBER_OF_TASK = 1000000;

    @Override
    public void run() {
        for(int i = 1; i < 100000; i++);
        System.out.println("i am done.");
    }
}
