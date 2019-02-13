package com.retail.demo.service.discount;

import org.springframework.stereotype.Service;
import com.retail.demo.domain.bill.Bill;
import com.retail.demo.domain.user.User;

@Service
public class DefaultDiscountService implements DiscountService {

    @Override
    public Double discountCalculation(User user, Bill bill) {
        System.out.println("Calculate discount for user: "+user.toString());
        System.out.println("With Bill = "+bill.toString());
        return Double.valueOf(0.0);
    }
}
