package com.example.cinema_backend.dtos;

import lombok.Data;

@Data
public class TicketDTO {
    private int id;
    private String qrImageURL;
    private ScheduleDTO schedule;
    private SeatDTO seat;
    private BillDTO bill;
}
