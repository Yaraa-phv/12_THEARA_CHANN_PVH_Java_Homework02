package Project_Name;

public class FinalDemo {
    public static void main(String[] args) {
        int count;
        Final ob = new Final(0);

        for (count = 1; count<10; count++){
            ob.generator(count);
            System.out.println("Hi!!");

//            ob = null;
        }
    }
}
