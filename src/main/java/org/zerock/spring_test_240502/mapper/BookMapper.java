package org.zerock.spring_test_240502.mapper;

import org.zerock.spring_test_240502.domain.BookVO;

import java.util.List;

public interface BookMapper {

    void addBook(BookVO bookVO);

    List<BookVO> bookList();

    BookVO bookInfo(String id);

    void delete(String id);

    void update(BookVO bookVO);

}
