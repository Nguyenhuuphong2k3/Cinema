package com.example.cinema_backend.services;

import com.example.cinema_backend.dtos.BranchDTO;

import java.util.List;

public interface IBranchService {
    List<BranchDTO> getBranchesThatShowTheMovie(Integer movieId);
}
