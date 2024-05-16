package org.example.barappapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.barappapi.model.Barmaker;
import org.example.barappapi.service.BarmakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("barmaker")
@RequiredArgsConstructor
public class BarmakerController {
    @Autowired
    private final BarmakerService barmakerService;

    @GetMapping("")
    public ResponseEntity<List<Barmaker>> getAllBarmakers() {
        try {
            return ResponseEntity.ok(barmakerService.getAllBarmakers());
        }catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Barmaker> getBarmakerById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(barmakerService.getBarmakerById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteBarmaker(@PathVariable Long id) {
        try {
            barmakerService.deleteBarmakerById(id);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

}
