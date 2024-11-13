package com.rodrigoramos.prize_draw.repositories;

import com.rodrigoramos.prize_draw.entities.PrizeDraw;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrizeDrawRepository extends MongoRepository<PrizeDraw, ObjectId> {
}