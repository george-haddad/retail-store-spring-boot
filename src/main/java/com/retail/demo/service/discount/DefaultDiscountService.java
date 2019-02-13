package com.retail.demo.service.discount;

import org.springframework.stereotype.Service;
import com.retail.demo.domain.bill.Bill;

@Service
public class DefaultDiscountService implements DiscountService {

    @Override
    public Double discountCalculation(long userId, Bill bill) {
        System.out.println("Calculate discount for user: "+userId);
        System.out.println("Bill = "+bill.getId()+" "+bill.getStoreName());
        return Double.valueOf(0.0);
    }
}
