package com.scaixeta.budgetmanager.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpensesDatabase  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "expenses-database";
    public static final int VERSION = 1;
    private static ExpensesDatabase instance;

    public ExpensesDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public static ExpensesDatabase getInstance(Context context){
        if (instance == null){
            instance = new ExpensesDatabase(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
