import java.util.Scanner;

//import table style
import javafx.scene.control.Tab;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.BorderStyle;

//Movie Management System - MMS
public class MMS {
    //Code Color
    final static String Red = "\u001b[31;1m";
    final static String Green = "\u001b[32m";
    final static String Reset = "\u001b[0m";
    final static String Blue = "\u001b[34m";
    final static String White = "\u001b[97m";
    final static String LightYellow = "]-\u001b[93m";

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
//        int hallIndex[] = new int[5];
//        int seatIndex[] = new int[9];
        int hallNum = 0;
        int seatNum = 0;


        int[][] seatSetup = null;
        String[] movieNames = null;
        String[] movieTypes = null;
        String[] movieDuration = null;

        int num = 1;

        System.out.println(White + "------------------> SETTING UP CINEMA <------------------\n");
        while (true) {
            System.out.print(Green + "Enter number of Hall in Cinema : " + Blue);
            String hallAmount = "";
            hallAmount = scanner.next();

            if (!hallAmount.matches(".*\\d.*")) {
                System.out.println(Red + "Wrong Input! Please set the Hall Amount with digits!" + Reset);
            } else {
                hallNum = Integer.parseInt(hallAmount);
                if (hallNum > 0 && hallNum <= 5) {
                    System.out.print(Green + "Enter number of Seat in each hall : ");
                    String seatAmount = "";
                    System.out.print(Blue);
                    seatAmount = scanner.next();
                    if (!seatAmount.matches(".*\\d.*")) {
                        System.out.println(Red + "Wrong Input! Please set the Seat Amount with digits!" + Reset);
                    } else {
                        seatNum = Integer.parseInt(seatAmount);
                        if (seatNum >= 50 && seatNum <= 90) {
                            seatSetup = new int[hallNum][seatNum];
                            movieNames = new String[hallNum];
                            movieTypes = new String[hallNum];
                            movieDuration = new String[hallNum];
                            break;
                        } else {
                            System.out.println(Red + "Please input amount of Seat around 50 to 90!" + Reset);
                        }
                    }
                } else {
                    System.out.println(Red + "Please input amount of Hall around 1 to 5!" + Reset);
                }
            }


        }

        int currentHall = 0;
        boolean exit = false;

