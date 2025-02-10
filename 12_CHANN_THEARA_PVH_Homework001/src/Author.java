public class Author {
    private String authorName;
    private String authorActiveYear;

    public Author(String authorName, String authorActiveYear){
        this.authorName = authorName;
        this.authorActiveYear = authorActiveYear;
    }

    public String getAuthorName(){
        return authorName;
    }

    public void setAuthorName(String authorName) {
        if (!allValidation.isValidAuthorName(authorName)) {
            System.out.println("Invalid author name. Must be 2-50 letters.");
        }
        this.authorName = authorName.trim();
    }

    public String getAuthorActiveYear() {
        return authorActiveYear;
    }

    public void setAuthorActiveYear(String authorActiveYear){
        if (!allValidation.isValidYear(authorActiveYear)) {
            System.out.println("Invalid author active year.");
        }
        this.authorActiveYear = authorActiveYear;
    }
}
