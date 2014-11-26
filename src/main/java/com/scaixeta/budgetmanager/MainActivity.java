package com.scaixeta.budgetmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.inject.Inject;


public class MainActivity extends Activity {

    BudgetCalculator budgetCalculator = new BudgetCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView income = (TextView) findViewById(R.id.income);
        Button calculate = (Button) findViewById(R.id.calculate);
        final TextView result = (TextView) findViewById(R.id.result);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer incomeInt = Integer.valueOf(income.getText().toString());
                double value = budgetCalculator.calculateDailyBudget(incomeInt, 30);
                result.setText(String.valueOf(value));
            }
        });

    }

}
