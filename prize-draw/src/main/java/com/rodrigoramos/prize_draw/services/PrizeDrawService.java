package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.AuditLogDto;
import com.rodrigoramos.prize_draw.dto.ParticipantDto;
import com.rodrigoramos.prize_draw.dto.PrizeDrawDto;
import com.rodrigoramos.prize_draw.entities.AuditLog;
import com.rodrigoramos.prize_draw.entities.Participant;
import com.rodrigoramos.prize_draw.entities.PrizeDraw;
import com.rodrigoramos.prize_draw.entities.User;
import com.rodrigoramos.prize_draw.repositories.ParticipantRepository;
import com.rodrigoramos.prize_draw.repositories.PrizeDrawRepository;
import com.rodrigoramos.prize_draw.services.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PrizeDrawService {

    @Autowired
    private PrizeDrawRepository prizeDrawRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private AuditLogService auditLogService;

    @Autowired
    private AuthService authService;

    public void save(PrizeDraw prizeDraw) {
        prizeDrawRepository.save(prizeDraw);
    }

    public List<PrizeDrawDto> findAll() {
        List<PrizeDraw> list = prizeDrawRepository.findAll();
        return list.stream().map(x -> new PrizeDrawDto(x)).toList();
    }

    public PrizeDrawDto findById(String id) {
        PrizeDraw entity = findPrizeDrawById(id);
        return new PrizeDrawDto(entity);
    }

    @Transactional
    public PrizeDrawDto insert(PrizeDrawDto dto, String token) {
        if (token == null || token.isBlank()) {
            throw new TokenNotProvidedException("Token não informado.");
        }

        User user = authService.validateToken(token)
                .orElseThrow(() -> new InvalidTokenException("Token inválido ou sessão expirada."));

        PrizeDraw entity = new PrizeDraw();
        copyDtoToEntity(dto, entity);
        entity = prizeDrawRepository.insert(entity);

        if (entity.getCreator() != null) {
            User creator = userService.findCreatorById(entity.getCreator().getId());

            if (creator.getDraws() == null) {
                creator.setDraws(new ArrayList<>());
            }

            creator.getDraws().add(entity.getId());
            creator.setQuantityPrizeDraw(creator.getQuantityPrizeDraw() + 1);
            userService.save(creator);
        }

        AuditLog log = new AuditLog();
        log.setAction("Sorteio criado!");
        log.setDetails("Sorteio criado com sucesso.");
        log.setPrizeDrawId(entity.getId());
        AuditLogDto logDto = auditLogService.insert(new AuditLogDto(log));

        if (!entity.getAuditLogsId().contains(logDto.getId())) {
            entity.getAuditLogsId().add(logDto.getId());
            prizeDrawRepository.save(entity);
        }

        return new PrizeDrawDto(entity);
    }

    public PrizeDrawDto update(String id, PrizeDrawDto dto) {
        PrizeDraw entity = findPrizeDrawById(id);
        copyDtoToEntity(dto, entity);
        entity = prizeDrawRepository.save(entity);
        return new PrizeDrawDto(entity);
    }

    public void delete(String id) {
        PrizeDraw prizeDraw = findPrizeDrawById(id);

        if (prizeDraw.getCreator() != null) {
            User creator = userService.findCreatorById(prizeDraw.getCreator().getId());
            creator.getDraws().remove(id);
            creator.setQuantityPrizeDraw(creator.getQuantityPrizeDraw() - 1);
            userService.save(creator);
        }

        prizeDrawRepository.deleteById(id);
    }

    @Transactional
    public List<ParticipantDto> winners(String id) {
        PrizeDraw prizeDraw = findPrizeDrawById(id);

        if (!prizeDraw.getWinners().isEmpty()) {
            throw new PrizeDrawAlreadyMadeException("O sorteio dos ganhadores já foi realizado.");
        }

        int quantityWinners = prizeDraw.getAwards().size();

        List<Participant> participants = participantRepository.findByPrizeDrawId(prizeDraw.getId());

        List<ParticipantDto> winners = new ArrayList<>();
        Random random = new Random();

        List<Participant> remainingParticipants = new ArrayList<>(participants);
        List<Participant> winnersList = new ArrayList<>();

        for (int i = 0; i < quantityWinners; i++) {
            int index = random.nextInt(remainingParticipants.size());
            Participant winner = remainingParticipants.remove(index);
            winners.add(new ParticipantDto(winner));
            winnersList.add(winner);
        }

        prizeDraw.setWinners(winnersList);

        AuditLog log = new AuditLog();
        log.setAction("Sorteio realizado!");
        log.setDetails("Os ganhadores foram sorteados.");
        log.setPrizeDrawId(prizeDraw.getId());
        AuditLogDto logDto = auditLogService.insert(new AuditLogDto(log));

        if (!prizeDraw.getAuditLogsId().contains(logDto.getId())) {
            prizeDraw.getAuditLogsId().add(logDto.getId());
        }

        prizeDrawRepository.save(prizeDraw);

        return winners;
    }

    public PrizeDraw findPrizeDrawById(String id) {
        Optional<PrizeDraw> result = prizeDrawRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity(PrizeDrawDto dto, PrizeDraw entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setEndDate(dto.getEndDate());
        entity.setAwards(dto.getAwards());

        if (dto.getCreator() != null && dto.getCreator().getId() != null) {
            User creator = userService.findCreatorById(dto.getCreator().getId());
            entity.setCreator(creator);
        }
    }
}
