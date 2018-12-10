package com.dxc.bffService.delegate;

import com.dxc.bffService.api.V1ApiDelegate;
import com.dxc.bffService.api.model.Book;
import com.dxc.bffService.api.model.Ticket;
import com.dxc.bffService.api.model.User;
import com.dxc.bffService.service.BookService;
import com.dxc.bffService.service.TicketService;
import com.dxc.bffService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1Delegate implements V1ApiDelegate {

    @Autowired
    private BookService bookService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    //BOOK

    @Override
    public ResponseEntity<String> deleteBookById(String id) {
        return ResponseEntity.ok(bookService.deleteBoook(id));
    }

    @Override
    public ResponseEntity<List<String>> deleteMultiBook(List<String> listId) {
        return ResponseEntity.ok(bookService.deleteMultiBooks(listId));
    }

    @Override
    public ResponseEntity<List<Book>> searchBook(String title, String author, String publishedYear, String isbn, String shortDescription) {
        return ResponseEntity.ok(bookService.searchBook(title,author,publishedYear,isbn,shortDescription));
    }

    @Override
    public ResponseEntity<Book> searchBookById(String id) {
        return ResponseEntity.ok(bookService.searchBookById(id));
    }

    @Override
    public ResponseEntity<List<Book>> searchBookByStatus(Boolean status) {
        return ResponseEntity.ok(bookService.searchByStatus(status));
    }

    @Override
    public ResponseEntity<List<Book>> searchByNumberBorrowingTicket(Integer numberBorrowingTicket) {
        return ResponseEntity.ok(bookService.searchBookByNumberTicket(numberBorrowingTicket));
    }

    @Override
    public ResponseEntity<String> upsertBook(String book) {
        return ResponseEntity.ok(bookService.upsertBook(book));
    }

    @Override
    public ResponseEntity<List<String>> upsertMultiBooks(String books) {
        return ResponseEntity.ok(bookService.upsertMultiBooks(books));
    }
    /**
     * ticket
     */
    @Override
    public ResponseEntity<String> upsertTicket(Ticket ticket) {
        return ResponseEntity.ok(ticketService.upsertTicket(ticket));
    }

    @Override
    public ResponseEntity<String> deleteTicket(String idTicket) {
        return ResponseEntity.ok(ticketService.deleteTicketById(idTicket));
    }

    @Override
    public ResponseEntity<Ticket> getTicketByIdTicket(String idTicket) {
        return ResponseEntity.ok(ticketService.getTicketById(idTicket));
    }

    @Override
    public ResponseEntity<String> getTotalBorrowingOfBook(String isbn) {
        return ResponseEntity.ok(ticketService.getTotalBorrowingOfBook(isbn));
    }

    @Override
    public ResponseEntity<List<String>> returnBook(List<String> listIsbn, String idTicket) {
        return ResponseEntity.ok(ticketService.returnBook(listIsbn,idTicket));
    }

    @Override
    public ResponseEntity<List<Ticket>> searchTicketByIsbn(String isbn) {
        return null;
    }

    @Override
    public ResponseEntity<Void> setLimitBorrowingBook(String idTicket, Integer limitBookNumber) {
        ticketService.setLimitBorrowingBook(idTicket, limitBookNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> statisticsIncome(String type) {
        return ResponseEntity.ok(ticketService.statisticsIncome(type));
    }

    @Override
    public ResponseEntity<String> statisticsNumberBorrowingTicket(String type) {
        return ResponseEntity.ok(ticketService.statisticsForNumberBorrowingTicket(type));
    }

    //USER

    @Override
    public ResponseEntity<String> deleteUser(String username) {
        return ResponseEntity.ok(userService.deleteUser(username));
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @Override
    public ResponseEntity<String> loginUser(String username, String password) {
        return ResponseEntity.ok(userService.loginUser(username, password));
    }

    @Override
    public ResponseEntity<Void> logoutUser() {
        userService.logoutUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> upsertUser(User user) {
        return ResponseEntity.ok(userService.upsertUseR(user));
    }
}
