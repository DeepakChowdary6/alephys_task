package model;

import java.time.LocalDate;

public interface Transaction {
    double getAmount();
    String getCategory();
    LocalDate getDate();
    String getType(); // "INCOME" or "EXPENSE"
}
