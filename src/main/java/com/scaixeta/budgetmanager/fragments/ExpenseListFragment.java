package com.scaixeta.budgetmanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpenseListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense_list, container);

        ListView listView = (ListView) view.findViewById(R.id.expenses_recycler_view);

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense("food", 20d, Calendar.getInstance()));

        ExpenseListAdapter adapter = new ExpenseListAdapter(getActivity(),
                R.layout.fragment_expense_list_item, expenseList);
        listView.setAdapter(adapter);

        return view;
    }
}
