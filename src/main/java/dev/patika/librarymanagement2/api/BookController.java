package dev.patika.librarymanagement2.api;


import dev.patika.librarymanagement2.business.absract.IBookService;
import dev.patika.librarymanagement2.business.absract.ICategoryService;
import dev.patika.librarymanagement2.core.config.ModelMapper.IModelMapperService;
import dev.patika.librarymanagement2.core.result.Result;
import dev.patika.librarymanagement2.core.result.ResultData;
import dev.patika.librarymanagement2.core.utilies.ResultHelper;
import dev.patika.librarymanagement2.dto.request.book.BookSaveRequest;
import dev.patika.librarymanagement2.dto.request.book.BookUpdateRequest;
import dev.patika.librarymanagement2.dto.response.BookResponse;
import dev.patika.librarymanagement2.entites.Book;
import dev.patika.librarymanagement2.entites.Category;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final IBookService bookService;
    private final ICategoryService categoryService;
    private final IModelMapperService modelMapper;

    public BookController(IBookService bookService, ICategoryService categoryService, IModelMapperService modelMapper) {
        this.bookService = bookService;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookResponse> save(@Valid @RequestBody BookSaveRequest bookSaveRequest) {
        Book saveBook = this.modelMapper.forRequest().map(bookSaveRequest, Book.class);
        List<Category> categories = bookSaveRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.get(categoryId))
                .collect(Collectors.toList());
        saveBook.setCategories(categories);
        this.bookService.save(saveBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(saveBook, BookResponse.class);
        return ResultHelper.created(bookResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> update(@Valid @RequestBody BookUpdateRequest bookUpdateRequest) {
        Book updateBook = this.modelMapper.forRequest().map(bookUpdateRequest, Book.class);
        List<Category> categories = bookUpdateRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.get(categoryId))
                .collect(Collectors.toList());
        updateBook.setCategories(categories);
        this.bookService.update(updateBook);
        BookResponse bookResponse = this.modelMapper.forResponse().map(updateBook, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookResponse> get(@PathVariable("id") int id) {
        Book book = this.bookService.get(id);
        BookResponse bookResponse = this.modelMapper.forResponse().map(book, BookResponse.class);
        return ResultHelper.success(bookResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookService.delete(id);
        return ResultHelper.ok();
    }
}
