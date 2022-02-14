package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;

	public Menu(InputStream input, OutputStream output) { //constructor for the Menu class
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) { //Object data type can be any data type
		Object choice = null;
		while (choice == null) {
			//System.out.println("Selection cannot be completed"); //We added this. Is it good?***********
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

//	public Object getChoiceFromOptionsNoDisplay(Object[] options) { //Object data type can be any data type
//		Object choice = null;
//		while (choice == null) {
//			//System.out.println("Selection cannot be completed"); //We added this. Is it good?***********
//			choice = getChoiceFromUserInput(options);
//		}
//		return choice;
//	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
}
