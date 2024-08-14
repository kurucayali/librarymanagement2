package dev.patika.librarymanagement2.business.concretes;


import dev.patika.librarymanagement2.business.absract.IPublisherService;
import dev.patika.librarymanagement2.core.exception.NotFoundException;
import dev.patika.librarymanagement2.core.utilies.Msg;
import dev.patika.librarymanagement2.dao.PublisherRepo;
import dev.patika.librarymanagement2.entites.Publisher;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Publisher update(Publisher publisher) {
        Publisher existingPublisher = this.get(publisher.getPublisherId());
        existingPublisher.setPublisherName(publisher.getPublisherName());
        existingPublisher.setPublisherEstablishmentYear(publisher.getPublisherEstablishmentYear());
        existingPublisher.setAddress(publisher.getAddress());
        existingPublisher.setBooks(publisher.getBooks());
        return this.publisherRepo.save(existingPublisher);
    }

    @Override
    public boolean delete(int id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }
}