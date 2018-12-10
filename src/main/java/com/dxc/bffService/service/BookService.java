package com.dxc.bffService.service;

import com.dxc.bffService.api.BookServiceApi;
import com.dxc.bffService.api.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookServiceApi api;

    private static final Logger LOGGER = LoggerFactory.getLogger(String.class);

    @Cacheable("searchBook")
    public List<Book> searchBook(String title, String author, String publishedYear, String isbn, String shortDescription){
        return api.searchBook(title,author,publishedYear,isbn,shortDescription);
    }

    @Cacheable("searchBookById")
    public Book searchBookById(String id){
        return api.searchBookById(id);
    }

    @Cacheable("searchByStatus")
    public List<Book> searchByStatus(boolean status){
        LOGGER.info("Search " +api.searchByStatus(status).toString());
        return api.searchByStatus(status);
    }


    @CacheEvict(value = {"searchByStatus","searchBookId","searchBook"}, allEntries = true)
    public List<String> upsertMultiBooks(String books){
        return api.upsertMultiBook(books);
    }

    @CacheEvict(value = {"searchByStatus","searchBookId","searchBook"}, allEntries = true)
    public String upsertBook(String book){
        return api.upsertBook(book);
    }

    @CacheEvict(value = {"searchByStatus","searchBookId","searchBook"}, allEntries = true)
    public List<String> deleteMultiBooks(List<String> listId){
        return api.deleteMultiBooks(listId);
    }

    @CacheEvict(value = {"searchByStatus","searchBookId","searchBook"}, allEntries = true)
    public String deleteBoook(String id){
        return api.deleteBook(id);
    }

    public List<Book> searchBookByNumberTicket(Integer numberBorrowingTicket){
        return api.searchBookByNumberTicket(numberBorrowingTicket);
    }
}
