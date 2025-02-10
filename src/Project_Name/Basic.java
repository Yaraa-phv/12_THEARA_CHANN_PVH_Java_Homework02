package Project_Name;

import java.util.Scanner;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.Table;

// Movie Management System - MMS
public class Basic {
    // Code Color
    final static String Red = "\u001b[31;1m";
    final static String Green = "\u001b[32m";
    final static String Reset = "\u001b[0m";
    final static String Blue = "\u001b[34m";
    final static String White = "\u001b[97m";
    final static String LightYellow = "\u001b[93m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hallNum = 0;
        int seatNum = 0;

        int[][] seatSetup = null;
        String[] movieNames = null;
        String[] movieTypes = null;
        String[] movieDuration = null;

        // Setting up cinema
        System.out.println(White + "------------------> SETTING UP CINEMA <------------------\n");
        while (true) {
            System.out.print(Green + "Enter number of Hall in Cinema (1-5): " + Blue);
            String hallAmount = scanner.next();

            if (!hallAmount.matches("\\d+")) {
                System.out.println(Red + "Wrong Input! Please set the Hall Amount with digits!" + Reset);
            } else {
                hallNum = Integer.parseInt(hallAmount);
                if (hallNum > 0 && hallNum <= 5) {
                    System.out.print(Green + "Enter number of Seats in each hall (50-90): " + Blue);
                    String seatAmount = scanner.next();
                    if (!seatAmount.matches("\\d+")) {
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
                            System.out.println(Red + "Please input Seat Amount between 50 and 90!" + Reset);
                        }
                    }
                } else {
                    System.out.println(Red + "Please input Hall Amount between 1 and 5!" + Reset);
                }
            }
        }

        int currentHall = 0;

        while (true) {
            // Display menu
            System.out.println(White + "\n==============> MMS - MOVIE MANAGEMENT SYSTEM <==============\n");
            System.out.print(Blue);
            System.out.println("1. Insert A Movie");
            System.out.println("2. Check and Booking Seat");
            System.out.println("3. Exit!");

            int option = 0;
            boolean validOption = false;

            while (!validOption) {
                System.out.print(Green + "\nChoose an option: ");
                String input = scanner.next();
                if (input.matches("\\d+")) {
                    option = Integer.parseInt(input);
                    if (option >= 1 && option <= 3) {
                        validOption = true;
                    } else {
                        System.out.println(Red + "Please choose a valid option between 1 and 3." + Reset);
                    }
                } else {
                    System.out.println(Red + "Please input a digit (e.g., 1)." + Reset);
                }
            }

            switch (option) {
                case 1: // Insert a Movie
                    if (currentHall < hallNum) {
                        System.out.println(White + "\n-----------------> SET UP THE MOVIE <-----------------");
                        System.out.print(Blue + "Insert Movie Name: ");
                        scanner.nextLine(); // Clear input buffer
                        String movieName = scanner.nextLine();

                        System.out.print(Blue + "Insert Movie Type: ");
                        String movieType = scanner.nextLine();

                        System.out.print(Blue + "Insert Movie Duration (minutes): ");
                        String movieDur = scanner.nextLine();
                        if (movieDur.matches("\\d+")) {
                            int duration = Integer.parseInt(movieDur);
                            if (duration >= 60 && duration <= 120) {
                                movieNames[currentHall] = movieName;
                                movieTypes[currentHall] = movieType;
                                movieDuration[currentHall] = movieDur;
                                System.out.println(LightYellow + "Movie " + movieName + " with " + movieDur + " minutes will show in Hall #" + (currentHall + 1) + "." + Reset);
                                currentHall++;
                            } else {
                                System.out.println(Red + "Movie duration should be between 60 and 120 minutes." + Reset);
                            }
                        } else {
                            System.out.println(Red + "Please input duration as digits (e.g., 90)." + Reset);
                        }
                    } else {
                        System.out.println(Red + "All halls are already set up with movies!" + Reset);
                    }
                    break;

                case 2:
                    System.out.println(White + "\n-----------------> CHECK AND BOOKING THE SEAT <-----------------\n");
                    System.out.print(Green + "Enter Hall Number to check and book seat (1-" + hallNum + "): " + Blue);
                    String hallInput = scanner.next();

                    if (hallInput.matches("\\d+")) { // Validate if input is digits
                        int hallToBook = Integer.parseInt(hallInput);

                        if (hallToBook < 1 || hallToBook > hallNum) {
                            System.out.println(Red + "Invalid Hall number! Please input a valid Hall number between 1 and " + hallNum + "." + Reset);
                        } else {
                            // Table setup
                            CellStyle tableStyle = new CellStyle(CellStyle.HorizontalAlign.center);
                            Table table = new Table(4, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);

                            // Define table column widths
                            table.setColumnWidth(0, 10, 10); // ID
                            table.setColumnWidth(1, 30, 30); // Movie Name
                            table.setColumnWidth(2, 30, 30); // Seat Info
                            table.setColumnWidth(3, 30, 30); // Availability

                            // Table header
                            table.addCell("ID", tableStyle);
                            table.addCell("Movie Name", tableStyle);
                            table.addCell("Seat Info", tableStyle);
                            table.addCell("Availability", tableStyle);

                            // Display data for the selected hall
                            String movieName = movieNames[hallToBook - 1];
                            String movieType = movieTypes[hallToBook - 1];
                            String duration = movieDuration[hallToBook - 1];
                            int totalSeats = seatSetup[hallToBook - 1].length;

                            // Count booked seats and available seats
                            int bookedSeats = 0;
                            for (int seat : seatSetup[hallToBook - 1]) {
                                if (seat == 1) {
                                    bookedSeats++;
                                }
                            }
                            int availableSeats = totalSeats - bookedSeats;

                            // Populate table rows
                            table.addCell(String.valueOf(hallToBook), tableStyle); // Hall ID
                            table.addCell(movieName + " (" + movieType + ", " + duration + " min)", tableStyle); // Movie details
                            table.addCell("Total Seats: " + totalSeats, tableStyle); // Seat info
                            table.addCell("Available: " + availableSeats + ", Booked: " + bookedSeats, tableStyle); // Availability info

                            // Render the table
                            System.out.println(White + "\n-----------------> DISPLAY THE MOVIE INFORMATION <-----------------\n");
                            System.out.println(table.render());

                            // Booking a seat
                            System.out.print(Green + "Do you want to book a seat? (y/n): " + Blue);
                            String choice = scanner.next().toLowerCase();

                            if (choice.equals("y")) {
                                System.out.print(Green + "Enter Seat Number to book (1-" + totalSeats + "): " + Blue);
                                String seatInput = scanner.next();
                                if (seatInput.matches("\\d+")) {
                                    int seatNumber = Integer.parseInt(seatInput);
                                    if (seatNumber >= 1 && seatNumber <= totalSeats) {
                                        if (seatSetup[hallToBook - 1][seatNumber - 1] == 0) {
                                            seatSetup[hallToBook - 1][seatNumber - 1] = 1; // Book the seat
                                            System.out.println(LightYellow + "Seat " + seatNumber + " successfully booked in Hall " + hallToBook + "." + Reset);
                                        } else {
                                            System.out.println(Red + "Seat " + seatNumber + " is already booked! Please choose another seat." + Reset);
                                        }
                                    } else {
                                        System.out.println(Red + "Invalid seat number! Please choose a valid seat number." + Reset);
                                    }
                                } else {
                                    System.out.println(Red + "Invalid input! Please enter a numeric seat number." + Reset);
                                }
                            } else if (!choice.equals("n")) {
                                System.out.println(Red + "Invalid input! Please respond with 'y' or 'n'." + Reset);
                            }
                        }
                    } else {
                        System.out.println(Red + "Invalid input! Please enter a numeric Hall number." + Reset);
                    }
                    break;


                case 3: // Exit
                    System.out.println(Green + "Exiting Movie Management System. Goodbye!" + Reset);
                    return;

                default:
                    System.out.println(Red + "Invalid option!" + Reset);
                    break;
            }
        }
    }
}

