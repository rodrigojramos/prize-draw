package com.rodrigoramos.prize_draw.controllers;

import com.rodrigoramos.prize_draw.dto.ParticipantDto;
import com.rodrigoramos.prize_draw.services.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/participants")
public class ParticipantController {

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public ResponseEntity<List<ParticipantDto>> findAll(@RequestParam String prizeDrawId) {
        List<ParticipantDto> list = participantService.findByPrizeDrawId(prizeDrawId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ParticipantDto> findById(@PathVariable String id) {
        ParticipantDto dto = participantService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<ParticipantDto> insert(@RequestBody ParticipantDto dto, @RequestParam String prizeDrawId) {
        dto = participantService.insert(dto, prizeDrawId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        participantService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
