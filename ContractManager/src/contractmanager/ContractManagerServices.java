/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contractmanager;

import static contractmanager.ContractManager.scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 *
 * @author Preethi
 */
public class ContractManagerServices 
{
    public String contractsFileLocation;
    public String archiveFileLocation;
    
    
    
    public void createNewContract() 
    {
        //creating the empty contract
        Contract contract = new Contract();
        
        Scanner scanner = new Scanner(System.in);
        
        // initlising the required parameters
        Integer businessType=100;
        Integer minutePackage = 100;
        Integer contractPeriod=100;
        Integer dataPack = 100;
        
        
        
        //initlising the value to loop the input process untill user provides correct input
        Boolean stayInCurrentQuestion=true;
        //asks for name input
        System.out.println("Enter your name");
        
        while(stayInCurrentQuestion)
        {
            String name = scanner.nextLine();

            if(contract.setCustomerName(name)){
                stayInCurrentQuestion=false;
            }else{
                System.out.println("Name should be less than 25 characters\nplease Try again");
            }
        }
      
        stayInCurrentQuestion=true;
        
        
        //Asks for type of Contract
        while(stayInCurrentQuestion){  
        try{
        System.out.println("Enter Contract Type\n1.Business\n2.NON-Business\n3.Charity");
        businessType = scanner.nextInt();
        
        if(contract.setContactType(businessType))
        {
            stayInCurrentQuestion=false;
        }else{
            throw new Exception("invalid selection");
        }
        }catch(Exception e){
             //Incase of wrong inputs exception will be caught and asks user to re enter the values
            System.out.println("Select Correct option");
            scanner.next();
          } 
        }
        stayInCurrentQuestion=true;
        //asks the user for minuates package
        
        while(stayInCurrentQuestion)
        {  
            try
            {
                System.out.println("Choose your minutePackage package \n1.Small 300 Minutes"+
                        "\n2.Medium 500 minutePackage\n3.Large 1200 Minutes");
                minutePackage = scanner.nextInt();

                if(contract.setMinuatespackage(minutePackage))
                {
                    stayInCurrentQuestion=false;
                }
                else{
                    throw new Exception("Invalid Selection");
                }

            }catch(Exception e)
            {
                System.out.println("Select Correct option");
                scanner.next();
            } 
        }
        stayInCurrentQuestion=true;
           //asks the user for dataPack package
        
        while(stayInCurrentQuestion)
        {  
            try
            {
                if(contract.getMinuatespackage()==3)
                {
                    System.out.println("Choose your Data package \n1.Low 1GB "+
                    "\n2.Medium 4GB\n3.High 8GB \n4.Unlimited");
                }else{
                     System.out.println("Choose your Data package \n1.Low 1GB "+
                    "\n2.Medium 4GB\n3.High 8GB ");
                }
                dataPack = scanner.nextInt();
                if(contract.setDataPackage(dataPack))
                {
                    stayInCurrentQuestion=false;
                }else{
                    throw new Exception("invalid selection");
                }

            }catch(Exception e)
            {
                System.out.println("Select Correct option");
                scanner.next();
             } 
        }
        
        stayInCurrentQuestion=true;
           //asks the user for Contract contractPeriod
        while(stayInCurrentQuestion)
        {  
        try{
        System.out.println("Choose your package contractPeriod \n1.1 Month "+
                "\n2.12 months\n3.18 Months \n4.24 Months");
          contractPeriod = scanner.nextInt();
        if(contract.setPeriodOfContract(contractPeriod))
        {
        
        stayInCurrentQuestion=false;
        
        }else{throw new Exception("invalid selection");}
        
        }catch(Exception e){
            System.out.println("Select Correct option");
            scanner.next();
          } 
        }
       
        contract.setPrice(Utilities.getCharges(minutePackage, dataPack));
        
        stayInCurrentQuestion=true;
        
        
        while(stayInCurrentQuestion)
        {  
        try{
        
                //Taking the input from user to set the internatinoal charges
                System.out.println("include international calls? (15% more for international charges)"
                        + "\n1.Yes\n2.No");
                int international = scanner.nextInt();

                if(contract.setInternationalCalls(international))
                {
                    contract.setPrice(Utilities.getAppliesInternationalCharges(contract.getPrice())); 
                    stayInCurrentQuestion=false;
                }
                else{
                   throw new Exception("invalid selection");
                }
        
        }catch(Exception e){
            System.out.println("Select Correct option");
            scanner.next();
        }
        }
        
        contract.setPrice(Utilities.getDiscountedPrice(businessType, contractPeriod,contract.getPrice()));
        
        contract.setContractDate(getCurrentDate());
       
        contract.setReferenceNumber(Utilities.generateReferencenumber(CompanyPlans.accountType[contract.getContractType()].charAt(0)));
        
        // Saving the Contract record into the file contacts.txt
        File file = new File(contractsFileLocation);
        FileWriter fr;
        try 
        {
            fr = new FileWriter(file, true);
            try (BufferedWriter br = new BufferedWriter(fr); 
                    PrintWriter pr = new PrintWriter(br)) {
                pr.println(contract.toString());
            }
            fr.close();
            
         } catch (IOException ex) {
             System.out.println(ex.toString());   
        }
        System.out.println();
        
        //Finally Displays the contract
        contract.printSummary();
    }
    
    
    
    
    
