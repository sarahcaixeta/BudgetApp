package com.scaixeta.budgetmanager.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scaixeta.budgetmanager.BudgetCalculator;
import com.scaixeta.budgetmanager.R;

import java.util.Calendar;


public class BudgetSetupFragment extends Fragment {

    private OnFragmentInteractionListener interactionListener;

    public BudgetSetupFragment() {
        // Required empty public constructor
    }

    public static BudgetSetupFragment newInstance() {
        BudgetSetupFragment fragment = new BudgetSetupFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_budget_setup, container, false);

        View from = view.findViewById(R.id.from_date_action_text);
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("initialDateDialog");
            }
        });

        View to = view.findViewById(R.id.to_date_action_text);
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog("finalDateDialog");
            }
        });

        return view;
    }

    private void showDatePickerDialog(String tag) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        DatePickerFragment newFragment = new DatePickerFragment();

        newFragment.show(fragmentManager, tag);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) activity;
        } else {
            interactionListener = defaultOnFragmentInterationListener();
        }
    }

    private OnFragmentInteractionListener defaultOnFragmentInterationListener() {
        return new OnFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction(double income, Calendar initialDate, Calendar finalDate) {
            }
        };
    }

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(double income, Calendar initialDate, Calendar finalDate);
    }
}
