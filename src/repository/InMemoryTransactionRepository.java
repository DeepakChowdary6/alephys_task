package repository;

import model.Expense;
import model.Income;
import model.Transaction;

import model.Transaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTransactionRepository implements TransactionRepository {
   // private final List<Transaction> transactions = new ArrayList<>();

    private final String filePath = "transactions.txt";
    @Override
    public void addTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(transaction.getType() + "," +
                    transaction.getCategory() + "," +
                    transaction.getAmount() + "," +
                    transaction.getDate());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) return transactions;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;

                String type = parts[0].trim();
                String category = parts[1].trim();
                double amount = Double.parseDouble(parts[2].trim());
                LocalDate date = LocalDate.parse(parts[3].trim());

                Transaction transaction = switch (type.toLowerCase()) {
                    case "income" -> new Income(amount, category, date);
                    case "expense" -> new Expense(amount, category, date);
                    default -> null;
                };

                if (transaction != null) transactions.add(transaction);
            }
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }
}
