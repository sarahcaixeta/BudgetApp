package com.scaixeta.budgetmanager;

import org.hamcrest.Matchers;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class BudgetCalculatorTest {

    @Test
    public void shouldDivideIncomeByNumberOfDays(){
        BudgetCalculator calculator = new BudgetCalculator();
        double dailyBudget = calculator.calculateDailyBudget(3000, 30);
        assertThat(dailyBudget, equalTo(100.0));
    }

}