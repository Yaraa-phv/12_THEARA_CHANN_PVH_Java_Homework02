import java.lang.ref.SoftReference;
import java.util.Scanner;
public class Main {

    static String Red = "\u001b[31;1m";
    static String Green = "\u001b[32m";
    static String Reset = "\u001b[0m";
    static String Blue = "\u001b[34m";
    static String White = "\u001b[97m";
    static String LightYellow = "]-\u001b[93m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(White+"\n===================> SET UP THE LIBRARY <===================\n"+Reset);

        String libraryName ="";
        String libraryAddress ="";
        while (true) {
            System.out.print("=> Library Name : ");
            libraryName = scanner.nextLine();
            if (!libraryName.matches("^[a-zA-Z\\s]+$")){
                System.out.println(Red + "Please input only letter!" + Reset);
            }else {
                System.out.print("=> Library Address : ");
                libraryAddress = scanner.nextLine();
                if (!libraryAddress.matches("^[a-zA-Z\\s]+$")){
                    System.out.println(Red + "Please input only letter!" + Reset);
                } else {
                    LibrarySetUp librarySetUp = new LibrarySetUp(libraryName, libraryAddress);
                    System.out.println(librarySetUp.displayLibraryInfo());
                    break;
                }
            }
        }

        while (true){
            displayContent library = new displayContent(100);
            library.addBook("Java Programming", "John Smith", "1999-2010", "2000");
            library.addBook("Data Structures", "Alice Johnson", "1890-1999", "1990");
            library.addBook("Design Patterns", "Bob Williams", "1980-2015", "2013");

            while (true) {
                System.out.println(White+"\n===================> "+LightYellow+libraryName.toUpperCase()+","+libraryAddress.toUpperCase()+Reset+White+" <==================="+Reset);
                System.out.println(Green);
                System.out.println("1. Add Book");
                System.out.println("2. Show All Books");
                System.out.println("3. Show Available Books");
                System.out.println("4. Borrow Book");
                System.out.println("5. Return Book");
                System.out.println("6. Set Row Limit");
                System.out.println("7. Delete Book");
                System.out.println("8. Exit");
                System.out.println(Reset);

                System.out.print("Enter your choice (1-8): ");

                while (!scanner.hasNextInt()) {
                    System.out.println(Red+"Invalid input. Please enter a number between 1 and 8."+Reset);
                    scanner.next();
                    System.out.print("Enter your choice (1-8): ");
                }

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice < 1 || choice > 8) {
                    System.out.println(Red+"Invalid choice. Please enter a number between 1 and 8."+Reset);
                    continue;
                }

                switch (choice) {
                    //ADD BOOK
                    case 1:
                        System.out.print("\nEnter Book Title: ");
                        String title = scanner.nextLine();
                        while (allValidation.isValidAuthorName(title)){
                            break;
                        }

                        System.out.print("Enter Author Name: ");
                        String author = scanner.nextLine();
                        while (allValidation.isValidAuthorName(author)){
                            break;
                        }

                        System.out.print("Enter Author Active Year (YYYY-YYYY): ");
                        String activeYear = scanner.nextLine();
                        while (allValidation.isValidYear(activeYear)) {
                            break;
                        }

                        System.out.print("Enter Publish Year: ");
                        String publishYear = scanner.nextLine();
                        while (allValidation.isValidYear(publishYear)) {
                            break;
                        }

                        library.addBook(title, author, activeYear, publishYear);
                        break;

                    //SHOW ALL BOOKS
                    case 2:
                        library.showAllBooks();
                        break;

                    //SHOW AVAILABLE BOOK
                    case 3:
                        library.showAvailableBooks();
                        break;

                    //BORROW BOOK
                    case 4:
                        System.out.print("Enter Book ID to borrow: ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid Book ID. Please enter a valid number.");
                            scanner.next();
                            System.out.print("Enter Book ID to borrow: ");
                        }

                        int borrowID = scanner.nextInt();
                        scanner.nextLine();

                        library.borrowBook(borrowID);
                        break;

                    //RETURN BOOK
                    case 5:
                        System.out.print("Enter Book ID to return: ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid Book ID. Please enter a valid number.");
                            scanner.next();
                            System.out.print("Enter Book ID to return: ");
                        }

                        int returnID = scanner.nextInt();
                        scanner.nextLine();

                        library.returnBook(returnID);
                        break;

                    //SET ROW TO SHOW RECORD
                    case 6:
                        System.out.print("Enter number of rows to display: ");

                        while (!scanner.hasNextInt()) {
                            System.out.println("Invalid input. Please enter a valid number.");
                            scanner.next();
                            System.out.print("Enter number of rows to display: ");
                        }

                        int limit = scanner.nextInt();
                        scanner.nextLine();

                        library.setRowLimit(limit);
                        break;

                    //REMOVE BOOK BY ID
                    case 7:
                        System.out.print("Enter Book ID to delete: ");

                        while (!scanner.hasNextInt()) {
                            System.err.println("Invalid Book ID. Please enter a valid number.");
                            scanner.next();
                            System.out.print("Enter Book ID to delete: ");
                        }

                        int deleteID = scanner.nextInt();
                        scanner.nextLine();

                        library.deleteBook(deleteID);
                        break;

                    //EXIT
                    case 8:
                        System.out.println(LightYellow+"Thank you for using the Library Management System! Goodbye! 👋"+Reset);
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println(Red+"Invalid choice. Please enter a number between 1 and 8."+Reset);
                }
            }
        }
    }
}