import java.util.*;
import java.io.*;

public class GalacticCurrencyConverter {
	public GalacticCurrencyConverter() {
	}
	
	public void processFile(String filename) {
		File file = new File(filename);
		FileReader fr;
		
		// open the command file
		try {
			fr = new FileReader(file);
		} catch(FileNotFoundException f) {
			System.out.println("file: " + filename + " not found.");
			return;
		}
		
		BufferedReader br = new BufferedReader(fr);
		
		GalacticCurrencyTranslator gct = new GalacticCurrencyTranslator();
		String cmd = "";
		
		// procesx each line of the file as a command to the GalacticCorrencyTranslator
		while (cmd != null) {
			try {
				cmd = br.readLine();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			}
			
			if (cmd != null) {
				String r = gct.processCommand(cmd);
			
				if (r != "") {
					System.out.println(r);
				}
			}
		}
		
		try {
			br.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		}
	}
	
	public static void main(String[] args) {
		GalacticCurrencyConverter gcc = new GalacticCurrencyConverter();
		
		gcc.processFile(args[0]);
	}
}
