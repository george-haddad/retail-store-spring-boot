package com.retail.demo.service.discount;

import com.retail.demo.domain.bill.Bill;

public interface DiscountService {

    Double discountCalculation(long userId, Bill bill);
}
