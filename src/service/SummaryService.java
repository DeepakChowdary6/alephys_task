package service;

import model.Transaction;

import java.util.List;

public interface SummaryService {
    void showMonthlySummary(List<Transaction> transactions);
}