package com.scaixeta.budgetmanager;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

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
        resultText = (TextView) findViewById(R.id.result);

    }

    /* Used by tests :( */
    public void setBudgetCalculator(BudgetCalculator budgetCalculator) {
        this.budgetCalculator = budgetCalculator;
    }

    @Override
    public void onFragmentInteraction(Budget budget) {
        resultText.setText(String.valueOf(budgetCalculator.calculateDailyBudget(budget)));
    }
}
