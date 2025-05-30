package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.BranchDTO;
import com.example.cinema_backend.repositories.IBranchRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BranchService implements IBranchService{

    @Autowired
    private IBranchRepository branchRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<BranchDTO> getBranchesThatShowTheMovie(Integer movieId) {
        return branchRepository.getBranchThatShowTheMovie(movieId)
                .stream().map(branch -> modelMapper.map(branch,BranchDTO.class))
                .collect(Collectors.toList());
    }
}
