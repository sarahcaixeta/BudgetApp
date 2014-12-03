package com.scaixeta.budgetmanager;

public final class Modules {
    public static Object[] list() {
        return new Object[]{
                new MainModule()
        };
    }

    private Modules() {
    }
}
