import java.util.*;

public class RomanNumeral {
	private static HashMap<Character, Integer> letterValues = new HashMap<Character, Integer>();
	private int value = 0;
	
	public RomanNumeral(String romanNumeral) throws Exception {
		char lastChar = ' ';

		// use putIfAbsent, since letterValues is static, and will get initialized every time the
		// constructor is called
		letterValues.putIfAbsent('I', 1);
		letterValues.putIfAbsent('V', 5);
		letterValues.putIfAbsent('X', 10);
		letterValues.putIfAbsent('L', 50);
		letterValues.putIfAbsent('C', 100);
		letterValues.putIfAbsent('D', 500);
		letterValues.putIfAbsent('M', 1000);

		if (romanNumeral.contains("IIII") || romanNumeral.contains("VV") || romanNumeral.contains("XXXX") ||
		    romanNumeral.contains("LL") || romanNumeral.contains("CCCC") || romanNumeral.contains("DD") ||
		    romanNumeral.contains("MMMM")) {
			throw new Exception("Too many of the same character in a row");
		}
		
		for(int i = 0; i < romanNumeral.length(); i++) {
			char currChar = romanNumeral.charAt(i);
			
			// check for valid character arrangements; throwe exception if not
			if (!("IVXLCDM".contains(((Character)currChar).toString()))) {
				throw new Exception("Only I, V, X, L, C, D and M allowed");
			} else if (lastChar == ' ') {
				lastChar = currChar;
			} else if (lastChar == currChar && "VLD".contains(((Character)lastChar).toString())) {
				throw new Exception("No more than one sequential V, L, or D allowed");
			} else if (lastChar == 'I' && "LCDM".contains(((Character)currChar).toString())) {
				throw new Exception("Only I, V or X can follow I");
			} else if (lastChar == 'X' && "DM".contains(((Character)currChar).toString())) {
				throw new Exception("D or M cannot follow X");
			} else {
				// process one character at a time
				int lastValue = letterValues.get(lastChar);
				int currValue = letterValues.get(currChar);
				
				if (lastValue < currValue) {
					value = currValue - lastValue;
					lastChar = ' ';
				} else {
					value = value + lastValue;
					
					if (i == romanNumeral.length() - 1) {
						value = value + currValue;
					}
						
					lastChar = currChar;
				}
			}
		}
	}
	
	int getValue() {
		return value;
	}
	
	/* public static void main(String[] args) {
		RomanNumeral rn = new RomanNumeral("IV");
		System.out.println(rn.getValue());
		rn = new RomanNumeral("II");
		System.out.println(rn.getValue());
		rn = new RomanNumeral("XX");
		System.out.println(rn.getValue());
		rn = new RomanNumeral("XLII");
		System.out.println(rn.getValue());
	} */
}