    //Function for display the summary of contracts
    public void displaySummaryOfContracts() throws Exception 
    {
        
        boolean stayInCurrentQuestion=true;
        boolean selectedSourceOfContracts=true;
        //taking the default mount stats
        int[] monthsdataPack={0,0,0,0,0,0,0,0,0,0,0,0};
        // asks the user to select the type of record to fetch
        System.out.println("Select the Source\n1.Contacts.txt\n2.Archive.txt");
        while(stayInCurrentQuestion){
            try{
                int refc = scanner.nextInt();
                switch (refc) {
                    case 1:
                        selectedSourceOfContracts=true;
                        stayInCurrentQuestion=false;
                        break;
                    case 2:
                        selectedSourceOfContracts=false;
                        stayInCurrentQuestion=false;
                        break;
                    default:
                        System.out.println("Please choose a valid option");
                        break;   
                }
            }catch(Exception e){
                System.out.println("Please choose a valid option");
            }  
        }
        
        scanner.nextLine();
        
        if(selectedSourceOfContracts)
        { //contracts.txt will be selected and started calculation the records
            int contractsQuantity=0;
            int maxValueOfBundle=0;
            int AvarageHighPricecount=0;
            int totalHighPricecount=0;
           try  
                {  //opening the file
                File file=new File(contractsFileLocation);    //creates a new file instance  
                try (FileReader fr = new FileReader(file) 
                ) {
                    BufferedReader br=new BufferedReader(fr);  
                    StringBuilder sb=new StringBuilder();    
                    //constructs a string buffer with no characters
                    String line;
                    while((line=br.readLine())!=null)
                    {  
                        // adding the number of records based on there month and total records
                        ++contractsQuantity;
                        String tempLine[]=line.split("\t");
                        if(Integer.parseInt(tempLine[2])>=3){
                            ++maxValueOfBundle;
                        }
                        if(Integer.parseInt(tempLine[1])==3){
                            ++AvarageHighPricecount;
                            totalHighPricecount=totalHighPricecount+Integer.parseInt(line.split("\t")[8]);
                        }
                        
                        ++monthsdataPack[getMonthNumberFromDate(tempLine[0])] ;
                        
                    }     
                } //creates a buffering character input stream
                }  
                catch(IOException e){e.printStackTrace();}  
                //printing the results
                System.out.println("Total number of contracts: "+contractsQuantity);
                System.out.println("Number of contracts with High or Unlimited Data Bundles: "+maxValueOfBundle);
                try{
                     System.out.println("Average charge for large packages: "+ Utilities.convertToPounds(totalHighPricecount/AvarageHighPricecount)+"£" );
                }
                catch (Exception e){
                         System.out.println("Average charge for large packages: 0");
                        }
                System.out.println("Jan\tFeb\tMar\tApr\tMay\tJun\tJul\tAug\tSep\toct\tNoc\tDec" );
                System.out.println(monthsdataPack[0]+"\t"+monthsdataPack[1]+"\t"+monthsdataPack[2]+"\t"+monthsdataPack[3]+"\t"+monthsdataPack[4]+"\t"+monthsdataPack[5]+"\t"+monthsdataPack[6]+"\t"+monthsdataPack[7]+"\t"+monthsdataPack[8]+"\t"+monthsdataPack[9]+"\t"+monthsdataPack[10]+"\t"+monthsdataPack[11]);
        }
        else
                {
             //Section for printing the values from archive.txt file
            int contractsQuantity=0;
            int maxValueOfBundle=0;
            int AvarageHighPricecount=0;
            int totalHighPricecount=0;
           try  
                {  
                File file=new File(archiveFileLocation);    //creates a new file instance  
                try (FileReader fr = new FileReader(file) //reads the file
                ) {
                    BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream
                    StringBuilder sb=new StringBuilder();    //constructs a string buffer with no characters
                    String line;
                    while((line=br.readLine())!=null)
                    {  // calculation the every month records
                        ++contractsQuantity;
                        String tempLine[]=line.split("\t");
                        if(Integer.parseInt(tempLine[2])>=3){
                            ++maxValueOfBundle;
                        }
                        if(Integer.parseInt(tempLine[1])==3){
                            ++AvarageHighPricecount;
                            totalHighPricecount=totalHighPricecount+(int)(Float.parseFloat(line.split("\t")[8])*100);
                        }
                        ++monthsdataPack[getMonthNumberFromDate(tempLine[0])] ;
                    }     
                } //creates a buffering character input stream
                }  
                catch(IOException e){}
                   
           

                //Printing the records 
                    
                System.out.println("Total number of contracts: "+contractsQuantity);
                System.out.println("Number of contracts with High or Unlimited Data Bundles: "+maxValueOfBundle);
                try{
                     System.out.println("Average charge for large packages: "+ Utilities.convertToPounds(totalHighPricecount/AvarageHighPricecount)+"£" );
                }
                catch (Exception e){
                         System.out.println("Average charge for large packages: 0");
                        }
                System.out.println("Jan\tFeb\tMar\tApr\tMay\tJun\tJul\tAug\tSep\toct\tNoc\tDec" );
                System.out.println(monthsdataPack[0]+"\t"+monthsdataPack[1]+"\t"+monthsdataPack[2]+"\t"+monthsdataPack[3]+"\t"+monthsdataPack[4]+"\t"+monthsdataPack[5]+"\t"+monthsdataPack[6]+"\t"+monthsdataPack[7]+"\t"+monthsdataPack[8]+"\t"+monthsdataPack[9]+"\t"+monthsdataPack[10]+"\t"+monthsdataPack[11]);
        
                    
                    
                }

    }
    
    
    
    
    //Function to display Summary of one selected month
    public void displaySummaryOfContractsForSelectedMonth() throws Exception {
        
        //initilising the default values 
        int contractsQuantity=0;
        int maxValueOfBundle=0;
        int AvarageHighPricecount=0;
        int totalHighPricecount=0;
        int inputMonth=0;
        boolean stayInCurrentQuestion=true;
        boolean selectedSourceOfContracts=true;
        
        
        //asking the user to select the file type
        
        
        System.out.println("Select the Source\n1.Contacts.txt\n2.Archive.txt");
        while(stayInCurrentQuestion){
            try{
                int refc = scanner.nextInt();
                switch (refc) {
                    case 1:
                        selectedSourceOfContracts=true;
                        stayInCurrentQuestion=false;
                        break;
                    case 2:
                        selectedSourceOfContracts=false;
                        stayInCurrentQuestion=false;
                        break;
                    default:
                        System.out.println("Please choose a valid option");
                        break;   
                }
            }catch(Exception e){
                System.out.println("Please choose a valid option");
                scanner.next();
            }  
        }
        stayInCurrentQuestion=true;
        scanner.nextLine();
        
        if(selectedSourceOfContracts)
        { 
            // user selects cnotacts.txt
            //Asking the user to select the month
            System.out.println("Select Month"
                    + "\n0.January\t6.July");
            System.out.println("1.February"
                    + "\t7.August");
            System.out.println("2.March\t"
                    + "8.September");
            System.out.println("3.April\t"
                    + "9.October");
            System.out.println("4.May\t"
                    + "10.November");
            System.out.println("5.June\t"
                    + "11.December");
            while(stayInCurrentQuestion){
                //taking the response frmo user
            try{
                inputMonth = scanner.nextInt();
                if(inputMonth>=0 && inputMonth<=11)
                {
                    stayInCurrentQuestion=false;
                }
                else{
                    throw new Exception("");
                }
            }catch(Exception e){
                System.out.println("Select Correct Input");
                scanner.next();
            }
            
            }
            try  
                {  
                    //Opening the file for the records of contracts
                File file=new File(contractsFileLocation);    //creates a new file instance  
                try (FileReader fr = new FileReader(file) //reads the file
                ) {
                    BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
                    StringBuilder sb=new StringBuilder();    //constructs a string buffer with no characters  
                    String line;
                    while ((line=br.readLine())!=null) {
                        String tempLine[]=line.split("\t");
                        if(inputMonth==getMonthNumberFromDate(tempLine[0])) //filtering for month number
                        {
                            ++contractsQuantity;
                            
                            if(Integer.parseInt(tempLine[2])>=3){ //filtering for high level packages
                                ++maxValueOfBundle;
                            }
                            if(Integer.parseInt(tempLine[1])==3){
                                ++AvarageHighPricecount;
                                totalHighPricecount=totalHighPricecount+Integer.parseInt(line.split("\t")[8]);// adding every price
                            }
                            
                        }
                    }
                    //closes the stream and release the resources  
                } //creates a buffering character input stream
                
                }  
                catch(IOException e)  
                {  
                }  
                 //Printing the prices and and other information
                System.out.println("Total number of contracts: "+contractsQuantity);
                System.out.println("Number of contracts with High or Unlimited Data Bundles: "+maxValueOfBundle);
                try{
                     System.out.println("Average charge for large packages: "+ Utilities.convertToPounds(totalHighPricecount/AvarageHighPricecount)+"£" );
                }
                catch (Exception e){
                         System.out.println("Average charge for large packages: 0");
                }
        }
        else{
            //user select the archive.txt file records
            
            System.out.println("Select Month\n0.January\t"
                    + "6.July");
            System.out.println("1.February\t"
                    + "7.August");
            System.out.println("2.March\t"
                    + "8.September");
            System.out.println("3.April\t"
                    + "9.October");
            System.out.println("4.May\t"
                    + "10.November");
            System.out.println("5.June\t"
                    + "11.December");
            while(stayInCurrentQuestion)
            {
            try{
                inputMonth = scanner.nextInt();
                if(inputMonth>=0 && inputMonth<=11)
                {
                    stayInCurrentQuestion=false;
                }
                else{
                    throw new Exception("");
                }
            }catch(Exception e){
                System.out.println("Select Correct Input");
                scanner.next();
            }
            }
            try  
                {  //Fetching records from the file
                File file=new File(archiveFileLocation);      
                try (FileReader fr = new FileReader(file)) {
                    BufferedReader br=new BufferedReader(fr);
                    StringBuilder sb=new StringBuilder();
                    String line;
                    while((line=br.readLine())!=null)  //reading line by line
                    {
                        String tempLine[]=line.split("\t");//filtering for month number
                        if(inputMonth==getMonthNumberFromDate(tempLine[0]))
                        {
                            ++contractsQuantity;
                            
                            if(Integer.parseInt(tempLine[2])>=3){
                                ++maxValueOfBundle;
                            }
                            if(Integer.parseInt(tempLine[1])==3){
                                ++AvarageHighPricecount;
                                totalHighPricecount=totalHighPricecount+(int)(Float.parseFloat(line.split("\t")[8])*100);//calculation averages
                            }
                        }
                    }
                }
                }catch(IOException e){e.printStackTrace();}  
                //printing the extracted in formation
                System.out.println("Total number of contracts: "+contractsQuantity);
                System.out.println("Number of contracts with High or Unlimited Data Bundles: "+maxValueOfBundle);
                try{
                     System.out.println("Average charge for large packages: "+ Utilities.convertToPounds(totalHighPricecount/AvarageHighPricecount)+"£" );
                }
                catch (Exception e){
                         System.out.println("Average charge for large packages: 0");
                }
        }

    }
    
    
     public void findAndDisplayContract() 
    {
        
        boolean stayInCurrentQuestion=true;
        boolean selectedSourceOfContracts=true;
        System.out.println("Select the Source\n1.Contacts.txt\n2.Archive.txt");// asking the source of file
        while(stayInCurrentQuestion){
            try{
                int refc = scanner.nextInt();
                switch (refc) {
                    case 1:
                        // for contracts.txt file
                        selectedSourceOfContracts=true;
                        stayInCurrentQuestion=false;
                        break;
                    case 2:
                        // for archive.txt file
                        selectedSourceOfContracts=false;
                        stayInCurrentQuestion=false;
                        break;
                    default:
                        System.out.println("Please choose a valid option");//On wrong inputs prints to console
                        break;   
                }
            }catch(Exception e){
                System.out.println("Please choose a valid option");
                scanner.next();
            }  
        }
        
        scanner.nextLine();
        int counter = 0;
        
        if(selectedSourceOfContracts)// for contracts.txt file selection records
        {
            System.out.println("Enter your Search text:");
            String searchtext = scanner.next();//takes the input search key word
            try {  // fetching the contracts from the contracts.txt
                File file=new File(contractsFileLocation);    
                try (FileReader fr = new FileReader(file)) {
                    BufferedReader br=new BufferedReader(fr);
                    StringBuilder sb=new StringBuilder();
                    String line;
                    while((line=br.readLine())!=null)
                    {
                        String tempLine[]=line.split("\t");
                        //Comparing the keyword with the contract recods
                        if(Pattern.compile(Pattern.quote(searchtext), Pattern.CASE_INSENSITIVE).matcher(tempLine[6]).find() || Pattern.compile(Pattern.quote(searchtext), Pattern.CASE_INSENSITIVE).matcher(tempLine[5]).find())
                        {
                            new Contract(Integer.parseInt(tempLine[8]),
                                    tempLine[6],
                                    Integer.parseInt(tempLine[1]),
                                    Integer.parseInt(tempLine[2]),
                                    Integer.parseInt(tempLine[3]),
                                    Integer.parseInt(tempLine[7]),
                                    Boolean.parseBoolean(tempLine[4]),
                                    tempLine[0],    
                                    tempLine[5])
                                    .printSummary();
                            ++counter;//Printing to console
                        }
                    }
                }
            }catch(IOException e){e.printStackTrace();}  
        }
        else{// for archive file
            System.out.println("Enter your Search text:");
            String searchtext = scanner.next();// taking user input
            try {  
                File file=new File(archiveFileLocation);    
                try (FileReader fr = new FileReader(file)) {
                    BufferedReader br=new BufferedReader(fr);
                    StringBuilder sb=new StringBuilder();
                    String line;
                    while((line=br.readLine())!=null)  // reading line by line
                    {
                        String tempLine[]=line.split("\t");
                        float gotprice=Float.parseFloat(tempLine[8]);
                        int postion= Utilities.findIndex(CompanyPlans.typeOfPeriodOfContract,Integer.parseInt(tempLine[3])); // taking the position of record
                        if(Pattern.compile(Pattern.quote(searchtext), Pattern.CASE_INSENSITIVE).matcher(tempLine[6]).find() || Pattern.compile(Pattern.quote(searchtext), Pattern.CASE_INSENSITIVE).matcher(tempLine[5]).find()){
                            OldContract tempObj = new OldContract((int)(gotprice*100),tempLine[6],Integer.parseInt(tempLine[1]),Integer.parseInt(tempLine[2]),postion,tempLine[7],tempLine[4],tempLine[0],tempLine[5]);
                            ++counter;
                            tempObj.printSummary();// printing the contract to console
                        }    
                    }
                }
            }catch(IOException e){}
            
        }
        if(counter==0){
            System.out.println("No Match Found");
        }
    }

