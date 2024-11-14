package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.ParticipantDto;
import com.rodrigoramos.prize_draw.entities.Participant;
import com.rodrigoramos.prize_draw.repositories.ParticipantRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public List<ParticipantDto> findAll() {
        List<Participant> list = participantRepository.findAll();
        return list.stream().map(x -> new ParticipantDto(x)).toList();
    }

    public ParticipantDto findById(String id) {
        Participant entity = findParticipantById(id);
        return new ParticipantDto(entity);
    }

    public ParticipantDto insert(ParticipantDto dto) {
        Participant entity = new Participant();
        copyDtoToEntity(dto, entity);
        entity = participantRepository.insert(entity);
        return new ParticipantDto(entity);
    }

    public ParticipantDto update(String id, ParticipantDto dto) {
        Participant entity = findParticipantById(id);
        copyDtoToEntity(dto, entity);
        entity = participantRepository.save(entity);
        return new ParticipantDto(entity);
    }

    public void delete(String id) {
        findParticipantById(id);
        participantRepository.deleteById(id);
    }

    private Participant findParticipantById(String id) {
        Optional<Participant> result = participantRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity (ParticipantDto dto, Participant entity) {
        entity.setName(dto.getName());
        entity.setDocument(dto.getDocument());
        entity.setEmail(dto.getEmail());
    }
}
