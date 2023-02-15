import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
		implements SearchTreeInterface<T>, java.io.Serializable {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
		setRootNode(new BinaryNode<T>(rootEntry));
	}

	@Override
	public T add(T newEntry) {
		T result = null;
		if (isEmpty())
			setRootNode(new BinaryNode<T>(newEntry));
		else
			result = addEntryHelperNonRecursive(newEntry);
		return result;
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	private T addEntryHelperNonRecursive(T newEntry) {
		// YOUR CODE HERE! 
		T result=null;
		BinaryNode<T> tempNode= getRootNode();
		boolean addNode= false;
		while(tempNode!=null && !addNode){
			int comparison= newEntry.compareTo(tempNode.getData());
			if(comparison<=0){
				if(tempNode.hasLeftChild()){
					tempNode=tempNode.getLeftChild();
				}else{
					tempNode.setLeftChild(new BinaryNode<>(newEntry));
					addNode=true;
					result=tempNode.getLeftChild().getData();

				}
			}else{
				if(tempNode.hasRightChild()){
					tempNode=tempNode.getRightChild();

				}else{
					tempNode.setRightChild(new BinaryNode<>(newEntry));
					addNode=true;
					result=tempNode.getRightChild().getData();
				}
			}
		}
		return result; // placeholder: replace with your own code
	}

	
	// THIS METHOD CANNOT BE RECURSIVE.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countEntriesNonRecursive(T target) {
		// YOUR CODE HERE! 
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		//int timeLoop=0;
		BinaryNode<T> currentNode = getRootNode();
	
	
		while(currentNode!=null){
			//timeLoop++;
			int comparison=target.compareTo(currentNode.getData());
			if(comparison==0){
				count++;
				currentNode=currentNode.getLeftChild();
				
			}else if(comparison<0){
				currentNode=currentNode.getLeftChild();
			}else{
				currentNode=currentNode.getRightChild();
			}
			
		}
		//System.out.println(timeLoop);
		// consider a loop!
	
		return count; 
	}
	
	
	// THIS METHOD MUST BE RECURSIVE! 
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterRecursive(T target) {
		// YOUR CODE HERE! 
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null){
			return count;
		}else{
			count= countGreaterRecursiveHelper(rootNode,target);
		}	
		// consider a helper method!
			
		return count;
	}
	private int countGreaterRecursiveHelper(BinaryNode<T> rootNode,T target){
		int count=0;
		
		int comparison=rootNode.getData().compareTo(target); //6<8 return -1
 		if(rootNode.isLeaf()){
			if(comparison>0){

				return count + 1;
			}else{
				return count;
			}

		}

		
		if(comparison>0 ){
				count+=1;
				if(rootNode.hasLeftChild()){
					count += countGreaterRecursiveHelper(rootNode.getLeftChild(), target);
				}
				
				if(rootNode.hasRightChild()){
					count += countGreaterRecursiveHelper(rootNode.getRightChild(), target);
				}
			}
		
		if(comparison<=0 && rootNode.hasRightChild()){
			//root> target && hasLeft
				count += countGreaterRecursiveHelper(rootNode.getRightChild(), target);
			}

		return count;
	}
		
	
	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {
		// YOUR CODE HERE! 
		
		// this initial code is meant as a suggestion to get your started- use it or delete it!
		int count = 0;
		BinaryNode<T> rootNode = getRootNode();
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
		nodeStack.push(rootNode);
		BinaryNode<T> current;
		while(!nodeStack.isEmpty()){
			current= nodeStack.pop();
			int comparison=current.getData().compareTo(target);
			BinaryNode<T> leftChild = current.getLeftChild();
			BinaryNode<T> rightChild = current.getRightChild();
			if(comparison<=0){				
				if(rightChild!=null){
					nodeStack.push(rightChild);
				}
			}
			if(comparison>0){
				count+=1;
				if(rightChild!=null){
					nodeStack.push(rightChild);
				}
				if(leftChild!=null){
					nodeStack.push(leftChild);
				}

			}
			
		}

		// consider a loop based on the stack!

		return count;
	}

		/*
		 * check stack
		 * if node.data > target
		 * 		count+1
		 * 	
		 */
	
	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in Part B.
	public int countUniqueValues() {
		int count=0;
		Stack<BinaryNode<T>> nodeStack = new Stack<>();
		Stack<BinaryNode<T>> temp = new Stack<>();
		BinaryNode<T> currentNode = getRootNode();

		while (!nodeStack.isEmpty() || (currentNode != null)) {

			// Find leftmost node with no left child
			while (currentNode != null) {
				nodeStack.push(currentNode);
				currentNode = currentNode.getLeftChild();
			} 

			// Visit leftmost node, then traverse its right subtree
			if (!nodeStack.isEmpty()) {
				BinaryNode<T> nextNode = nodeStack.pop();
				if(temp.isEmpty() 
				|| !temp.peek().getData().equals(nextNode.getData())){
					temp.push(nextNode);
					count++;
				}
				currentNode = nextNode.getRightChild();
			} 
		} 
		return count; 
	}
		
	
	public int getLeftHeight() {
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null) {
			return 0;
		} else if(!rootNode.hasLeftChild()) {
			return 0;
		} else {
			return rootNode.getLeftChild().getHeight();
		}
	}

	public int getRightHeight() {
		BinaryNode<T> rootNode = getRootNode();
		if(rootNode==null) {
			return 0;
		} else if(!rootNode.hasRightChild()) {
			return 0;
		} else {
			return rootNode.getRightChild().getHeight();
		}
	}
	

}