package com.scaixeta.budgetmanager.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.scaixeta.budgetmanager.BudgetCalculator;
import com.scaixeta.budgetmanager.R;


public class BudgetSetupFragment extends Fragment {

    private static BudgetCalculator budgetCalculator;
    private OnFragmentInteractionListener interactionListener;

    public BudgetSetupFragment() {
        // Required empty public constructor
    }

    public static BudgetSetupFragment newInstance(BudgetCalculator budgetCalculator) {
        BudgetSetupFragment.budgetCalculator = budgetCalculator;
        BudgetSetupFragment fragment = new BudgetSetupFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_budget_setup, container, false);

        final TextView income = (TextView) view.findViewById(R.id.income);

        View to = view.findViewById(R.id.to_date_action_text);
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                DatePickerFragment newFragment = new DatePickerFragment();

                newFragment.show(fragmentManager, "finalDateDialog");
            }
        });

        View from = view.findViewById(R.id.from_date_action_text);
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                DatePickerFragment newFragment = new DatePickerFragment();

                newFragment.show(fragmentManager, "finalDateDialog");
            }
        });

        Button calculate = (Button) view.findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interactionListener.onFragmentInteraction(calculate(income));
            }
        });

        return view;
    }

    private double calculate(TextView income) {
        String enteredValue = income.getText().toString();
        if (enteredValue.length() != 0) {
            Integer incomeInt = Integer.valueOf(income.getText().toString());
            return budgetCalculator.calculateDailyBudget(incomeInt, 30);
        }
        return 0;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) activity;
        } else {
            interactionListener = new OnFragmentInteractionListener() {
                @Override
                public void onFragmentInteraction(double result) { }
            };
        }
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(double result);
    }
}
