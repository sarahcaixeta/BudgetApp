package com.scaixeta.budgetmanager.fragments;

import android.widget.ListView;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;
import com.scaixeta.budgetmanager.utils.TestDateUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class ExpenseListFragmentTest {

    private ExpenseListFragment fragment;

    @Before
    public void setUp() {
        fragment = new ExpenseListFragment();
        FragmentTestUtil.startFragment(fragment);
    }

    @Test
    public void shouldContainAListView() {
        assertThat(fragment.getView().findViewById(R.id.expenses_list_view), notNullValue());
    }
    
    @Test
    public void theListShouldHaveAnAdapter() {
        ListView list = (ListView) fragment.getView().findViewById(R.id.expenses_list_view);

        assertThat(list.getAdapter(), notNullValue());
        assertThat(list.getAdapter().getCount(), equalTo(0));
    }

    @Test
    public void shouldAddAnItemToTheList() {
        Expense expense = new Expense("food", 10d, TestDateUtils.aCalendarOn(2015, 1, 20));
        fragment.addExpense(expense);

        ListView list = (ListView) fragment.getView().findViewById(R.id.expenses_list_view);
        assertThat(list.getAdapter().getCount(), equalTo(1));
    }

}
