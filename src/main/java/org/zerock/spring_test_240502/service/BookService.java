package org.zerock.spring_test_240502.service;

import org.zerock.spring_test_240502.DTO.BookDTO;

import java.util.List;

public interface BookService {

    void registerBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();

    BookDTO getOneBook(String id);

    void removeBook(String id);

    void modifyBook(BookDTO bookDTO);

}
