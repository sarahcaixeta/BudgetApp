package com.scaixeta.budgetmanager.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;

import java.util.List;

public class ExpenseListAdapter extends ArrayAdapter<Expense> {

    public ExpenseListAdapter(Context context, int resource, List<Expense> expenses) {
        super(context, resource, expenses);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(getContext())
                .inflate(R.layout.fragment_expense_list_item, parent, false);

        TextView name = (TextView) v.findViewById(R.id.expense_name);
        name.setText(getItem(position).getName());
        TextView price = (TextView) v.findViewById(R.id.expense_price);
        price.setText(String.valueOf(getItem(position).getPrice()));

        return v;
    }

}
