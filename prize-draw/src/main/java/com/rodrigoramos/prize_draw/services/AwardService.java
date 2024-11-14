package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.AwardDto;
import com.rodrigoramos.prize_draw.entities.Award;
import com.rodrigoramos.prize_draw.repositories.AwardRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AwardService {

    @Autowired
    private AwardRepository awardRepository;

    public List<AwardDto> findAll() {
        List<Award> list = awardRepository.findAll();
        return list.stream().map(x -> new AwardDto(x)).toList();
    }

    public AwardDto findById(String id) {
        Award entity = findAwardById(id);
        return new AwardDto(entity);
    }

    public AwardDto insert(AwardDto dto) {
        Award entity = new Award();
        copyDtoToEntity(dto, entity);
        entity = awardRepository.insert(entity);
        return new AwardDto(entity);
    }

    public AwardDto update(String id, AwardDto dto) {
        Award entity = findAwardById(id);
        copyDtoToEntity(dto, entity);
        entity = awardRepository.save(entity);
        return new AwardDto(entity);
    }

    public void delete(String id) {
        findAwardById(id);
        awardRepository.deleteById(id);
    }

    private Award findAwardById(String id) {
       Optional<Award> result = awardRepository.findById(id);
       return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity(AwardDto dto, Award entity) {
        entity.setName(dto.getName());
        entity.setPrizeDraw(dto.getPrizeDraw());
    }
}
