package com.example.cinema_backend.services;


import com.example.cinema_backend.entities.Role;

public interface IRoleService extends IGeneralService<Role> {
    Role findByName(String name);
}