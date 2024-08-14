package dev.patika.librarymanagement2.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    private int id;
    private String name;
    private String establishmentYear;
}
