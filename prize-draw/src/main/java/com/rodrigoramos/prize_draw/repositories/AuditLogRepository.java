package com.rodrigoramos.prize_draw.repositories;

import com.rodrigoramos.prize_draw.entities.AuditLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditLogRepository extends MongoRepository<AuditLog, String> {

    @Query("{ 'prizeDrawId': ?0 }")
    List<AuditLog> findLogsByPrizeDrawId(String prizeDrawId);
}
