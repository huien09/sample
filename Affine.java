/*
*CSCI361 A1 Part 1
*done: 2019
*revised: 15/9/2022
*/

import java.io.*;
import java.util.*;
import java.math.*;

public class Affine {
	private static String upperCaseAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String lowerCaseAlpha = "abcdefghijklmnopqrstuvwxyz";
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String encDec, inputFileName, outputFileName;
		int a, b;
		boolean check;
		do{
			String aKey = args[1]; //a
			String bKey = args[2]; //b
			encDec = args[3]; //-e, -d
			inputFileName = args[4]; //input
			outputFileName = args[5]; //output
			
			a = Integer.parseInt(aKey);
			b = Integer.parseInt(bKey);
			check = check(a, b);

		/*	if (check = true)
				System.out.println("a and b are valid"); */
		}while(check = false);	
		
		if (encDec.equals("-e"))
			encrypt(a, b, inputFileName, outputFileName);
		else if (encDec.equals("-d"))
			decrypt(a, b, inputFileName, outputFileName);
		else
			System.out.println("Wrong input");
		
	}
	
	public static ArrayList<String> readFile(String fileName) throws FileNotFoundException{
		Scanner fromFile = new Scanner(new File(fileName));
		ArrayList<String> data = new ArrayList<String>();
		
		while (fromFile.hasNextLine()){
			data.add(fromFile.nextLine());
			
		}
		//close file
		fromFile.close();	
		return data;
	}
	
	//write to file
	public static void fileOutput(ArrayList<String> text, String fileName) throws IOException{
			
		PrintWriter toFile = new PrintWriter (fileName);
		for (String s : text)
			toFile.println(s);
			
		System.out.println("Output file -> " + fileName);
		toFile.close();
	}	
	
	public static void printText(ArrayList<String> input){
		int count = 0;
		for (String s : input){	
			System.out.println("Line " + count + ":" + s);
			count++;
		}
	}

	public static boolean check(int a, int b){
		boolean flag = true;
		
		if (a < 1 || b < 1){
			System.out.println("a or b is lesser than 1!");
			flag = false;
		}
		else if (a > 26 || b > 26){
			System.out.println("a or b is greater than 26!");
			flag = false;
		}
	
		return flag;

	}
	
	public static void encrypt(int a, int b, String inputFileName, String outputFileName) throws IOException{
		ArrayList<String> pText = readFile(inputFileName);

		ArrayList<String> cTextList = new ArrayList<String>();
		
		for (int i = 0; i < pText.size(); i++)
		{
			char[] chars = (pText.get(i)).toCharArray();
			String cText = "";
			for (int j = 0; j < chars.length; j++)
			{
				if(Character.isUpperCase(chars[j]))
				{
					int numeric = upperCaseAlpha.indexOf(chars[j]);
					int c = ((a * numeric) + b ) % 26;
					cText = cText + upperCaseAlpha.charAt(c);
				}
				else if(Character.isLowerCase(chars[j]))
				{
					int numeric = lowerCaseAlpha.indexOf(chars[j]);
					int c = ((a * numeric) + b ) % 26;
					cText = cText + lowerCaseAlpha.charAt(c);
				}
				else
					cText += chars[j];	
			}
			cTextList.add(cText);
		}
		fileOutput(cTextList, outputFileName);
	}
	
	public static int inverseMod(int a){
		BigInteger a2 = BigInteger.valueOf(a);
		BigInteger p2 = BigInteger.valueOf(26);
		BigInteger result = a2.modInverse(p2);
		
		return result.intValue();
	}
	
	public static void decrypt(int a, int b, String inputFileName, String outputFileName) throws IOException{
		ArrayList<String> cipherText = readFile(inputFileName);
		
		ArrayList<String> pTextList = new ArrayList<String>();
		int invA = inverseMod(a);
		
		for (int i = 0; i < cipherText.size(); i++)
		{
			char[] chars = (cipherText.get(i)).toCharArray();
			String pText = "";
			for (int j = 0; j < chars.length; j++)
			{
				if(Character.isUpperCase(chars[j]))
				{
					int numeric = upperCaseAlpha.indexOf(chars[j]);
					
					int value = numeric - b;
					while (value < 0)
						value = value + 26;
					int p = ((invA * value) % 26 );
					//System.out.println("p:" + p);
					pText = pText + upperCaseAlpha.charAt(p);
				}
				else if(Character.isLowerCase(chars[j]))
				{
					int numeric = lowerCaseAlpha.indexOf(chars[j]);
					
					int value = numeric - b;
					while (value < 0)
						value = value + 26;
					int p = ((invA * value) % 26);
					//System.out.println("p:" + p);
					pText = pText + lowerCaseAlpha.charAt(p);
				}
				else
					pText += chars[j];
			}
			pTextList.add(pText);	
		}
		fileOutput(pTextList, outputFileName); 
	}
	
}
