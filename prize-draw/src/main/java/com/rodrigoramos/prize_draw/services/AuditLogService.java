package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.AuditLogDto;
import com.rodrigoramos.prize_draw.entities.AuditLog;
import com.rodrigoramos.prize_draw.repositories.AuditLogRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuditLogService {

    @Autowired
    private AuditLogRepository auditLogRepository;

    public List<AuditLogDto> findAll() {
        List<AuditLog> list = auditLogRepository.findAll();
        return list.stream().map(x -> new AuditLogDto(x)).toList();
    }

    public AuditLogDto findById(String id) {
        AuditLog entity = findAuditLogById(id);
        return new AuditLogDto(entity);
    }

    public AuditLogDto insert(AuditLogDto dto) {
        AuditLog entity = new AuditLog();
        copyDtoToEntity(dto, entity);
        entity = auditLogRepository.insert(entity);
        return new AuditLogDto(entity);
    }

    public AuditLogDto update(String id, AuditLogDto dto) {
        AuditLog entity = findAuditLogById(id);
        copyDtoToEntity(dto, entity);
        entity = auditLogRepository.save(entity);
        return new AuditLogDto(entity);
    }

    public void delete(String id) {
        findAuditLogById(id);
        auditLogRepository.deleteById(id);
    }

    private AuditLog findAuditLogById(String id) {
        Optional<AuditLog> result = auditLogRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity(AuditLogDto dto, AuditLog entity) {
        entity.setDate(dto.getDate());
        entity.setAction(dto.getAction());
        entity.setDetails(dto.getDetails());
        entity.setPrizeDraw(dto.getPrizeDraw());
    }
}
