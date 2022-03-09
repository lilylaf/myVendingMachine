package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };

	//creating a menu for our select purchase screen, modeled after the given first menu setup
	private static final String PURCHASE_MENU_OPTION_DEPOSIT_MONEY = "Deposit Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_PURCHASE = "Finish Purchase";
	private static final String[] PURCHASE_MENU_OPTIONS =
			{PURCHASE_MENU_OPTION_DEPOSIT_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_PURCHASE};

	public Scanner inputScanner = new Scanner(System.in);
	private Menu menu; //our instance of our Class Menu


	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	//running through our menu options
	public void run() {
		VendingMachine myVendingMachine = scanningFileToGetOptions();
		Audit vMAudit = new Audit();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS); //displays our array of options in our main menu
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) { //if they select display items
				System.out.println(myVendingMachine); // display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { //if our customer selects the purchase menu
				boolean endTransaction = false;	 //how we will end our purchase menu

				while(!endTransaction){
					System.out.println(scanningFileToGetOptions());
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS); //getting options from our purchase menu

					if(choice.equals(PURCHASE_MENU_OPTION_DEPOSIT_MONEY)){
						System.out.println("Please insert dollar bills:");
						String strDeposit = inputScanner.nextLine();
						myVendingMachine.depositMoney(strDeposit);
					} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
						System.out.println("Please select a product");
						String strSelection = inputScanner.nextLine();
						myVendingMachine.selectProduct(strSelection);
					} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_PURCHASE)){
						myVendingMachine.finishTransaction();
						writeAuditFile(myVendingMachine.returnAudits());
						endTransaction = true;
					}
				}
			}
		}
	}

	//todo add these methods to the vending machine class, make the constructor call them, and add a parameter in theser methods for the file path as a stribg
	//create and write audit file
	public static void writeAuditFile(String auditString){ //unit test WIP
		File auditFile = new File("exampleFiles/AuditFile.txt");
		try(PrintWriter pw = new PrintWriter(auditFile)) {
			pw.print(auditString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//scanning our products file and importing info
	public static VendingMachine scanningFileToGetOptions(){ //unit test WIP
		File vendingMachineFile = new File("exampleFiles/VendingMachine.txt"); //hard coded since we only want these menu items
		VendingMachine myVM = new VendingMachine();
		try(Scanner scanMyFile = new Scanner(vendingMachineFile)){
			while(scanMyFile.hasNextLine()){
				String line = scanMyFile.nextLine();
				String [] productsFromFile = line.split("\\|");
				myVM.addToMap(productsFromFile[0], new Product(productsFromFile[1], BigDecimal.valueOf(Double.parseDouble(productsFromFile[2])), productsFromFile[3]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return myVM;
	}

	//main
	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
