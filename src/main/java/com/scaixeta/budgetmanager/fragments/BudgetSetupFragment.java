package com.scaixeta.budgetmanager.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.scaixeta.budgetmanager.Budget;
import com.scaixeta.budgetmanager.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class BudgetSetupFragment extends Fragment {

    private OnFragmentInteractionListener interactionListener;
    private Calendar initialDate = Calendar.getInstance();
    private Calendar finalDate = Calendar.getInstance();

    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Budget budget);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_budget_setup, container, false);

        TextView from = (TextView) view.findViewById(R.id.from_date_action_text);
        from.setText(getDateAsString(initialDate));
        from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(getDateSetListener((TextView) v, initialDate));
            }
        });

        TextView to = (TextView) view.findViewById(R.id.to_date_action_text);
        to.setText(getDateAsString(finalDate));
        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(getDateSetListener((TextView) v, finalDate));
            }
        });

        Button ok = (Button) view.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    interactionListener.onFragmentInteraction(new Budget(getEnteredIncome(), initialDate, finalDate));
                } catch (NumberFormatException exception) {
                    Toast.makeText(getActivity(), R.string.message_no_income_entered, Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

    private double getEnteredIncome() {
        TextView income = (TextView) getView().findViewById(R.id.income);
        return Double.valueOf(income.getText().toString());
    }

    private void showDatePickerDialog(DatePickerDialog.OnDateSetListener dateSetListener) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setOnDateSetListener(dateSetListener);

        datePickerFragment.show(getActivity().getSupportFragmentManager(), "datePickerDialog");
    }

    private DatePickerDialog.OnDateSetListener getDateSetListener(final TextView actionView, final Calendar calendar) {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth);
                actionView.setText(getDateAsString(calendar));
            }
        };
    }

    private String getDateAsString (Calendar date) {
        SimpleDateFormat fmt = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        fmt.setCalendar(date);
        return fmt.format(date.getTime());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) activity;
        } else {
            interactionListener = defaultOnFragmentInteractionListener();
        }
    }

    private OnFragmentInteractionListener defaultOnFragmentInteractionListener() {
        return new OnFragmentInteractionListener() {
            @Override
            public void onFragmentInteraction(Budget budget) {
            }
        };
    }

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener listener) {
        this.interactionListener = listener;
    }

}
