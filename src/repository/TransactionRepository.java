package repository;

import model.Transaction;
import java.util.List;

public interface TransactionRepository {
    void addTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}

