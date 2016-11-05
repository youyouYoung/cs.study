package datastructures.list;

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
		this.listLength = 0;
		this.rootNode = null;
	}
	
	/**
	 * constructor
	 * Initializes this list to a lot of element contained by otherList.
	 * */
	public LinkedList(LinkedList<? extends T> otherList)
	{
		this.listLength = 0;
		copy(otherList);
	}
	
	/**
	 * Method to return the number of nodes in this list.
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
	 * Method to insert newItem at the end of this list.
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
		this.listLength++;
	}
	
	/**
	 * Method to insert newItem at the start of this list.
	 * */
	public void addFirst(T t)
	{
		ListNode<T> node = new ListNode<T>(t);
		
		node.setNextNode(rootNode);
		this.rootNode = node;
		this.listLength++;
	}
	
	/**
	 * Method to return the element at the specified position in this list.
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
	 * Method to get the position of a element.
	 * */
	public int indexOf(T t)
	{
		ListNode<T> currentNode = this.rootNode;
		int resultIndex = -1;
		int currentIndex = 0;
		
		while (currentNode != null && resultIndex == -1)
		{
			if (currentNode.getElement().equals(t))
			{
				resultIndex = currentIndex;
			}
			
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}
		
		return resultIndex;
	}
	
	/**
	 * Method to search the list is contain this element.
	 * */
	public boolean contains(T t)
	{
		ListNode<T> currentNode = this.rootNode;
		boolean isFound = false;
		
		while (currentNode != null && !isFound)
		{
			isFound = currentNode.getElement().equals(t);
			currentNode = currentNode.getNextNode();
		}
		
		return isFound;
	}
	
	/**
	 * Method to remove the element at the specified position in this list.
	 * */
	public T remove(int index)
	{
		if (index < 0 || index >= listLength)
		{
			throw new IndexOutOfBoundsException("Error: Index out of Bounds. [index="+index+"]");
		}
		
		ListNode<T> prevNode = null;
		ListNode<T> currentNode = rootNode;		//as long as listLength != 0, the rootNode will nerver be null;
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
	
	/**
	 * Method to remove the first occurrence of the specified element from this list.
	 * */
	public boolean remove(T t)
	{
		boolean result = false;
		int indexOfElement = indexOf(t);
		
		if (result = (indexOfElement != -1))
		{
			remove(indexOfElement);
		}
		
		return result;
	}
	
	/**
	 * Method to replaces the element at the specified position in the list with a specified element.
	 * */
	public T set(int index, T newElement)
	{
		if (index < 0 || index >= this.listLength)
		{
			throw new IndexOutOfBoundsException("Error: Index out of Bounds. [index="+index+"]");
		}
		
		ListNode<T> currentNode = this.rootNode;
		T originElement = null;
		for (int i = 0; i < index; i++)
		{
			currentNode = currentNode.getNextNode();
		}
		
		originElement = currentNode.getElement();
		currentNode.setElement(newElement);
		return originElement;
	}
	
	private void copy(LinkedList<? extends T> otherList)
	{
		if (otherList.size() == 0)
		{
			return;
		}
		
		/**
		 * STEP1: make a new rootNode to the otherList.
		 * 
		 * STEP2: link the new rootNode to the end of this list.
		 * 
		 * STEP3: add the length of the otherList to the listLength.
		 * */
		//STEP1
		ListNode<T> firstNewNode = null;
		ListNode<T> prevNode = null;
		ListNode<? extends T> currentNode_other = otherList.rootNode;
		for (int i = 0; i < otherList.listLength; i++)
		{
			ListNode<T> newNode = new ListNode<T>(currentNode_other.getElement());
			currentNode_other = currentNode_other.getNextNode();
			if (prevNode == null)
			{
				firstNewNode = newNode;
				prevNode = firstNewNode;
			}
			else
			{
				prevNode.setNextNode(newNode);
				prevNode = prevNode.getNextNode();
			}
		} //end for
		
		//STEP2
		if (this.rootNode == null)
		{
			rootNode = firstNewNode;
		}
		else
		{
			ListNode<T> currentNode = this.rootNode;
			while (currentNode.getNextNode() != null)
			{
				currentNode = currentNode.getNextNode();
			}
			currentNode.setNextNode(firstNewNode);
		}
		
		//STEP3
		this.listLength += otherList.listLength;
	}
}