    //This function modifys the records of the Contract by Search with name of reference number
    public void modifyTheExistingContract() {
        
        // taking list to add matched with string name
        ArrayList<String> matchers;
        matchers = new ArrayList<>();
        BufferedReader br;
        StringBuffer sb=null;
        System.out.println("Enter the Search text: ");
        String searchtext = scanner.next();
        System.out.println("Select From Search");
        try {  // opening file to access the records
                File file=new File(contractsFileLocation);    
            try (FileReader fr = new FileReader(file)) {
                br=new BufferedReader(fr);
                sb=new StringBuffer();
                String line;
                while((line=br.readLine())!=null)
                { 
                    sb.append(line).append(System.lineSeparator());
                    String tempLine[]=line.split("\t");
                    // matching the string input to the records
                    if(Pattern.compile(Pattern.quote(searchtext), Pattern.CASE_INSENSITIVE).matcher(tempLine[6]).find() || Pattern.compile(Pattern.quote(searchtext), Pattern.CASE_INSENSITIVE).matcher(tempLine[5]).find()){
                        matchers.add(line); // for matched record add to list
                        System.out.println("No:"+matchers.size());// printing hte record serial number which makes user to select
                        new Contract(Integer.parseInt(tempLine[8]),tempLine[6],Integer.parseInt(tempLine[1]),Integer.parseInt(tempLine[2]),Integer.parseInt(tempLine[3]),Integer.parseInt(tempLine[7]),Boolean.parseBoolean(tempLine[4]),tempLine[0],tempLine[5]).printSummary();
                        // Printing the ContractSummary
                    }    
                }
            }
            }catch(IOException e){}
        
        
        
        if(matchers.isEmpty()){
            System.out.println("No matching results found");// id no matching records found
        }else{
            System.out.println("Enter the Sno of record you want to change");// asking the serial number to edit hte record
            boolean gotWrongNumber=true;
            while(gotWrongNumber)
            {
                try{
                     int selection = scanner.nextInt();
                     if(selection>0 && selection<=matchers.size())//checking weather input is valid or not
                     {
                         gotWrongNumber=false;
                         boolean proceed=false;
                         String[] dataPack = matchers.get(selection-1).split("\t");
                         int contractPeriod = CompanyPlans.typeOfPeriodOfContract[Integer.parseInt(dataPack[3])];// extracting the contract  contractPeriods and validatings
                         int diffrenceInmonths = diffrenceBewteenTwoDatesInMonths(dataPack[0],getCurrentDate());
                         if( Math.ceil(contractPeriod/2) >= diffrenceInmonths){// validationg the user to eligible or not for editing
                             if( contractPeriod>6 )
                             {
                                 proceed =true;// approving the user to edit
                                 
                             }else if(Integer.parseInt(dataPack[7])==3){
                                 proceed=true;// approving the user to edit
                             }
                             else{System.out.println("Cannot edit this Contract!");}// disapproving the user to edit
                         }
                         else{System.out.println("Cannot edit this Contract!");}
                         if(proceed){
                             
                             // Funcition to add the changed values to contract with same reference number
                           String k = getTheChangedValues(dataPack[5]);
                           
                           // after getting information from user replacing the old record
                           String fullText;
                             fullText = sb.toString().replaceAll(matchers.get(selection-1), k);
                             try (BufferedWriter writer = new BufferedWriter(new FileWriter(contractsFileLocation))) {
                                 writer.write(fullText);
                             }
                            //Printing the message
                            System.out.println("\nChanges are made to Contract\n");
                            SendFakeNotification();
                         }
                     }else{
                         System.out.println("invalid value");
                     }
                }catch(IOException | NumberFormatException | ParseException e){ System.out.println("invalid value by exception"+e);// for invalid inputs
                }
            }
               
        }
        
    }
    
    
    // Function returns the number of months differ between two months
    public static int diffrenceBewteenTwoDatesInMonths(String date1,String date2) throws ParseException{
        
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
    Date firstDate = sdf.parse(date1);
    Date secondDate = sdf.parse(date2);
    long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    return (int)(diff/30);
    }
    
