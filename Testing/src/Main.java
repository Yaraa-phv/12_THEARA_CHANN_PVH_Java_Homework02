
import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.BorderStyle;

// Custom Exception for Library-specific Errors
class LibraryException extends Exception {
    public LibraryException(String message) {
        super(message);
    }
}

// Input Validation Utility-Done
class InputValidator {
    // Validate book title
    public static boolean isValidBookTitle(String title) {
        return title != null && !title.trim().isEmpty() && title.length() >= 2 && title.length() <= 100;
    }

    // Validate author name
    public static boolean isValidAuthorName(String name) {
        return name != null && name.trim().matches("^[A-Za-z ]{2,50}$");
    }

    // Validate year (between 1000 and current year)
    public static boolean isValidYear(int year) {
        int currentYear = LocalDate.now().getYear();
        return year >= 1000 && year <= currentYear;
    }
}

// Author class with enhanced validation
class Author {
    private String authorName;
    private int authorActiveYear;

    public Author(String authorName, int authorActiveYear) throws LibraryException {
        setAuthorName(authorName);
        setAuthorActiveYear(authorActiveYear);
    }

    public void setAuthorName(String authorName) throws LibraryException {
        if (!InputValidator.isValidAuthorName(authorName)) {
            throw new LibraryException("Invalid author name. Must be 2-50 letters.");
        }
        this.authorName = authorName.trim();
    }

    public void setAuthorActiveYear(int authorActiveYear) throws LibraryException {
        if (!InputValidator.isValidYear(authorActiveYear)) {
            throw new LibraryException("Invalid author active year.");
        }
        this.authorActiveYear = authorActiveYear;
    }

    // Getters
    public String getAuthorName() {
        return authorName;
    }

    public int getAuthorActiveYear() {
        return authorActiveYear;
    }
}

// Book class with enhanced validation
class Book extends Author {
    private int bookID;
    private String bookTitle;
    private int publishYear;
    private boolean isAvailable;

    public Book(int bookID, String bookTitle, String authorName,
                int authorActiveYear, int publishYear) throws LibraryException {
        super(authorName, authorActiveYear);
        setBookID(bookID);
        setBookTitle(bookTitle);
        setPublishYear(publishYear);
        this.isAvailable = true;
    }

    public void setBookID(int bookID) throws LibraryException {
        if (bookID <= 0) {
            throw new LibraryException("Invalid Book ID. Must be a positive number.");
        }
        this.bookID = bookID;
    }

    public void setBookTitle(String bookTitle) throws LibraryException {
        if (!InputValidator.isValidBookTitle(bookTitle)) {
            throw new LibraryException("Invalid book title. Must be 2-100 characters.");
        }
        this.bookTitle = bookTitle.trim();
    }

    public void setPublishYear(int publishYear) throws LibraryException {
        if (!InputValidator.isValidYear(publishYear)) {
            throw new LibraryException("Invalid publish year.");
        }
        this.publishYear = publishYear;
    }

