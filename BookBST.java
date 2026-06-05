public class BookBST {
    private Book root;

    public void insert(int isbn, String title, String author) {
        root = ins(root, isbn, title, author);
    }

    private Book ins(Book r, int isbn, String title, String author) {

        if (r == null) {
            return new Book(isbn, title, author);
        }

        if (isbn < r.isbn) {
            r.left = ins(r.left, isbn, title, author);
        }
        else if (isbn > r.isbn) {
            r.right = ins(r.right, isbn, title, author);
        }

        return r;
    }

    public Book search(int isbn) {
        return sea(root, isbn);
    }

    private Book sea(Book r, int isbn) {
        if (r == null || r.isbn == isbn) return r;

        return (isbn < r.isbn)
                ? sea(r.left, isbn)
                : sea(r.right, isbn);
    }

public void delete(int isbn) {
        root = del(root, isbn);
    }

    private Book del(Book r, int isbn) {
        if (r == null) return null;

        if (isbn < r.isbn) {
            r.left = del(r.left, isbn);
        } else if (isbn > r.isbn) {
            r.right = del(r.right, isbn);
        } else {
            // Node to delete found
            if (r.left == null) return r.right;
            if (r.right == null) return r.left;

            // Two children: Get inorder successor
            r.isbn = minValue(r.right);
            Book successor = sea(r.right, r.isbn);
            r.title = successor.title;
            r.author = successor.author;

            r.right = del(r.right, r.isbn);
        }
        return r;
    }

    private int minValue(Book r) {
        int minv = r.isbn;
        while (r.left != null) {
            minv = r.left.isbn;
            r = r.left;
        }
        return minv;
    }
}