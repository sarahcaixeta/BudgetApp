package com.scaixeta.budgetmanager;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;

import org.junit.Before;
import org.junit.Test;

import static com.scaixeta.budgetmanager.utils.TestDateUtils.aCalendarOn;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BudgetCalculatorTest {

    private BudgetCalculator calculator;

    @Before
    public void setUp() {
        calculator = new BudgetCalculator();
    }

    @Test
    public void shouldCalculateTheBudgetForOneDayIfInitialAndFinalDateAreTheSame() {
        Budget budget = new Budget(3000d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 1));

        assertThat(calculator.calculateDailyBudget(budget), equalTo(3000d));
    }

    @Test
    public void shouldDivideIncomeByNumberOfDays(){
        Budget budget = new Budget(3000d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 30));

        double dailyBudget = calculator.calculateDailyBudget(budget);
        assertThat(dailyBudget, equalTo(100d));
    }

    @Test
    public void shouldSubtractFromTheTotalBudgetWhenThereIsAnExpense() {
        Budget budget = new Budget(3000d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 30));
        budget.addExpense(new Expense("food", 300d, aCalendarOn(2015, 0, 1)));

        double dailyBudget = calculator.calculateDailyBudget(budget);
        assertThat(dailyBudget, equalTo(90d));
    }

}