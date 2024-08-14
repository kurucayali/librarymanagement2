package dev.patika.librarymanagement2.business.absract;


import dev.patika.librarymanagement2.entites.Author;

public interface IAuthorService {
    Author save(Author author);
    Author get(int id);
    Author update(Author author);
    boolean delete(int id);
}
