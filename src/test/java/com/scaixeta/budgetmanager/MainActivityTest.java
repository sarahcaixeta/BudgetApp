package com.scaixeta.budgetmanager;

import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp(){
        activity = Robolectric.buildActivity(MainActivity.class).create().get();

    }

    @Test
    public void shouldAssertSarahIsLame(){
        Robolectric.buildActivity(MainActivity.class).get().getResources();
        assertFalse(false);
    }

    @Test
    public void shouldContainIncomeEditText(){
        assertThat(activity.findViewById(R.id.income), notNullValue());
        assertThat(activity.findViewById(R.id.calculate), notNullValue());
        assertThat(activity.findViewById(R.id.result), notNullValue());
    }

    @Test
    public void shouldShowTheDailyBudgetAfterCalculate(){
        activity.setBudgetCalculator(aBudgetCalculatorWithDailyValueOf(50d));
        TextView income = (TextView) activity.findViewById(R.id.income);
        income.setText("0000");
        activity.findViewById(R.id.calculate).callOnClick();
        TextView result = (TextView) activity.findViewById(R.id.result);
        assertThat(result.getText().toString(), equalTo("50.0"));
    }

    private BudgetCalculator aBudgetCalculatorWithDailyValueOf(Double value) {
        BudgetCalculator calculator = mock(BudgetCalculator.class);
        when(calculator.calculateDailyBudget(anyInt(), anyInt())).thenReturn(value);
        return calculator;
    }
}