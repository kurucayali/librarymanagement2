package dev.patika.librarymanagement2.business.absract;


import dev.patika.librarymanagement2.entites.BookBorrowing;

public interface IBookBorrowingService {
    BookBorrowing save(BookBorrowing bookBorrowing);
    BookBorrowing get(int id);
    BookBorrowing update(BookBorrowing bookBorrowing);
    boolean delete(int id);
}
