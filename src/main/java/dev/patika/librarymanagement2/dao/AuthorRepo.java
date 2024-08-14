package dev.patika.librarymanagement2.dao;

import dev.patika.librarymanagement2.entites.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {
}
