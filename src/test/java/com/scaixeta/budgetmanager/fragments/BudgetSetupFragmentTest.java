package com.scaixeta.budgetmanager.fragments;

import android.app.DatePickerDialog;
import android.widget.EditText;
import android.widget.TextView;

import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDatePickerDialog;
import org.robolectric.util.FragmentTestUtil;

import java.util.Calendar;

import static com.scaixeta.budgetmanager.utils.TestDateUtils.aCalendarOn;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class BudgetSetupFragmentTest {

    private BudgetSetupFragment fragment;
    private BudgetSetupFragment.OnFragmentInteractionListener listener;

    @Before
    public void setUp() {
        listener = mock(BudgetSetupFragment.OnFragmentInteractionListener.class);
        fragment = new BudgetSetupFragment();
        FragmentTestUtil.startFragment(fragment);
        fragment.setOnFragmentInteractionListener(listener);
    }

    @Test
    public void shouldContainIncomeEditText(){
        assertThat(fragment.getView().findViewById(R.id.income), notNullValue());
        assertThat(fragment.getView().findViewById(R.id.to_date_action_text), notNullValue());
        assertThat(fragment.getView().findViewById(R.id.from_date_action_text), notNullValue());
    }

    @Test
    public void shouldOpenADatePickerDialogWhenTheTextViewToIsClicked() {
        fragment.getView().findViewById(R.id.to_date_action_text).callOnClick();

        DatePickerDialog dialog = (DatePickerDialog) ShadowDatePickerDialog.getLatestDialog();
        assertNotNull(dialog);
        assertDialogShowsTodaysDate(dialog, Calendar.getInstance());
    }

    @Test
    public void shouldOpenADatePickerDialogWhenTheTextViewFromIsClicked() {
        fragment.getView().findViewById(R.id.from_date_action_text).callOnClick();

        DatePickerDialog dialog = (DatePickerDialog) ShadowDatePickerDialog.getLatestDialog();
        assertNotNull(dialog);
        assertDialogShowsTodaysDate(dialog, Calendar.getInstance());
    }

    @Test
    public void shouldChangeIncomeValueWhenADifferentValueIsSet() {
        EditText income = (EditText) fragment.getView().findViewById(R.id.income);
        income.setText("1000");

        setDateOnDialog(R.id.from_date_action_text, 2015, 9, 30);
        setDateOnDialog(R.id.to_date_action_text, 2015, 10, 15);

        fragment.getView().findViewById(R.id.ok).callOnClick();
        verify(listener).onFragmentInteraction(eq(new Budget(1000d, aCalendarOn(2015, 9, 30), aCalendarOn(2015, 10, 15))));
    }

    @Test
    public void shouldSetTheTextOfTheViewWhenTheInitialDateIsSelected() {
        setDateOnDialog(R.id.from_date_action_text, 2015, 9, 30);

        TextView view = (TextView) fragment.getView().findViewById(R.id.from_date_action_text);
        assertThat(view.getText().toString(), Matchers.equalTo("Friday, Oct 30, 2015"));

    }

    @Test
    public void shouldSetTheTextOfTheViewWhenTheFinalDateIsSelected() {
        setDateOnDialog(R.id.to_date_action_text, 2015, 10, 15);

        TextView view = (TextView) fragment.getView().findViewById(R.id.to_date_action_text);
        assertThat(view.getText().toString(), Matchers.equalTo("Sunday, Nov 15, 2015"));

    }

    @Test
    public void shouldNotFinishIfTheIncomeIsEmpty() {
        EditText income = (EditText) fragment.getView().findViewById(R.id.income);
        income.setText("");

        fragment.getView().findViewById(R.id.ok).callOnClick();
        verify(listener, never()).onFragmentInteraction(any(Budget.class));
    }

    @Test
    public void shouldNotFinishIfTheFinalDateIsBeforeTheInitialDate() {
        EditText income = (EditText) fragment.getView().findViewById(R.id.income);
        income.setText("0");

        setDateOnDialog(R.id.from_date_action_text, 2015, 11, 30);
        setDateOnDialog(R.id.to_date_action_text, 2015, 10, 15);

        fragment.getView().findViewById(R.id.ok).callOnClick();
        verify(listener, never()).onFragmentInteraction(any(Budget.class));
    }

    @Test
    public void shouldAcceptABudgetOfOneDay() {
        EditText income = (EditText) fragment.getView().findViewById(R.id.income);
        income.setText("0");

        setDateOnDialog(R.id.from_date_action_text, 2015, 11, 30);
        setDateOnDialog(R.id.to_date_action_text, 2015, 11, 30);

        fragment.getView().findViewById(R.id.ok).callOnClick();
        verify(listener).onFragmentInteraction(any(Budget.class));
    }

    private void setDateOnDialog(int actionField, int year, int month, int day) {
        fragment.getView().findViewById(actionField).callOnClick();
        DatePickerDialog finalDateDialog = (DatePickerDialog) ShadowDatePickerDialog.getLatestDialog();
        finalDateDialog.onDateChanged(finalDateDialog.getDatePicker(), year, month, day);
        finalDateDialog.onClick(finalDateDialog, DatePickerDialog.BUTTON_POSITIVE);
    }

    private void assertDialogShowsTodaysDate(DatePickerDialog dialog, Calendar calendar) {
        assertThat(dialog.getDatePicker().getYear(), equalTo(calendar.get(Calendar.YEAR)));
        assertThat(dialog.getDatePicker().getMonth(), equalTo(calendar.get(Calendar.MONTH)));
        assertThat(dialog.getDatePicker().getDayOfMonth(), equalTo(calendar.get(Calendar.DAY_OF_MONTH)));
    }

}
