package Project_Name;
public class Final {
//    public static void main(String[] args) {
        int x;

        Final(int i){
            x=i;
        }

        protected void finalize(){
            System.out.println("Finalizing : "+x);
        }

        void generator(int i){
            Final o = new Final(i);
        }
//    }
}

