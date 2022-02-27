// TO DO: add your implementation and JavaDoc

//These are all the imports you are allowed, don't add any more!
import java.util.Scanner;
import java.io.IOException;

public class RadixSort{

	// DO NOT MODIFY INSTANCE VARIABLES PROVIDED BELOW
	// EXCEPT TO ADD JAVADOCS
	
	//an array of buckets to be used in each round
	private SmartArray<SmartArray<Sortable>> buckets;	
	
	//A SmartArray to store values to be sorted.
	//It is also used to store intermediate results after 
	//each round and the final group of sorted values.
	//The values should always be sorted in ascending order.	
	private SmartArray<Sortable> values; 

	// ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
	private int round; 
	private int numBuckets;
	private int maxLength; 

	// constructor
	
	/**
	 * Constructs a new RadixSort and initializes its private data types.   
	*/
	public RadixSort(){
		round = 0; 
		values = new SmartArray<>(); 
		buckets = new SmartArray<>(); 
 
	}
	
	// return true if the numbers are guaranteed to be sorted,
	// i.e. all digit locations have been inspected
	// return false otherwise
	
	
	/**
	 * checks if the values are sorted  
	 * @return true if all numbers are guaranteed sorted, false otherwise 
	 */
	public boolean isSorted() {

		if (this.getMaxLength() == this.round) {
			return true;
		}
		else {
			return false;
		}
		
	}

	
	/**
	 * Return the round which is about to be performed.
	 * @return the round which is about to be performed
	 */
	// return which round is about to be performed
	// round number starts with 0 after a new group of value
	// gets initialized
	public int getRound() {
	
		return round;
	}
	
	
	/**
	 * Return the number of buckets in each round 
	 * @return the number of buckets in each round 
	 */
	
	// return the number of buckets to be used in each round
	public int getNumBuckets() {
	
		return numBuckets;
	
	}

	/**
	 * Return the group of values to be sorted  
	 * @return the group of values to be sorted 
	 */
	// return the group of values to be sorted
	public SmartArray<Sortable> getValues() {
		
		return values;
	}
	
	
	/**
	 * Return the array of values corresponding to bucketNum 
	 * @return the array of values corresponding to bucketNum
     * null if invalid bucket 
	 */

	// return the array of values corresponding to bucketNum
	// return null for invalid bucketNum
	public SmartArray<Sortable> getBucket(int bucketNum) {
		
		if (bucketNum < 0 || bucketNum > numBuckets) {
			return null;
		}
		else if (buckets.get(bucketNum) == null) {
			return null;
		}
		else {
			return buckets.get(bucketNum);
		}
		
	}

	
	/**
	 * Return the max number of digits of the values 
	 * @return the max number of digits of the values  
	 */
	// return the max number of digits of the values
	public int getMaxLength() {
		int maxNum = values.get(0).digits().length(); 
		for (int i = 1; i < values.size(); i++) {
			if (values.get(i).digits().length() > maxNum) {
				maxNum = values.get(i).digits().length();
			}
		}	
		
		return maxNum;
	}
	
	
	
	/**
	 * Reads values from the provided scanner and sets it to the values array
	 * based on what base it is 
	 */

	// read values from the provided scanner
	// get ready to sort the new group of values
		
	// first specify the base:
	// "a" - alphabetic string with CAPITAL LETTERS only;
	// otherwise it would be an integer base in [2,16]
	// (10 for decimal, 2 for binary, 16 for hexdecimal, etc.)
	
	// Note: you can assume the type input is always valid	
	public void initValuesFromScanner(Scanner s) throws IOException {
		
		//--------------------------------------------------------
		// The following lines are provided to you as a starting point.
		// Use it as is or make changes as you want
		String type = s.next();
		int base;
		 
		if (!type.equals("a"))
			base = Integer.parseInt(type);		
		else
			base = SortableString.BASE;
		// end of provided code
		//--------------------------------------------------------
		
		Sortable so = null; 
		while (s.hasNext()) {
			type = s.next();
			if (base == SortableString.BASE) {
				so = new SortableString(type); 
				
			}
			else {
				so = new SortableNumber(base, type);
			}
			values.add(values.size(), so);
		
			
		} 
		if(base == SortableString.BASE) {
			buckets = new SmartArray<>(27);
			numBuckets = 27;
		}
		else {
			int x = base ;
			buckets = new SmartArray<>(x);
			numBuckets = x; 
		}
			
		
				
		
		// your code can start from here to finish 
		// reading from the scanner and initialization of values
		
		

	
	}
	
