package com.scaixeta.budgetmanager.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;
import com.scaixeta.budgetmanager.utils.DateUtils;

public class ExpenseListAdapter extends ArrayAdapter<Expense> {

    private static int RESOURCE = R.layout.fragment_expense_list_item;

    public ExpenseListAdapter(Context context) {
        super(context, RESOURCE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = LayoutInflater.from(getContext())
                .inflate(RESOURCE, parent, false);

        TextView name = (TextView) v.findViewById(R.id.expense_name);
        name.setText(getItem(position).getName());
        TextView price = (TextView) v.findViewById(R.id.expense_price);
        price.setText(String.valueOf(getItem(position).getPrice()));
        TextView date = (TextView) v.findViewById(R.id.expense_date);
        date.setText(DateUtils.parseCalendarToString(getItem(position).getDate()));



        return v;
    }

}
