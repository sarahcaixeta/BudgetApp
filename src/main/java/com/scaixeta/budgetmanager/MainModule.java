package com.scaixeta.budgetmanager;

import dagger.Module;
import dagger.Provides;

@Module(injects = MainActivity.class, library = true)
public class MainModule {

    @Provides
    BudgetCalculator budgetCalculator() {
        return new BudgetCalculator();
    }

    @Provides
    BudgetManager budgetManager() {
        return new BudgetManager(budgetCalculator());
    }

}
