package com.rodrigoramos.prize_draw.controllers;

import com.rodrigoramos.prize_draw.dto.AwardDto;
import com.rodrigoramos.prize_draw.services.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/awards")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @GetMapping
    public ResponseEntity<List<AwardDto>> findAll() {
        List<AwardDto> list = awardService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AwardDto> findById(@PathVariable String id) {
        AwardDto dto = awardService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AwardDto> insert(@RequestBody AwardDto dto) {
        dto = awardService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<AwardDto> update(@PathVariable String id, @RequestBody AwardDto dto) {
        dto = awardService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        awardService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
