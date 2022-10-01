package link;

/**
 * 功能描述: 单项列表结点
 *
 * @author youyou
 * @date 12/2/19 9:31 PM
 */
public class LinkedNode<T> {
    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LinkedNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }

    private T value;
    private LinkedNode<T> next;


}
