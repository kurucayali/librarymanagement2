package dev.patika.librarymanagement2.business.absract;


import dev.patika.librarymanagement2.entites.Book;

public interface IBookService {
    Book save(Book book);
    Book get(int id);
    Book update(Book book);
    boolean delete(int id);
}
