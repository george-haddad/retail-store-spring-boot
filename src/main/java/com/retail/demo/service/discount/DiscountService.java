package com.retail.demo.service.discount;

import com.retail.demo.domain.bill.Bill;
import com.retail.demo.domain.user.User;

public interface DiscountService {

    Double discountCalculation(User user, Bill bill);
}
