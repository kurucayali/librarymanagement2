package dev.patika.librarymanagement2.business.concretes;


import dev.patika.librarymanagement2.business.absract.IAuthorService;
import dev.patika.librarymanagement2.core.exception.NotFoundException;
import dev.patika.librarymanagement2.core.utilies.Msg;
import dev.patika.librarymanagement2.dao.AuthorRepo;
import dev.patika.librarymanagement2.entites.Author;
import org.springframework.stereotype.Service;

@Service
public class AuthorManager implements IAuthorService {

    private final AuthorRepo authorRepo;

    public AuthorManager(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author save(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public Author get(int id) {
        return this.authorRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Author update(Author author) {
        return this.authorRepo.save(author);
    }

    @Override
    public boolean delete(int id) {
        Author author = this.get(id);
        this.authorRepo.delete(author);
        return true;
    }
}