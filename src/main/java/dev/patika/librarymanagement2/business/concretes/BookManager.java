package dev.patika.librarymanagement2.business.concretes;

import dev.patika.librarymanagement2.business.absract.IBookService;
import dev.patika.librarymanagement2.core.exception.NotFoundException;
import dev.patika.librarymanagement2.core.utilies.Msg;
import dev.patika.librarymanagement2.dao.BookRepo;
import dev.patika.librarymanagement2.dao.CategoryRepo;
import dev.patika.librarymanagement2.entites.Book;
import dev.patika.librarymanagement2.entites.Category;
import org.springframework.stereotype.Service;

@Service
public class BookManager implements IBookService {
    private final BookRepo bookRepo;
    private final CategoryRepo categoryRepo;

    public BookManager(BookRepo bookRepo, CategoryRepo categoryRepo) {
        this.bookRepo = bookRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Book save(Book book) {
        Book savedBook = this.bookRepo.save(book);
        // Kategorileri kaydedin
        for (Category category : book.getCategories()) {
            category.getBooks().add(savedBook);
            categoryRepo.save(category);
        }
        return savedBook;
    }

    @Override
    public Book get(int id) {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Book update(Book book) {
        Book existingBook = this.bookRepo.findById(book.getBookId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));

        // Mevcut borrowings koleksiyonunu koruyun
        if (book.getBorrowings() == null) {
            book.setBorrowings(existingBook.getBorrowings());
        }

        return this.bookRepo.save(book);
    }

    @Override
    public boolean delete(int id) {
        Book book = this.get(id);
        bookRepo.delete(book);
        return true;
    }
}
