package com.scaixeta.budgetmanager.fragments;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.scaixeta.budgetmanager.BudgetManager;
import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.robolectric.Robolectric.buildActivity;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class FloatingActionButtonFragmentTest {

    private FloatingActionButtonBasicFragment fragment;
    private BudgetManager budgetManager;

    @Before
    public void setUp() {
        fragment = new FloatingActionButtonBasicFragment();
        budgetManager = Mockito.mock(BudgetManager.class);
        MainActivity activity = buildActivity(MainActivity.class).create().start().resume().get();
        activity.setBudgetManager(budgetManager);

        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(fragment, null);
        fragmentTransaction.commit();

    }

    @Test
    public void shouldShowAFloatingButton() {
        FrameLayout fab = (FrameLayout) fragment.getView().findViewById(R.id.fab);
        assertThat(fab, notNullValue());
    }

    @Ignore
    @Test
    public void shouldOpenADialogWhenTheButtonIsClicked() {
        fragment.getView().findViewById(R.id.fab).callOnClick();
        when(budgetManager.getBudget(any(Context.class))).thenReturn(new Budget(0d, null, null));

        Dialog dialog = ShadowDialog.getLatestDialog();
        assertThat(dialog, notNullValue());
    }
}
