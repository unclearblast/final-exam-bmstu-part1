package menu;

import dao.*;
import model.*;

import java.util.Scanner;

public class Menu {

    private final BookDAO bookDAO = new BookDAO();
    private final ReaderDAO readerDAO = new ReaderDAO();
    private final LoanDAO loanDAO = new LoanDAO();

    private final Scanner scanner = new Scanner(System.in);

    public void start() {

        while (true) {

            System.out.println("\n==== LIBRARY MENU ====");
            System.out.println("1 Добавить книгу");
            System.out.println("2 Показать книги");
            System.out.println("3 Найти книгу");
            System.out.println("4 Зарегистрировать читателя");
            System.out.println("5 Показать читателей");
            System.out.println("6 Выдать книгу");
            System.out.println("7 Вернуть книгу");
            System.out.println("8 Выданные книги");
            System.out.println("9 Популярные книги");
            System.out.println("0 Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1 -> addBook();
                case 2 -> showBooks();
                case 3 -> findBook();
                case 4 -> addReader();
                case 5 -> readerDAO.showReaders();
                case 6 -> issueBook();
                case 7 -> returnBook();
                case 8 -> loanDAO.showIssuedBooks();
                case 9 -> loanDAO.popularBooks();
                case 0 -> System.exit(0);
            }
        }
    }

    private void addBook() {

        System.out.print("Название: ");
        String title = scanner.nextLine();

        System.out.print("Автор: ");
        String author = scanner.nextLine();

        System.out.print("Год: ");
        int year = scanner.nextInt();

        bookDAO.addBook(new Book(title, author, year));
    }

    private void showBooks() {

        bookDAO.getAllBooks().forEach(
                b -> System.out.println(
                        b.getId() + " | " + b.getTitle() + " | " + b.getAuthor()
                )
        );
    }

    private void findBook() {

        System.out.print("Введите название: ");
        String title = scanner.nextLine();

        bookDAO.findByTitle(title);
    }

    private void addReader() {

        System.out.print("Имя: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        readerDAO.addReader(new Reader(name, email));
    }

    private void issueBook() {

        System.out.print("ID книги: ");
        int bookId = scanner.nextInt();

        System.out.print("ID читателя: ");
        int readerId = scanner.nextInt();

        loanDAO.issueBook(bookId, readerId);
    }

    private void returnBook() {

        System.out.print("ID книги: ");
        int bookId = scanner.nextInt();

        loanDAO.returnBook(bookId);
    }
}
