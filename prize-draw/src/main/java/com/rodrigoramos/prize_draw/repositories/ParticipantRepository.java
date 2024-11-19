package com.rodrigoramos.prize_draw.repositories;

import com.rodrigoramos.prize_draw.entities.Participant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends MongoRepository<Participant, String> {

    boolean existsByEmailAndDocumentAndPrizeDrawsId(String email, String document, String prizeDrawId);

}
