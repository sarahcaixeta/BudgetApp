package com.scaixeta.budgetmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.scaixeta.budgetmanager.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import static com.scaixeta.budgetmanager.utils.DateUtils.parseISODateToDate;

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

    public List<Expense> getAll() {
        List<Expense> expenses = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_EXPENSES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                expenses.add(assembleExpense(cursor));
            } while (cursor.moveToNext());
        }

        return expenses;
    }

    private Expense assembleExpense(Cursor cursor) {
        return new Expense(cursor.getString(0),
                cursor.getDouble(1),
                parseISODateToDate(cursor.getString(2)));
    }
}
