package com.scaixeta.budgetmanager;

import android.view.View;
import android.widget.TextView;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
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
    public void shouldDivideTheIncomeBy30(){
        TextView income = (TextView) activity.findViewById(R.id.income);
        income.setText("3000");
        activity.findViewById(R.id.calculate).callOnClick();
        TextView result = (TextView) activity.findViewById(R.id.result);
        assertThat(result.getText().toString(), equalTo("100.0"));
    }
}