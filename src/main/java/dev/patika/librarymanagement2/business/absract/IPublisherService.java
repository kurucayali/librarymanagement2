package dev.patika.librarymanagement2.business.absract;


import dev.patika.librarymanagement2.entites.Publisher;

public interface IPublisherService {
    Publisher save(Publisher publisher);
    Publisher get(int id);
    Publisher update(Publisher publisher);
    boolean delete(int id);
}

