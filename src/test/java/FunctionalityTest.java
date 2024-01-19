import com.example.healthy_wallet.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionalityTest {
    @Test
    public void testCategoryPercentageCalculation() {
        String str1 = "testcategory";
        String str2 = "testcategory2";
        ExpenseCategory expenseCategory = new ExpenseCategory(str1);
        ExpenseCategory expenseCategory2 = new ExpenseCategory(str2);

        Map<String, Double> percentages = ExpenseCategory.getPercentagesOfAllCategories();

        assertEquals(50.0, percentages.get("testcategory"));
        assertEquals(50.0, percentages.get("testcategory2"));
    }

    @Test
    public void testAccountBalance() {
        Account account = new Account();

        Income income = new Income(10.0, LocalDate.parse("2023-01-30"), "testdesc", "testcategory");
        Expense expense = new Expense(10.0, LocalDate.parse("2023-01-30"), "testdesc", "testcategory", TransactionPriority.HIGH);

        account.addTransaction(income);
        account.addTransaction(expense);

        assertEquals(0.0, account.getBalance());
    }
}
