import java.time.LocalDate;
import java.util.Scanner;

// Base Entity Class
abstract class LibraryEntity {
    private int id;
    private String name;

    public LibraryEntity(int id, String name) {
        setId(id);
        setName(name);
    }

    public void setId(int id) {
        if (id <= 0) {
            System.out.println("Invalid ID. Must be a positive number.");
            return;
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty() || name.length() < 2) {
            System.out.println("Invalid name. Must be at least 2 characters long.");
            return;
        }
        this.name = name.trim();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

// Author Class with Enhanced Validation
class Author extends LibraryEntity {
    private int activeYear;

    public Author(int id, String name, int activeYear) {
        super(id, name);
        setActiveYear(activeYear);
    }

    public void setActiveYear(int activeYear) {
        int currentYear = LocalDate.now().getYear();
        if (activeYear < 1000 || activeYear > currentYear) {
            System.out.println("Invalid active year. Must be between 1000 and " + currentYear);
            return;
        }
        this.activeYear = activeYear;
    }

    public int getActiveYear() {
        return activeYear;
    }
}

// Book Class with Advanced Features
class Book extends Author {
    private String title;
    private int publishYear;
    private boolean available;

    public Book(int bookId, String title, String authorName, int authorId,
                int authorActiveYear, int publishYear) {
        super(authorId, authorName, authorActiveYear);
        setTitle(title);
        setPublishYear(publishYear);
        this.available = true;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty() || title.length() < 2 || title.length() > 100) {
            System.out.println("Invalid book title. Must be 2-100 characters.");
            return;
        }
        this.title = title.trim();
    }

    public void setPublishYear(int publishYear) {
        int currentYear = LocalDate.now().getYear();
        if (publishYear < 1000 || publishYear > currentYear) {
            System.out.println("Invalid publish year. Must be between 1000 and " + currentYear);
            return;
        }
        this.publishYear = publishYear;
    }

    public String getTitle() {
        return title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

// Library Management System with Robust Design
class LibraryManagementSystem {
    private String libraryName;
    private String address;
    private LocalDate setupDate;
    private Book[] bookCollection;
    private int bookCount;
    private int maxBooks;
    private int displayRowLimit;

    public LMS(String libraryName, String address, int maxBooks) {
        validateLibrarySetup(libraryName, address, maxBooks);
        this.libraryName = libraryName.trim();
        this.address = address.trim();
        this.setupDate = LocalDate.now();
        this.maxBooks = maxBooks;
        this.bookCollection = new Book[maxBooks];
        this.bookCount = 0;
        this.displayRowLimit = 10;
    }

    private void validateLibrarySetup(String name, String address, int maxBooks) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Library name cannot be empty.");
            return;
        }
        if (address == null || address.trim().isEmpty()) {
            System.out.println("Library address cannot be empty.");
            return;
        }
        if (maxBooks <= 0 || maxBooks > 1000) {
            System.out.println("Invalid max books. Must be between 1 and 1000.");
            return;
        }
    }

    public void addBook(Book newBook) {
        if (bookCount >= maxBooks) {
            System.out.println("Library is full. Cannot add more books.");
            return;
        }
        bookCollection[bookCount++] = newBook;
        System.out.println("Book '" + newBook.getTitle() + "' added successfully with Book ID: " + newBook.getId());
    }

    public void displayLibraryInfo() {
        System.out.println("\n🎉 " + libraryName + " Successfully Set Up! 📚");
        System.out.println("Address: " + address);
        System.out.println("Setup Date: " + setupDate);
    }

    public void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books in the library.");
            return;
        }

