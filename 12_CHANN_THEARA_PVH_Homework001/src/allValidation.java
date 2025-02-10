public class allValidation {
    // Validation for bookTitle
    public static boolean isValidBookTitle(String title) {
        return title != null && !title.isEmpty() && title.length() >= 2 && title.length() <= 100;
    }

    // Validation for authorName
    public static boolean isValidAuthorName(String name) {
        return name != null && name.matches("^[A-Za-z ]{2,50}$");
    }

    // Validation for authorActiveYear
    public static boolean isValidYear(String year) {
        if (year.matches("^\\d{4}-\\d{4}$")) {
            String[] years = year.split("-");
            int birthYear = Integer.parseInt(years[0]);
            int deadYear = Integer.parseInt(years[1]);

            if (birthYear < deadYear) {
                return true;
            } else {
                System.out.println(Main.Red + "The birth year should be smaller than the dead year!" + Main.Reset);
                System.out.print("Enter Author Active Year (YYYY-YYYY): ");
                return false;
            }
        } else if (year.matches("^\\d{4}$")) {
            return true;
        } else {
            System.out.println(Main.Red + "Please input the active year with the correct format! (e.g., 2000-2040)" + Main.Reset);
            return false;
        }
    }
}
