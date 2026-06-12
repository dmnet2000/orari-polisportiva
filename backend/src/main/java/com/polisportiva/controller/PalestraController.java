package com.polisportiva.controller;

import com.polisportiva.dto.PalestraDTO;
import com.polisportiva.service.PalestraService;
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
@RequestMapping("/palestre")
@Tag(name = "Palestre", description = "Facilities management endpoints")
public class PalestraController {

    @Autowired
    private PalestraService palestraService;

    @GetMapping
    @Operation(summary = "Get all facilities", description = "Retrieve all facilities")
    public ResponseEntity<List<PalestraDTO>> getAllPalestre() {
        List<PalestraDTO> palestre = palestraService.getAllPalestre();
        return ResponseEntity.ok(palestre);
    }

    @GetMapping("/attive")
    @Operation(summary = "Get active facilities", description = "Retrieve only active facilities")
    public ResponseEntity<List<PalestraDTO>> getPalestreAttive() {
        List<PalestraDTO> palestre = palestraService.getPalestreAttive();
        return ResponseEntity.ok(palestre);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get facility by ID", description = "Retrieve a specific facility")
    public ResponseEntity<PalestraDTO> getPalestraById(@PathVariable Long id) {
        PalestraDTO palestra = palestraService.getPalestraById(id);
        return ResponseEntity.ok(palestra);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Create new facility", description = "Create a new sports facility (Admin only)")
    public ResponseEntity<PalestraDTO> createPalestra(@RequestBody PalestraDTO palestraDTO) {
        PalestraDTO createdPalestra = palestraService.createPalestra(palestraDTO);
        return new ResponseEntity<>(createdPalestra, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Update facility", description = "Update an existing facility (Admin only)")
    public ResponseEntity<PalestraDTO> updatePalestra(@PathVariable Long id, @RequestBody PalestraDTO palestraDTO) {
        PalestraDTO updatedPalestra = palestraService.updatePalestra(id, palestraDTO);
        return ResponseEntity.ok(updatedPalestra);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Delete facility", description = "Delete a facility (Admin only)")
    public ResponseEntity<?> deletePalestra(@PathVariable Long id) {
        palestraService.deletePalestra(id);
        return ResponseEntity.ok("Facility deleted successfully");
    }
}
