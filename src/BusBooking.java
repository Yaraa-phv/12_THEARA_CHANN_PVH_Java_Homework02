import java.util.Scanner;

public class BusBooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============> SET UP CHAIR IN BUS <==============");
        int chairSeat = 0;

        while (true) {
            //scan1
            System.out.print("Enter the number of seats on the bus (25-45): ");
            chairSeat = scanner.nextInt();
            if (chairSeat >= 25 && chairSeat <= 45) {
                break;
            } else {
                System.out.println("The number is not in the range. Please input a number between 25 and 45.");
            }
        }

//        String[] plus = new String[busChair.length];
//        int countPlus = plus.length;
//        String[] minus = new String[busChair.length];
//        int countMinus = 0;

        int[] busChairArr = new int[chairSeat];
        for (int i = 0; i < chairSeat; i++) {
            busChairArr[i] = 1;
        }
        // Booking loop
        while (true) {
            System.out.println("\n------------------ Display Bus Information ------------------\n");
            for (int i = 0; i < busChairArr.length; i++) {
                if (busChairArr[i] == 1) {
                    System.out.print("|\t(+) " + (i + 1) + "\t|");
                } else {
                    System.out.print("|\t(-) " + (i + 1) + "\t|");
                }
                if ((i + 1) % 5 == 0) {
                    System.out.println();
                    System.out.println("-------------------------------------------------------------");
                }
            }
            int countPlus = 0; //plus : available
            int countMinus = 0; //minus : unavailable
            for (int seat : busChairArr) {
                if (seat == 1) {
                    countPlus++;
                } else {
                    countMinus++;
                }
            }
            System.out.println("\n(+) : Available (" + countPlus + ")\t\t(-) : Unavailable (" + countMinus + ")\n");

            //scan2
            System.out.print("Do you want to book a chair? (y/n): ");
            char choice = scanner.next().toLowerCase().charAt(0);

            if (choice == 'n') {
                System.out.println("Thank you for visiting!");
                break;
            } else if (choice == 'y') {
                System.out.print("Enter the chair number to book: ");
                int chairNumber = scanner.nextInt();

                // conditions of validate chairNumber
                if (chairNumber < 1 || chairNumber > chairSeat) {
                    System.out.println("Invalid chair number. Please try again.");
                } else if (busChairArr[chairNumber - 1] == 0) {
                    System.out.println("Chair " + chairNumber + " is already booked!");
                } else {
                    System.out.print("Do you want to confirm booking chair " + chairNumber + "? (y/n): ");
                    char confirm = scanner.next().toLowerCase().charAt(0);
                    if (confirm == 'y') {
                        busChairArr[chairNumber - 1] = 0;
                        System.out.println("==================================================");
                        System.out.println("|| Chair number " + chairNumber + " has been booked successfully! ||");
                        System.out.println("==================================================");
                    } else {
                        System.out.println("Booking cancelled.");
                    }
                }
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }

        scanner.close();
    }
}