    // Getters and Setters with additional validation
    public int getBookID() {
        return bookID;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

// Library Management System with Enhanced Features
class LibraryManagementSystem {
    private String libraryName;
    private String libraryAddress;
    private LocalDate setupDate;
    private Book[] books;
    private int bookCount;
    private int maxBooks;
    private int displayRowLimit;

    // Constructor with input validation
    public LibraryManagementSystem(String libraryName, String libraryAddress, int maxBooks){
        // Validate library name and address
        if (libraryName == null || libraryName.trim().isEmpty()) {
            System.out.println("Library name cannot be empty.");
        }
        if (libraryAddress == null || libraryAddress.trim().isEmpty()) {
            System.out.println("Library address cannot be empty.");
        }
        if (maxBooks <= 0 || maxBooks > 1000) {
            System.out.println("Invalid max books. Must be between 1 and 1000.");
        }

        this.libraryName = libraryName.trim();
        this.libraryAddress = libraryAddress.trim();
        this.setupDate = LocalDate.now();
        this.maxBooks = maxBooks;
        this.books = new Book[maxBooks];
        this.bookCount = 0;
        this.displayRowLimit = 10;
    }

    // Display setup message
    public void displaySetupMessage() {
        System.out.println("\n🎉 " + libraryName + " Successfully Set Up! 📚");
        System.out.println("Address: " + libraryAddress);
        System.out.println("Setup Date: " + setupDate);
    }

    // Add Book with error handling
    public void addBook(String bookTitle, String authorName,
                        int authorActiveYear, int publishYear) {
        try {
            if (bookCount >= maxBooks) {
                throw new LibraryException("Library is full. Cannot add more books.");
            }

            int newBookID = bookCount + 1;
            Book newBook = new Book(newBookID, bookTitle, authorName,
                    authorActiveYear, publishYear);
            books[bookCount] = newBook;
            System.out.println("Book '" + bookTitle + "' added successfully with Book ID: " + newBookID);
            bookCount++;
        } catch (LibraryException e) {
            System.err.println("Error adding book: " + e.getMessage());
        }
    }

    // Show All Books with TextTableFmt
    public void showAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }

        Table table = new Table(6, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        // Set column widths
        table.setColumnWidth(0, 5, 10);   // Book ID
        table.setColumnWidth(1, 30, 50);  // Title
        table.setColumnWidth(2, 20, 30);  // Author
        table.setColumnWidth(3, 20, 30);  // Author Active Year
        table.setColumnWidth(4, 15, 20);  // Publish Year
        table.setColumnWidth(5, 10, 15);  // Status

        // Add headers
        CellStyle headerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("ID", headerStyle);
        table.addCell("Title", headerStyle);
        table.addCell("Author", headerStyle);
        table.addCell("Author Active Year", headerStyle);
        table.addCell("Publish Year", headerStyle);
        table.addCell("Status", headerStyle);

        // Add book data
        int limit = Math.min(bookCount, displayRowLimit);
        for (int i = 0; i < limit; i++) {
            Book book = books[i];
            table.addCell(String.valueOf(book.getBookID()));
            table.addCell(book.getBookTitle());
            table.addCell(book.getAuthorName());
            table.addCell(String.valueOf(book.getAuthorActiveYear()));
            table.addCell(String.valueOf(book.getPublishYear()));
            table.addCell(book.isAvailable() ? "Available" : "Unavailable");
        }

        // Print the table
        System.out.println(table.render());
    }

    // Show Available Books with TextTableFmt
    public void showAvailableBooks() {
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

        // Set column widths
        table.setColumnWidth(0, 5, 10);   // Book ID
        table.setColumnWidth(1, 30, 50);  // Title
        table.setColumnWidth(2, 20, 30);  // Author
        table.setColumnWidth(3, 20, 30);  // Author Active Year
        table.setColumnWidth(4, 15, 20);  // Publish Year

        // Add headers
        CellStyle headerStyle = new CellStyle(CellStyle.HorizontalAlign.center);
        table.addCell("ID", headerStyle);
        table.addCell("Title", headerStyle);
        table.addCell("Author", headerStyle);
        table.addCell("Author Active Year", headerStyle);
        table.addCell("Publish Year", headerStyle);

        // Track available books
        int availableCount = 0;
        for (int i = 0; i < bookCount; i++) {
            if (books[i].isAvailable()) {
                Book book = books[i];
                table.addCell(String.valueOf(book.getBookID()));
                table.addCell(book.getBookTitle());
                table.addCell(book.getAuthorName());
                table.addCell(String.valueOf(book.getAuthorActiveYear()));
                table.addCell(String.valueOf(book.getPublishYear()));
                availableCount++;
            }
        }

        // Render table or show message
        if (availableCount > 0) {
            System.out.println("\nAvailable Books:");
            System.out.println(table.render());
        } else {
            System.out.println("No books are currently available.");
        }
    }

    // Borrow Book with enhanced error checking
    public void borrowBook(int bookID) {
        try {
            if (bookID <= 0) {
                throw new LibraryException("Invalid Book ID.");
            }

            for (int i = 0; i < bookCount; i++) {
                if (books[i].getBookID() == bookID) {
                    if (books[i].isAvailable()) {
                        books[i].setAvailable(false);
                        System.out.println("Book '" + books[i].getBookTitle() + "' has been successfully borrowed.");
                    } else {
                        System.out.println("Sorry, this book is not available for borrowing.");
                    }
                    return;
                }
            }
            throw new LibraryException("Book ID not found.");
        } catch (LibraryException e) {
            System.err.println("Borrow Error: " + e.getMessage());
        }
    }

    // Return Book with enhanced error checking
    public void returnBook(int bookID) {
        try {
            if (bookID <= 0) {
                throw new LibraryException("Invalid Book ID.");
            }

            for (int i = 0; i < bookCount; i++) {
                if (books[i].getBookID() == bookID) {
                    if (!books[i].isAvailable()) {
                        books[i].setAvailable(true);
                        System.out.println("Book '" + books[i].getBookTitle() + "' has been successfully returned.");
                    } else {
                        System.out.println("This book is already in the library.");
                    }
                    return;
                }
            }
            throw new LibraryException("Book ID not found.");
        } catch (LibraryException e) {
            System.err.println("Return Error: " + e.getMessage());
        }
    }

    // Set Row Limit with validation
    public void setRowLimit(int limit) {
        try {
            if (limit <= 0 || limit > bookCount) {
                throw new LibraryException("Invalid row limit. Must be between 1 and total book count.");
            }
            this.displayRowLimit = limit;
            System.out.println("Row limit set to " + limit);
        } catch (LibraryException e) {
            System.err.println("Row Limit Error: " + e.getMessage());
        }
    }

    // Delete Book with enhanced error checking
    public void deleteBook(int bookID) {
        try {
            if (bookID <= 0) {
                throw new LibraryException("Invalid Book ID.");
            }

            for (int i = 0; i < bookCount; i++) {
                if (books[i].getBookID() == bookID) {
                    books[i].setBookTitle("REMOVED");
                    books[i].setAvailable(false);
                    System.out.println("Book with ID " + bookID + " has been marked as REMOVED.");
                    return;
                }
            }
            throw new LibraryException("Book ID not found.");
        } catch (LibraryException e) {
            System.err.println("Delete Book Error: " + e.getMessage());
        }
    }
}

// Main Application with Enhanced Error Handling
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("🌟 Welcome to Library Management System 🌟");
            System.out.print("Enter Library Name: ");
            String libraryName = scanner.nextLine();
            System.out.print("Enter Library Address: ");
            String libraryAddress = scanner.nextLine();

