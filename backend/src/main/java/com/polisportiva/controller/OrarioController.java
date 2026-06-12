package com.polisportiva.controller;

import com.polisportiva.dto.OrarioDTO;
import com.polisportiva.service.OrarioService;
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
@RequestMapping("/orari")
@Tag(name = "Orari", description = "Schedules management endpoints")
public class OrarioController {

    @Autowired
    private OrarioService orarioService;

    @GetMapping
    @Operation(summary = "Get all schedules", description = "Retrieve all training schedules")
    public ResponseEntity<List<OrarioDTO>> getAllOrari() {
        List<OrarioDTO> orari = orarioService.getAllOrari();
        return ResponseEntity.ok(orari);
    }

    @GetMapping("/attivi")
    @Operation(summary = "Get active schedules", description = "Retrieve only active schedules")
    public ResponseEntity<List<OrarioDTO>> getOrariAttivi() {
        List<OrarioDTO> orari = orarioService.getOrariAttivi();
        return ResponseEntity.ok(orari);
    }

    @GetMapping("/palestra/{palestraId}")
    @Operation(summary = "Get schedules by facility", description = "Retrieve all schedules for a specific facility")
    public ResponseEntity<List<OrarioDTO>> getOrariByPalestra(@PathVariable Long palestraId) {
        List<OrarioDTO> orari = orarioService.getOrariByPalestra(palestraId);
        return ResponseEntity.ok(orari);
    }

    @GetMapping("/gruppo/{gruppoId}")
    @Operation(summary = "Get schedules by group", description = "Retrieve all schedules for a specific group")
    public ResponseEntity<List<OrarioDTO>> getOrariByGruppo(@PathVariable Long gruppoId) {
        List<OrarioDTO> orari = orarioService.getOrariByGruppo(gruppoId);
        return ResponseEntity.ok(orari);
    }

    @GetMapping("/giorno/{giorno}")
    @Operation(summary = "Get schedules by day", description = "Retrieve all schedules for a specific day of week")
    public ResponseEntity<List<OrarioDTO>> getOrariByGiorno(@PathVariable String giorno) {
        List<OrarioDTO> orari = orarioService.getOrariByGiorno(giorno);
        return ResponseEntity.ok(orari);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get schedule by ID", description = "Retrieve a specific training schedule")
    public ResponseEntity<OrarioDTO> getOrarioById(@PathVariable Long id) {
        OrarioDTO orario = orarioService.getOrarioById(id);
        return ResponseEntity.ok(orario);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Create new schedule", description = "Create a new training schedule (Admin only)")
    public ResponseEntity<OrarioDTO> createOrario(@RequestBody OrarioDTO orarioDTO) {
        OrarioDTO createdOrario = orarioService.createOrario(orarioDTO);
        return new ResponseEntity<>(createdOrario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Update schedule", description = "Update an existing training schedule (Admin only)")
    public ResponseEntity<OrarioDTO> updateOrario(@PathVariable Long id, @RequestBody OrarioDTO orarioDTO) {
        OrarioDTO updatedOrario = orarioService.updateOrario(id, orarioDTO);
        return ResponseEntity.ok(updatedOrario);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Delete schedule", description = "Delete a training schedule (Admin only)")
    public ResponseEntity<?> deleteOrario(@PathVariable Long id) {
        orarioService.deleteOrario(id);
        return ResponseEntity.ok("Schedule deleted successfully");
    }
}
