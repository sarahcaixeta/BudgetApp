package com.scaixeta.budgetmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;
import com.scaixeta.budgetmanager.fragments.BudgetSetupFragment;
import com.scaixeta.budgetmanager.fragments.ExpenseListFragment;
import com.scaixeta.budgetmanager.fragments.NewExpenseDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity
        implements BudgetSetupFragment.OnFragmentInteractionListener, NewExpenseDialogFragment.NewExpenseListener{

    @Inject BudgetCalculator budgetCalculator;

    private TextView resultText;
    private ExpenseListFragment listFragment;
    private BudgetSetupFragment budgetSetupFragment;
    private Budget budget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BudgetApp.getInjectable(this).inject(this);

        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.daily_budget);

        FragmentManager manager = getSupportFragmentManager();
        listFragment = (ExpenseListFragment) manager.findFragmentById(R.id.list_fragment);
        budgetSetupFragment = (BudgetSetupFragment) manager.findFragmentById(R.id.budget_setup_fragment);

    }

    /* Used by tests :( */
    public void setBudgetCalculator(BudgetCalculator budgetCalculator) {
        this.budgetCalculator = budgetCalculator;
    }

    @Override
    public void onFragmentInteraction(Budget budget) {
        this.budget = budget;
        calculateAndShowBudget();
        findViewById(R.id.daily_budget_view).setVisibility(View.VISIBLE);
        budgetSetupFragment.getView().setVisibility(View.GONE);
    }

    private void calculateAndShowBudget() {
        double dailyBudget = budgetCalculator.calculateDailyBudget(budget);
        resultText.setText(getResources().getString(R.string.budget_per_day, dailyBudget));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_new_expense){
            NewExpenseDialogFragment dialog = new NewExpenseDialogFragment();
            dialog.setNewExpenseListener(this);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            dialog.show(supportFragmentManager, "newExpenseDialog");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNewExpenseCreated(String name, double price) {
        Expense expense = new Expense(name, price, Calendar.getInstance());
        listFragment.addExpense(expense);
        budget.addExpense(expense);
        calculateAndShowBudget();
    }
}
