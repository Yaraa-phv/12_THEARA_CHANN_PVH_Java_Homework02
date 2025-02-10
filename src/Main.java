import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int hallNum = 0;
        int seatNum = 0;

        int[][] seatSetup = null;
        String[] movieNames = null;
        String[] movieTypes = null;
        int[] movieDurations = null;

        System.out.println("------------------> Setting Up Cinema <------------------");
        while (true) {
            System.out.print("Enter number of Hall in Cinema: ");
            String hallAmount = scanner.nextLine();

            if (!hallAmount.matches("\\d+")) {
                System.out.println("Wrong Input! Please set the Hall Amount with digits!");
            } else {
                hallNum = Integer.parseInt(hallAmount);
                if (hallNum > 0 && hallNum <= 5) {
                    System.out.print("Enter number of Seat in each hall: ");
                    String seatAmount = scanner.nextLine();
                    if (!seatAmount.matches("\\d+")) {
                        System.out.println("Wrong Input! Please set the Seat Amount with digits!");
                    } else {
                        seatNum = Integer.parseInt(seatAmount);
                        if (seatNum >= 50 && seatNum <= 90) {
                            seatSetup = new int[hallNum][seatNum];
                            movieNames = new String[hallNum];
                            movieTypes = new String[hallNum];
                            movieDurations = new int[hallNum];
                            break;
                        } else {
                            System.out.println("Please input amount of Seat around 50 to 90!");
                        }
                    }
                } else {
                    System.out.println("Please input amount of Hall around 1 to 5!");
                }
            }
        }

        int currentHall = 0;

        while (true) {
            // DISPLAY MENU
            System.out.println("\n==============> MMS - MOVIE MANAGEMENT SYSTEM <==============\n");
            System.out.println("1. Insert A Movie");
            System.out.println("2. Check and Booking Seat");
            System.out.println("3. Check Ticket");
            System.out.println("4. Reset Hall");
            System.out.println("5. Exit!");

            int option = 0;
            boolean validOption = false;
            while (!validOption) {
                System.out.print("Choose an option: ");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option >= 1 && option <= 5) {
                        validOption = true;
                    } else {
                        System.out.println("Invalid option! Please choose a valid option between 1 and 5.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a number between 1 and 5.");
                }
            }

            // OPTION
            switch (option) {
                // OPTION 1: Insert A Movie
                case 1:
                    if (currentHall < hallNum) {
                        System.out.print("Insert Movie Name: ");
                        String movieName = scanner.nextLine();
                        System.out.print("Insert Movie Type: ");
                        String movieType = scanner.nextLine();
                        if (movieType.matches(".*\\d.*")) {
                            System.out.println("Please input text (e.g., Horror) for Movie Type!");
                        } else {
                            System.out.print("Insert Movie Duration (minutes): ");
                            String movieDur = scanner.nextLine();
                            int duration = 0;
                            if (!movieDur.matches("\\d+")) {
                                System.out.println("Please input the duration as digits (e.g., 100) for Movie Duration!");
                            } else {
                                duration = Integer.parseInt(movieDur);
                                if (duration >= 90 && duration <= 120) {
                                    movieNames[currentHall] = movieName;
                                    movieTypes[currentHall] = movieType;
                                    movieDurations[currentHall] = duration;
                                    System.out.println("Movie \"" + movieName + "\" with " + duration + " minutes will show in Hall " + (currentHall + 1) + ".");
                                    currentHall++;
                                } else {
                                    System.out.println("Movie duration should be between 90 and 120 minutes. Please try again.");
                                }
                            }
                        }
                    } else {
                        System.out.println("All halls are occupied. No more movies can be inserted.");
                    }
                    break;

                // OPTION 2: Check and Book Seat
                case 2:
                    System.out.print("Enter Hall Number to book seat (1-" + hallNum + "): ");
                    int hallToBook = scanner.nextInt();
                    if (hallToBook < 1 || hallToBook > hallNum) {
                        System.out.println("Invalid Hall number!");
                    } else {
                        // Show movie details first
                        System.out.println("Movie Details for Hall " + hallToBook + ":");
                        System.out.println("Name: " + movieNames[hallToBook - 1]);
                        System.out.println("Type: " + movieTypes[hallToBook - 1]);
                        System.out.println("Duration: " + movieDurations[hallToBook - 1] + " minutes");
                        System.out.println();

                        // Ask if user wants to proceed with booking
                        System.out.print("Do you want to proceed with booking seats? (y/n): ");
                        String proceed = scanner.nextLine();
                        while (!proceed.equals("y") && !proceed.equals("n")) {
                            System.out.println("Invalid input! Please enter 'y' to proceed or 'n' to go back.");
                            System.out.print("Do you want to proceed with booking seats? (y/n): ");
                            proceed = scanner.nextLine();
                        }

                        if (proceed.equals("y")) {
                            // Display seat availability table with numbers and symbols
                            System.out.println("Seat Availability in Hall " + hallToBook + ":");
                            for (int i = 0; i < seatNum; i++) {
                                if (i % 5 == 0) System.out.println();  // New row after every 5 seats
                                if (seatSetup[hallToBook - 1][i] == 0) {
                                    System.out.print("+" + (i + 1) + " ");  // Available seat with number
                                } else {
                                    System.out.print("-" + (i + 1) + " ");  // Booked seat with number
                                }
                            }
                            System.out.println();

                            // Booking process
                            System.out.print("How many seats would you like to book (2-5): ");
                            int seatsToBook = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            if (seatsToBook < 2 || seatsToBook > 5) {
                                System.out.println("You can only book between 2 to 5 seats.");
                            } else {
                                int bookedSeats = 0;
                                for (int i = 0; i < seatNum && bookedSeats < seatsToBook; i++) {
                                    if (seatSetup[hallToBook - 1][i] == 0) {  // Seat is available
                                        seatSetup[hallToBook - 1][i] = 1; // Mark seat as booked
                                        bookedSeats++;
                                    }
                                }
                                if (bookedSeats == seatsToBook) {
                                    System.out.println(seatsToBook + " seats have been successfully booked in Hall " + hallToBook + ".");
                                } else {
                                    System.out.println("Not enough available seats to book " + seatsToBook + " seats. Try again.");
                                }
                            }
                        } else {
                            System.out.println("Returning to the menu.");
                        }
                    }
                    break;

                // OPTION 3: Check Ticket
                case 3:
                    System.out.print("Enter Hall Number to check ticket (1-" + hallNum + "): ");
                    int hallToCheck = scanner.nextInt();
                    if (hallToCheck < 1 || hallToCheck > hallNum) {
                        System.out.println("Invalid Hall number!");
                    } else {
                        System.out.println("Seat Availability in Hall " + hallToCheck + ":");
                        for (int i = 0; i < seatNum; i++) {
                            if (i % 5 == 0) System.out.println();  // New row after every 5 seats
                            if (seatSetup[hallToCheck - 1][i] == 0) {
                                System.out.print("+" + (i + 1) + " ");  // Available seat with number
                            } else {
                                System.out.print("-" + (i + 1) + " ");  // Booked seat with number
                            }
                        }
                        System.out.println();
                    }
                    break;

                // OPTION 4: Reset Hall
                case 4:
                    System.out.print("Enter Hall Number to reset (1-" + hallNum + "): ");
                    int hallToReset = scanner.nextInt();
                    if (hallToReset < 1 || hallToReset > hallNum) {
                        System.out.println("Invalid Hall number!");
                    } else {
                        for (int i = 0; i < seatNum; i++) {
                            seatSetup[hallToReset - 1][i] = 0; // Reset all seats in the hall to unbooked
                        }
                        System.out.println("Hall " + hallToReset + " has been reset.");
                    }
                    break;

                // OPTION 5: Exit
                case 5:
                    System.out.println("Exiting the system.");
                    return;  // Exit the program

                // Invalid option
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }
}
