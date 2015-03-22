package com.scaixeta.budgetmanager;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import java.util.Calendar;

import static com.scaixeta.budgetmanager.utils.TestDateUtils.aCalendarOn;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;
    private BudgetCalculator budgetCalculator;

    @Before
    public void setUp(){
        activity = Robolectric.buildActivity(MainActivity.class).create().start().get();
        budgetCalculator = Mockito.mock(BudgetCalculator.class);
        activity.setBudgetCalculator(budgetCalculator);
    }

    @Test
    public void shouldContainHomeScreenFragment(){
        assertThat(activity.findViewById(R.id.budget_setup_fragment), notNullValue());
    }

    @Test
    public void theIncomeViewShouldBeInvisibleWhenTheActivityFirstOpens() {
        assertThat(activity.findViewById(R.id.daily_budget_view).getVisibility(), equalTo(View.GONE));
    }

    @Test
    public void theIncomeViewShouldBeVisibleWhenTheBudgetIsSet() {
        Budget budget = new Budget(0d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 2));
        when(budgetCalculator.calculateDailyBudget(any(Budget.class))).thenReturn(0d);

        activity.onFragmentInteraction(budget);
        assertThat(activity.findViewById(R.id.daily_budget_view).getVisibility(), equalTo(View.VISIBLE));
        assertThat(activity.findViewById(R.id.budget_setup_fragment).getVisibility(), equalTo(View.GONE));
    }

    @Test
    public void shouldCalculateTheBudgetWhenTheSetupIsFinished() {
        Budget budget = new Budget(0d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 2));
        when(budgetCalculator.calculateDailyBudget(any(Budget.class))).thenReturn(100d);

        activity.onFragmentInteraction(budget);
        verify(budgetCalculator).calculateDailyBudget(budget);
        TextView budgetText = (TextView) activity.findViewById(R.id.daily_budget);
        assertThat(budgetText.getText().toString(), equalTo(activity.getResources().getString(R.string.budget_per_day, 100.00)));
    }

    @Ignore
    @Test
    public void shouldOpenADialogWhenTheNewExpenseButtonIsSelected() {
        activity.onFragmentInteraction(new Budget(10d, Calendar.getInstance(), Calendar.getInstance()));
        View button = activity.findViewById(R.id.fab);
        button.callOnClick();

        Dialog dialog = ShadowDialog.getLatestDialog();
        assertNotNull(dialog);
    }

    @Test
    public void shouldNotOpenADialogWhenThereIsNoBudget() {
        View button = activity.findViewById(R.id.fab);
        button.callOnClick();

        Dialog dialog = ShadowDialog.getLatestDialog();
        assertThat(dialog, nullValue());
    }
}