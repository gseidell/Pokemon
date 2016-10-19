import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* Date started: 10/19/16
 * 
 * Currently working on:
 * Nothing! newSet() is done!
 * 
 * Warnings:
 * Set the static path variable to your project folder.
 * 
 * Ideas for the future:
 * Trimming the standard files so they don't have retarded data. (worth while?)
 */

public abstract class SetManager {
	
	static String path = "C:/Users/Micha/Documents/Jafa/Pokemon/";
	
	public static String truncate(String path) {
		String line = "";
		String p = path.substring(0, path.length()-4) + "2" + ".txt";
		int counter = 0;
		try {
			FileReader FR = new FileReader(path);
			BufferedReader BR = new BufferedReader(FR);
			FileWriter FW = new FileWriter(p);
			BufferedWriter BW = new BufferedWriter(FW);
			
			while((line = BR.readLine()) != null) {
				if(!line.subSequence(0, 1).equals("\t")) {
					System.out.println("Could not truncate!\n" + p + " is corrupt!");
					System.out.println("broke at " + counter + "\nv this line is lying!!! v");
					break;
				} else {
					BW.write(line.substring(1) + System.lineSeparator());
				}
				counter++;
			}
			
			System.out.println("Truncate Successful!");
			BR.close();
			BW.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return p;
	}
	
	public static void newSet(String p, String name) {

		String path = SetManager.path + "sets/";
		path = path + name + ".txt";
		String line = "";
		ArrayList<String> mistakes = new ArrayList<String>();
		
		try {
			FileReader FR = new FileReader(p);
			BufferedReader BR = new BufferedReader(FR);
			FileWriter FW = new FileWriter(path);
			BufferedWriter BW = new BufferedWriter(FW);
			InputStreamReader ISR = new InputStreamReader(System.in);
			BufferedReader input = new BufferedReader(ISR);
			
			String pokemon;
			String left = "in";
			String right = "out";
			String in; //input
			
			while(line != null) {
				line = BR.readLine();
				if(line.substring(0,1).equals("\t") || line.substring(0,1).equals("}")) {		//finds pokemon names
					continue;
				} else {
					pokemon = line.substring(0,line.length()-3);
				}
				System.out.print(left);
				for(int i=0; i < 2 - left.length()/8; i++) {		//im slick
					System.out.print("\t");
				}
				System.out.print(right);
				for(int i=0; i < 2 - right.length()/8; i++) {
					System.out.print("\t");
				}
				System.out.print(pokemon);
				
				in = input.readLine();
				//System.out.println();
				
				if(in.equals("w"))
					mistakes.add(pokemon);

				switch(in) {
				
				case "a":
					left = pokemon;
					right = " ";
					while(!line.substring(0,1).equals("}")) {
						 BW.write(line + System.lineSeparator());
						 line = BR.readLine();
					}
					BW.write(line + System.lineSeparator());
					break;
					
				case "d":
					left = " ";
					right = pokemon;
					line = BR.readLine();
					break;
					
				default:
					System.out.println("a = in\nd = out\nw = mistake");
					break;
				}
			}
			BW.close();
			BR.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]) {
		String original = SetManager.path + "sets/Pokemon.txt";
		String orig = SetManager.truncate(original); //drops the first line of tabs
		SetManager.newSet(orig,"Randoms");
		
	}
}
