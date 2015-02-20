package com.scaixeta.budgetmanager.fragments;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.DatePicker;

import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDatePickerDialog;
import org.robolectric.shadows.ShadowDialog;

import java.util.Calendar;

import static android.content.DialogInterface.BUTTON_POSITIVE;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.robolectric.Robolectric.buildActivity;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class DatePickerFragmentTest {

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Before
    public void setUp(){
        onDateSetListener = mock(DatePickerDialog.OnDateSetListener.class);
    }

    @Test
    public void shouldOpenWithTodayDateSet() {
        final Calendar calendar = Calendar.getInstance();
        int expectedYear = calendar.get(Calendar.YEAR);
        int expectedMonth = calendar.get(Calendar.MONTH);
        int expectedDay = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = getDatePickerDialog();

        assertThat(dialog, is(not(nullValue())));
        assertThat(dialog.getDatePicker().getDayOfMonth(), is(expectedDay));
        assertThat(dialog.getDatePicker().getMonth(), is(expectedMonth));
        assertThat(dialog.getDatePicker().getYear(), is(expectedYear));
    }

    @Test
    public void shouldReturnTheDateSet() {
        DatePickerDialog dialog = getDatePickerDialog();

        DatePicker datePicker = dialog.getDatePicker();
        dialog.onDateChanged(datePicker, 2015, 03, 15);
        dialog.onClick(dialog, BUTTON_POSITIVE);

        Mockito.verify(onDateSetListener).onDateSet(datePicker, 2015, 03, 15);
    }

    private DatePickerDialog getDatePickerDialog() {
        MainActivity activity = buildActivity(MainActivity.class)
                .create().start().resume().get();

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setOnDateSetListener(onDateSetListener);
        datePickerFragment.show(fragmentManager, "datePicker");

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

        return (DatePickerDialog) ShadowDatePickerDialog.getLatestDialog();
    }


}
