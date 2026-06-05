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
}
