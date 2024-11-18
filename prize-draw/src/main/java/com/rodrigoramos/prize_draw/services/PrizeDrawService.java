package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.PrizeDrawDto;
import com.rodrigoramos.prize_draw.entities.PrizeDraw;
import com.rodrigoramos.prize_draw.entities.User;
import com.rodrigoramos.prize_draw.repositories.PrizeDrawRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PrizeDrawService {

    @Autowired
    private PrizeDrawRepository prizeDrawRepository;

    @Autowired
    private UserService userService;

    public List<PrizeDrawDto> findAll() {
        List<PrizeDraw> list = prizeDrawRepository.findAll();
        return list.stream().map(x -> new PrizeDrawDto(x)).toList();
    }

    public PrizeDrawDto findById(String id) {
        PrizeDraw entity = findPrizeDrawById(id);
        return new PrizeDrawDto(entity);
    }

    public PrizeDrawDto insert(PrizeDrawDto dto) {
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

    private PrizeDraw findPrizeDrawById(String id) {
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
