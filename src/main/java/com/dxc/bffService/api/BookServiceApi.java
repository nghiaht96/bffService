package com.dxc.bffService.api;

import com.dxc.bffService.api.model.Book;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bbfService",url = "${book.url}")
public interface BookServiceApi {
    @GetMapping("/v1/library/books/")
    List<Book> searchBook(@RequestParam("title") String title, @RequestParam("author") String author,
                          @RequestParam("publishedYear") String publishedYear, @RequestParam("isbn") String isbn,
                          @RequestParam("shortDescription") String shortDescription);

    @GetMapping("/v1/library/books/{id}")
    Book searchBookById(@PathVariable("id") String id);

    @GetMapping(value = "/v1/library/books/searchByStatus/", produces = "application/json")
    List<Book> searchByStatus(@RequestParam("status") boolean status);

    @PostMapping(value = "/v1/library/books/",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<String> upsertMultiBook(@RequestBody String books);

    @PutMapping(value = "/v1/library/books/",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String upsertBook(@RequestBody String book);

    @DeleteMapping("/v1/library/books/")
    List<String> deleteMultiBooks(@RequestBody List<String> listId);

    @DeleteMapping("/v1/library/books/{id}")
    String deleteBook(@PathVariable("id") String id);

    @GetMapping("/v1/library/books/checkAvailableBook/{isbn}")
    String checkAvailableBook(@PathVariable("isbn") String isbn);

    @PutMapping("/v1/library/books/updateCurrentQuantity/")
    List<String>updateCurrentQuantityOfBook(@RequestBody List<String> listIsbn,@RequestParam("typeUpdate") Integer typeUpdate);

    @GetMapping("/v1/library/books/searchByNumberTicket/")
    List<Book> searchBookByNumberTicket(@RequestParam("numberBorrowingTicket") Integer numberBorrowingTicket);
}
