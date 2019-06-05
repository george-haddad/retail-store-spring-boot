package com.retail.store.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.retail.store.domain.DiscountCalculation;
import com.retail.store.domain.bill.Bill;
import com.retail.store.domain.user.User;
import com.retail.store.service.discount.DiscountService;

@RequestMapping("discounts")
@RestController
public class DiscountController {

    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Double createDiscount(@Valid @RequestBody DiscountCalculation discCalc) {
        User user = discCalc.getUser();
        Bill bill = discCalc.getBill();
        return discountService.discountCalculation(user, bill);
    }
}
