package com.scaixeta.budgetmanager.fragments;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scaixeta.budgetmanager.BudgetManager;
import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import static com.scaixeta.budgetmanager.utils.TestDateUtils.aCalendarOn;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.buildActivity;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class BudgetDetailsFragmentTest {

    private BudgetManager budgetManager;
    private Dialog dialog;

    @Before
    public void setup() {
        budgetManager = mock(BudgetManager.class);
        Mockito.when(budgetManager.getBudget(any(Context.class))).thenReturn(
                new Budget(500d, aCalendarOn(2015, 1, 1), aCalendarOn(2015, 1, 20)));

        FragmentManager fragmentManager = buildActivity(MainActivity.class)
                .create().start().resume().get().getSupportFragmentManager();

        BudgetDetailsFragment dialogFragment = new BudgetDetailsFragment();
        dialogFragment.setBudgetManager(budgetManager);
        dialogFragment.show(fragmentManager, "budgetDetails");

        fragmentManager.beginTransaction().commit();
        fragmentManager.executePendingTransactions();

        dialog = ShadowDialog.getLatestDialog();
    }

    @Test
    public void shouldShowBudgetAmountTextViewAndEditButton() {
        assertThat(dialog.findViewById(R.id.total_budget), notNullValue());
        assertThat(dialog.findViewById(R.id.edit_budget), notNullValue());
        assertThat(dialog.findViewById(R.id.edit_budget).getVisibility(), equalTo(View.VISIBLE));
        assertThat(dialog.findViewById(R.id.edit_ok).getVisibility(), equalTo(View.GONE));
    }

    @Test
    public void shouldShowBudgetStartAndEndDateTextViews() {
        assertThat(dialog.findViewById(R.id.initial_date), notNullValue());
        assertThat(dialog.findViewById(R.id.final_date), notNullValue());
    }

    @Test
    public void shouldShowBudgetPerDayTextView() {
        assertThat(dialog.findViewById(R.id.daily_budget), notNullValue());
    }

    @Test
    public void shouldShowTheOkButtonWhenEditIsClicked() {
        Button edit = (Button) dialog.findViewById(R.id.edit_budget);
        edit.callOnClick();

        assertThat(edit.getVisibility(), equalTo(View.GONE));
        assertThat(dialog.findViewById(R.id.edit_ok).getVisibility(), equalTo(View.VISIBLE));
    }

    @Test
    public void shouldUpdateTheBudgetAmount() {
        Button edit = (Button) dialog.findViewById(R.id.edit_budget);
        edit.callOnClick();
        EditText budgetEdit = (EditText) dialog.findViewById(R.id.budget_edittext);
        budgetEdit.setText("1000");
        View confirmEdit = dialog.findViewById(R.id.edit_ok);
        confirmEdit.callOnClick();

        verify(budgetManager).updateBudget(any(Context.class), Mockito.eq(1000d));

        assertThat(edit.getVisibility(), equalTo(View.VISIBLE));
        assertThat(confirmEdit.getVisibility(), equalTo(View.GONE));
    }

}
