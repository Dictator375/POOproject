public class LibrarySystemTest {
    public static void main(String[] args) {
        Book book = new PaperBook("Java Basics", "Ali", "111");
        assert book.getTitle().equals("Java Basics");
        assert !book.isBorrowed();
        book.borrow();
        assert book.isBorrowed();
        book.returnItem();
        assert !book.isBorrowed();
        System.out.println(" All tests succeeded!");
    }
}