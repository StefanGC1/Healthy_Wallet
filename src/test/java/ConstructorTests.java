import com.example.healthy_wallet.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstructorTests {

    @Test
    public void testIncomeConstructor() {
        Income income = new Income(10.0, LocalDate.parse("2023-01-30"), "testdesc", "testcategory");
        assertEquals("Income", income.getType());
        assertEquals(10.0, income.getAmount());
        assertEquals(LocalDate.parse("2023-01-30"), income.getDate());
        assertEquals("testdesc", income.getDescription());
        assertEquals("testcategory", income.getCategory());
        assertEquals(TransactionPriority.NONE, income.getPriority());
    }

    @Test
    public void testExpenseConstructor() {
        Expense expense = new Expense(10.0, LocalDate.parse("2023-01-30"), "testdesc", "testcategory", TransactionPriority.HIGH);
        assertEquals("Expense", expense.getType());
        assertEquals(10.0, expense.getAmount());
        assertEquals(LocalDate.parse("2023-01-30"), expense.getDate());
        assertEquals("testdesc", expense.getDescription());
        assertEquals("testcategory", expense.getCategory());
        assertEquals(TransactionPriority.HIGH, expense.getPriority());
    }

    @Test
    public void testCategoryConstructor() {
        ExpenseCategory expenseCategory = new ExpenseCategory("asd");
        ExpenseCategory expenseCategory2 = new ExpenseCategory("testCategory2");

        assertEquals("asd", expenseCategory.getCategory());
        assertEquals(50.0, expenseCategory.getPercentageOfCategory());
    }
}
