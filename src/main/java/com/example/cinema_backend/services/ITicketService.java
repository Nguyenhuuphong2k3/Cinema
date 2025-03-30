package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.TicketDTO;

import java.util.List;

public interface ITicketService {
    List<TicketDTO> getTicketsByUserId(Integer userId);
}
