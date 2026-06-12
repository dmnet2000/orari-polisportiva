package com.polisportiva.controller;

import com.polisportiva.dto.GruppoDTO;
import com.polisportiva.service.GruppoService;
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
@RequestMapping("/gruppi")
@Tag(name = "Gruppi", description = "Training groups management endpoints")
public class GruppoController {

    @Autowired
    private GruppoService gruppoService;

    @GetMapping
    @Operation(summary = "Get all groups", description = "Retrieve all training groups")
    public ResponseEntity<List<GruppoDTO>> getAllGruppi() {
        List<GruppoDTO> gruppi = gruppoService.getAllGruppi();
        return ResponseEntity.ok(gruppi);
    }

    @GetMapping("/attivi")
    @Operation(summary = "Get active groups", description = "Retrieve only active training groups")
    public ResponseEntity<List<GruppoDTO>> getGruppiAttivi() {
        List<GruppoDTO> gruppi = gruppoService.getGruppiAttivi();
        return ResponseEntity.ok(gruppi);
    }

    @GetMapping("/disciplina/{disciplinaId}")
    @Operation(summary = "Get groups by discipline", description = "Retrieve all training groups for a specific discipline")
    public ResponseEntity<List<GruppoDTO>> getGruppiByDisciplina(@PathVariable Long disciplinaId) {
        List<GruppoDTO> gruppi = gruppoService.getGruppiByDisciplina(disciplinaId);
        return ResponseEntity.ok(gruppi);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get group by ID", description = "Retrieve a specific training group")
    public ResponseEntity<GruppoDTO> getGruppoById(@PathVariable Long id) {
        GruppoDTO gruppo = gruppoService.getGruppoById(id);
        return ResponseEntity.ok(gruppo);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Create new group", description = "Create a new training group (Admin only)")
    public ResponseEntity<GruppoDTO> createGruppo(@RequestBody GruppoDTO gruppoDTO) {
        GruppoDTO createdGruppo = gruppoService.createGruppo(gruppoDTO);
        return new ResponseEntity<>(createdGruppo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Update group", description = "Update an existing training group (Admin only)")
    public ResponseEntity<GruppoDTO> updateGruppo(@PathVariable Long id, @RequestBody GruppoDTO gruppoDTO) {
        GruppoDTO updatedGruppo = gruppoService.updateGruppo(id, gruppoDTO);
        return ResponseEntity.ok(updatedGruppo);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Delete group", description = "Delete a training group (Admin only)")
    public ResponseEntity<?> deleteGruppo(@PathVariable Long id) {
        gruppoService.deleteGruppo(id);
        return ResponseEntity.ok("Group deleted successfully");
    }
}
