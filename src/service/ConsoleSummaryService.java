package service;

import model.Transaction;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

public class ConsoleSummaryService implements SummaryService {
    @Override
    public void showMonthlySummary(List<Transaction> transactions) {
        Map<YearMonth, List<Transaction>> groupedByMonth = transactions.stream()
                .collect(Collectors.groupingBy(t -> YearMonth.from(t.getDate())));

        for (YearMonth month : groupedByMonth.keySet().stream().sorted().collect(Collectors.toList())) {
            List<Transaction> monthTransactions = groupedByMonth.get(month);
            double income = monthTransactions.stream()
                    .filter(t -> t.getType().equals("INCOME"))
                    .mapToDouble(Transaction::getAmount).sum();

            double expense = monthTransactions.stream()
                    .filter(t -> t.getType().equals("EXPENSE"))
                    .mapToDouble(Transaction::getAmount).sum();

            System.out.println("Summary for " + month + ":");
            System.out.println("  Total Income: " + income);
            System.out.println("  Total Expense: " + expense);
            System.out.println("  Net Savings: " + (income - expense));
            System.out.println();
        }
    }
}