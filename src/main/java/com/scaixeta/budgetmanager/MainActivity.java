package com.scaixeta.budgetmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;
import com.scaixeta.budgetmanager.fragments.BudgetDetailsFragment;
import com.scaixeta.budgetmanager.fragments.BudgetSetupFragment;
import com.scaixeta.budgetmanager.fragments.ExpenseListFragment;
import com.scaixeta.budgetmanager.fragments.NewExpenseDialogFragment;

import java.util.Calendar;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity
        implements BudgetSetupFragment.OnFragmentInteractionListener, NewExpenseDialogFragment.NewExpenseListener{

    @Inject BudgetCalculator budgetCalculator;
    @Inject BudgetManager budgetManager;

    private ExpenseListFragment listFragment;
    private BudgetSetupFragment budgetSetupFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BudgetApp.getInjectable(this).inject(this);

        setContentView(R.layout.activity_main);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ExpenseListFragment) manager.findFragmentById(R.id.list_fragment);
        budgetSetupFragment = (BudgetSetupFragment) manager.findFragmentById(R.id.budget_setup_fragment);

        Budget budget = budgetManager.getBudget(this);
        if (budget != null){
            calculateAndShowBudget();
            listFragment.addExpense(budget.getExpenses().toArray(new Expense[]{}));
        }
        findViewById(R.id.daily_budget_view).setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openDetailsFragment();
                return false;
            }
        });
    }

    private void openDetailsFragment() {
        BudgetDetailsFragment dialog = new BudgetDetailsFragment();
        dialog.show(getSupportFragmentManager(), "budgetDetails");
    }

    /* Used by tests :( */
    public void setBudgetCalculator(BudgetCalculator budgetCalculator) {
        this.budgetCalculator = budgetCalculator;
    }

    /* Used by tests */
    public void setBudgetManager(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    @Override
    public void onFragmentInteraction(Budget budget) {
        budgetManager.saveBudget(this, budget);
        calculateAndShowBudget();
    }

    private void calculateAndShowBudget() {
        double dailyBudget = budgetCalculator.calculateDailyBudget(budgetManager.getBudget(this));
        TextView resultText = (TextView) findViewById(R.id.daily_budget);
        resultText.setText(getResources().getString(R.string.budget_per_day, dailyBudget));
        findViewById(R.id.daily_budget_view).setVisibility(View.VISIBLE);
        budgetSetupFragment.getView().setVisibility(View.GONE);
    }

    @Override
    public void onNewExpenseCreated(String name, double price) {
        Expense expense = new Expense(name, price, Calendar.getInstance());
        listFragment.addExpense(expense);
        budgetManager.saveExpense(this, expense);
        calculateAndShowBudget();
    }

}