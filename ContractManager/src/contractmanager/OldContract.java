/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contractmanager;


/**
 *
 * @author Preethi
 */
public class OldContract extends ContractPrototype {
    
    
    
    private String contractType;
    
   
    private String internationalCallValue;
    
    

    @Override
    public void setReferenceNumber(String referenceNumber) {
        super.setReferenceNumber(referenceNumber); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getReferenceNumber() {
        return super.getReferenceNumber(); //To change body of generated methods, choose Tools | Templates.
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
    public boolean setPeriodOfContract(int periodOfContract) {
        return super.setPeriodOfContract(periodOfContract); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPeriodOfContract() {
        return super.getPeriodOfContract(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setDataPackage(int dataPackage) {
        return super.setDataPackage(dataPackage); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getDataPackage() {
        return super.getDataPackage(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setMinuatespackage(int minuatespackage) {
        return super.setMinuatespackage(minuatespackage); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMinuatespackage() {
        return super.getMinuatespackage(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setCustomerName(String customerName) {
        return super.setCustomerName(customerName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCustomerName() {
        return super.getCustomerName(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPrice(int price) {
        super.setPrice(price); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getPrice() {
        return super.getPrice(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    @Override
    public boolean setInternationalCalls(boolean internationalCalls) {
        return super.setInternationalCalls(internationalCalls); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isInternationalCalls() {
        return super.isInternationalCalls(); //To change body of generated methods, choose Tools | Templates.
    }



 
    public String getInternationalCallValue() {
        return internationalCallValue;
    }

    public void setInternationalCallValue(String internationalCallValue) {
        this.internationalCallValue = internationalCallValue;
    }
    
    
    
    
    
    
    
     public OldContract(int price, String customerName, int minuatespackage, int dataPackage, int periodOfContract,String contractType, String internationalCalls, String contractDate, String referenceNumber) {
        super.setPrice(price);
        super.setCustomerName(customerName);
        super.setMinuatespackage(minuatespackage);
        super.setDataPackage(dataPackage);
        super.setPeriodOfContract(periodOfContract);
        this.contractType = contractType;
        this.internationalCallValue = internationalCalls;
        super.setContractDate(contractDate);
        super.setReferenceNumber(referenceNumber);
      
    }
     
    public void printSummary(){
        System.out.println("+-------------------------------------------------------------+");
        System.out.println("|                                                             |");
        System.out.println("|  Customer: "+this.getCustomerName()+"                                                 ".substring(this.getCustomerName().length())+"|");
        System.out.println("|                                                             |");
        System.out.println("|       ref: "+this.getReferenceNumber()+"                       Date: "+this.getContractDate()+"   |");
        System.out.println("|   Package: "+CompanyPlans.minPackagesTypes[this.getMinuatespackage()]+"                            Data: ".substring(CompanyPlans.minPackagesTypes[this.getMinuatespackage()].length())+CompanyPlans.dataPackagesTypes[this.getDataPackage()]+"              |".substring(CompanyPlans.dataPackagesTypes[this.getDataPackage()].length()));
        System.out.println("|    Period: "+CompanyPlans.typeOfPeriodOfContract[this.getPeriodOfContract()]+" Months"+"                     Type: ".substring(this.getContractType().length())+this.getContractType()+"              |".substring(this.getContractType().length()));
        System.out.println("|                                                             |");
        System.out.println("|  Discount: None                       "+" Intl.Calls: "+this.getInternationalCallValue()+"        |".substring(this.getInternationalCallValue().length()));
        System.out.println("|                                                             |");
        System.out.println("|              Discounted Mothly Charges: "+Utilities.convertToPounds(this.getPrice())+"£   "+"                |".substring(String.valueOf(Utilities.convertToPounds(this.getPrice())).length()));
        System.out.println("|                                                             |");
        System.out.println("+-------------------------------------------------------------+");
    }
     
    
    
 
}
