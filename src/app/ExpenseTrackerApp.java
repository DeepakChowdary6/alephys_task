package app;

import model.Expense;
import model.Income;
import model.Transaction;
import repository.TransactionRepository;
import service.SummaryService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ExpenseTrackerApp {
    private final TransactionRepository repository;
    private final SummaryService summaryService;
    private final Scanner scanner = new Scanner(System.in);

    public ExpenseTrackerApp(TransactionRepository repository, SummaryService summaryService) {
        this.repository = repository;
        this.summaryService = summaryService;
    }

    public void run() {
        while (true) {
            System.out.println("Enter command:\nadd\nview\ndisplay\nexit");
            String cmd = scanner.nextLine().trim();
            switch (cmd.toLowerCase()) {
                case "add":
                    handleAdd();
                    break;
                case "view":
                    viewTransactions();
                    break;
                case "display":
                    summaryService.showMonthlySummary(repository.getAllTransactions());
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    private void handleAdd() {
        System.out.println("Enter type:\nIncome\nExpense");
        String type = scanner.nextLine().trim().toLowerCase();

        String categoryPrompt = type.equals("income") ? "Enter category (salary/business):" : "Enter category (food/rent/travel):";
        System.out.println(categoryPrompt);
        String category = scanner.nextLine().trim();

        System.out.println("Enter amount:");
        double amount = Double.parseDouble(scanner.nextLine().trim());

        Transaction transaction = createTransaction(type, category, amount, LocalDate.now());
        if (transaction != null) {
            repository.addTransaction(transaction);
            System.out.println("Transaction added successfully: " + transaction);
        }
    }

    private void viewTransactions() {
        List<Transaction> all = repository.getAllTransactions();
        if (all.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            all.forEach(System.out::println);
        }
    }

    private Transaction createTransaction(String type, String category, double amount, LocalDate date) {
        return switch (type.toLowerCase()) {
            case "income" -> new Income(amount, category, date);
            case "expense" -> new Expense(amount, category, date);
            default -> {
                System.out.println("Invalid transaction type");
                yield null;
            }
        };
    }
}