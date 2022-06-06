/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contractmanager;

import java.util.Scanner;

/**
 *
 * @author Preethi
 */
public class ContractManager {

    
    public static Scanner scanner ;
    
    
    public static String INVALID_TEXT = "Invalid Selection\nPlease enter valid option";
    
    public static String WELCOME_TEXT = "\"Welcome to Tele-Communication proto type \\nSelect your option\"";
    
    public static String SELECT_OPTIONS_TEXT = "1. Enter new Contract\n" +
                                "2. Display Summary of Contracts\n" +
                                "3. Display Summary of Contracts for Selected Month\n" +
                                "4. Find and display Contract\n" +
                                "5. Modify existing Contract\n" +
                                "0. Exit";
    
    
    public static String contractsFileLocation="C:\\Users\\Preethi\\Desktop\\contracts.txt";
    public static String archiveFileLocation="C:\\Users\\Preethi\\Desktop\\archive.txt";
    
    
    public static void main(String[] args) 
    {
        
        scanner =new Scanner(System.in);
        boolean doNotExit=true;
        
        
        System.out.println(WELCOME_TEXT);
        
        ContractManagerServices service =  new ContractManagerServices(contractsFileLocation,archiveFileLocation);
        
        
        while (doNotExit) 
        {    
            System.out.println(SELECT_OPTIONS_TEXT);
            try
            {
                   int selectedOption = scanner.nextInt();
                   switch(selectedOption)
                   {
                       case 0:
                           doNotExit=false;
                           System.out.println("\n**************Quitting the application*****************");
                           break;
                       case 1:
                           service.createNewContract();
                           break;
                       case 2:
                           service.displaySummaryOfContracts();
                           break;
                       case 3:
                           service.displaySummaryOfContractsForSelectedMonth();
                           break;
                       case 4:
                           service.findAndDisplayContract();
                           break;
                       case 5:
                           service.modifyTheExistingContract();
                           break;
                       default:
                           System.out.println(INVALID_TEXT);
                   }
            }
            catch(Exception e)
            {
                System.out.println(INVALID_TEXT);
                scanner.next();
            }
        }
    }
    
}
