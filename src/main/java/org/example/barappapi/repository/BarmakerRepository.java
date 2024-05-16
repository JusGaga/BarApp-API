package org.example.barappapi.repository;

import org.example.barappapi.model.Barmaker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarmakerRepository extends JpaRepository<Barmaker, Long> {
    Optional<Barmaker> findByEmail(String email);
}
