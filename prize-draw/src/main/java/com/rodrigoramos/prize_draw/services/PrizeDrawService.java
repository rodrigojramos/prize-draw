package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.PrizeDrawDto;
import com.rodrigoramos.prize_draw.entities.PrizeDraw;
import com.rodrigoramos.prize_draw.repositories.PrizeDrawRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrizeDrawService {

    @Autowired
    private PrizeDrawRepository prizeDrawRepository;

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
        return new PrizeDrawDto(entity);
    }

    public PrizeDrawDto update(String id, PrizeDrawDto dto) {
        PrizeDraw entity = findPrizeDrawById(id);
        copyDtoToEntity(dto, entity);
        entity = prizeDrawRepository.save(entity);
        return new PrizeDrawDto(entity);
    }

    public void delete(String id) {
        findPrizeDrawById(id);
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
        entity.setCreator(dto.getCreator());
        entity.setAwards(dto.getAwards());
    }

}
