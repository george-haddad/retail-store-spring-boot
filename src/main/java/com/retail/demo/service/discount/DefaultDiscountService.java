package com.retail.demo.service.discount;

import org.springframework.stereotype.Service;
import com.retail.demo.domain.bill.Bill;
import com.retail.demo.domain.bill.ItemType;
import com.retail.demo.domain.user.User;

@Service
public class DefaultDiscountService implements DiscountService {

    @Override
    public Double discountCalculation(User user, Bill bill) {
        DiscountEngine de = new DiscountEngine();
        
        double groceryAmount = de.calculateTotal(bill.getItems(), ItemType.GROCERY);
        double totalAmount = de.calculateTotal(bill.getItems(), null);
        double nonGroceryAmount = totalAmount - groceryAmount;
        double userDiscount = de.getUserSpecificDiscount(user);
        double billsDiscount = de.calculateBillsDiscount(totalAmount, 100, 5);
        
        if(nonGroceryAmount > 0) {
            nonGroceryAmount = de.calculateDiscount(nonGroceryAmount, userDiscount);
        }
        
        double finalAmount = (groceryAmount + nonGroceryAmount) - billsDiscount;
        return Double.valueOf(finalAmount);
    }
}
