package com.scaixeta.budgetmanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;

public class ExpenseListFragment extends Fragment {

    private ExpenseListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense_list, container);

        adapter = new ExpenseListAdapter(getActivity());

        ListView listView = (ListView) view.findViewById(R.id.expenses_list_view);
        listView.setAdapter(adapter);

        return view;
    }

    public void addExpense(Expense expense) {
        adapter.add(expense);
    }
}
