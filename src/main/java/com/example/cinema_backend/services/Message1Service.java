package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.Message1DTO;
import com.example.cinema_backend.entities.Message1;
import com.example.cinema_backend.repositories.Message1Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Message1Service {

    @Autowired
    private Message1Repository message1Repository;

    @Autowired
    private ModelMapper modelMapper;

    public Message1DTO saveMessage(Message1DTO messageDTO) {
        Message1 message = modelMapper.map(messageDTO, Message1.class);
        Message1 savedMessage = message1Repository.save(message);
        return modelMapper.map(savedMessage, Message1DTO.class);
    }

    public List<Message1DTO> getMessagesByRoomId(Long roomId) {
        List<Message1> messages = message1Repository.findByRoom1Id(roomId);
        return messages.stream()
                .map(message -> modelMapper.map(message, Message1DTO.class))
                .collect(Collectors.toList());
    }
}
