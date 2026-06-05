import java.util.Scanner;
import java.util.InputMismatchException;

public class SmartLibrary implements LibraryADT {
    
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        System.out.println(">>> Book added successfully: " + title);
    }

    @Override
    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println(">>> Book found: [ISBN: " + b.isbn + "] " + b.title + " Author: " + b.author);
        } else {
            System.out.println(">>> Book not found for the given ISBN.");
        }
    }

    @Override
    public void borrowBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            history.push(b);
            catalogue.delete(isbn); // Added the missing deletion logic
            System.out.println(">>> Successfully borrowed: " + b.title);
        } else {
            System.out.println(">>> Borrow failed: Book not found in the catalogue.");
        }
    }

    @Override
    public void viewLatestHistory() {
        history.show();
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Enter choice (1-5): ");
            
            if (!sc.hasNextInt()) {
                System.out.println("Error: Please enter a valid numerical option!");
                sc.next();
                continue;
            }
            
            int choice = sc.nextInt();
            sc.nextLine();
            
            if (choice == 5) {
                System.out.println("System exited. Thank you!");
                break;
            }
            
            handleChoice(choice, sc);
        }
        sc.close();
    }

    private void printMenu() {
        System.out.println("\n--- SmartLibrary Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. View History");
        System.out.println("5. Exit");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                System.out.print("Enter ISBN (Integer): ");
                while (!sc.hasNextInt()) {
                    System.out.print("Error: ISBN must be an integer! Try again: ");
                    sc.next();
                }
                int id = sc.nextInt();
                sc.nextLine();
                
                System.out.print("Enter Title: ");
                String title = sc.nextLine();
                
                System.out.print("Enter Author: ");
                String author = sc.nextLine();
                
                addBook(id, title, author);
                break;
                
            case 2:
                System.out.print("Enter ISBN to search: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Error: Please enter an integer ISBN: ");
                    sc.next();
                }
                int searchId = sc.nextInt();
                sc.nextLine();
                searchBook(searchId);
                break;
                
            case 3:
                System.out.print("Enter ISBN to borrow: ");
                while (!sc.hasNextInt()) {
                    System.out.print("Error: Please enter an integer ISBN: ");
                    sc.next();
                }
                int borrowId = sc.nextInt();
                sc.nextLine();
                borrowBook(borrowId);
                break;
                
            case 4:
                viewLatestHistory();
                break;
                
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
        }
    }
}
