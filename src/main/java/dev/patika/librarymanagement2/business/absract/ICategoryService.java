package dev.patika.librarymanagement2.business.absract;


import dev.patika.librarymanagement2.entites.Category;

public interface ICategoryService {
    Category save(Category category);
    Category get(int id);
    Category update(Category category);
    String delete(int id);
}
