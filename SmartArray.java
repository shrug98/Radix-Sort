// TO DO: add your implementation and JavaDoc

public class SmartArray<T>{

	private static final int DEFAULT_CAPACITY = 2;	//default initial capacity / minimum capacity
	private T[] data;	//underlying array
	private int size; 
	private int capacity; 

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	
	@SuppressWarnings("unchecked")
	
	
	/**
	 * Constructs a new SmartArray and sets size to DEFAULT_CAPACITY
	 * size is set to 0 and initial capacity is 2 
	*/
	public SmartArray(){
		//constructor
		data = (T[]) new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.capacity = 2; 
		
		//initial capacity of the array should be DEFAULT_CAPACITY
	}

	@SuppressWarnings("unchecked")
	
	/**
	 * Constructs a new SmartArray with the specified initialCapacity
	 * @param initialCapacity it is in integer form and stores the capacity of the array 
	 * of this object. 
	 */
	public SmartArray(int initialCapacity){
		// constructor
		// set the initial capacity of the smart array as initialCapacity
		data = (T[]) new Object[initialCapacity];
		this.size = 0;
		this.capacity = initialCapacity;

		// throw IllegalArgumentException if initialCapacity is smaller than 1
	}
	
	/**
	 * Return the size of the SmartArray object.
	 * @return the size of the SmartArray object
	 */

	public int size() {	  
		//report number of elements in the smart array
		// O(1)
		
		return size;
	}
		
	/**
	 * Return the capacity of the SmartArray object.
	 * @return the capacity of the SmartArray object
	 */
	public int capacity() { 
		//report max number of elements before the next expansion
		// O(1)

		return capacity;
	}
	
	@SuppressWarnings("unchecked")
	
	/**
	 * Adds a new value at the specified index
	 * shifts elements to the right if needed
	 * @param index. Index is an integer value that determines the position at which value to be stored.  
	 * value of type T which is what will be put in the specified location 
	 */
	public void add(int index, T value){
		// insert value at index, shift elements if needed  
		// double the capacity if no space is available
		// throw IndexOutOfBoundsException for invalid index
		// O(N) where N is the number of elements in the array
		
		// Note: this method may be used to append items as
		// well as insert items
		
		if (index < 0 || index > data.length) {
			throw new IndexOutOfBoundsException(); 
		}
		
		if(this.size + 1 > data.length) {
			T[] newData = (T[]) new Object[size*2];
			this.capacity = size * 2; 
			
			//copy from old array
			for (int i=0;i<size;i++){
				newData[i] = data[i];
			}
				
			data = newData;
			
		}
		
		if(data[index] != null) {
			for(int j = size; j > index; j--) {
				data[j] = data[j-1]; 
			}
		}
		
		
		
		
		//add x
		data[index] = value;
		size++;
		
		
	}
	
	/**
	 * returns the item at specified index 
	 * @param index. Index is an integer value from which the value will be taken.  
	 * @return value at specified index 
	 */

	public T get(int index){
		// return the item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		if (index < 0 || index > data.length) {
			throw new IndexOutOfBoundsException(); 
		}
		else {
			return data[index];	
			 
		}
		
	}
		
	/**
	 * replaces the value at given index with given value  
	 * @param index. Index is an integer value where the new value will be put.  
	 * value which is of type T is the new item to be put 
	 * @return old item at index 
	 */

	public T replace(int index, T value){
		// change item at index to be value	
		// return old item at index
		// throw IndexOutOfBoundsException for invalid index
		// O(1)
		
		// Note: you cannot add new items with this method
		
		if (index < 0 || index > data.length) {
			throw new IndexOutOfBoundsException(); 
		}
		else {
			T a = data[index];
			data[index] = value;
			return a;
		}
		
		
	}

	/**
	 * removes the value at given index   
	 * shifts elements to remove any gap in the array 
	 * @param index. Index is an integer value which gives the position of the value to be removed   
	 * @return value that was removed 
	 */

	@SuppressWarnings("unchecked")
	public T delete(int index){
		// remove and return element at position index
		// shift elements to remove any gap in the array
		// throw IndexOutOfBoundsException for invalid index
		
		// halve capacity if the number of elements falls below 1/4 of the capacity
		// capacity should NOT go below DEFAULT_CAPACITY
		
		// O(N) where N is the number of elements in the list
		
		if (index < 0 || index > data.length) {
			throw new IndexOutOfBoundsException(); 
		}
		
		T a = data[index];
		for(int i = index; i < size-1; i++) {
			T x = data[i+1];
			data[i] = x;
		}
		data[size-1] = null;
		size--; 
		
		if(this.size <= 0.25*data.length) {
			T[] newData = (T[]) new Object[capacity/2];
			this.capacity = capacity/2;
			for (int i=0;i<size;i++){
				newData[i] = data[i];
			}
				
			data = newData;
		}
		
		
	
		
		

		return a;
	}  

	
	/**
	 * changes the max number of items allowed before next expansion
	 * ensures newCapacity is above DEFAULT_CAPACITY
	 * ensures newCapacity large enough to accommodate current number of items 
	 * @param newCapacity. Integer value that stores the new capacity value 
	 * @return true if capacity changed; false otherwise
	 */
	@SuppressWarnings("unchecked")
	public boolean ensureCapacity(int newCapacity){
		// change the max number of items allowed before next expansion to newCapacity
		
		if (newCapacity < DEFAULT_CAPACITY || newCapacity < this.size) {
			return false; 
		}
		else {
			T[] newData = (T[]) new Object[newCapacity];
			this.capacity = newCapacity; 
			
			//copy from old array
			for (int i=0;i<size;i++){
				newData[i] = data[i];
			}
				
			data = newData;
			return true; 
		}
		
		
		// capacity should not be changed if:
		//   - newCapacity is below DEFAULT_CAPACITY; or 
		//   - newCapacity is not large enough to accommodate current number of items
		
		// return true if newCapacity gets applied; false otherwise
		// O(N) where N is the number of elements in the array
		
		
	}


	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------

	// Not required, update for your own testing
	public String toString(){
		// return string representation of DynamicArray
		// update if you want to include more information 
		return "SmartArray";
	}

	// Not required, update for your own testing
	public static void main (String args[]){

		//create a smart array of integers
		SmartArray<Integer> nums = new SmartArray<>();
		if ((nums.size() == 0) && (nums.capacity() == 2)){
			System.out.println("Yay 1");
		}

		//append some numbers 
		for (int i=0; i<3;i++)
			nums.add(i,i*2);
		
		if (nums.size()==3 && nums.get(2) == 4 && nums.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		//create a smart array of strings
		SmartArray<String> msg = new SmartArray<>();
		
		
		
		//insert some strings
		msg.add(0,"world");
		msg.add(0,"hello");
		msg.add(1,"new");
		msg.add(3,"!");
		
		//replace and checking
		if (msg.get(0).equals("hello") && msg.replace(1,"beautiful").equals("new") 
			&& msg.size() == 4 && msg.capacity() == 4 ){
			//System.out.println(msg);
			System.out.println("Yay 3");
		}
		
		//change capacity
		if (!msg.ensureCapacity(0) && !msg.ensureCapacity(3) && msg.ensureCapacity(20)
			&& msg.capacity() == 20){
			System.out.println("Yay 4");
		}	 

		//delete and shrinking
		if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world")  
			&& msg.size() == 3 && msg.capacity() == 10 ){
			System.out.println("Yay 5");
		}
	}
}