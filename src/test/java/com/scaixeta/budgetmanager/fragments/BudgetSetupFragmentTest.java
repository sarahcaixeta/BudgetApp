package com.scaixeta.budgetmanager.fragments;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.fragments.BudgetSetupFragment;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.util.FragmentTestUtil;

import static org.hamcrest.Matchers.notNullValue;
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
        assertThat(fragment.getView().findViewById(R.id.calculate), notNullValue());
    }
}
