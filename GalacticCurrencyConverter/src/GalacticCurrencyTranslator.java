import java.util.*;

public class GalacticCurrencyTranslator {
	private HashMap<String, String> alienCode = new HashMap<String, String>();
	private HashMap<String, Double> metalCost = new HashMap<String, Double>();
	
	public GalacticCurrencyTranslator() {		
	}
	
	public String processCommand(String cmd) {
		StringTokenizer st = new StringTokenizer(cmd);
		String romanNumeral = "";
		RomanNumeral rn;
				
		if (cmd.startsWith("how many Credits is ")) {
			String alienPhrase = "";
			
			String s = st.nextToken(); // should be "how"
			s = st.nextToken(); // should be "many"
			s = st.nextToken(); // should be "credits"
			s = st.nextToken(); // should be "is"
			s = st.nextToken(); // first alien word
			
			// construct a Roman numeral using all words beginning with a lowercase letter
			while (s.charAt(0) > 'Z') {
				romanNumeral = romanNumeral + alienCode.get(s);
				alienPhrase = alienPhrase + s + " ";
				s = st.nextToken();
			}
			
			// convert Roman numeral to integer
			try {
				rn = new RomanNumeral(romanNumeral);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
			
			return alienPhrase + s + " is " + ((Integer)(int)(rn.getValue() * metalCost.get(s))).toString() + " Credits";
		} else if (cmd.startsWith("how much is ")) {
			String alienPhrase = "";
			
			String s = st.nextToken(); // should be "how"
			s = st.nextToken(); // should be "much"
			s = st.nextToken(); // should be "is"
			s = st.nextToken(); // first alien word
			
			// construct a Roman numeral using all words beginning with a lowercase letter			
			while (s.charAt(0) != '?') {
				romanNumeral = romanNumeral + alienCode.get(s);
				alienPhrase = alienPhrase + s + " ";
				s = st.nextToken();
			}

			// convert Roman numeral to integer
			try {
				rn = new RomanNumeral(romanNumeral);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
			
			return alienPhrase + "is " + ((Integer)rn.getValue()).toString(); 
		} else if (cmd.endsWith(" Credits")) {
			String s = st.nextToken();
			
			// construct a Roman numeral using all words beginning with a lowercase letter			
			while (s.charAt(0) > 'Z') {
				romanNumeral = romanNumeral + alienCode.get(s);
				s = st.nextToken();
			}
			
			String metal = s;
			String whatever = st.nextToken(); // should be "is"
			double credits = Double.parseDouble(st.nextToken());

			// construct a Roman numeral using all words beginning with a lowercase letter			
			try {
				rn = new RomanNumeral(romanNumeral);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
			
			credits = credits / rn.getValue();
			metalCost.put(metal,  credits);
			
			return "";
		} else if (cmd.endsWith(" is I") || cmd.endsWith(" is V") || cmd.endsWith(" is X") || cmd.endsWith("is L") ||
				   cmd.endsWith(" is C") || cmd.endsWith(" is D") || cmd.endsWith(" is M")) {
			// map alien word to Roman numeral
			String alienWord = st.nextToken();
			romanNumeral = st.nextToken(); // should be "is"
			romanNumeral = st.nextToken();
			alienCode.put(alienWord, romanNumeral);
			
			return "";
		} else {
			return "I have no idea what you are talking about";
		}
	}
	
	/* public static void main(String args[]) {
		GalacticCurrencyTranslator gct = new GalacticCurrencyTranslator();
		
		gct.processCommand("glob is I");
		gct.processCommand("prok is V");
		gct.processCommand("pish is X");
		gct.processCommand("tegj is L");
		gct.processCommand("glob glob Silver is 34 Credits");
		gct.processCommand("glob prok Gold is 57800 Credits");
		gct.processCommand("pish pish Iron is 3910 Credits");
		
		System.out.println(gct.processCommand("how much is pish tegj glob glob ?"));
		System.out.println(gct.processCommand("how many Credits is glob prok Silver ?"));
		System.out.println(gct.processCommand("how many Credits is glob prok Gold ?"));
		System.out.println(gct.processCommand("how many Credits is glob prok Iron ?"));
		System.out.println(gct.processCommand("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"));
	} */
}
