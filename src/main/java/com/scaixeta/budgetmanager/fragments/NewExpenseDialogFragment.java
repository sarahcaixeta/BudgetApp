package com.scaixeta.budgetmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;

public class NewExpenseDialogFragment extends DialogFragment {

    private NewExpenseListener newExpenseListener = new DefaultNewExpenseListener();

    public void setNewExpenseListener(NewExpenseListener newExpenseListener) {
        this.newExpenseListener = newExpenseListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(R.string.new_expense_dialog_title);
        View view = inflater.inflate(R.layout.fragment_new_expense_dialog, container, false);
        Button ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createExpense();
            }
        });
        return view;
    }

    private void createExpense() {
        String name = getValueFromInput(R.id.expense_name);
        String price = getValueFromInput(R.id.expense_price);
        if (!name.equals("") && !price.equals("")){
            newExpenseListener.onNewExpenseCreated(null);
        }
    }

    private String getValueFromInput(int id) {
        EditText name = (EditText) getView().findViewById(id);
        return name.getText().toString();
    }

    public interface NewExpenseListener {
        public void onNewExpenseCreated(Expense expense);
    }

    private class DefaultNewExpenseListener implements NewExpenseListener {

        @Override
        public void onNewExpenseCreated(Expense expense) {

        }
    }
}
