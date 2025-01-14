package dev.patika.librarymanagement2.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookBorrowingResponse {

    private int id;
    private String name;
    private LocalDate borrowingDate;
    private LocalDate returnDate;
    private int bookId;

}
