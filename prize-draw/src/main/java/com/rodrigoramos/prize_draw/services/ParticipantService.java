package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.ParticipantDto;
import com.rodrigoramos.prize_draw.entities.Participant;
import com.rodrigoramos.prize_draw.entities.PrizeDraw;
import com.rodrigoramos.prize_draw.repositories.ParticipantRepository;
import com.rodrigoramos.prize_draw.services.exceptions.InvalidPrizeDrawException;
import com.rodrigoramos.prize_draw.services.exceptions.ParticipantAlreadyRegisteredException;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private PrizeDrawService prizeDrawService;

    public List<ParticipantDto> findAll() {
        List<Participant> list = participantRepository.findAll();
        return list.stream().map(ParticipantDto::new).toList();
    }

    public ParticipantDto findById(String id) {
        Participant entity = findParticipantById(id);
        return new ParticipantDto(entity);
    }

    @Transactional
    public ParticipantDto insert(ParticipantDto dto, String prizeDrawId) {
        PrizeDraw prizeDraw = validateAndFindPrizeDraw(prizeDrawId);

        boolean alreadyRegistered = participantRepository.existsByEmailAndDocumentAndPrizeDrawsId(
                dto.getEmail(),
                dto.getDocument(),
                prizeDrawId);

        if (alreadyRegistered) {
            throw new ParticipantAlreadyRegisteredException("O participante já está inscrito neste sorteio.");
        }

        Participant entity = new Participant();
        copyDtoToEntity(dto, entity, prizeDrawId);
        entity = participantRepository.insert(entity);

        if (!prizeDraw.getParticipantsId().contains(entity.getId())) {
            prizeDraw.getParticipantsId().add(entity.getId());
            prizeDrawService.save(prizeDraw);
        }

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

    private PrizeDraw validateAndFindPrizeDraw(String prizeDrawId) {
        if (prizeDrawId == null || prizeDrawId.isBlank()) {
            throw new InvalidPrizeDrawException("O ID do sorteio não pode ser nulo ou vazio.");
        }
        return prizeDrawService.findPrizeDrawById(prizeDrawId);
    }

    private void copyDtoToEntity (ParticipantDto dto, Participant entity, String prizeDrawId) {
        entity.setName(dto.getName());
        entity.setDocument(dto.getDocument());
        entity.setEmail(dto.getEmail());
        if (!entity.getPrizeDrawsId().contains(prizeDrawId)) {
            entity.getPrizeDrawsId().add(prizeDrawId);
        }
    }
}