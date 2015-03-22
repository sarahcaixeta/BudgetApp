package com.scaixeta.budgetmanager.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.scaixeta.budgetmanager.BudgetManager;
import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Budget;

import static com.scaixeta.budgetmanager.utils.DateUtils.parseCalendarToString;

public class BudgetDetailsFragment extends DialogFragment {

    private BudgetManager budgetManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getDialog().setTitle(R.string.budget_details);

        View view = inflater.inflate(R.layout.fragment_budget_details, container, false);
        populateBudgetView(view, budgetManager.getBudget(getActivity()));

        Button edit = (Button) view.findViewById(R.id.edit_budget);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditFields();
            }
        });

        Button ok = (Button) view.findViewById(R.id.edit_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                budgetManager.updateBudget(getActivity(), getNewBudgetValue());
                populateBudgetView(getView(), budgetManager.getBudget(getActivity()));
            }
        });

        return view;
    }

    private Double getNewBudgetValue() {
        EditText budgetValueEditText = (EditText) getView().findViewById(R.id.budget_edittext);
        return Double.valueOf(budgetValueEditText.getText().toString());
    }

    private void populateBudgetView(View view, Budget budget) {
        EditText budgetEditText = (EditText) view.findViewById(R.id.budget_edittext);
        budgetEditText.setVisibility(View.GONE);
        budgetEditText.setText(String.valueOf(budget.getValue()));

        TextView budgetView = (TextView) view.findViewById(R.id.total_budget);
        budgetView.setVisibility(View.VISIBLE);
        budgetView.setText(getResources().getString(R.string.price, budget.getValue()));

        TextView budgetInitialDate = (TextView) view.findViewById(R.id.initial_date);
        budgetInitialDate.setText(getResources().getString(R.string.from_date,
                parseCalendarToString(budget.getInitialDate())));

        TextView budgetFinalDate = (TextView) view.findViewById(R.id.final_date);
        budgetFinalDate.setText(getResources().getString(R.string.to_date,
                parseCalendarToString(budget.getFinalDate())));

        TextView dailyBudget = (TextView) view.findViewById(R.id.daily_budget);
        dailyBudget.setText(getResources().getString(R.string.price, budgetManager.dailyBudget()));

        view.findViewById(R.id.edit_budget).setVisibility(View.VISIBLE);
        view.findViewById(R.id.edit_ok).setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof MainActivity){
            this.budgetManager = ((MainActivity) activity).getBudgetManager();
        }
    }

    private void showEditFields() {
        getView().findViewById(R.id.edit_ok).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.budget_edittext).setVisibility(View.VISIBLE);
        getView().findViewById(R.id.edit_budget).setVisibility(View.GONE);
        getView().findViewById(R.id.total_budget).setVisibility(View.GONE);
    }

}
