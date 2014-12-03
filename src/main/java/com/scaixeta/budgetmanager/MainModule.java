package com.scaixeta.budgetmanager;

import dagger.Module;
import dagger.Provides;

@Module(injects = MainActivity.class)
public class MainModule {

    @Provides
    BudgetCalculator budgetCalculator() {
        return new BudgetCalculator();
    }


}
