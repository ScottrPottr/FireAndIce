package io.scottware.fireandice.presentation.view.model;

public class BookViewModel {

    private final String bookNumber;
    private final String bookTitle;

    public BookViewModel(String bookNumber, String bookTitle) {
        this.bookNumber = bookNumber;
        this.bookTitle = bookTitle;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public String getBookTitle() {
        return bookTitle;
    }
}
