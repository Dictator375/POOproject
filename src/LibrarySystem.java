import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    private static ArrayList<Book> books = new ArrayList<>();
    private static ArrayList<Borrower> borrowers = new ArrayList<>();
    private static ArrayList<BorrowingProcess> borrowings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add a book");
            System.out.println("2. Add a borrower");
            System.out.println("3. Book loan");
            System.out.println("4. Retrieve a book");
            System.out.println("5. View all books");
            System.out.println("0. exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addBorrower();
                    break;
                case 3:
                    borrowBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    listBooks();
                    break;
                case 0:
                System.out.println("Exit the system...");
                break;
            default:
                System.out.println("Incorrect choice!");
            }
        } while (choice != 0);
    }

    private static void addBook() {
        System.out.print("Book title: ");
        String title = scanner.nextLine();
        System.out.print("Author's name: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String ISBN = scanner.nextLine();
        System.out.print("Book type (paper/electronic): ");
        String type = scanner.nextLine();

        Book book;
        if (typeChoice == 1) {
            book = new PaperBook(title, author, ISBN);
        } else {
            book = new EBook(title, author, ISBN);
        }

        books.add(book);
        System.out.println("The book has been added successfully..");
    }

    private static void addBorrower() {
        System.out.print("Borrower's name: ");
        String name = scanner.nextLine();
        System.out.print("University number: ");
        String universityID = scanner.nextLine();

        Borrower borrower = new Borrower(name, universityID);
        borrowers.add(borrower);
        System.out.println("The borrower has been added successfully..");
    }


    private static void borrowBook() {
        System.out.print("ISBN of the book: ");
        String isbn = scanner.nextLine();
        System.out.print("Borrower's university number: ");
        String universityID = scanner.nextLine();

        Book book = findBookByISBN(isbn);
        Borrower borrower = findBorrowerByID(universityID);

        if (book != null && borrower != null && !book.isBorrowed()) {
            book.borrow();
            borrowings.add(new BorrowingProcess(book, borrower));
            System.out.println("The book has been successfully loaned..");
        } else {
            System.out.println("The book could not be borrowed. Check the information..");
        }
    }

    private static void returnBook() {
        System.out.print("ISBN number of the book to be returned:");
        String isbn = scanner.nextLine();

        for (BorrowingProcess bp : borrowings) {
            if (bp.getBook().getISBN().equals(isbn) && bp.getReturnDate() == null) {
                bp.returnBook();
                System.out.println("The book has been successfully retrieved..");
                return;
            }
        }
        System.out.println("No active checkout was found for this book..");
    }

    private static void listBooks() {
        for (Book book : books) {
            System.out.println("- " + book.getTitle() + " (" + book.getType() + ") | " + (book.isBorrowed() ? "معار" : "متوفر"));
        }
    }

    private static Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private static Borrower findBorrowerByID(String id) {
        for (Borrower borrower : borrowers) {
            if (borrower.getUniversityID().equals(id)) {
                return borrower;
            }
        }
        return null;
    }
}