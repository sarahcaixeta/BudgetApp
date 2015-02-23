package com.scaixeta.budgetmanager.data;

import java.util.Calendar;

public class Budget {

    private Double value;
    private Calendar initialDate;
    private Calendar finalDate;

    public Budget(Double value, Calendar initialDate, Calendar finalDate) {
        this.value = value;
        this.initialDate = initialDate;
        this.finalDate = finalDate;
    }

    public Double getValue() {
        return value;
    }

    public Calendar getInitialDate() {
        return initialDate;
    }

    public Calendar getFinalDate() {
        return finalDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Budget)){
            return false;
        }
        Budget other = (Budget) o;
        return this.value.equals(other.value)
                && dateFieldsAreEqual(initialDate, other.initialDate)
                && dateFieldsAreEqual(finalDate, other.finalDate);
    }

    private boolean dateFieldsAreEqual(Calendar initialDate, Calendar other) {
        return initialDate.get(Calendar.YEAR) == other.get(Calendar.YEAR)
                && initialDate.get(Calendar.MONTH) == other.get(Calendar.MONTH)
                && initialDate.get(Calendar.DAY_OF_MONTH) == other.get(Calendar.DAY_OF_MONTH);
    }
}
