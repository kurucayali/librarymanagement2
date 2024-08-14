package dev.patika.librarymanagement2.business.concretes;


import dev.patika.librarymanagement2.business.absract.IBookBorrowingService;
import dev.patika.librarymanagement2.core.exception.NotFoundException;
import dev.patika.librarymanagement2.core.utilies.Msg;
import dev.patika.librarymanagement2.dao.BookBorrowingRepo;
import dev.patika.librarymanagement2.dao.BookRepo;
import dev.patika.librarymanagement2.entites.BookBorrowing;
import dev.patika.librarymanagement2.entites.Book;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
public class BookBorrowingManager implements IBookBorrowingService {
    private final BookBorrowingRepo bookBorrowingRepo;
    private final BookRepo bookRepo;

    public BookBorrowingManager(BookBorrowingRepo bookBorrowingRepo, BookRepo bookRepo) {
        this.bookBorrowingRepo = bookBorrowingRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public BookBorrowing save(BookBorrowing bookBorrowing) {
        Book book = bookRepo.findById(bookBorrowing.getBook().getBookId())
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
        if (book.getBookStock() <= 0) {
            throw new RuntimeException("Kitap stokta yok.");
        }
        book.setBookStock(book.getBookStock() - 1);
        bookRepo.save(book);
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public BookBorrowing get(int id) {
        return this.bookBorrowingRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public BookBorrowing update(BookBorrowing bookBorrowing) {
        return this.bookBorrowingRepo.save(bookBorrowing);
    }

    @Override
    public boolean delete(int id) {
        BookBorrowing bookBorrowing = this.get(id);
        bookBorrowingRepo.delete(bookBorrowing);
        return true;
    }
}