package com.scaixeta.budgetmanager.fragments;

import android.app.Dialog;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;

import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.buildActivity;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class NewExpenseDialogFragmentTest {

    private Dialog dialog;
    private NewExpenseDialogFragment.NewExpenseListener listener;

    @Before
    public void setup() {
        listener = Mockito.mock(NewExpenseDialogFragment.NewExpenseListener.class);

        FragmentManager fragmentManager = buildActivity(MainActivity.class)
                .create().start().resume().get().getSupportFragmentManager();

        NewExpenseDialogFragment dialogFragment = new NewExpenseDialogFragment();
        dialogFragment.setNewExpenseListener(listener);
        dialogFragment.show(fragmentManager, "newExpense");

        fragmentManager.beginTransaction().commit();
        fragmentManager.executePendingTransactions();

        dialog = ShadowDialog.getLatestDialog();
    }

    @Test
    public void shouldShowNameAndPriceInputsAndOkButton() {
        assertThat(dialog.findViewById(R.id.expense_name), notNullValue());
        assertThat(dialog.findViewById(R.id.expense_price), notNullValue());
        assertThat(dialog.findViewById(R.id.ok), notNullValue());
    }

    @Test
    public void shouldNotCreateAnExpenseWhenNameAndPriceAreEmpty() {
        dialog.findViewById(R.id.ok).callOnClick();

        verify(listener, never()).onNewExpenseCreated(anyString(), anyDouble());
    }

    @Test
    public void shouldNotCreateAnExpenseWhenPriceIsEmpty() {
        EditText name = (EditText) dialog.findViewById(R.id.expense_name);
        name.setText("name");
        dialog.findViewById(R.id.ok).callOnClick();

        verify(listener, never()).onNewExpenseCreated(anyString(), anyDouble());
    }

    @Test
    public void shouldNotCreateAnExpenseWhenNameIsEmpty() {
        EditText price = (EditText) dialog.findViewById(R.id.expense_price);
        price.setText("10");
        dialog.findViewById(R.id.ok).callOnClick();

        verify(listener, never()).onNewExpenseCreated(anyString(), anyDouble());
    }

    @Test
    public void shouldCreateAnExpenseWithNameAndPrice() {
        EditText name = (EditText) dialog.findViewById(R.id.expense_name);
        name.setText("name");
        EditText price = (EditText) dialog.findViewById(R.id.expense_price);
        price.setText("10");
        dialog.findViewById(R.id.ok).callOnClick();

        verify(listener).onNewExpenseCreated("name", 10d);
    }
}
