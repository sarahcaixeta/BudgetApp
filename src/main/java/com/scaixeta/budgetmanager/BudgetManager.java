package com.scaixeta.budgetmanager;

import android.content.Context;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;
import com.scaixeta.budgetmanager.data.ExpensesDatabase;

import org.joda.time.LocalDate;


public class BudgetManager {

    private Budget budget;
    private BudgetCalculator budgetCalculator;

    public BudgetManager(BudgetCalculator calculator) {
        this.budgetCalculator = calculator;
    }

    public Budget getBudget(Context context) {
        if (budget == null) {
            budget = ExpensesDatabase.getInstance(context).getBudget();
        }
        return budget;
    }

    public void saveBudget(Context context, Budget newBudget) {
        ExpensesDatabase.getInstance(context).insertBudget(newBudget);
        this.budget = ExpensesDatabase.getInstance(context).getBudget();
    }

    public void updateBudget(Context context, double value){
        ExpensesDatabase.getInstance(context).updateBudget(budget, value, budget.getInitialDate(), budget.getFinalDate());
        this.budget = ExpensesDatabase.getInstance(context).getBudget();
    }

    public void updateBudget(Context context, LocalDate initialDate, LocalDate finalDate){
        ExpensesDatabase.getInstance(context).updateBudget(budget, budget.getValue(), initialDate, finalDate);
        this.budget = ExpensesDatabase.getInstance(context).getBudget();
    }

    public void saveExpense(Context context, Expense newExpense) {
        ExpensesDatabase.getInstance(context).insertExpense(newExpense);
        this.budget = ExpensesDatabase.getInstance(context).getBudget();
    }

    public double dailyBudget(){
        return budgetCalculator.calculateDailyBudget(budget);
    }
}
