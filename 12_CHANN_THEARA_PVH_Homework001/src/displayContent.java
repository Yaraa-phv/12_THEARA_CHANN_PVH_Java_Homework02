import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.time.LocalDate;

class displayContent {
    private Book[] books;
    static private int bookCount;
    private int maxBooks;

    private int displayRowLimit;

    public displayContent(int maxBooks){
        this.maxBooks = maxBooks;
        this.books = new Book[maxBooks];
        this.bookCount = 0;
        this.displayRowLimit = 10;
    }


    //ADD BOOK
    public void addBook(String bookTitle, String authorName, String authorActiveYear, String publishYear) {
        int newBookID = (bookCount > 0)
                ? books[bookCount - 1].getBookID() + 1
                : 1;

        if (bookCount >= maxBooks) {
            System.out.println(Main.Red+"Library is full. Cannot add more books."+Main.Reset);
            return;
        }

        Book newBook = new Book(newBookID, bookTitle, authorName, authorActiveYear, publishYear);
        books[bookCount] = newBook;
        System.out.println("Book '" + bookTitle + "' added successfully with Book ID: " + newBookID);
        bookCount++;
    }

    // SHOW ALL BOOKS IN LIBRARY
    public void showAllBooks() {
        if (bookCount == 0) {
            System.out.println(Main.Red+"No books in the library."+Main.Reset);
            return;
        }

        Table table = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle tableStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 30, 50);
        table.setColumnWidth(2, 20, 30);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 20);
        table.setColumnWidth(5, 10, 50);

        table.addCell(Main.Blue+"ID", tableStyle);
        table.addCell(Main.Blue+"Title", tableStyle);
        table.addCell(Main.Blue+"Author", tableStyle);
        table.addCell(Main.Blue+"Author Active Year", tableStyle);
        table.addCell(Main.Blue+"Publish Year", tableStyle);
        table.addCell(Main.Blue+"Status", tableStyle);

        int limit = (bookCount < displayRowLimit) ? bookCount : displayRowLimit;
        for (int i = 0; i < limit; i++) {
            Book book = books[i];
            table.addCell(String.valueOf(book.getBookID()));
            table.addCell(book.getBookTitle());
            table.addCell(book.getAuthorName());
            table.addCell(String.valueOf(book.getAuthorActiveYear()));
            table.addCell(String.valueOf(book.getPublishYear()));
            table.addCell(book.isAvailableBook() ? "Available" : "Unavailable");
        }

        System.out.println(table.render());
    }

    //SHOW ONLY AVAILABLE BOOK
    public void showAvailableBooks() {
        Table table = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        CellStyle tableStyle = new CellStyle(CellStyle.HorizontalAlign.center);

        table.setColumnWidth(0, 5, 10);
        table.setColumnWidth(1, 30, 50);
        table.setColumnWidth(2, 20, 30);
        table.setColumnWidth(3, 20, 30);
        table.setColumnWidth(4, 15, 20);
        table.setColumnWidth(1, 30, 50);


        table.addCell(Main.Blue+"ID", tableStyle);
        table.addCell(Main.Blue+"Title", tableStyle);
        table.addCell(Main.Blue+"Author", tableStyle);
        table.addCell(Main.Blue+"Author Active Year", tableStyle);
        table.addCell(Main.Blue+"Publish Year", tableStyle);
        table.addCell(Main.Blue+"Status", tableStyle);


        // TRACKING AVAILABLE COUNT
        int availableCount = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailableBook()) {
                Book book = books[i];
                table.addCell(String.valueOf(book.getBookID()));
                table.addCell(book.getBookTitle());
                table.addCell(book.getAuthorName());
                table.addCell(String.valueOf(book.getAuthorActiveYear()));
                table.addCell(String.valueOf(book.getPublishYear()));
                table.addCell(String.valueOf(book.isAvailableBook()));
                availableCount++;
            }
        }

        if (availableCount > 0) {
            System.out.println("\nAvailable Books : ");
            System.out.println(table.render());
        } else {
            System.out.println(Main.Red+"No books are currently available."+Main.Reset);
        }
    }

    //BORROW BOOK
    public void borrowBook(int bookID) {
        while (true){
            if (bookID <= 0) {
                System.out.println(Main.Red+"Invalid Book ID."+Main.Reset);
            }

            for (int i = 0; i < bookCount; i++) {
                if (books[i].getBookID() == bookID) {
                    if (books[i].isAvailableBook()) {
                        books[i].setAvailable(false);
                        System.out.println(Main.LightYellow+"Book '" + books[i].getBookTitle() + "' has been successfully borrowed."+Main.Reset);
                    } else {
                        System.out.println(Main.Red+"Sorry, this book is not available for borrowing."+Main.Reset);
                    }
                    return;
                }
            }
            System.out.println(Main.Red+"Book ID not found."+Main.Reset);
        }
    }

    //RETURN BOOK
    public void returnBook(int bookID) {
        while (true){
            if (bookID <= 0) {
                System.out.println(Main.Red+"Invalid Book ID."+Main.Reset);
            }

            for (int i = 0; i < bookCount; i++) {
                if (books[i].getBookID() == bookID) {
                    if (!books[i].isAvailableBook()) {
                        books[i].setAvailable(true);
                        System.out.println(Main.LightYellow+"Book '" + books[i].getBookTitle() + "' has been successfully returned."+Main.Reset);
                    } else {
                        System.out.println(Main.LightYellow+"This book is already in the library."+Main.Reset);
                    }
                    return;
                }
            }
            System.out.println("Book ID not found.");
        }
    }

    //ONLY SET ROW
    public void setRowLimit(int limit) {
        while (true){
            if (limit <= 0 || limit > bookCount) {
                System.out.println(Main.Red+"Invalid row limit. Must be between 1 and total book count."+Main.Reset);
            }
            this.displayRowLimit = limit;
            Table table = new Table(limit, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
            CellStyle tableStyle = new CellStyle(CellStyle.HorizontalAlign.center);

            table.setColumnWidth(0, 5, 10);
            table.setColumnWidth(1, 30, 50);
            table.setColumnWidth(2, 20, 30);
            table.setColumnWidth(3, 20, 30);
            table.setColumnWidth(4, 15, 20);
            table.setColumnWidth(1, 30, 50);


            table.addCell(Main.Blue+"ID", tableStyle);
            table.addCell(Main.Blue+"Title", tableStyle);
            table.addCell(Main.Blue+"Author", tableStyle);
            table.addCell(Main.Blue+"Author Active Year", tableStyle);
            table.addCell(Main.Blue+"Publish Year", tableStyle);
            table.addCell(Main.Blue+"Status", tableStyle);


            // TRACKING AVAILABLE COUNT
            for (int i = 0; i < limit; i++) {
                if (books[i].isAvailableBook()) {
                    Book book = books[i];
                    table.addCell(String.valueOf(book.getBookID()));
                    table.addCell(book.getBookTitle());
                    table.addCell(book.getAuthorName());
                    table.addCell(String.valueOf(book.getAuthorActiveYear()));
                    table.addCell(String.valueOf(book.getPublishYear()));
                    table.addCell(String.valueOf(book.isAvailableBook()));
                }
                System.out.println(table.render());
            }
            break;
        }
    }

    //DELETE BOOK
    public void deleteBook(int bookID) {
        while (true){
            if (bookID <= 0) {
                System.out.println(Main.Red+"Invalid Book ID."+Main.Reset);
            }

            for (int i = 0; i < bookCount; i++) {
                if (books[i].getBookID() == bookID) {
                    books[i].setTitle(Main.Red+"REMOVED");
                    books[i].setTitle(Main.Red+"REMOVED");
                    books[i].setAuthorName(Main.Red+"REMOVED");
                    books[i].setAuthorActiveYear(Main.Red+"REMOVED");
                    books[i].setPublishYear(Main.Red+"REMOVED");
                    books[i].setAvailable(false);
                    System.out.println("Book with ID " + bookID + " has been marked as "+Main.Red+"REMOVED.");
                    return;
                }
            }
            System.out.println(Main.Red+"Book ID not found."+Main.Reset);
        }
    }
}