    // To get Month number by dataPack string
    public static int getMonthNumberFromDate(String sDate)throws Exception{
        
        return new SimpleDateFormat("dd-MMM-yyyy").parse(sDate).getMonth();
    }
    
    //To get the present date
    public static String getCurrentDate()
    {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    return sdf.format(cal.getTime());
    }
    
    // function to take new inputs form user
    private static String getTheChangedValues(String referenceNumber) {
        Contract contract = new Contract();
        
        
        int businessType=100;
        int minutePackage = 100;
        int contractPeriod=100;
        int dataPack = 100;        
        
        scanner.nextLine();
        
        boolean stayInCurrentQuestion=true;
        System.out.println("Enter new name");
        while(stayInCurrentQuestion)
        {
        String name = scanner.nextLine();
        if(name.length()>25){
            System.out.println("Name should be less than 25 characters\nplease Try again");
        }else{
            contract.setCustomerName(name);
            stayInCurrentQuestion=false;
        }  
        }
        stayInCurrentQuestion=true;
        
        while(stayInCurrentQuestion){  
        try{
        System.out.println("Enter Contract Type\n1.Business\n2.NON-Business\n3.Charity");
        businessType = scanner.nextInt();
        if(businessType>=1 && businessType<=3)
        {
            contract.setContactType(businessType);
            stayInCurrentQuestion=false;
            
        }else{throw new Exception("invalid selection");}
        
        }catch(Exception e){
            System.out.println("Select Correct option");
            scanner.next();
          } 
        }
        stayInCurrentQuestion=true;
        
        while(stayInCurrentQuestion){  
        try{
        System.out.println("Choose your minute package \n1.Small 300 Minutes"+
                "\n2.Medium 500 minute\n3.Large 1200 Minutes");
        minutePackage = scanner.nextInt();
        if(minutePackage>=1 && minutePackage<=3)
        {
         contract.setMinuatespackage(minutePackage);
         stayInCurrentQuestion=false;
        }else{throw new Exception("invalid selection");}
        }catch(Exception e){
            System.out.println("Select Correct option");
            scanner.next();
          } 
        }
        stayInCurrentQuestion=true;
        
        while(stayInCurrentQuestion){  
        try{
            if(contract.getMinuatespackage()==3)
            {
                System.out.println("Choose your new Data package \n1.Low 1GB "+
                "\n2.Medium 4GB\n3.High 8GB \n4.Unlimited");
                dataPack = scanner.nextInt();
                if(dataPack>=1 && dataPack<=4)
                {
                contract.setDataPackage(dataPack);
                stayInCurrentQuestion=false;
                }else{throw new Exception("invalid selection");}
   
            }else{
                 System.out.println("Choose your new Data package \n1.Low 1GB "+
                "\n2.Medium 4GB\n3.High 8GB ");
                dataPack = scanner.nextInt();
                if(dataPack>=1 && dataPack<=3)
                {
                contract.setDataPackage(dataPack);
                stayInCurrentQuestion=false;
                }else{throw new Exception("invalid selection");}
            }

        }catch(Exception e){
            System.out.println("Select Correct option");
            scanner.next();
          } 
        }
        
        stayInCurrentQuestion=true;
        
        while(stayInCurrentQuestion){  
        try{
        System.out.println("Choose your new package contractPeriod \n1.1 Month "+
                "\n2.12 months\n3.18 Months \n4.24 Months");
          contractPeriod = scanner.nextInt();
        if(contractPeriod>=1 && contractPeriod<=4)
        {
        contract.setPeriodOfContract(contractPeriod);
        stayInCurrentQuestion=false;
        }else{throw new Exception("invalid selection");}
        }catch(Exception e){
            System.out.println("Select Correct option");
            scanner.next();
          } 
        }
        stayInCurrentQuestion=true;

        contract.setPrice(Utilities.getCharges(minutePackage, dataPack));
        
        System.out.println("include international calls? (15% more for international charges)"
                + "\n1.Yes\n2.No");
        int international = scanner.nextInt();
        if(international==1)
        {
            contract.setInternationalCalls(true);
            contract.setPrice(Utilities.getAppliesInternationalCharges(contract.getPrice()));          
        }else{
            contract.setInternationalCalls(false);
        }
         contract.setPrice(Utilities.getDiscountedPrice(businessType, contractPeriod,contract.getPrice()));

        contract.setContractDate(getCurrentDate());
        contract.setReferenceNumber(referenceNumber);

    
    return contract.toString();
    }

    private static void SendFakeNotification() { 
    
    
            System.out.println("\n\n a notification is sent to both the customer and the finance\n" +
            "department\n\n");  
    
    }

    
    public ContractManagerServices(String contractsFileLocation, String archiveFileLocation)
    {
        super();
        this.contractsFileLocation = contractsFileLocation;
        this.archiveFileLocation= archiveFileLocation;
    }
    public ContractManagerServices()
    {
        super();
    }
}
