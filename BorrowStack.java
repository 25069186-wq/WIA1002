import java.util.Stack;

public class BorrowStack {
    private Stack<Book> stack = new Stack<>();
    public void push(Book b) {
        stack.push(b);
    }

    public void show() {
        if (stack.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("\n--- Borrowing History (Most Recent First) ---");
            for (int i = stack.size() - 1; i >= 0; i--) {
                Book b = stack.get(i);
                System.out.println("[ISBN: " + b.isbn + "] " + b.title + " by " + b.author);
            }
        }
    }
}
