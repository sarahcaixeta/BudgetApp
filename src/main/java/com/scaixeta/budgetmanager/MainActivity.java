package com.scaixeta.budgetmanager;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.scaixeta.budgetmanager.fragments.BudgetSetupFragment;

import java.util.Calendar;

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
        int days = (int) ((budget.getFinalDate().getTimeInMillis() - budget.getInitialDate().getTimeInMillis()) / 1000 / 60 / 60 / 24);

        resultText.setText(String.valueOf(budgetCalculator.calculateDailyBudget(budget.getValue(), days)));
    }
}
