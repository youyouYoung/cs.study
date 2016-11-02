package datastructures.list;

public class LinkedList<T>
{
	private int listLength;			//当前集合节点数量
	private ListNode<T> rootNode;	//集合头节点
	
	public LinkedList()
	{
		listLength = 0;
	}
	
	public int size()
	{
		return listLength;
	}
	
	public boolean isEmpty()
	{
		return listLength == 0;
	}
	
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
