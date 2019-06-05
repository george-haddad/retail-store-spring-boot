package com.retail.store.service.discount;

import com.retail.store.domain.bill.Bill;
import com.retail.store.domain.user.User;

public interface DiscountService {

    Double discountCalculation(User user, Bill bill);
}
