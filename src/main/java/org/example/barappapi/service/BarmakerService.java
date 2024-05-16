package org.example.barappapi.service;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.model.Barmaker;
import org.example.barappapi.repository.BarmakerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarmakerService {
    private final BarmakerRepository barmakerRepository;

    public List<Barmaker> getAllBarmakers() {
        return barmakerRepository.findAll();
    }

    public Barmaker getBarmakerById(Long id) {
        return barmakerRepository.findById(id).orElse(null);
    }

    public void deleteBarmakerById(Long id) {
        barmakerRepository.deleteById(id);
    }
}