        while (!exit) {
            //DISPLAY MENU
            System.out.println(White + "\n==============> MMS - MOVIE MANAGEMENT SYSTEM <==============\n");
            System.out.print(Blue);
            System.out.println("1. Insert A Movie");
            System.out.println("2. Check and Booking Seat");
            System.out.println("3. Check Ticket");
            System.out.println("4. Reset Hall");
            System.out.println("5. Exit!");

            int option = 0;
            boolean validOption = false;
            //OPTION Conditions
            while (!validOption) {
                System.out.print(Green + "\nChoose an option: ");
                String input = scanner.next();
                if (input.matches(".*\\d.*")) {
                    option = Integer.parseInt(input);
                    if (option >= 1 && option <= 5) {
                        validOption = true;
                        break;
                    } else {
                        System.out.println(Red + "Please choose a valid option between 1 and 5." + Reset);
                    }

                } else {
                    System.out.println(Red + "Please input as digit (eg: 1) between 1 and 5!" + Reset);
                    break;
                }

            }

            //OPTION
            switch (option) {
                //OPTION 1 : INSERT MOVIE
                case 1:
                    System.out.println(White + "\n-----------------> SET UP THE MOVIE <-----------------");
                    for (int i = currentHall; i < hallNum; i++) {
                        System.out.print(Blue + "\nInsert Movie Name : ");
                        String movieName = scanner.next();
                        movieName = scanner.nextLine();
                        System.out.print(Blue + "Insert Movie Type : ");

                        String movieType = scanner.nextLine();
                        if (movieType.matches(".*\\d.*")) {
                            System.out.println(Red + "Please input text (e.g., Horror) for Movie Type!" + Reset);
                        } else {
                            System.out.print(Blue + "Insert Movie Duration (minutes) : ");
                            String movieDur = scanner.nextLine();
                            int duration = 0;
                            if (!movieDur.matches("^(?:[1-9][0-9]?|1[0-1][0-9]|120)$")) {
                                System.out.println(Red + "Please input the duration as digits (e.g., 100) for Movie Duration!" + Reset);
                            } else {
                                duration = Integer.parseInt(movieDur);
                                if (duration >= 60 && duration <= 120) {
                                    movieNames[currentHall] = movieName;
                                    movieTypes[currentHall] = movieType;
                                    movieDuration[currentHall] = movieDur;
                                    System.out.println(LightYellow + "\n" + "Movie " + movieNames[currentHall] + " with " + movieTypes[currentHall] + " minutes will show in Hall #" + (currentHall + 1) + ".");
                                    currentHall++;

                                    System.out.print(Green + "\nDo you want to insert more movie? (y/n) : ");
                                    String YesNo = scanner.nextLine();
                                    if (!YesNo.matches("^[yYnN]$")) {
                                        System.out.println(Red + "Please input 'y' or 'n' to continue!" + Reset);
                                        break;
                                    }
                                    if (YesNo.matches("^[nN]$")) {
                                        break;
                                    }
                                } else {
                                    System.out.println(Red + "Movie duration should be between 90 and 120 minutes. Please try again." + Reset);
                                }
                            }
                        }

                    }
                    break;

                //OPTION 2 : CHECK AND BOOKING
                case 2:
                    System.out.println(White + "\n-----------------> CHECK AND BOOKING THE SEAT <-----------------\n");
                    System.out.print(Green + "Enter Hall Number to book seat (1-" + hallNum + "): ");
                    String hallInput = scanner.next();
                    int hallToBook = Integer.parseInt(hallInput);

                    if (hallToBook < 1 || hallToBook > hallNum) {
                        System.out.println(Red + "Invalid Hall number!" + Reset);
                    } else {
                        CellStyle tableStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                        Table table = new Table(8, BorderStyle.UNICODE_BOX_DOUBLE_BORDER_WIDE, ShownBorders.ALL);
                        System.out.println(White + "\n-----------------> DISPLAY THE MOVIE <-----------------\n");

                        // Table Width Height
                        table.setColumnWidth(0, 10, 10);
                        table.setColumnWidth(1, 30, 30);
                        table.setColumnWidth(2, 30, 30);
                        table.setColumnWidth(3, 20, 20);
                        table.setColumnWidth(4, 30, 30);
                        table.setColumnWidth(5, 30, 30);
                        table.setColumnWidth(6, 30, 0);
                        table.setColumnWidth(7, 30, 30);

                        // Table Header
                        table.addCell("ID", tableStyle);
                        table.addCell("Movie Name", tableStyle);
                        table.addCell("Movie Type", tableStyle);
                        table.addCell("Duration", tableStyle);
                        table.addCell("Hall", tableStyle);
                        table.addCell("Seat", tableStyle);
                        table.addCell("Available", tableStyle);
                        table.addCell("Unavailable", tableStyle);

                        // Table Content
                        table.addCell(String.valueOf(hallToBook), tableStyle);
                        table.addCell(movieNames[hallToBook - 1], tableStyle);
                        table.addCell(movieTypes[hallToBook - 1], tableStyle);
                        table.addCell(movieDuration[hallToBook - 1], tableStyle);
                        table.addCell(String.valueOf(hallToBook), tableStyle);
                        table.addCell(String.valueOf(seatNum), tableStyle);

                        int totalSeats = seatSetup[hallToBook - 1].length;

                        // Count booked seats and available seats
                        int unavailableSeats = 0;
                        for (int seat : seatSetup[hallToBook - 1]) {
                            if (seat == 1) {
                                unavailableSeats++;
                            }
                        }
                        int availableSeats = totalSeats - unavailableSeats;

                        table.addCell(String.valueOf(availableSeats), tableStyle);  // Number of available seats
                        table.addCell(String.valueOf(unavailableSeats), tableStyle);  // Number of unavailable seats

                        System.out.println(table.render());

                        // Ask the user if they want to book seats
                        System.out.print(Green + "\nDo you want to book a seat? (y/n): ");
                        String bookingOption = scanner.next();

                        if (bookingOption.equalsIgnoreCase("y")) {
                            // Display available seats with (+) format
                            System.out.println(White + "\n-----------------> AVAILABLE SEAT IN THIS HALL <-----------------");
                            for (int i = 0; i < seatSetup[hallToBook - 1].length; i++) {
                                if (seatSetup[hallToBook - 1][i] == 0) {
                                    System.out.print("(+)\t" + (i + 1) + "\t");
                                }

                                if (seatSetup[hallToBook-1].length%10==0){
                                    System.out.println();
                                }
                            }
                            System.out.println();

                            System.out.print(Green + "\nEnter seat numbers to book (e.g., 1, 2, 3): ");
                            scanner.nextLine(); // Consume the leftover newline
                            String seatsToBookInput = scanner.nextLine();

                            int i = 0;
                            String seatNumber = "";
                            while (i < seatsToBookInput.length()) {
                                char currentChar = seatsToBookInput.charAt(i);

                                if (currentChar >= '0' && currentChar <= '9') {
                                    seatNumber += currentChar;
                                }

                                if (currentChar == ',' || i == seatsToBookInput.length() - 1) {
                                    if (!seatNumber.isEmpty()) {
                                        int seatToBook = Integer.parseInt(seatNumber);
                                        seatNumber = "";

                                        // Validate the seat number and booking logic
                                        if (seatToBook < 1 || seatToBook > seatSetup[hallToBook - 1].length) {
                                            System.out.println(Red + "Invalid Seat number: " + seatToBook + Reset);
                                        } else if (seatSetup[hallToBook - 1][seatToBook - 1] == 1) {
                                            System.out.println(Red + "Seat " + seatToBook + " is already booked!" + Reset);
                                        } else {
                                            seatSetup[hallToBook - 1][seatToBook - 1] = 1; // Mark seat as booked
                                            System.out.println(LightYellow + "Seat " + seatToBook + " booked successfully!" + Reset);
                                        }
                                    }
                                }

                                i++;
                            }
                            break;


                        }
                    }

                //OPTION 3 : TICKET
                case 3:
                    System.out.println(White + "\n-----------------> CHECK TICKET <-----------------\n");
                    System.out.print(Green + "Enter Hall Number (1-" + hallNum + "): ");
                    int hallCheck = scanner.nextInt();
                    System.out.print(Green + "Enter Seat Number: ");
                    int seatCheck = scanner.nextInt();

                    if (hallCheck < 1 || hallCheck > hallNum || seatCheck < 1 || seatCheck > seatSetup[hallCheck - 1].length) {
                        System.out.println(Red + "Invalid hall or seat number!" + Reset);
                    } else if (seatSetup[hallCheck - 1][seatCheck - 1] == 0) {
                        System.out.println(Red + "This seat is not booked!" + Reset);
                    } else {
                        System.out.println(White + "\nTicket Details:");
                        System.out.println("Movie Name: " + movieNames[hallCheck - 1]);
                        System.out.println("Hall Number: " + hallCheck);
                        System.out.println("Seat Number: " + seatCheck);
                        System.out.println(Green + "Enjoy your movie!" + Reset);
                    }
                    break;

                //OPTION 4 : RESET
                case 4:
                    System.out.println(White + "\n-----------------> RESET HALL <-----------------\n");
                    System.out.print(Green + "Are you sure you want to reset all halls? (y/n): ");
                    String resetConfirmation = scanner.next();

                    if (resetConfirmation.equalsIgnoreCase("y")) {
                        for (int i = 0; i < hallNum; i++) {
                            for (int j = 0; j < seatNum; j++) {
                                seatSetup[i][j] = 0;
                            }
                        }
                        System.out.println(Green + "All halls and seats have been reset!" + Reset);
                    } else {
                        System.out.println(Red + "Reset operation canceled." + Reset);
                    }
                    break;

                //OPTION 5 : EXIT
                case 5:
                    System.out.println("\nThank you for using the Movie Management System. Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        }
    }
}