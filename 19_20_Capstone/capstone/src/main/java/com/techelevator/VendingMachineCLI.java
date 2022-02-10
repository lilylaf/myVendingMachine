package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	//purchase menu options
	private static final String PURCHASE_MENU_OPTION_DEPOSIT_MONEY = "Deposit Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_PURCHASE = "Finish Purchase";
	private static final String[] PURCHASE_MENU_OPTIONS =
			{PURCHASE_MENU_OPTION_DEPOSIT_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_PURCHASE};

	public Scanner inputScanner = new Scanner(System.in);
	private Menu menu; //our instance of our Class Menu

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	} //what is this doing

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(scanningFileToGetOptions());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				int totalDeposit = 0;
				boolean finishTransaction = false;
				while(!finishTransaction){
					System.out.println(scanningFileToGetOptions());
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if(choice.equals(PURCHASE_MENU_OPTION_DEPOSIT_MONEY)){
						//user input deposit money
						System.out.println("Please insert dollar bills:");
						String strDeposit = inputScanner.nextLine();
						try {
							int intDeposit = Integer.valueOf(strDeposit);
							totalDeposit +=intDeposit;
							System.out.println("Your total balance is:  $" + totalDeposit + ".00\n");
						}catch (NumberFormatException e) {
							System.out.println(System.lineSeparator() + "*** " + strDeposit + " is not a valid option ***" + System.lineSeparator());
						}
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						//select product (

					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_PURCHASE)){
						//give change
						finishTransaction = true;
					}
				}
			}
		}
	}


	public static VendingMachine scanningFileToGetOptions(){ //return a map
		File vendingMachine = new File("exampleFiles/VendingMachine.txt");
		VendingMachine myVM = new VendingMachine();
		try(Scanner scanMyFile = new Scanner(vendingMachine)){
			while(scanMyFile.hasNextLine()){
				String line = scanMyFile.nextLine();
				String [] productsFromFile = line.split("\\|");
				myVM.addToMap(productsFromFile[0], new Product(productsFromFile[1], Double.parseDouble(productsFromFile[2]), productsFromFile[3]));
				//create instance of Vending Machine
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return myVM;
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachine myVendingMachineMap = scanningFileToGetOptions();
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
