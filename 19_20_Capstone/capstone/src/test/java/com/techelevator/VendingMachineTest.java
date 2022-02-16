package com.techelevator;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {

    @Test //happy path
    public void vending_machine_returnAudits_happy_path(){ //method returnAudits
        //arrange
        VendingMachine testVM = new VendingMachine();
        List<Audit> auditList = new ArrayList<>();
        testVM.addToAuditList(new Audit("TEST ACTION", "TEST DEPOSIT", "TEST BALANCE"));
        Audit testAudit = new Audit("TEST ACTION", "TEST DEPOSIT", "TEST BALANCE");
        //act
        String actual = testVM.returnAudits();
        String expected = String.valueOf(testAudit) + "\n";
        //assert
        Assert.assertEquals("The string should be equal", expected, actual);
    }
    @Test //null
    public void vending_machine_user_returnAudits_null(){ //method returnAudits
        //arrange
        VendingMachine testVM = new VendingMachine();
        List<Audit> auditList = new ArrayList<>();
        Audit nullAudit = null;
        testVM.addToAuditList(nullAudit);
        //act
        String actual = testVM.returnAudits();
        String expected = null + "\n";
        //assert
        Assert.assertEquals("this is null", expected, actual);
    }
    @Test //negative deposit
    public void vending_machine_getDepositMoney_happy_path(){ //method getDepositMoney
        //arrange
        VendingMachine testVM = new VendingMachine();
        int intDeposit = 2;
        //act
        String expected = "2.00";
        String actual = testVM.depositMoneyForAudit("2");
        //assert
        Assert.assertEquals("should display formatted big decimal of intDeposit", expected,actual);
    }
    @Test //happy path
    public void vending_machine_getDepositMoney_negative_numbers(){ //method getDepositMoney
        //arrange
        VendingMachine testVM = new VendingMachine();
        int intDeposit = -2;
        //act
        String expected = "please input a valid dollar amount";
        String actual = testVM.depositMoneyForAudit("-2");
        //assert
        Assert.assertEquals("insert message here", expected,actual);
    }
//    @Test //happy path
//    public void vending_machine_buttonSelection_happy_path(){ //method buttonSelection
//        //arrange
//        VendingMachine testVM = new VendingMachine();
//        Map<String, Product> testMap = new HashMap();
//        testMap.put("A1", new Product("Potato Crisps", BigDecimal.valueOf(3.05), "Chip"));
//        testMap.put("D4", new Product("Triplemint", BigDecimal.valueOf(0.75), "Gum"));
//        //act
//        testVM.buttonSelection("a1");
//        testVM.buttonSelection("d4");
//        //assert
//        Assert.assertEquals(testVM.buttonSelection("a1"),"A1");
//    }
    @Test //happy path
    public void vending_machine_selectProduct_happy_path(){ //method selectProduct
        //arrange
        VendingMachine testVM = new VendingMachine();

        //act

        //assert

    }
    @Test //state what this is testing here
    public void vending_machine_selectProduct_NAMETOBEDETERMINED(){ //method selectProduct
        //arrange
        VendingMachine testVM = new VendingMachine();

        //act

        //assert

    }
    @Test //happy path
    public void vending_machine_completeAPurchase_happy_path(){ //method completeAPurchase
        //arrange
        VendingMachine testVM = new VendingMachine();

        //act

        //assert

    }
    @Test //state what this is testing here
    public void vending_machine_completeAPurchase_NAMETOBEDETERMINED(){ //method completeAPurchase
        //arrange
        VendingMachine testVM = new VendingMachine();

        //act

        //assert

    }
    @Test //happy path
    public void vending_machine_finishTransaction_happy_path(){ //method finishTransaction
        //arrange
        VendingMachine testVM = new VendingMachine();

        //act

        //assert

    }
    @Test //state what this is testing here
    public void vending_machine_finishTransaction_NAMETOBEDETERMINED(){ //method finishTransaction
        //arrange
        VendingMachine testVM = new VendingMachine();

        //act

        //assert

    }

    //these test templates should cover al of our methods in the Vending Machine Class! :)
}
