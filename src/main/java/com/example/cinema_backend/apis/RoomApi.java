package com.example.cinema_backend.apis;

import com.example.cinema_backend.dtos.RoomDTO;
import com.example.cinema_backend.services.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/rooms")
public class RoomApi {
    @Autowired
    private IRoomService roomService;

    @GetMapping
    public List<RoomDTO> getRooms(@RequestParam Integer movieId,@RequestParam Integer branchId,
                                  @RequestParam String startDate,@RequestParam String startTime){
        return roomService.getRooms(movieId, branchId, startDate, startTime);
    }
}
