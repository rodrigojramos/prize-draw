package com.rodrigoramos.prize_draw.services;

import com.rodrigoramos.prize_draw.dto.UserDto;
import com.rodrigoramos.prize_draw.entities.User;
import com.rodrigoramos.prize_draw.repositories.UserRepository;
import com.rodrigoramos.prize_draw.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findCreatorById(String id) {
        User entity = findUserById(id);
        return entity;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public UserDto findById(String id) {
        User entity = findUserById(id);
        return new UserDto(entity);
    }

    public UserDto insert(UserDto dto) {
        User entity = new User();
        copyDtoToEntity(dto, entity);
        entity = userRepository.insert(entity);
        return new UserDto(entity);
    }

    public UserDto update(String id, UserDto dto) {
        User entity = findUserById(id);
        copyDtoToEntity(dto, entity);
        entity = userRepository.save(entity);
        return new UserDto(entity);
    }

    public void delete(String id) {
        findUserById(id);
        userRepository.deleteById(id);
    }

    private User findUserById(String id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("Objeto não encontrado"));
    }

    private void copyDtoToEntity (UserDto dto, User entity) {
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setDocument(dto.getDocument());
        entity.setQuantityPrizeDraw(dto.getQuantityPrizeDraw());

        if (dto.getPrizeDrawsId() != null) {
            entity.getDraws().clear();
            entity.getDraws().addAll(dto.getPrizeDrawsId());
        }
    }

}
