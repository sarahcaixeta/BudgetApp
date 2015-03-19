package com.scaixeta.budgetmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scaixeta.budgetmanager.BudgetCalculator;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Budget;
import com.scaixeta.budgetmanager.data.ExpensesDatabase;

import static com.scaixeta.budgetmanager.utils.DateUtils.parseCalendarToString;

public class BudgetDetailsFragment extends DialogFragment {

    private BudgetCalculator calculator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(R.string.budget_details);

        View view = inflater.inflate(R.layout.fragment_budget_details, container, false);
        calculator = new BudgetCalculator();
        Budget budget = ExpensesDatabase.getInstance(getActivity()).getBudget();

        TextView budgetAmount = (TextView) view.findViewById(R.id.total_budget);
        budgetAmount.setText(getResources().getString(R.string.budget_amount, budget.getValue()));
        TextView initialDate = (TextView) view.findViewById(R.id.initial_date);
        initialDate.setText(getResources().getString(R.string.from_date, parseCalendarToString(budget.getInitialDate())));
        TextView finalDate = (TextView) view.findViewById(R.id.final_date);
        finalDate.setText(getResources().getString(R.string.to_date, parseCalendarToString(budget.getFinalDate())));
        TextView dailyBudget = (TextView) view.findViewById(R.id.daily_budget);
        dailyBudget.setText(getResources().getString(R.string.price, calculator.calculateDailyBudget(budget)));

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

}
