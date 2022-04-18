package list;

class ListNode<T>
{
	private T element;				//variable to store the element of the node.
	private ListNode<T> nextNode;	//pointer to the next node.
	
	/**
	 * Initialized the node element to param t.
	 * */
	public ListNode(T t)
	{
		this.element = t;
		nextNode = null;
	}

	/**
	 * Method to return the element of this node.
	 * */
	public T getElement()
	{
		return element;
	}

	/**
	 * Method to set element value of this node.
	 * */
	public void setElement(T element)
	{
		this.element = element;
	}

	public ListNode<T> getNextNode()
	{
		return nextNode;
	}

	public void setNextNode(ListNode<T> nextNode)
	{
		this.nextNode = nextNode;
	}
}

