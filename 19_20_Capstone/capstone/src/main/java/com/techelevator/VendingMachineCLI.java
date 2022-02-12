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
	} //what is this doing


	public void run() {
		VendingMachine myVendingMachine = scanningFileToGetOptions();
		Audit vMAudit = new Audit();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(myVendingMachine);
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) { //if our customer selects the purchase menu
				boolean endTransaction = false;					//how we will end our purchase menu
				while(!endTransaction){
					System.out.println(scanningFileToGetOptions());
					choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
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
	public static void writeAuditFile(String auditString){
		File auditFile = new File("exampleFiles/AuditFile.txt");
		try(PrintWriter pw = new PrintWriter(auditFile)) {
			pw.print(auditString);
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	public static VendingMachine scanningFileToGetOptions(){ //return a map
		File vendingMachine = new File("exampleFiles/VendingMachine.txt");
		VendingMachine myVM = new VendingMachine();
		try(Scanner scanMyFile = new Scanner(vendingMachine)){
			while(scanMyFile.hasNextLine()){
				String line = scanMyFile.nextLine();
				String [] productsFromFile = line.split("\\|");
				myVM.addToMap(productsFromFile[0], new Product(productsFromFile[1], BigDecimal.valueOf(Double.parseDouble(productsFromFile[2])), productsFromFile[3]));
				//create instance of Vending Machine
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return myVM;
	}

	public static void main(String[] args) {

		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
