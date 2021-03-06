package com.scaixeta.budgetmanager.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import static com.scaixeta.budgetmanager.utils.DateUtils.parseISODateToDate;
import static com.scaixeta.budgetmanager.utils.DateUtils.parseToISOString;

public class ExpensesDatabase  extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "expenses-database";
    public static final int VERSION = 1;

    public static final String TABLE_EXPENSES = "expenses";
    public static final String EXPENSE_NAME = "name";
    public static final String EXPENSE_DATE = "date";
    public static final String EXPENSE_PRICE = "price";

    public static final String TABLE_BUDGETS = "budgets";
    public static final String BUDGET_AMOUNT = "amount";
    public static final String BUDGET_START = "start";
    public static final String BUDGET_END = "end";

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
        String createBudgetTable = "CREATE TABLE " + TABLE_BUDGETS + " ("
                + BUDGET_AMOUNT + " REAL, "
                + BUDGET_START + " TEXT, "
                + BUDGET_END + " TEXT )";
        db.execSQL(createBudgetTable);

        String createExpensesTable = "CREATE TABLE " + TABLE_EXPENSES + " ("

            + EXPENSE_NAME + " TEXT, "
            + EXPENSE_PRICE + " REAL, "
            + EXPENSE_DATE + " TEXT "
            + ")";
        db.execSQL(createExpensesTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }

    public void insertExpense(Expense expense) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EXPENSE_NAME, expense.getName());
        values.put(EXPENSE_PRICE, expense.getPrice());
        values.put(EXPENSE_DATE, parseToISOString(expense.getDate()));

        db.insert(TABLE_EXPENSES, null, values);
    }

    public void insertBudget(Budget budget) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUDGET_AMOUNT, budget.getValue());
        values.put(BUDGET_START, parseToISOString(budget.getInitialDate()));
        values.put(BUDGET_END, parseToISOString(budget.getFinalDate()));

        db.insert(TABLE_BUDGETS, null, values);
    }

    public void updateBudget(Budget budget, double value, LocalDate from, LocalDate to) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BUDGET_AMOUNT, value);

        db.update(TABLE_BUDGETS, values, BUDGET_AMOUNT+"="+budget.getValue(), null);
    }

    public Budget getBudget() {
        String selectQuery = "SELECT  * FROM " + TABLE_BUDGETS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Budget budget = null;
        if (cursor.moveToFirst()) {
            budget = new Budget(cursor.getDouble(0),
                    parseISODateToDate(cursor.getString(1)),
                    parseISODateToDate(cursor.getString(2)));
            budget.addExpense(getAll().toArray(new Expense[]{}));
        }

        return budget;
    }

    private List<Expense> getAll() {
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
