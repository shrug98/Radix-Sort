// TO DO: add your implementation and JavaDoc

public class SortableNumber implements Sortable{

	// ADD PRIVATE MEMBERS HERE IF NEEDED!
	private int base;
	private String digits;
	private String padDigits; 
	
	
	/**
	 * Constructs a new SortableNumber with the specified digits and base
	 * @param digits Digits is in string format and used as the collection of characters
	 * of this object. It is assumed that the incoming string is 
	 * not empty and has no leading/trailing spaces. Base is an int which could be between
	 * 2 and 16. 
	 */
	public SortableNumber(int base, String digits){
		// constructor
		// set the base and digits of the number
		this.base = base;
		this.digits = digits; 
		this.padDigits = digits; 

		// Note: you can assume that 
		//		 - the incoming base is an integer from 2 to 16 inclusively
		// 		 - the incoming digits has no leading/trailing spaces
		//       - the incoming digits is not an empty string
		
	}
	
	/**
	 * Return the non-padded digits of the SortableNumber object.
	 * @return the non-padded digits of the SortableNumber object
	 */
	public String digits(){
		// return the non-padded digits
		
		
		return digits;
	}

	/**
	 * Return the padded digits of the SortableNumber object.
	 * @return the padded digits of the SortableNumber object
	 */
	public String paddedDigits(){
		// return the padded digits
		
		return padDigits;
	}
	
	/**
	 * Return the max possible numeric value of a single digit in decimal
	 * @return the max possible numeric value of a single digit in decimal
	 */	
	public int maxNum(){
		// return the max possible numeric value of a single digit as a decimal
		
		return base - 1;
	}
	
	/**
	 * Convert and return the value at specified position as a decimal
	 * If padded, use the padded digits for conversion
	 * @param pos integer position; rightmost position is 0
	 * @return corresponding numeric value (in decimal) from position pos
	 */
	public int posToNum(int pos){
		// return the value at location pos of the padded digits as a decimal
		// rightmost position (least significant digit position) is 0
		// return -1 if position is invalid or any exception occurs 
		
		int c; 
		char num = '7';
		int length = padDigits.length();
		if (pos > length) {
			return -1;
		}  
		int pos1 = length - pos - 1;
		char a = padDigits.charAt(pos1);
		if (this.base == 16) {
			if ((int)a >= 65) {
				c = (int)a - (int)num; 
			}
			else {
				c = Character.getNumericValue(a);
			}
		}
		else {
			c = Character.getNumericValue(a);
			
		}		
		
		
		
		return c;				
	}
	
	
	/**
	 * Performs padding to ensure the length of padded string is at least minLength.
	 * @param minLength integer as the minimum number of digits after padding
	 */
	public void padDigits(int minLength){
		// pad to ensure the length of padded string is 
		// at least minLength
		int length = minLength - padDigits.length();
		int i;
		for(i = 0; i < length; i++) {
			padDigits = "0" + padDigits;
		}
		
		
	}

	// --------------------------------------------------------
	// example testing code... edit this as much as you want!
	// --------------------------------------------------------
	
	public static void main(String[] args){
	
		// create a decimal
		SortableNumber num = new SortableNumber(10,"123");
		
		// check features
		if ((num.maxNum() == 9) && (num.digits().equals("123"))
			&& (num.posToNum(0)==3)){
			System.out.println("Yay 1");
		}
		
		// pad
		num.padDigits(5);
		if ( num.paddedDigits().length()==5 && num.posToNum(2)==1
			&& num.posToNum(4)==0){
			System.out.println("Yay 2");
		}
		
		// create a hex
		SortableNumber hex = new SortableNumber(16,"AB");
		
		// check features
		if ( hex.maxNum()==15 && hex.posToNum(0)==11
			&& hex.posToNum(1)==10  && hex.posToNum(10)==-1){
			System.out.println("Yay 3");
		}
		
	}
}