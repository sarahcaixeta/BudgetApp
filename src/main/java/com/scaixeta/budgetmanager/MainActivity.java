package com.scaixeta.budgetmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.ObjectGraph;


public class MainActivity extends Activity {

    @Inject BudgetCalculator budgetCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BudgetApp.getInjectable(this).inject(this);

        setContentView(R.layout.activity_main);
        final TextView income = (TextView) findViewById(R.id.income);
        Button calculate = (Button) findViewById(R.id.calculate);
        final TextView result = (TextView) findViewById(R.id.result);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredValue = income.getText().toString();
                if (enteredValue.length() != 0) {
                    Integer incomeInt = Integer.valueOf(income.getText().toString());
                    double value = budgetCalculator.calculateDailyBudget(incomeInt, 30);
                    result.setText(String.valueOf(value));
                }
            }
        });

    }

    /* Used by tests :( */
    public void setBudgetCalculator(BudgetCalculator budgetCalculator) {
        this.budgetCalculator = budgetCalculator;
    }
}
