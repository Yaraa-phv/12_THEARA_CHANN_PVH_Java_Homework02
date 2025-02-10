public class Book extends Author {
    private int bookID;
    private String bookTitle;
    private String publishYear;
    private boolean availableBook;

    public Book(int bookID, String bookTitle, String authorName, String authorActiveYear, String publishYear){
        super(authorName, authorActiveYear);
        this.bookID = bookID;
        this.bookTitle = bookTitle;
        this.publishYear = publishYear;
        this.availableBook = true;
    }


//    public Book(int bookTitle, String authorName, String authorActiveYear, String publishYear, String availableBook){
//        super(authorName, authorActiveYear);
//        this.bookID = bookID;
//        this.bookTitle = bookTitle;
//        this.publishYear = publishYear;
//        this.availableBook = true;
//    }



    public int getBookID() {
        return bookID;
    }

    public void setBookID(int id) {
        if (bookID != 0) {
            this.bookID = id;
        } else {
            int intId = id;
            if (intId > 0) {
                this.bookID = id;
            } else {
                System.out.println("Invalid Book ID. Must be a positive number.");
            }
        }
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setTitle(String bookTitle) {
        if (!allValidation.isValidBookTitle(bookTitle)) {
            System.out.println("Invalid book title. Must be 2-100 characters.");
        }
        this.bookTitle = bookTitle;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        if (!allValidation.isValidYear(publishYear)) {
            System.out.println("Invalid publish year.");
        }
        this.publishYear = publishYear;
    }

    public boolean isAvailableBook() {
        return availableBook;
    }

    public void setAvailable(boolean available) {
        availableBook = available;
    }

}
