import service.*;
import model.*;
import repository.*;
import app.*;
public class Main {
    public static void main(String[] args) {

        TransactionRepository repository = new InMemoryTransactionRepository();
        SummaryService summaryService = new ConsoleSummaryService();
        new ExpenseTrackerApp(repository, summaryService).run();

    }
}