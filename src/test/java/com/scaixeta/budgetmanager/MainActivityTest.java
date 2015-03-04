package com.scaixeta.budgetmanager;

import android.app.Dialog;
import android.view.MenuItem;
import android.view.View;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.Expense;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;
import org.robolectric.tester.android.view.TestMenuItem;

import java.util.ArrayList;

import static com.scaixeta.budgetmanager.utils.TestDateUtils.aCalendarOn;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyCollection;
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
        when(budgetCalculator.calculateDailyBudget(any(Budget.class), anyCollection())).thenReturn(0d);

        activity.onFragmentInteraction(budget);
        assertThat(activity.findViewById(R.id.daily_budget_view).getVisibility(), equalTo(View.VISIBLE));
        assertThat(activity.findViewById(R.id.budget_setup_fragment).getVisibility(), equalTo(View.GONE));
    }

    @Test
    public void shouldCalculateTheBudgetWhenTheSetupIsFinished() {
        Budget budget = new Budget(0d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 2));
        when(budgetCalculator.calculateDailyBudget(any(Budget.class), anyCollection())).thenReturn(100d);

        activity.onFragmentInteraction(budget);
        verify(budgetCalculator).calculateDailyBudget(budget, new ArrayList<Expense>());
    }

    @Test
    public void shouldOpenADialogWhenTheNewExpenseMenuIsSelected() {
        MenuItem item = new TestMenuItem(R.id.action_new_expense);
        activity.onOptionsItemSelected(item);

        Dialog dialog = ShadowDialog.getLatestDialog();
        assertNotNull(dialog);
    }
}