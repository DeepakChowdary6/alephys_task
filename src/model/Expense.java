package model;

import java.time.LocalDate;

public class Expense implements Transaction {
    private final double amount;
    private final String category;
    private final LocalDate date;

    public Expense(double amount, String category, LocalDate date) {
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    @Override
    public String getType() {
        return "EXPENSE";
    }
    @Override
    public String toString() {
        return "Expense, "+String.format("%s, %.2f on %s", category, amount, date);
    }
}