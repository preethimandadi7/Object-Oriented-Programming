

package contractmanager;

import java.util.Arrays;
import java.util.Random;


//Charges Class to compute calculations regarding the company discounts, charges and plan amounts.

public class Utilities {

    //properties of class
    // these properties makes easy to calculate the charges rather than calculating with logic's
    public static int INTERNATIONAL_CHARGES_PERCENTAGE = 15;
    
    //Returns moth's periods based on the user input
    public static int[] contractPeriods = {1,12,18,24};
    
    // Returns the monthly cost or Acutal plan 
    public static int[][] chargesRates = {
            {500, 700, 900, 0}, 
        
            {650, 850, 1050, 0}, 
            {850, 1050, 1250, 2000}
        };
    // Returns the discount rates in % on user plans
     public static int[][] discountRates = {
            {0, 10, 10, 10}, 
            {0, 5, 5, 10}, 
            {30, 30, 30, 30}
        };
    
     // this method returns the monthly charges based on minates and data packages;
    public static int getCharges(int minuatesPackage, int dataPackage){
        
        return chargesRates[minuatesPackage-1][dataPackage-1];
    }
    
    //Outputs the discount price
     public static int getDiscountedPrice(int contractType,int contractDuration,int price){
        
         
         return price-((price*discountRates[contractType-1][contractDuration-1])/100) ;

    }
     
     // Returns the Discount percentage based on the Contract
     public static int getDiscountPercentage(int contractType,int contractDuration,int price){
        
         
         return discountRates[contractType-1][contractDuration-1] ;

    }
    
    // Returns the price adding 15% international charges
    public static int getAppliesInternationalCharges(int price){
        
        return (int) Math.round(price + ((price*INTERNATIONAL_CHARGES_PERCENTAGE)/100));
    }
    
       // returs the number of charecters in int
    public static int getNumberOfCharecters( int value) {
        if(value>=10)
            return 2;
        else 
            return 1;
        }
    //Converts pence to pounds
    public static double convertToPounds(int pence){

        return Math.round((pence*0.01) * 100.0) / 100.0;   
    }
    //converts boolean to YES and NO
    public static String internationalFormat(boolean value){
        if(value){
            return "Yes";
        }
        else
        return "No";   
    }
    
    // for finding the position of int in array
    public static int findIndex(int arr[], int t) 
    { 
        
            int index = Arrays.binarySearch(arr, t); 
        return (index < 0) ? -1 : index; 

    } 
    
// Genrates the random reference number based on caontract type
    public static String generateReferencenumber(char acc) {

       return new Random().ints(97, 123)
      .limit(2)
      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
      .toString().toUpperCase()+(100+new Random().nextInt(899))+acc;
        
    }
    
}

