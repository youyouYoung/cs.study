package datastructures;

public class LinkedList<T>
{
	private int listLength;			//variable to store the number of elements in the list
	private ListNode<T> rootNode;	//pointer to the first node of the list
	
	/**
	 * default constructor
	 * Initializes the list to an empty state.
	 * */
	public LinkedList()
	{
		listLength = 0;
		rootNode = null;
	}
	
	/**
	 * Method to return the number of nodes in the list.
	 * */
	public int size()
	{
		return listLength;
	}
	
	/**
	 * Method to determine whether the list is empty.
	 * */
	public boolean isEmpty()
	{
		return listLength == 0;
	}
	
	/**
	 * Method to insert newItem at the end of the list.
	 * */
	public void add(T t)
	{
		ListNode<T> node = new ListNode<T>(t);
		
		if (rootNode == null)
		{
			rootNode = node;
		}
		else
		{
			ListNode<T> currentNode = rootNode;
			while (currentNode.getNextNode() != null)
			{
				currentNode = currentNode.getNextNode();
			}
			currentNode.setNextNode(node);
		}
		listLength++;
	}
	
	/**
	 * Method to insert newItem at the start of the list.
	 * */
	public void addFirst(T t)
	{
		ListNode<T> node = new ListNode<T>(t);
		
		node.setNextNode(rootNode);
		rootNode = node;
		listLength++;
	}
	
	/**
	 * Method to return the element at the specified position in the list.
	 * */
	public T get(int index)
	{
		if (index < 0 || index >= listLength)
		{
			throw new IndexOutOfBoundsException("Error: Index out of Bounds. [index="+index+"]");
		}

		ListNode<T> currentNode = rootNode;
		for (int i = 0; i < index; i++)
		{
			currentNode = currentNode.getNextNode();
		}
		return currentNode.getElement();
	}
	
	/**
	 * Method to remove the element at the specified position in the list.
	 * */
	public T remove(int index)
	{
		if (index < 0 || index >= listLength)
		{
			throw new IndexOutOfBoundsException("Error: Index out of Bounds. [index="+index+"]");
		}
		
		ListNode<T> prevNode = null;
		ListNode<T> currentNode = rootNode;
		for (int i = 0; i < index; i++)
		{
			prevNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		if (prevNode == null)
		{
			rootNode = rootNode.getNextNode();
		}
		else
		{
			prevNode.setNextNode(currentNode.getNextNode());
		}
		listLength--;
		return currentNode.getElement();
	}
}

