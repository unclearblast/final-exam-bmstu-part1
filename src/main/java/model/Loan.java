package model;

public class Loan {

    private int bookId;
    private int readerId;

    public Loan(int bookId, int readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getReaderId() {
        return readerId;
    }
}
