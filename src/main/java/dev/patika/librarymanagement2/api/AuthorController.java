package dev.patika.librarymanagement2.api;

import dev.patika.librarymanagement2.business.absract.IAuthorService;
import dev.patika.librarymanagement2.core.config.ModelMapper.IModelMapperService;
import dev.patika.librarymanagement2.core.result.Result;
import dev.patika.librarymanagement2.core.result.ResultData;
import dev.patika.librarymanagement2.core.utilies.ResultHelper;
import dev.patika.librarymanagement2.dto.request.author.AuthorSaveRequest;
import dev.patika.librarymanagement2.dto.request.author.AuthorUpdateRequest;
import dev.patika.librarymanagement2.dto.response.AuthorResponse;
import dev.patika.librarymanagement2.entites.Author;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/authors")
public class AuthorController {
    private final IAuthorService authorService;
    private final IModelMapperService modelMapper;

    public AuthorController(IAuthorService authorService, IModelMapperService modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AuthorResponse> save(@Valid @RequestBody AuthorSaveRequest authorSaveRequest) {
        Author saveAuthor = this.modelMapper.forRequest().map(authorSaveRequest, Author.class);
        this.authorService.save(saveAuthor);
        AuthorResponse authorResponse = this.modelMapper.forResponse().map(saveAuthor, AuthorResponse.class);
        return ResultHelper.created(authorResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> get(@PathVariable("id") int id) {
        Author author = this.authorService.get(id);
        AuthorResponse authorResponse = this.modelMapper.forResponse().map(author, AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AuthorResponse> update(@Valid @RequestBody AuthorUpdateRequest authorUpdateRequest) {
        Author updateAuthor = this.modelMapper.forRequest().map(authorUpdateRequest, Author.class);
        this.authorService.update(updateAuthor);
        AuthorResponse authorResponse = this.modelMapper.forResponse().map(updateAuthor, AuthorResponse.class);
        return ResultHelper.success(authorResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.authorService.delete(id);
        return ResultHelper.ok();
    }
}
