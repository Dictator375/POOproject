public class Borrower {
    private String name;
    private String universityID;

    public Borrower(String name, String universityID) {
        this.name = name;
        this.universityID = universityID;
    }

    public String getName() {
        return name;
    }

    public String getUniversityID() {
        return universityID;
    }
}