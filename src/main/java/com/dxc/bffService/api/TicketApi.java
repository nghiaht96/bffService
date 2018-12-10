package com.dxc.bffService.api;

import com.dxc.bffService.api.model.Ticket;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bffService",url = "${ticket.url}")
public interface TicketApi {
    @PostMapping("/v1/tickets/")
    String upsertTicket(@RequestBody Ticket ticket);

    @GetMapping("/v1/tickets/{idTicket}")
    Ticket getTicketById(@PathVariable("idTicket") String idTicket);

    @DeleteMapping("/v1/tickets/{idTicket}")
    String deleteTicketById(@PathVariable("idTicket") String idTicket);

    @GetMapping("/v1/tickets/totalBorrowingTicketOfBook")
    String getTotalBorrowingOfBook(@RequestParam("isbn") String isbn);

    @GetMapping("/v1/tickets/statisticsForNumberBorrowingTicket")
    String statisticsForNumberBorrowingTicket(@RequestParam("type") String type);

    @GetMapping("/v1/tickets/statisticsIncome")
    String statisticsIncome(@RequestParam("type") String type);

    @PutMapping("/v1/tickets/{idTicket}/setLimitBook")
    void setLimitBorrowingBook(@PathVariable("idTicket") String idTicket, @RequestParam("limitBookNumber") int limitBookNumber);

    @PutMapping("/v1/tickets/{idTicket}/returnBook")
    List<String> returnBook(@RequestBody List<String> listIsbn, @PathVariable("idTicket") String idTicket);
}
