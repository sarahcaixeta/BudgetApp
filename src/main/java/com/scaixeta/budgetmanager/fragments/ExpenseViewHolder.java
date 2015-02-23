package com.scaixeta.budgetmanager.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpenseViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout view;

    public ExpenseViewHolder(View itemView) {
        super(itemView);
        this.view = (LinearLayout) itemView;
    }

    public TextView getTextView(int resId){
        return (TextView) view.findViewById(resId);
    }

}
