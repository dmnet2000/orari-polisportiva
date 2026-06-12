package com.polisportiva.controller;

import com.polisportiva.dto.DisciplinaDTO;
import com.polisportiva.service.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/discipline")
@Tag(name = "Discipline", description = "Sports disciplines management endpoints")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping
    @Operation(summary = "Get all disciplines", description = "Retrieve all sports disciplines")
    public ResponseEntity<List<DisciplinaDTO>> getAllDiscipline() {
        List<DisciplinaDTO> discipline = disciplinaService.getAllDiscipline();
        return ResponseEntity.ok(discipline);
    }

    @GetMapping("/attive")
    @Operation(summary = "Get active disciplines", description = "Retrieve only active disciplines")
    public ResponseEntity<List<DisciplinaDTO>> getDisciplineAttive() {
        List<DisciplinaDTO> discipline = disciplinaService.getDisciplineAttive();
        return ResponseEntity.ok(discipline);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get discipline by ID", description = "Retrieve a specific discipline")
    public ResponseEntity<DisciplinaDTO> getDisciplinaById(@PathVariable Long id) {
        DisciplinaDTO disciplina = disciplinaService.getDisciplinaById(id);
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Create new discipline", description = "Create a new sports discipline (Admin only)")
    public ResponseEntity<DisciplinaDTO> createDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO createdDisciplina = disciplinaService.createDisciplina(disciplinaDTO);
        return new ResponseEntity<>(createdDisciplina, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Update discipline", description = "Update an existing discipline (Admin only)")
    public ResponseEntity<DisciplinaDTO> updateDisciplina(@PathVariable Long id, @RequestBody DisciplinaDTO disciplinaDTO) {
        DisciplinaDTO updatedDisciplina = disciplinaService.updateDisciplina(id, disciplinaDTO);
        return ResponseEntity.ok(updatedDisciplina);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Delete discipline", description = "Delete a discipline (Admin only)")
    public ResponseEntity<?> deleteDisciplina(@PathVariable Long id) {
        disciplinaService.deleteDisciplina(id);
        return ResponseEntity.ok("Discipline deleted successfully");
    }
}
