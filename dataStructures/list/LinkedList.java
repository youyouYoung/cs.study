/**
 * this class use to make a custom linked list, and it support genericity.
 * 		2016-11-13 add a member to point the end of this list.
 * */
package datastructures.list;

public class LinkedList<T>
{
	private int listLength;			//variable to store the number of elements in the list
	private ListNode<T> rootNode;	//pointer to the start of this list
	private ListNode<T> tail;		//pointer to the end of this list
	
	/**
	 * default constructor
	 * Initializes the list to an empty state.
	 * */
	public LinkedList()
	{
		this.listLength = 0;
		this.rootNode = null;
		this.tail = null;
	}
	
	/**
	 * constructor
	 * Initializes this list to a lot of element contained by otherList.
	 * */
	public LinkedList(LinkedList<? extends T> otherList)
	{
		this.listLength = otherList.listLength;
		this.rootNode = null;
		this.tail = null;
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
		
		if (tail == null)
		{
			rootNode = node;
			tail = node;
		}
		else
		{
			tail.setNextNode(node);		//add new node to the tail of this list.
			tail = node;
			/**
			 * after i add a member to point the end of this list.
			 * the code under this annotation is not use any more.
			 * */
//			ListNode<T> currentNode = rootNode;
//			while (currentNode.getNextNode() != null)
//			{
//				currentNode = currentNode.getNextNode();
//			}
//			currentNode.setNextNode(node);
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
	 * Method to append all of the element in the specified otherList into the end of this list.
	 * */
	public void addAll(LinkedList<? extends T> otherList)
	{
		copy(otherList);
		this.listLength += otherList.listLength;
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
	 * Method to get the index of the first occurrence of the specified element in this list.
	 * or -1 if this list does not contain the element.
	 * */
	public int indexOf(T t)
	{
		ListNode<T> currentNode = this.rootNode;
		int resultIndex = -1;	//the index of the specified element t.
		int currentIndex = 0;	//the index of the currentNode in this list.
		
		while (currentNode != null && resultIndex == -1)
		{
			if (currentNode.getElement().equals(t))
			{
				resultIndex = currentIndex;
			}
			
			currentNode = currentNode.getNextNode();
			currentIndex++;
		}//end loop this list.
		
		return resultIndex;
	}
	
	/**
	 * Method to search the list is contain the specified element t.
	 * it will return true if the list contain this specified element.
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
	 * it will return the element in the list of this specified index.
	 * */
	public T remove(int index)
	{
		if (index < 0 || index >= listLength)
		{
			throw new IndexOutOfBoundsException("Error: Index out of Bounds. [index="+index+"]");
		}
		
		ListNode<T> previousNode = null;		//the previous of the currentNode.
		ListNode<T> currentNode = rootNode;		//as long as listLength != 0, the rootNode will nerver be null;
		for (int i = 0; i < index; i++)
		{
			previousNode = currentNode;
			currentNode = currentNode.getNextNode();
		}
		
		if (previousNode == null)	//if the index == 0
		{
			rootNode = rootNode.getNextNode();
		}
		else
		{
			previousNode.setNextNode(currentNode.getNextNode());
		}
		listLength--;
		return currentNode.getElement();
	}
	
	/**
	 * Method to remove the first occurrence of the specified element from this list.
	 * it will return false if the element t is not contained in this list.
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
	 * it will return the origin value of the specified index in the list.
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
	
	/**
	 * Method to copy the specified otherList to this list.
	 * */
	private void copy(LinkedList<? extends T> otherList)
	{
		if (otherList.size() == 0)
		{
			return;
		}
		
		/**
		 * STEP1: copy the otherList to a new LinkedList.
		 * 		and obtain the head of the new LinkedList.
		 * 
		 * STEP2: link the new LinkedList's head to the end of current list.
		 * 
		 * STEP3: add the length of the otherList to the listLength.
		 * 		Attention:
		 * 			2016-11-13 the step3 should not be this method's business. so i drop it.
		 * */
		//STEP1
		ListNode<T> head_newList = null;	//the start of the new list.
		ListNode<T> tail_newList = null;	//the end of the new list.
		ListNode<? extends T> currentNode_other = otherList.rootNode;	//the current node of the other list.
		
		while (currentNode_other != null)	//after 
		{
			ListNode<T> node = new ListNode<T>(currentNode_other.getElement());
			if (tail_newList == null)
			{
				head_newList = node;
				tail_newList = node;
			}
			else
			{
				tail_newList.setNextNode(node);
				tail_newList = tail_newList.getNextNode();
			}
			currentNode_other = currentNode_other.getNextNode();
		}//end loop the otherList
		
		//STEP2
		if (this.rootNode == null)
		{
			rootNode = head_newList;
		}
		else
		{
			ListNode<T> currentNode = this.rootNode;
			while (currentNode.getNextNode() != null)
			{
				currentNode = currentNode.getNextNode();
			}
			currentNode.setNextNode(head_newList);
		}
		
//		//STEP3
//		this.listLength += otherList.listLength;
	}
}
