package com.rodrigoramos.prize_draw.controllers;

import com.rodrigoramos.prize_draw.dto.ParticipantDto;
import com.rodrigoramos.prize_draw.dto.PrizeDrawDto;
import com.rodrigoramos.prize_draw.services.AuthService;
import com.rodrigoramos.prize_draw.services.PrizeDrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/draws")
public class PrizeDrawController {

    @Autowired
    private PrizeDrawService prizeDrawService;

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<List<PrizeDrawDto>> findAll() {
        List<PrizeDrawDto> list = prizeDrawService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PrizeDrawDto> findById(@PathVariable String id) {
        PrizeDrawDto dto = prizeDrawService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping(value = "/{id}/draws")
    public ResponseEntity<List<PrizeDrawDto>> findByCreatorId(@PathVariable String id) {
        List<PrizeDrawDto> list = prizeDrawService.findByCreatorId(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}/ongoing")
    public ResponseEntity<List<PrizeDrawDto>> getOngoingPrizeDrawsByUser(@PathVariable String id) {
        List<PrizeDrawDto> list = prizeDrawService.findOngoingPrizeDrawsByUser(id);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}/finished")
    public ResponseEntity<List<PrizeDrawDto>> getFinishedPrizeDrawsByUser(@PathVariable String id) {
        List<PrizeDrawDto> list = prizeDrawService.findFinishedPrizeDrawsByUser(id);
        return ResponseEntity.ok().body(list);
    }

    @PostMapping(value = "/{id}/winners")
    public ResponseEntity<List<ParticipantDto>> drawWinners(@PathVariable String id) {
        List<ParticipantDto> winners = prizeDrawService.winners(id);
        return ResponseEntity.ok().body(winners);
    }

    @PostMapping
    public ResponseEntity<PrizeDrawDto> insert(@RequestHeader("Authorization") String token,
                                               @RequestBody PrizeDrawDto dto) {
        dto = prizeDrawService.insert(dto, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PrizeDrawDto> update(@PathVariable String id, @RequestBody PrizeDrawDto dto) {
        dto = prizeDrawService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        prizeDrawService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
