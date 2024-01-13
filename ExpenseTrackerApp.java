
import java.io.*;
import java.util.*;

class Expense {
    String date;
    String category;
    double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Category: " + category + ", Amount: ₹" + amount;
    }
}

class ExpenseManager {
    List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public void listExpenses() {
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void listExpensesByCategory(String category) {
        for (Expense expense : expenses) {
            if (expense.category.equalsIgnoreCase(category)) {
                System.out.println(expense);
            }
        }
    }

    public double calculateTotalExpenseByCategory(String category) {
        double totalExpense = 0;
        for (Expense expense : expenses) {
            if (expense.category.equalsIgnoreCase(category)) {
                totalExpense += expense.amount;
            }
        }
        return totalExpense;
    }

    public void saveExpensesToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(expenses);
            System.out.println("Expenses saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadExpensesFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            expenses = (List<Expense>) ois.readObject();
            System.out.println("Expenses loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseManager expenseManager = new ExpenseManager();

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. List All Expenses");
            System.out.println("3. List Expenses by Category");
            System.out.println("4. Calculate Total Expense by Category");
            System.out.println("5. Save Expenses to File");
            System.out.println("6. Load Expenses from File");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter date (MM/DD/YYYY): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter amount: ₹");
                    double amount = scanner.nextDouble();
                    scanner.nextLine(); // Consume the newline character
                    expenseManager.addExpense(new Expense(date, category, amount));
                    System.out.println("Expense added successfully.");
                    break;

                case 2:
                    System.out.println("\nAll Expenses:");
                    expenseManager.listExpenses();
                    break;

                case 3:
                    System.out.print("Enter category to filter: ");
                    String filterCategory = scanner.nextLine();
                    System.out.println("\nExpenses in Category '" + filterCategory + "':");
                    expenseManager.listExpensesByCategory(filterCategory);
                    break;

                case 4:
                    System.out.print("Enter category to calculate total expense: ");
                    String totalCategory = scanner.nextLine();
                    double totalExpense = expenseManager.calculateTotalExpenseByCategory(totalCategory);
                    System.out.println("Total Expense in Category '" + totalCategory + "': ₹" + totalExpense);
                    break;

                case 5:
                    System.out.print("Enter filename to save expenses: ");
                    String saveFilename = scanner.nextLine();
                    expenseManager.saveExpensesToFile(saveFilename);
                    break;

                case 6:
                    System.out.print("Enter filename to load expenses: ");
                    String loadFilename = scanner.nextLine();
                    expenseManager.loadExpensesFromFile(loadFilename);
                    break;

                case 7:
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
}

