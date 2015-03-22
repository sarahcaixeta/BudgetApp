package com.scaixeta.budgetmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scaixeta.budgetmanager.BudgetCalculator;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.ExpensesDatabase;

import static com.scaixeta.budgetmanager.utils.DateUtils.parseCalendarToString;
import static java.lang.Double.valueOf;

public class BudgetDetailsFragment extends DialogFragment {

    private BudgetCalculator calculator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(R.string.budget_details);

        final View view = inflater.inflate(R.layout.fragment_budget_details, container, false);
        calculator = new BudgetCalculator();
        final Budget budget = ExpensesDatabase.getInstance(getActivity()).getBudget();

        final EditText editBudget = (EditText) view.findViewById(R.id.budget_edittext);
        editBudget.setText(String.valueOf(budget.getValue()));
        final TextView budgetAmount = (TextView) view.findViewById(R.id.total_budget);
        budgetAmount.setText(getResources().getString(R.string.price, budget.getValue()));
        TextView initialDate = (TextView) view.findViewById(R.id.initial_date);
        initialDate.setText(getResources().getString(R.string.from_date,
                parseCalendarToString(budget.getInitialDate())));
        TextView finalDate = (TextView) view.findViewById(R.id.final_date);
        finalDate.setText(getResources().getString(R.string.to_date,
                parseCalendarToString(budget.getFinalDate())));
        TextView dailyBudget = (TextView) view.findViewById(R.id.daily_budget);
        dailyBudget.setText(getResources().getString(R.string.price, calculator.calculateDailyBudget(budget)));

        final Button ok = (Button) view.findViewById(R.id.edit_ok);

        final Button edit = (Button) view.findViewById(R.id.edit_budget);
        edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toggleEditFieldsVisibility(edit, ok, editBudget, budgetAmount);
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpensesDatabase.getInstance(getActivity()).updateBudget(budget, valueOf(editBudget.getText().toString()));
                toggleEditFieldsVisibility(edit, ok, editBudget, budgetAmount);
            }
        });

        return view;
    }

    private void toggleEditFieldsVisibility(Button edit, Button ok, EditText editBudget, TextView budgetAmount) {
        int editVisibility = edit.getVisibility();
        int okVisibility = ok.getVisibility();
        ok.setVisibility(editVisibility);
        editBudget.setVisibility(editVisibility);
        edit.setVisibility(okVisibility);
        budgetAmount.setVisibility(okVisibility);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
