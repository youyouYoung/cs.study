package link;

import lombok.Data;


/**
 * 功能描述: 单项列表结点
 *
 * @author youyou
 * @date 12/2/19 9:31 PM
 */
@Data
public class LinkedNode<T> {
    private T value;
    private LinkedNode<T> next;
}
