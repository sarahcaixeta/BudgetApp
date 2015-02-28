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

import java.util.Calendar;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity implements BudgetSetupFragment.OnFragmentInteractionListener{

    @Inject BudgetCalculator budgetCalculator;

    private TextView resultText;
    private ExpenseListFragment listFragment;
    private BudgetSetupFragment budgetSetupFragment;

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
        double dailyBudget = budgetCalculator.calculateDailyBudget(budget);
        resultText.setText(getResources().getString(R.string.budget_per_day, dailyBudget));
        findViewById(R.id.daily_budget_view).setVisibility(View.VISIBLE);
        budgetSetupFragment.getView().setVisibility(View.GONE);
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
            dialog.setNewExpenseListener(new OnExpenseCreatedListener());
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            dialog.show(supportFragmentManager, "newExpenseDialog");

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class OnExpenseCreatedListener implements NewExpenseDialogFragment.NewExpenseListener {

        @Override
        public void onNewExpenseCreated(String name, double price) {
            Expense expense = new Expense(name, price, Calendar.getInstance());
            listFragment.addExpense(expense);
        }
    }
}
