package com.retail.demo.service.discount;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import org.springframework.stereotype.Service;
import com.retail.demo.domain.bill.Bill;
import com.retail.demo.domain.bill.Item;
import com.retail.demo.domain.bill.ItemType;
import com.retail.demo.domain.user.User;
import com.retail.demo.domain.user.UserType;

@Service
public class DefaultDiscountService implements DiscountService {

    @Override
    public Double discountCalculation(User user, Bill bill) {
        double groceryAmount = calculateTotal(bill.getItems(), ItemType.GROCERY);
        double totalAmount = calculateTotal(bill.getItems(), null);
        double nonGroceryAmount = totalAmount - groceryAmount;
        double userDiscount = getUserSpecificDiscount(user);
        double billsDiscount = calculateBillsDiscount(totalAmount, 100, 5);
        
        if(nonGroceryAmount > 0) {
            nonGroceryAmount = calculateDiscount(nonGroceryAmount, userDiscount);
        }
        
        double finalAmount = (groceryAmount + nonGroceryAmount) - billsDiscount;
        return Double.valueOf(finalAmount);
    }
    
    /**
     * Calculate the total of all items of a specific <code>type</code>. Should
     * the type to check be <code>null</code> then it will calculate the total
     * of all item types.
     * 
     * @param items - the {@link List} of items
     * @param type - the item type or null for all items
     * @return total of all items of a particular {@link ItemType}
     */
    private double calculateTotal(List<Item> items, ItemType type) {
        double total = 0.0;
        for(Item item : items) {
            if(type == null) {
                total = total + item.getPrice().doubleValue();
            }
            else {
                if(type.equals(item.getType())) {
                    total = total + item.getPrice().doubleValue();
                }
            }
        }
        
        return total;
    }
    
    private double calculateDiscount(double amount, double discount) {
        double x = amount * discount;
        return amount - x;
    }
    
    /**
     * Given a {@link User} it will calculate what their specific
     * discount is based on the retail store's policy.
     * 
     * @param user - the user
     * @return the specific discount based on {@link UserType}
     */
    private double getUserSpecificDiscount(User user) {
        UserType type = user.getType();
        double discount = 0;
        
        switch(type) {
            case AFFILIATE: {
                discount = 0.1;
                break;
            }
            
            case CUSTOMER: {
                if(isCustomerSince(user.getJoinDate(), 2)) {
                    discount = 0.05;
                }
                break;
            }
            
            case EMPLOYEE: {
                discount = 0.3;
                break;
            }
            
            default: {
                break;
            }
        }
        
        return discount;
    }
    
    /**
     * Returns true if the join date is greater than or equal to the
     * number of years to check.
     * 
     * @param joinDate - the date that the customer joined
     * @param years - the number of years to check
     * @return true if the customer has joined <code>years</code> ago
     */
    private boolean isCustomerSince(LocalDate joinDate, long years) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(joinDate, now);
        int diff = period.getYears();
        return diff >= years;
    }
    
    /**
     * For every <code>amount</code> it will discount <code>discountAmount</code>
     * from the total.
     * 
     * @param totalAmount - the total amount to check
     * @param amount - the amount to count
     * @param discountAmount - the amount to discount per <code>amount</code>
     * @return the total discount amount
     */
    private double calculateBillsDiscount(double totalAmount, double amount, double discountAmount) {
        int count = (int)(totalAmount / amount);
        return discountAmount * count;
    }
}
