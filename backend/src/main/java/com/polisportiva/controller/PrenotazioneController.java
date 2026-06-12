package com.polisportiva.controller;

import com.polisportiva.dto.PrenotazioneDTO;
import com.polisportiva.service.PrenotazioneService;
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
@RequestMapping("/prenotazioni")
@Tag(name = "Prenotazioni", description = "Bookings management endpoints")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Get all bookings", description = "Retrieve all bookings (Admin only or own bookings for users)")
    public ResponseEntity<List<PrenotazioneDTO>> getAllPrenotazioni() {
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.getAllPrenotazioni();
        return ResponseEntity.ok(prenotazioni);
    }

    @GetMapping("/orario/{orarioId}")
    @Operation(summary = "Get bookings by schedule", description = "Retrieve all bookings for a specific schedule")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByOrario(@PathVariable Long orarioId) {
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.getPrenotazioniByOrario(orarioId);
        return ResponseEntity.ok(prenotazioni);
    }

    @GetMapping("/utente/{utenteId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Get bookings by user", description = "Retrieve all bookings for a specific user")
    public ResponseEntity<List<PrenotazioneDTO>> getPrenotazioniByUtente(@PathVariable Long utenteId) {
        List<PrenotazioneDTO> prenotazioni = prenotazioneService.getPrenotazioniByUtente(utenteId);
        return ResponseEntity.ok(prenotazioni);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Get booking by ID", description = "Retrieve a specific booking")
    public ResponseEntity<PrenotazioneDTO> getPrenotazioneById(@PathVariable Long id) {
        PrenotazioneDTO prenotazione = prenotazioneService.getPrenotazioneById(id);
        return ResponseEntity.ok(prenotazione);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Create new booking", description = "Create a new booking for a training schedule")
    public ResponseEntity<PrenotazioneDTO> createPrenotazione(@RequestBody PrenotazioneDTO prenotazioneDTO) {
        PrenotazioneDTO createdPrenotazione = prenotazioneService.createPrenotazione(prenotazioneDTO);
        return new ResponseEntity<>(createdPrenotazione, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Update booking", description = "Update an existing booking")
    public ResponseEntity<PrenotazioneDTO> updatePrenotazione(@PathVariable Long id, @RequestBody PrenotazioneDTO prenotazioneDTO) {
        PrenotazioneDTO updatedPrenotazione = prenotazioneService.updatePrenotazione(id, prenotazioneDTO);
        return ResponseEntity.ok(updatedPrenotazione);
    }

    @DeleteMapping("/cancel/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Cancel booking", description = "Cancel an existing booking")
    public ResponseEntity<?> cancelPrenotazione(@PathVariable Long id) {
        prenotazioneService.cancelPrenotazione(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "Bearer")
    @Operation(summary = "Delete booking", description = "Delete a booking (Admin only)")
    public ResponseEntity<?> deletePrenotazione(@PathVariable Long id) {
        prenotazioneService.deletePrenotazione(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }
}
