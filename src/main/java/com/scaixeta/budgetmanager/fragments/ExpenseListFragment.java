package com.scaixeta.budgetmanager.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ExpenseListFragment extends Fragment {

    public static ExpenseListFragment newInstance() {
        return new ExpenseListFragment();
    }

    public ExpenseListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense_list, container);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.expenses_recycler_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        List<Expense> expenseList = new ArrayList<>();
        expenseList.add(new Expense("food", 20d, Calendar.getInstance()));

        ExpenseListAdapter adapter = new ExpenseListAdapter(expenseList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
