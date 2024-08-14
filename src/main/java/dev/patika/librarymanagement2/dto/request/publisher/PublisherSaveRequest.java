package dev.patika.librarymanagement2.dto.request.publisher;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublisherSaveRequest {
    private String publisherName;
    private String publisherEstablishmentYear;
    private String address;
}