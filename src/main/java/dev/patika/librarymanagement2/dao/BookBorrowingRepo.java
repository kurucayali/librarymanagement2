package dev.patika.librarymanagement2.dao;

import dev.patika.librarymanagement2.entites.BookBorrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookBorrowingRepo extends JpaRepository<BookBorrowing, Integer> {
}
