package com.scaixeta.budgetmanager.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.data.Expense;

import java.util.List;

public class ExpenseListAdapter extends RecyclerView.Adapter {

    private List<Expense> expenses;

    public ExpenseListAdapter(List<Expense> expenses){
        this.expenses = expenses;
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.fragment_expense_list_item, viewGroup, false);

        return new ExpenseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ExpenseViewHolder expenseViewHolder = (ExpenseViewHolder) viewHolder;
        expenseViewHolder.getTextView(R.id.expense_name).setText(expenses.get(position).getName());
        expenseViewHolder.getTextView(R.id.expense_price).setText(String.valueOf(expenses.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }
}
