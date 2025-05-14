package com.example.FinanceApp.controller;

import com.example.FinanceApp.template.Credit;
import com.example.FinanceApp.template.MortgageInterestCalculator;
import com.example.FinanceApp.template.PersonalLoanInterestCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interest")
public class InterestController {
    private final MortgageInterestCalculator mortgageInterestCalculator;
    private final PersonalLoanInterestCalculator personalLoanInterestCalculator;

    @Autowired
    public InterestController(MortgageInterestCalculator mortgageInterestCalculator,
                              PersonalLoanInterestCalculator personalLoanInterestCalculator) {
        this.mortgageInterestCalculator = mortgageInterestCalculator;
        this.personalLoanInterestCalculator = personalLoanInterestCalculator;
    }

    @PostMapping("/mortgage")
    public ResponseEntity<String> calculateMortgageInterest(@RequestBody Credit credit) {
        double interest = mortgageInterestCalculator.calculateInterest(credit);
        return ResponseEntity.ok("Odsetki dla kredytu hipotecznego: " + interest + " zł");
    }

    @PostMapping("/personal-loan")
    public ResponseEntity<String> calculatePersonalLoanInterest(@RequestBody Credit credit) {
        double interest = personalLoanInterestCalculator.calculateInterest(credit);
        return ResponseEntity.ok("Odsetki dla kredytu gotówkowego: " + interest + " zł");
    }
}


