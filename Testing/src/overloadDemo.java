class Overload{
    void ovlDemo(){
        System.out.println(overloadDemo.Red+"No Parameter!!!"+overloadDemo.Reset);
    }

    void ovlDemo(int a){
        System.out.println("This is an int parameter : "+overloadDemo.Green+a+overloadDemo.Reset);
    }

    int ovlDemo(int a, int b){
        System.out.println("This is two int parameters : "+a+" + "+b);
        return a+b;
    }

    double ovlDemo(double a, double b){
        System.out.println("This is two double parameters : "+a+" + "+b);
        return a+b;
    }
}

public class overloadDemo {
    static String Red = "\u001b[31;1m";
    static String Green = "\u001b[32m";
    static String Reset = "\u001b[0m";

    public static void main(String[] args) {
        Overload ovl = new Overload();
        int resetInt;
        double resetDouble;

        ovl.ovlDemo();
        ovl.ovlDemo(5);
        System.out.println();

        resetInt = ovl.ovlDemo(2, 5);
        System.out.println("Value of two int : "+Green+resetInt+Reset);

        resetDouble = ovl.ovlDemo(2.5, 5.5);
        System.out.println("Value of two double : "+Green+resetDouble+Reset);
    }

}
