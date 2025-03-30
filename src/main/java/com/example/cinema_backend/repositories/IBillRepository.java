package com.example.cinema_backend.repositories;

import com.example.cinema_backend.entities.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBillRepository extends JpaRepository<Bill, Integer> {
}