            // Create library with a maximum of 100 books
            LibraryManagementSystem library = new LibraryManagementSystem(libraryName, libraryAddress, 100);
            library.displaySetupMessage();

            // Add initial books with error handling
            library.addBook("Java Programming", "John Smith", 2010, 2022);
            library.addBook("Data Structures", "Alice Johnson", 2005, 2020);
            library.addBook("Design Patterns", "Bob Williams", 2015, 2023);

            while (true) {
                System.out.println("\n--- Library Management Menu ---");
                System.out.println("1. Add Book");
                System.out.println("2. Show All Books");
                System.out.println("3. Show Available Books");
                System.out.println("4. Borrow Book");
                System.out.println("5. Return Book");
                System.out.println("6. Set Row Limit");
                System.out.println("7. Delete Book");
                System.out.println("8. Exit");

                System.out.print("Enter your choice (1-8): ");

                // Enhanced input validation
                int choice = 0;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.err.println("Invalid input. Please enter a number between 1 and 8.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();

                        int activeYear = 0, publishYear = 0;
                        try {
                            System.out.print("Enter Author Active Year: ");
                            activeYear = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Author Active Year: ");
                            activeYear = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Publish Year: ");
                            publishYear = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid year input. Please enter valid numbers.");
                            continue;
                        }
                        library.addBook(title, author, activeYear, publishYear);
                        break;

                    case 2:
                        library.showAllBooks();
                        break;

                    case 3:
                        library.showAvailableBooks();
                        break;

                    case 4:
                        System.out.print("Enter Book ID to borrow: ");
                        try {
                            int borrowID = Integer.parseInt(scanner.nextLine());
                            library.borrowBook(borrowID);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid Book ID. Please enter a valid number.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Book ID to return: ");
                        try {
                            int returnID = Integer.parseInt(scanner.nextLine());
                            library.returnBook(returnID);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid Book ID. Please enter a valid number.");
                        }
                        break;

                    case 6:
                        System.out.print("Enter number of rows to display: ");
                        try {
                            int limit = Integer.parseInt(scanner.nextLine());
                            library.setRowLimit(limit);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid input. Please enter a valid number.");
                        }
                        break;

                    case 7:
                        System.out.print("Enter Book ID to delete: ");
                        try {
                            int deleteID = Integer.parseInt(scanner.nextLine());
                            library.deleteBook(deleteID);
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid Book ID. Please enter a valid number.");
                        }
                        break;

                    case 8:
                        System.out.println("🌈 Thank you for using the Library Management System! Goodbye! 👋");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 8.");
                }
            }
        }
    }
}