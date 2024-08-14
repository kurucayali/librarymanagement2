package dev.patika.librarymanagement2.dao;

import dev.patika.librarymanagement2.entites.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends JpaRepository<Publisher, Integer> {
}