public class TestLoop {
    public static void main(String[] args) {
//        //Testing Loop
//        //For Loop
//        int i = 7;
//        for (i = 1; i <= 5; i++) {
//
//            System.out.print(" " + i);
//        }
////        System.out.println(i);'[o
//        System.out.println("\nLoop has ended.");

//        int e;
//        int result;

//        for (int i=0; i< 5; i++) {
//            int result = 1;
//            int e = i;
//            while (e > 0) {
//                result *= 2;
//                e--;
//            }
//            System.out.println("2 to the " + i + " power is " + result);
//        }
//        //nested for loop
        int content = 2;
        int chapter = 3;

        for(int i=1; i<=content;i++){
            System.out.println("==> "+i+" <==");
            //inner loop
            for (int j=1; j<=chapter; j++){
                System.out.println("Chapter : "+j);
            }
        }
////        //nested while loop
////        int i = 1;
//////        while (i<=week){
//////            System.out.println("Week : "+i);
//////            i++;
//////            int j=1;
//////            while (j<=day){
//////                System.out.println("Day : "+j);
//////                j++;
//////            }
//////        }
////        //nested do-while loop
////        do {
////            System.out.println("Week : "+i);
////            i++;
////            int j=1;
////            do {
////                System.out.println("Day : "+j);
////                j++;
////            }while (j<=day);
////        }while (i<=week);

    }
}
