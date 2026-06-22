/**
 * מחלקה המייצגת משתמש או פעולה במערכת הנדל"ן.
 */
public class User {
    public static void main(String[] args){
        // שימוש בשמות ברורים למניעת בלבול בין חברי הצוות
        int basePropertyPrice = 5;
        int additionalTaxes = 10;
        
        int finalPriceCalculated = basePropertyPrice + additionalTaxes;
        
        System.out.println("The final price is: " + finalPriceCalculated);
    }
}