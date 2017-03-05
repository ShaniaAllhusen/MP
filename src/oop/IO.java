package oop;

import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;

public class IO {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static String readString()throws IOException {
		String text;
		text = br.readLine();

		return text;
	}

	public static boolean readBoolean() throws IOException {

		while (true) {
			String tmpEingabe = br.readLine();
			if (tmpEingabe != null) {
				if ((tmpEingabe.equalsIgnoreCase("ja"))
						|| (tmpEingabe.equalsIgnoreCase("j")) 
						|| (tmpEingabe.equalsIgnoreCase("yes"))
						|| (tmpEingabe.equalsIgnoreCase("y")) ) {
					return true;
				}
				else if ((tmpEingabe.equalsIgnoreCase("nein"))
						|| (tmpEingabe.equalsIgnoreCase("n")) 
						|| (tmpEingabe.equalsIgnoreCase("no"))
						|| (tmpEingabe.equalsIgnoreCase("ne")) ){
					return false;
				}
			}
			System.out.print("Ungültige Eingabe! Bitte erneut versuchen: ");
		}
	}

	public static double readDouble() throws IOException {

		while (true) {
			try {
				double number = Double.parseDouble(IO.readString().replace(',', '.'));
				return number;
			}
			catch (NumberFormatException e) {
				System.out.print("Keine Kommazahl! Bitte erneut versuchen: ");
			}
		}
	}

	public static int readInteger() throws IOException {

		while (true) {
			try {
				int number = Integer.parseInt(IO.readString());
				return number;
			}
			catch (NumberFormatException e) {
				System.out.print("Keine Ganzzahl! Bitte erneut versuchen: ");
			}
		}
	}

	public static int readInteger(int min, int max) throws IOException {
		while (true) {
			int value = IO.readInteger();
			if ((value >= min)&&(value <= max)) {
				return value;
			}
		}
	}

	public static double[] readDoubleArray() throws IOException {
		System.out.println("Anzahl der Zahlen: ");
		int anzahl = IO.readInteger();
		return readDoubleArray(anzahl);
	}

	public static double[] readDoubleArray(int anzahl) throws IOException {
		double[] data = new double[anzahl];
		for (int index=0; index <= (anzahl-1); index++ ) {
			data[index] = IO.readDouble();
		}
		return data;
	}

	public static void writeDoubleArray(double[] data) {
		for (int index=0; index < data.length; index++ ) {
			System.out.println("Zahl " +(index+1) +": " +data[index]);
		}
	}

	public static int[] readIntegerArray() throws IOException {
		System.out.println("Anzahl der Zahlen: ");
		int anzahl = IO.readInteger();
		return readIntegerArray(anzahl);
	}

	public static int[] readIntegerArray(int anzahl) throws IOException {
		int[] data = new int[anzahl];
		for (int index=0; index <= (anzahl-1); index++ ) {
			data[index] = IO.readInteger();
		}
		return data;
	}

	public static void writeIntegerArray(int[] data) {
		for (int index=0; index < data.length; index++ ) {
			System.out.println("Zahl " +(index+1) +": " +data[index]);
		}
	}

	public static void writeStringArray(String[] data) {
		for (int index=0; index < data.length; index++ ) {
			System.out.println("Zahl " +(index+1) +": " +data[index]);
		}
	}

	public static String[] readStringArray(int anzahl) throws IOException {
		String[] data = new String[anzahl];
		for (int index=0; index <= (anzahl-1); index++ ) {
			data[index] = IO.readString();
		}
		return data;
	}
}