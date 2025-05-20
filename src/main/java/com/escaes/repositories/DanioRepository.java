package com.escaes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escaes.models.Danios;

public interface DanioRepository extends JpaRepository<Danios, Long> {

}