	/**
	 * checks the list of values and removes the one with invalid digits
	 * loops through values and checks if each character is within base range   
	 * @return the number of invalid digits removed 
	 */
	
	// check the list of values and remove the ones with invalid digits
	// return the number of invalid values removed
	public int sanitizeValues(){
		int invalid_digits = 0; 
		int i = 0;
		int new_size = values.size();
		while ( i < new_size ) {
			for (int j = 0; j < values.get(i).digits().length(); j++) {
				if (values.get(i).posToNum(j) < 0 || values.get(i).posToNum(j) > values.get(i).maxNum()) {
					values.delete(i);
					invalid_digits++;
					i--;
					break;
				}
			}
			new_size = values.size();
			i++;
		
			
		} 
		
			
				
		
		return invalid_digits;
	}

	
	/**
	 * checks if all the values in the array is of same length
	 * sets the values to be of same length by padding  
	 * @return the number of values padded 
	 */
	// make sure all values are of the same length
	// use padding if needed
	// return the number of values padded
	public int setSameLength(){

		int valuesPadded = 0;
		Sortable so = null; 
		int maxLength = this.getMaxLength(); 
		//System.out.println(maxLength); 
		for (int i = 0; i < values.size(); i++) {
			if(values.get(i ).digits().length() != maxLength) {
				values.get(i).padDigits(maxLength);
				String n = values.get(i).paddedDigits();
				if (values.get(i).maxNum() == 26) {
					so = new SortableString(n); 
				}
				else {
					int base = values.get(i).maxNum() + 1; 
					so = new SortableNumber(base, n); 
				}
				values.delete(i);
				values.add(i, so);
				valuesPadded++;
			}
		}
		
				
		return valuesPadded;	
			
	}
	
	/**
	 * performs one round of radix sort 
	 * increases the round number by 1 
	 */
	
	// perform one round of radix sort
	//  - round number should be incremented by 1
	//  - instance variables buckets and values should be updated 
	//    based on LSD radix sort algorithm
	public void oneRound(){
		int i;
		for(i = 0; i < numBuckets; i++) {
			SmartArray<Sortable> v = new SmartArray<>();
			buckets.add(i, v); 
		}
		for(i = 0; i < values.size(); i++) {
			int pos = values.get(i).posToNum(round); 
			buckets.get(pos).add(buckets.get(pos).size(), values.get(i));
			
		
		}
		values = new SmartArray<>();
		int j = 0; 
		for(i = 0; i < numBuckets; i++) {
			if( buckets.get(i).size() == 0) {
				continue;
			}
			else {
				for(int k = 0; k < buckets.get(i).size(); k++) {
					values.add(j,buckets.get(i).get(k));
					j++;
				}
			}
		}
		round++; 
		
		
		

				
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	public static void main(String[] args){
	
		// create a RadixSort object and read in from a String
		RadixSort rs = new RadixSort();
		try{
			Scanner s = new Scanner("a BOB TOM AMY TIM");
			rs.initValuesFromScanner(s);
		}
		catch (IOException e){
			e.printStackTrace();
			System.exit(0);
		}
		
		// check features after initialization
		SmartArray<Sortable> vs = rs.getValues();
		if (vs.size() == 4 && vs.get(0).digits().equals("BOB")
			&& vs.get(2).digits().equals("AMY")){
			System.out.println("Yay 1");
		}
		
		 
		// get ready to sort
		if (rs.sanitizeValues() == 0 && rs.setSameLength() == 0
			&& rs.getMaxLength() == 3 && rs.getRound()==0){
			System.out.println("Yay 2");			
		}

		// one round of radix sort		
		rs.oneRound();
		vs = rs.getValues(); //should be "BOB","TOM","TIM","AMY"
		if (!rs.isSorted() && rs.getRound()==1 
			&& vs.get(0).digits().equals("BOB") 
			&& vs.get(2).digits().equals("TIM")){
			System.out.println("Yay 3");			
		}
		
		// bucket for "M": should contains "TOM" and "TIM"
		SmartArray<Sortable> bucket = rs.getBucket(13);
		if (bucket.size() == 2 
			&& bucket.get(0).digits().equals("TOM")
			&& bucket.get(1).digits().equals("TIM")){
			System.out.println("Yay 4");			
		}
		
		// Note: use provided RadixSortASCII class 
		//       for debug printing can be helpful
		
					
	}

}

