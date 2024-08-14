package dev.patika.librarymanagement2.dao;

import dev.patika.librarymanagement2.entites.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    List<Book> findByCategoriesCategoryId(int categoryId);
}

