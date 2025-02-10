package Project_Name;
public class ControlFlowDemo {
    public static void main(String[] args) {
        int number = 3;

        // Switch-Case
        switch (number) {
            case 1:
                System.out.println("One");
                break;
            case 3:
                System.out.println("Number : Three");
                break;
        }

        // For loop with If, Continue, and Break
        for (int i = 1; i <= 3; i++) {
            if (i == 4) {
                continue;
            } else if (i == 2) {
                break;
            }
            System.out.println("For Loop: " + i);
        }

        // While loop
        int count = 1;
        while (count <= 2) {
            System.out.println("While Loop: " + count);
            count++;
        }

        // Do-While loop
        int x = 1;
        do {
            System.out.println("Do-While Loop: " + x);
            x++;
        } while (x <= 3);
    }
}
