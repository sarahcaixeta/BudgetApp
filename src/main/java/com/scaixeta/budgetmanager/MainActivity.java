package com.scaixeta.budgetmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.fragments.BudgetSetupFragment;

import javax.inject.Inject;


public class MainActivity extends FragmentActivity implements BudgetSetupFragment.OnFragmentInteractionListener{

    @Inject BudgetCalculator budgetCalculator;

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BudgetApp.getInjectable(this).inject(this);

        setContentView(R.layout.activity_main);
        resultText = (TextView) findViewById(R.id.daily_budget);

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
            Toast.makeText(this, "An expense should be created", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
