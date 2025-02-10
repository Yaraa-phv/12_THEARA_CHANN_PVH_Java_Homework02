import java.util.Scanner;

public class ArrayWithScanner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of rows and columns
        System.out.print("Enter the number of rows: ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of columns: ");
        int numColumns = scanner.nextInt();

        int[][] numbers = new int[numRows][numColumns];
        int num = 1;

        // Loop to populate the 2D array
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                numbers[i][j] = num++;
            }
        }

        // Print the table
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(numbers[i][j] + " + ");
            }
            System.out.println();
        }

        // Check for a specific value in the array
//        int searchValue = 90;
//        boolean found = false;
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numColumns; j++) {
//                if (numbers[i][j] == searchValue) {
//                    System.out.println("Value " + searchValue + " found at row " + i + " and column " + j);
//                    found = true;
//                    break;
//                }
//            }
//            if (found) {
//                break;
//            }
//        }
    }
}