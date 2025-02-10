import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class ControlStatement {
    public static void main(String[] args) {
        //1/Write a Java program to get a number from the user and print whether it is positive or negative.
//        int num;
//        System.out.println("Input number : ");
//        Scanner scanNum = new Scanner(System.in);
//        num = scanNum.nextInt();
//
//        if(num>0){
//            System.out.println("Number is positive.");
//        }
//        else {
//            System.out.println("Number is negative.");
//        }

        //2/Write a Java program that takes three numbers from the user and prints the greatest number.
        Scanner in = new Scanner(System.in);
        System.out.println("Input 1st Number : ");
        int a = in.nextInt();
        System.out.println("Input 2nd Number : ");
        int b = in.nextInt();
        System.out.println("Input 3rd Number : ");
        int c = in.nextInt();

        if (a>b) {
            if (a > c) {
                System.out.println("=> " + a + " is the greatest number.");
            }
        }
        if (b>a){
            if (b>c){
                System.out.println("=> "+b+" is the greatest number.");
            }
        }
        if (c>a){
            if (c>b){
                System.out.println("=> "+c+" is the greatest number.");
            }
        }
    }
}
