package com.rodrigoramos.prize_draw.repositories;

import com.rodrigoramos.prize_draw.entities.PrizeDraw;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrizeDrawRepository extends MongoRepository<PrizeDraw, String> {

    List<PrizeDraw> findByCreatorId(String creatorId);
    @Query("{ 'creator.id': ?0, 'winners': { $exists: true, $size: 0 } }")
    List<PrizeDraw> findByCreatorIdAndWinnersEmpty(String creatorId);
    @Query("{ 'creator.id': ?0, 'winners.0': { $exists: true } }")
    List<PrizeDraw> findByCreatorIdAndWinnersNotEmpty(String creatorId);
}
