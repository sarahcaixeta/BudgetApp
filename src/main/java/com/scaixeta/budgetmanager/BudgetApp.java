package com.scaixeta.budgetmanager;

import android.app.Application;

import dagger.ObjectGraph;

public class BudgetApp extends Application {

    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        mObjectGraph =  ObjectGraph.create(getModules());
    }

    public Object[] getModules() {
        return Modules.list();
    }

    public void inject(Object o) {
        mObjectGraph.inject(o);
    }
}
