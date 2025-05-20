package com.escaes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escaes.models.Users;

public interface UserRepository extends JpaRepository<Users,Long> {
    Optional<Users>findByCorreoUsuario(String correoUsuario);

}
