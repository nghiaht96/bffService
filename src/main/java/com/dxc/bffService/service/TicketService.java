package com.dxc.bffService.service;

import com.dxc.bffService.api.BookServiceApi;
import com.dxc.bffService.api.TicketApi;
import com.dxc.bffService.api.model.Ticket;
import com.dxc.bffService.api.model.TicketDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketApi api;

    @Autowired
    private BookServiceApi bookApi;

    @Autowired
    private UserService userService;

    public String upsertTicket(Ticket ticket) {
        List<String> listIsbn = new ArrayList<>();
        userService.getUserByUsername(ticket.getUsername());
        for (int i = 0, len = ticket.getTicketDetails().size(); i < len; i++) {
            String isbn = bookApi.checkAvailableBook(ticket.getTicketDetails().get(i).getIsbn());
            if (isbn == null) {
                ticket.getTicketDetails().remove(ticket.getTicketDetails().get(i));
            } else {
                listIsbn.add(isbn);
            }
        }
        String rs = api.upsertTicket(ticket);
        bookApi.updateCurrentQuantityOfBook(listIsbn, -1);
        return rs;
    }

    public Ticket getTicketById(String idTicket) {
        return api.getTicketById(idTicket);
    }

    public String deleteTicketById(String idTicket) {
        return api.deleteTicketById(idTicket);
    }

    public String getTotalBorrowingOfBook(String isbn) {
        return api.getTotalBorrowingOfBook(isbn);
    }

    public String statisticsForNumberBorrowingTicket(String type) {
        return api.statisticsForNumberBorrowingTicket(type);
    }

    public String statisticsIncome(String type) {
        return api.statisticsIncome(type);
    }

    public void setLimitBorrowingBook(String idTicket, int limitBookNumber) {
        api.setLimitBorrowingBook(idTicket, limitBookNumber);
    }

    public List<String> returnBook(List<String> listIsbn, String idTicket) {
        List<String> rs = api.returnBook(listIsbn, idTicket);
        bookApi.updateCurrentQuantityOfBook(listIsbn, 1);
        return rs;
    }
}
