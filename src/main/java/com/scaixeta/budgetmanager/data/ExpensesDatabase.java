package com.scaixeta.budgetmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scaixeta.budgetmanager.utils.DateUtils;

public class ExpensesDatabase  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "expenses-database";
    public static final int VERSION = 1;
    public static final String TABLE_EXPENSES = "expenses";
    public static final String EXPENSE_NAME = "expense_name";
    public static final String EXPENSE_DATE = "expense_date";
    public static final String EXPENSE_PRICE = "expense_price";

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
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_EXPENSES + "("
            + EXPENSE_NAME + " TEXT, "
            + EXPENSE_PRICE + " REAL, "
            + EXPENSE_DATE + " TEXT "
            + ")";
        db.execSQL(CREATE_NOTES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }

    public void insert(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EXPENSE_NAME, expense.getName());
        values.put(EXPENSE_PRICE, expense.getPrice());
        values.put(EXPENSE_DATE, DateUtils.parseToISOString(expense.getDate()));

        db.insert(TABLE_EXPENSES, null, values);
    }
}
