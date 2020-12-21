package com.episen.sca.repository;

import com.episen.sca.model.Lock;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LockRepository extends MongoRepository<Lock, String> {
    Optional<Lock> findByDocumentId(String documentId);

    void deleteBydocumentId(String documentId);
}
