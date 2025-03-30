package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.RoomDTO;

import java.util.List;

public interface IRoomService {
    List<RoomDTO> getRooms(Integer movieId,Integer branchId,String startDate,String startTime);
}
