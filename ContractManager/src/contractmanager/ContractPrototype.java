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
public class ContractPrototype {
    
    private int price;
    private String customerName;
    private int minuatespackage;
    private int dataPackage;
    private int periodOfContract;
    private boolean internationalCalls;
    private String contractDate;
    private String referenceNumber;
    
    

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean setCustomerName(String customerName) {
        this.customerName = customerName;
        return true;
    }

    public int getMinuatespackage() {
        return minuatespackage;
    }

    public boolean setMinuatespackage(int minuatespackage) {
        this.minuatespackage = minuatespackage;
        return true;
    }

    public int getDataPackage() {
        return dataPackage;
    }

    public boolean setDataPackage(int dataPackage) {
        this.dataPackage = dataPackage;
        return true;
    }

    public int getPeriodOfContract() {
        return periodOfContract;
    }

    public boolean setPeriodOfContract(int periodOfContract) {
        this.periodOfContract = periodOfContract;
        return true;
    }

    public boolean isInternationalCalls() {
        return internationalCalls;
    }

    public boolean setInternationalCalls(boolean internationalCalls) {
        this.internationalCalls = internationalCalls;
        return true;
    }

    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    
    
}
