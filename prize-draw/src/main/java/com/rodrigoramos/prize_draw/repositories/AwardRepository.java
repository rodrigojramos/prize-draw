package com.rodrigoramos.prize_draw.repositories;

import com.rodrigoramos.prize_draw.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AwardRepository extends MongoRepository<User, ObjectId> {
}
