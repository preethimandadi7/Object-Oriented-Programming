
package contractmanager;  

//Contract Object represents single Contract

public class Contract extends ContractPrototype {
    

    

    private int contractType;    
    private boolean internationalCalls;

 
   // Declaring the array of values makes easy to get or set values
   //    because we are accessing user info with numericals
    
    

    @Override
    public void setReferenceNumber(String referenceNumber) {
        super.setReferenceNumber(referenceNumber); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getReferenceNumber() {
        return super.getReferenceNumber(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setCustomerName(String customerName) {
        
        if(customerName.length()>25){
            return false;
        }else{
             super.setCustomerName(customerName); 
             return true;
        
    }
      
    }

    @Override
    public String getCustomerName() {
        return super.getCustomerName(); //To change body of generated methods, choose Tools | Templates.
    }

    

    


    @Override
    public boolean setMinuatespackage(int minuatespackage) {
        
        if(minuatespackage>=1 && minuatespackage<=3)
        {
            super.setMinuatespackage(minuatespackage);
            return true;
        }else{
            return false;
        }
       
    }

    @Override
    public int getMinuatespackage() {
        return super.getMinuatespackage(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    



    @Override
    public boolean setDataPackage(int dataPackage) {
        
        if(super.getMinuatespackage()==3)
        {
            if(dataPackage>=1 && dataPackage<=4)
            {
                super.setDataPackage(dataPackage);
                return true;

            }else{
                return false;
            }
            
        }else{
       if(dataPackage>=1 && dataPackage<=3)
            {
               super.setDataPackage(dataPackage);
                return true;

            }else{
                return false;
            }
        
    }
    }
    

    @Override
    public int getDataPackage() {
        return super.getDataPackage(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

  

    public int getContractType() {
        return contractType;
    }

    public boolean setContactType(int contractType) 
    {
        if(contractType>=1 && contractType<=3)
        {
            this.contractType = contractType;
            return true;
        }
        else{
            return false;
        }
        
        
    }

    @Override
    public void setContractDate(String contractDate) {
        super.setContractDate(contractDate); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getContractDate() {
        return super.getContractDate(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setInternationalCalls(boolean internationalCalls) {
        return super.setInternationalCalls(internationalCalls); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInternationalCalls() {
        return super.isInternationalCalls(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setPeriodOfContract(int period) {
        
        if(period>=1 && period<=4)
        {
            return super.setPeriodOfContract(period); //To change body of generated methods, choose Tools | Templates.
            
        }
        else{
            return false;
        }
        
    }

    @Override
    public int getPeriodOfContract() {
        return super.getPeriodOfContract(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPrice() {
        return super.getPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean setInternationalCalls(int selectedOption) 
    {
        if(selectedOption==1){
             return super.setInternationalCalls(true);
        }
       
        if(selectedOption==2){
            return super.setInternationalCalls(false);
        }else{
            return false;
        }
        
        
    }
    
    

    // Generationg a custom toString to save the string sirectly into the contacts.txt file
    @Override
    public String toString() {
        return super.getContractDate()+"\t"+super.getMinuatespackage()+"\t"+super.getDataPackage()+"\t"+super.getPeriodOfContract()+"\t"+internationalCalls+"\t"+super.getReferenceNumber()+"\t"+super.getCustomerName()+"\t"+contractType+"\t"+super.getPrice();
       
              
    }
    
    //A Primary Constructor to create a contract with contracts.txt file supported properties 

    public Contract(int price, String customerName, int minuatespackage, int dataPackage, int periodOfContract, int contractType, boolean internationalCalls, String contractDate, String referenceNumber) {
        super.setPrice( price);
        super.setCustomerName(customerName);
        super.setMinuatespackage(minuatespackage);
        super.setDataPackage(dataPackage);
        super.setPeriodOfContract(periodOfContract);
        this.contractType = contractType;
        this.internationalCalls = internationalCalls;
        super.setContractDate(contractDate);
        super.setReferenceNumber(referenceNumber);
      
    }
    
    // Default Constructors uses to create a empty object
    public Contract() {
    }

    //Method to check the discount is exist or not
    String hasDiscount() 
    {
       if(Utilities.getDiscountPercentage(this.contractType, super.getPeriodOfContract(), super.getPrice())==0){
           return "none";
       }
       else{
           return Utilities.getDiscountPercentage(this.contractType, super.getPeriodOfContract(), super.getPrice())+"%";
       }
    }
    
    
     // SummeryViewer for printing the summery of single record of contract for contracts.txt
    public void printSummary(){
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|  Customer: "+super.getCustomerName()+"                                                 ".substring(super.getCustomerName().length())+"|");
        System.out.println("|                                                             |");
        System.out.println("|       Ref: "+super.getReferenceNumber()+"                       Date: "+super.getContractDate()+"   |");
        System.out.println("|   Package: "+CompanyPlans.minPackagesTypes[super.getMinuatespackage()]+"                             Data: ".substring(CompanyPlans.minPackagesTypes[super.getMinuatespackage()].length())+CompanyPlans.dataPackagesTypes[super.getDataPackage()]+"              |".substring(CompanyPlans.dataPackagesTypes[super.getDataPackage()].length()));
        System.out.println("|    Period: "+CompanyPlans.typeOfPeriodOfContract[super.getPeriodOfContract()]+" Months"+"                      Type: ".substring(Utilities.getNumberOfCharecters(CompanyPlans.typeOfPeriodOfContract[super.getPeriodOfContract()]))+CompanyPlans.accountType[this.contractType]+"              |".substring(CompanyPlans.accountType[this.contractType].length()));
        System.out.println("|                                                             |");
        System.out.println("|  Discount: "+this.hasDiscount()+"                           ".substring(this.hasDiscount().length())+" Intl.Calls: "+Utilities.internationalFormat(this.isInternationalCalls())+"         |".substring(Utilities.internationalFormat(this.isInternationalCalls()).length()));
        System.out.println("|                                                             |");
        System.out.println("|              Discounted Mothly Charges: "+Utilities.convertToPounds(super.getPrice())+"£   "+"                |".substring(String.valueOf(Utilities.convertToPounds(super.getPrice())).length()));
        System.out.println("|                                                             |");
        System.out.println("+-------------------------------------------------------------+");
    }
    
    
    
}

