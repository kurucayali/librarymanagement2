package dev.patika.librarymanagement2.api;


import dev.patika.librarymanagement2.business.absract.IBookBorrowingService;
import dev.patika.librarymanagement2.core.config.ModelMapper.IModelMapperService;
import dev.patika.librarymanagement2.core.result.Result;
import dev.patika.librarymanagement2.core.result.ResultData;
import dev.patika.librarymanagement2.core.utilies.ResultHelper;
import dev.patika.librarymanagement2.dto.request.bookBorrowing.BookBorrowingSaveRequest;
import dev.patika.librarymanagement2.dto.request.bookBorrowing.BookBorrowingUpdateRequest;
import dev.patika.librarymanagement2.dto.response.BookBorrowingResponse;
import dev.patika.librarymanagement2.entites.BookBorrowing;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bookborrowings")
public class BookBorrowingController {
    private final IBookBorrowingService bookBorrowingService;
    private final IModelMapperService modelMapper;

    public BookBorrowingController(IBookBorrowingService bookBorrowingService, IModelMapperService modelMapper) {
        this.bookBorrowingService = bookBorrowingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<BookBorrowingResponse> save(@Valid @RequestBody BookBorrowingSaveRequest bookBorrowingSaveRequest) {
        try {
            BookBorrowing saveBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingSaveRequest, BookBorrowing.class);
            this.bookBorrowingService.save(saveBookBorrowing);
            BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(saveBookBorrowing, BookBorrowingResponse.class);
            return ResultHelper.created(bookBorrowingResponse);
        } catch (RuntimeException e) {
            return ResultHelper.internalServerError(null, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> get(@PathVariable("id") int id) {
        BookBorrowing bookBorrowing = this.bookBorrowingService.get(id);
        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(bookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.success(bookBorrowingResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<BookBorrowingResponse> update(@Valid @RequestBody BookBorrowingUpdateRequest bookBorrowingUpdateRequest) {
        BookBorrowing updateBookBorrowing = this.modelMapper.forRequest().map(bookBorrowingUpdateRequest, BookBorrowing.class);
        this.bookBorrowingService.update(updateBookBorrowing);
        BookBorrowingResponse bookBorrowingResponse = this.modelMapper.forResponse().map(updateBookBorrowing, BookBorrowingResponse.class);
        return ResultHelper.success(bookBorrowingResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.bookBorrowingService.delete(id);
        return ResultHelper.ok();
    }
}