package com.scaixeta.budgetmanager.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDatePickerDialog;
import org.robolectric.util.FragmentTestUtil;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class BudgetSetupFragmentTest {

    private BudgetSetupFragment fragment;

    @Before
    public void setUp() {
        fragment = new BudgetSetupFragment();
        FragmentTestUtil.startFragment(fragment);
    }

    @Test
    public void shouldContainIncomeEditText(){
        assertThat(fragment.getView().findViewById(R.id.income), notNullValue());
        assertThat(fragment.getView().findViewById(R.id.to_date_action_text), notNullValue());
        assertThat(fragment.getView().findViewById(R.id.from_date_action_text), notNullValue());
        assertThat(fragment.getView().findViewById(R.id.calculate), notNullValue());
    }

    @Test
    public void shouldOpenADatePickerDialogWhenTheTextViewToIsClicked() {
        fragment.getView().findViewById(R.id.to_date_action_text).callOnClick();

        DatePickerDialog dialog = (DatePickerDialog) ShadowDatePickerDialog.getLatestDialog();
        assertNotNull(dialog);
        assertThat(dialog.getDatePicker().getYear(), equalTo(2015));
    }

}
