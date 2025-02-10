package Project_Name;

class Student {

    private int rank;

    public int getRank() {

        return rank;

    }

    public void setRank(int rank) {

        this.rank = rank;

    }

}

public class School {

    public static void main(String[] args) {

        Student s = new Student();

        s.setRank(1022);

        System.out.println("Student rank is " + s.getRank());

    }

}


