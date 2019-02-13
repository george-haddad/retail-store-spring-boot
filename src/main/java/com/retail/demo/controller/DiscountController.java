package com.retail.demo.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.retail.demo.domain.DiscountCalculation;
import com.retail.demo.domain.bill.Bill;
import com.retail.demo.domain.user.User;
import com.retail.demo.service.discount.DiscountService;

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
