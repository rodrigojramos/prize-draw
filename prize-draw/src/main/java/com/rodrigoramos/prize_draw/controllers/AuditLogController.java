package com.rodrigoramos.prize_draw.controllers;

import com.rodrigoramos.prize_draw.dto.AuditLogDto;
import com.rodrigoramos.prize_draw.services.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/logs")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLogDto>> findByPrizeDrawId(@RequestParam String prizeDrawId) {
        List<AuditLogDto> list = auditLogService.findLogsByPrizeDrawId(prizeDrawId);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AuditLogDto> findById(@PathVariable String id) {
        AuditLogDto dto = auditLogService.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<AuditLogDto> insert(@RequestBody AuditLogDto dto) {
        dto = auditLogService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        auditLogService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
