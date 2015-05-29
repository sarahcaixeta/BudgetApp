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

import org.joda.time.LocalDate;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity
        implements BudgetSetupFragment.OnFragmentInteractionListener, NewExpenseDialogFragment.NewExpenseListener{

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

        findViewById(R.id.daily_budget_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetailsFragment();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        onContentChanged();
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        if (listFragment != null){
            showBudgetAndExpenses();
        }
    }

    private void showBudgetAndExpenses() {
        Budget budget = budgetManager.getBudget(this);
        if (budget != null){
            calculateAndShowBudget();
            listFragment.addExpense(budget.getExpenses().toArray(new Expense[]{}));
        }
    }

    private void openDetailsFragment() {
        BudgetDetailsFragment dialog = new BudgetDetailsFragment();
        dialog.setBudgetManager(budgetManager);
        dialog.show(getSupportFragmentManager(), "budgetDetails");
    }

    /* Used by tests */
    public void setBudgetManager(BudgetManager budgetManager) {
        this.budgetManager = budgetManager;
    }

    public BudgetManager getBudgetManager() {
        return budgetManager;
    }

    @Override
    public void onFragmentInteraction(Budget budget) {
        budgetManager.saveBudget(this, budget);
        calculateAndShowBudget();
    }

    private void calculateAndShowBudget() {
        double dailyBudget = budgetManager.dailyBudget();
        TextView resultText = (TextView) findViewById(R.id.daily_budget);
        resultText.setText(getResources().getString(R.string.budget_per_day, dailyBudget));
        findViewById(R.id.daily_budget_view).setVisibility(View.VISIBLE);
        budgetSetupFragment.getView().setVisibility(View.GONE);
    }

    @Override
    public void onNewExpenseCreated(String name, double price) {
        Expense expense = new Expense(name, price, LocalDate.now());
        listFragment.addExpense(expense);
        budgetManager.saveExpense(this, expense);
        calculateAndShowBudget();
    }


}