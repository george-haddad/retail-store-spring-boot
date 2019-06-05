package com.retail.store.service.discount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import com.retail.store.domain.bill.Item;
import com.retail.store.domain.bill.ItemType;
import com.retail.store.domain.user.User;
import com.retail.store.domain.user.UserType;


public class DiscountEngineTest {

    @Test
    public void testCalculateTotal_GroceriesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Apple", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateTotal(items, ItemType.GROCERY);
        assertEquals(400.00, total, 0);
    }
    
    @Test
    public void testCalculateTotal_NonGroceriesOnly() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));
        
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateTotal(items, ItemType.STATIONARY);
        assertEquals(400.00, total, 0);
    }
    
    @Test
    public void testCalculateTotal_GroceriesMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));
        
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateTotal(items, ItemType.GROCERY);
        assertEquals(300.00, total, 0);
    }
    
    @Test
    public void testCalculateTotal_NonGroceriesMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));
        
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateTotal(items, ItemType.STATIONARY);
        assertEquals(400.00, total, 0);
    }
    
    @Test
    public void testCalculateTotal_AllMix() {
        List<Item> items = new ArrayList<Item>();
        items.add(new Item(Double.valueOf(100.0), "Pencil", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Eraser", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Banana", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Kiwi", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Pear", ItemType.GROCERY));
        items.add(new Item(Double.valueOf(100.0), "Stapler", ItemType.STATIONARY));
        items.add(new Item(Double.valueOf(100.0), "Glue", ItemType.STATIONARY));
        
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateTotal(items, null);
        assertEquals(700.00, total, 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void testCalculateTotal_error() {
        DiscountEngine de = new DiscountEngine();
        de.calculateTotal(null, ItemType.CLEANING);
    }

    @Test
    public void testCalculateDiscount_10pct() {
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateDiscount(1000, 0.1);
        assertEquals(900.00, total, 0);
    }
    
    @Test
    public void testCalculateDiscount_50pct() {
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateDiscount(1000, 0.5);
        assertEquals(500.00, total, 0);
    }
    
    @Test
    public void testCalculateDiscount_0pct() {
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateDiscount(1000, 0.0);
        assertEquals(1000.00, total, 0);
    }
    
    @Test
    public void testCalculateDiscount_100pct() {
        DiscountEngine de = new DiscountEngine();
        double total = de.calculateDiscount(1000, 1.0);
        assertEquals(0.0, total, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateDiscount_error() {
        DiscountEngine de = new DiscountEngine();
        de.calculateDiscount(1000, 2.0);
    }

    @Test
    public void testGetUserSpecificDiscount_affiliate() {
        User user = new User(Long.valueOf(100), "george", "George", "Haddad", UserType.AFFILIATE, LocalDate.now());
        DiscountEngine de = new DiscountEngine();
        double discount = de.getUserSpecificDiscount(user);
        assertEquals(0.1, discount, 0);
    }
    
    @Test
    public void testGetUserSpecificDiscount_employee() {
        User user = new User(Long.valueOf(100), "george", "George", "Haddad", UserType.EMPLOYEE, LocalDate.now());
        DiscountEngine de = new DiscountEngine();
        double discount = de.getUserSpecificDiscount(user);
        assertEquals(0.3, discount, 0);
    }
    
    @Test
    public void testGetUserSpecificDiscount_customer_old() {
        LocalDate joinDate = LocalDate.of(2016, 2, 23);
        User user = new User(Long.valueOf(100), "george", "George", "Haddad", UserType.CUSTOMER, joinDate);
        DiscountEngine de = new DiscountEngine();
        double discount = de.getUserSpecificDiscount(user);
        assertEquals(0.05, discount, 0);
    }
    
    @Test
    public void testGetUserSpecificDiscount_customer_new() {
        User user = new User(Long.valueOf(100), "george", "George", "Haddad", UserType.CUSTOMER, LocalDate.now());
        DiscountEngine de = new DiscountEngine();
        double discount = de.getUserSpecificDiscount(user);
        assertEquals(0.0, discount, 0);
    }
    
    @Test(expected = NullPointerException.class)
    public void testGetUserSpecificDiscount_customer_null_user() {
        DiscountEngine de = new DiscountEngine();
        de.getUserSpecificDiscount(null);
    }

    @Test
    public void testIsCustomerSince() {
        DiscountEngine de = new DiscountEngine();
        LocalDate joinDate = LocalDate.now();
        boolean isTwoYearsJoined = de.isCustomerSince(joinDate, 2);
        assertFalse(isTwoYearsJoined);
    }
    
    @Test
    public void testIsCustomerSince_1year() {
        DiscountEngine de = new DiscountEngine();
        LocalDate joinDate = LocalDate.now().minusYears(1);
        boolean isTwoYearsJoined = de.isCustomerSince(joinDate, 2);
        assertFalse(isTwoYearsJoined);
    }
    
    @Test
    public void testIsCustomerSince_2years() {
        DiscountEngine de = new DiscountEngine();
        LocalDate joinDate = LocalDate.now().minusYears(2);
        boolean isTwoYearsJoined = de.isCustomerSince(joinDate, 2);
        assertTrue(isTwoYearsJoined);
    }
    
    @Test
    public void testIsCustomerSince_3years() {
        DiscountEngine de = new DiscountEngine();
        LocalDate joinDate = LocalDate.now().minusYears(3);
        boolean isTwoYearsJoined = de.isCustomerSince(joinDate, 2);
        assertTrue(isTwoYearsJoined);
    }

    @Test
    public void testCalculateBillsDiscount() {
        DiscountEngine de = new DiscountEngine();
        double amount = de.calculateBillsDiscount(1000, 100, 5);
        assertEquals(50, amount, 0);
    }
    
    @Test
    public void testCalculateBillsDiscount_2() {
        DiscountEngine de = new DiscountEngine();
        double amount = de.calculateBillsDiscount(1000, 50, 5);
        assertEquals(100, amount, 0);
    }
    
    @Test
    public void testCalculateBillsDiscount_3() {
        DiscountEngine de = new DiscountEngine();
        double amount = de.calculateBillsDiscount(5632, 100, 5);
        assertEquals(280, amount, 0);
    }
}
