interface SalaryCalculator {
    void salaryCal(double hour);
}

class Teacher1 implements SalaryCalculator {
    final int rate = 10;
    @Override
    public void salaryCal(double hour){
        System.out.println("Salary of teacher1 : "+hour*rate+"$");
    }
}

class Teacher2 implements SalaryCalculator {
    final int rate = 15;
    @Override
    public void salaryCal(double hour){
        System.out.println("Salary of teacher2 : "+hour*rate+"$");
    }
}

public class SalaryCalculation {
    public static void main(String[] args) {
         Teacher1 v1 = new Teacher1();
         v1.salaryCal(5.5);
         Teacher2 v2 = new Teacher2();
         v2.salaryCal(2.3);
    }
}
