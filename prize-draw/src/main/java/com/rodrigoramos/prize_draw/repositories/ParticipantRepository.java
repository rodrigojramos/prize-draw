package com.rodrigoramos.prize_draw.repositories;

import com.rodrigoramos.prize_draw.entities.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> {

    boolean existsByEmailAndPrizeDrawsId(String email, String prizeDrawId);

    boolean existsByDocumentAndPrizeDrawsId(String document, String prizeDrawId);

    @Query("{ 'prizeDrawsId': ?0 }")
    List<Participant> findByPrizeDrawId(String prizeDrawId);

}