        int displayLimit = Math.min(bookCount, displayRowLimit);
        System.out.println("\nLibrary Books:");
        for (int i = 0; i < displayLimit; i++) {
            Book book = bookCollection[i];
            System.out.printf("ID: %d | Title: %s | Author: %s | Publish Year: %d | Status: %s%n",
                    book.getId(),
                    book.getTitle(),
                    book.getName(),
                    book.getPublishYear(),
                    book.isAvailable() ? "Available" : "Unavailable"
            );
        }
    }

    public void displayAvailableBooks() {
        System.out.println("\nAvailable Books:");
        for (int i = 0; i < bookCount; i++) {
            Book book = bookCollection[i];
            if (book.isAvailable()) {
                System.out.printf("ID: %d | Title: %s | Author: %s | Publish Year: %d%n",
                        book.getId(),
                        book.getTitle(),
                        book.getName(),
                        book.getPublishYear()
                );
            }
        }
    }

    public void borrowBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            if (book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book '" + book.getTitle() + "' has been successfully borrowed.");
            } else {
                System.out.println("Sorry, this book is not available for borrowing.");
            }
        }
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            if (!book.isAvailable()) {
                book.setAvailable(true);
                System.out.println("Book '" + book.getTitle() + "' has been successfully returned.");
            } else {
                System.out.println("This book is already in the library.");
            }
        }
    }

    private Book findBookById(int bookId) {
        for (int i = 0; i < bookCount; i++) {
            if (bookCollection[i].getId() == bookId) {
                return bookCollection[i];
            }
        }
        System.out.println("Book ID not found.");
        return null;
    }

    public void removeBook(int bookId) {
        Book bookToRemove = findBookById(bookId);
        if (bookToRemove != null) {
            bookToRemove.setTitle("REMOVED");
            bookToRemove.setAvailable(false);
            System.out.println("Book with ID " + bookId + " has been marked as REMOVED.");
        }
    }

    public int getBookCount() {
        return getBookCount();
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("🌟 Welcome to Library Management System 🌟");
        System.out.print("Enter Library Name: ");
        String libraryName = scanner.nextLine();
        System.out.print("Enter Library Address: ");
        String libraryAddress = scanner.nextLine();

        LibraryManagementSystem library = new LibraryManagementSystem(libraryName, libraryAddress, 100);
        library.displayLibraryInfo();

        // Initial book setup
        library.addBook(new Book(1, "Java Programming", "John Smith", 101, 2010, 2022));
        library.addBook(new Book(2, "Data Structures", "Alice Johnson", 102, 2005, 2020));
        library.addBook(new Book(3, "Design Patterns", "Bob Williams", 103, 2015, 2023));

        while (true) {
            displayMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    addNewBook(library, scanner);
                    break;
                case 2:
                    library.displayAllBooks();
                    break;
                case 3:
                    library.displayAvailableBooks();
                    break;
                case 4:
                    performBookOperation(library, scanner, "borrow");
                    break;
                case 5:
                    performBookOperation(library, scanner, "return");
                    break;
                case 6:
                    System.out.println("Row limit feature removed for simplicity.");
                    break;
                case 7:
                    performBookOperation(library, scanner, "remove");
                    break;
                case 8:
                    System.out.println("🌈 Thank you for using the Library Management System! Goodbye! 👋");
                    scanner.close();
                    System.exit(0);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n--- Library Management Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Show All Books");
        System.out.println("3. Show Available Books");
        System.out.println("4. Borrow Book");
        System.out.println("5. Return Book");
        System.out.println("6. Set Row Limit (Disabled)");
        System.out.println("7. Delete Book");
        System.out.println("8. Exit");
    }

    private static int getUserChoice(Scanner scanner) {
        while (true) {
            System.out.print("Enter your choice (1-8): ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 8) {
                    return choice;
                }
                System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static void addNewBook(LibraryManagementSystem library, Scanner scanner) {
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        int bookId = library.getBookCount() + 1;
        int authorId = bookId * 100;

        System.out.print("Enter Author Active Year: ");
        int activeYear = scanner.nextInt();
        System.out.print("Enter Publish Year: ");
        int publishYear = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Book newBook = new Book(bookId, title, author, authorId, activeYear, publishYear);
        library.addBook(newBook);
    }

    private static void performBookOperation(LibraryManagementSystem library, Scanner scanner, String operation) {
        System.out.print("Enter Book ID to " + operation + ": ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (operation) {
            case "borrow":
                library.borrowBook(bookId);
                break;
            case "return":
                library.returnBook(bookId);
                break;
            case "remove":
                library.removeBook(bookId);
                break;
        }
    }
}