package com.scaixeta.budgetmanager;

import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static com.scaixeta.budgetmanager.utils.TestDateUtils.aCalendarOn;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertFalse;
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
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
        budgetCalculator = Mockito.mock(BudgetCalculator.class);
        activity.setBudgetCalculator(budgetCalculator);
    }

    @Test
    public void shouldAssertSarahIsLame(){
        Robolectric.buildActivity(MainActivity.class).get().getResources();
        assertFalse(false);
    }

    @Test
    public void shouldContainHomeScreenFragment(){
        assertThat(activity.findViewById(R.id.home_screen_fragment), notNullValue());
    }

    @Test
    public void shouldCalculateTheBudgetWhenTheSetupIsFinished() {
        Budget budget = new Budget(0d, aCalendarOn(2015, 0, 1), aCalendarOn(2015, 0, 2));
        when(budgetCalculator.calculateDailyBudget(any(Budget.class))).thenReturn(100d);

        activity.onFragmentInteraction(budget);
        verify(budgetCalculator).calculateDailyBudget(budget);
    }

}