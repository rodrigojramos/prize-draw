package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.ParticipantDto;
import com.rodrigoramos.prize_draw.dto.UserDto;
import com.rodrigoramos.prize_draw.entities.Participant;
import com.rodrigoramos.prize_draw.entities.User;
import com.rodrigoramos.prize_draw.repositories.ParticipantRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    public ParticipantDto findById(String id) {
        Participant entity = findUserById(id);
        return new ParticipantDto(entity);
    }

    public ParticipantDto insert(ParticipantDto dto) {
        Participant entity = new Participant();
        copyDtoToEntity(dto, entity);
        entity = participantRepository.insert(entity);
        return new ParticipantDto(entity);
    }

    public ParticipantDto update(String id, ParticipantDto dto) {
        Participant entity = findUserById(id);
        copyDtoToEntity(dto, entity);
        entity = participantRepository.save(entity);
        return new ParticipantDto(entity);
    }

    public void delete(String id) {
        findUserById(id);
        participantRepository.deleteById(id);
    }

    private Participant findUserById(String id) {
        Optional<Participant> result = participantRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity (ParticipantDto dto, Participant entity) {
        entity.setName(dto.getName());
        entity.setDocument(dto.getDocument());
        entity.setEmail(dto.getEmail());
    }
}
