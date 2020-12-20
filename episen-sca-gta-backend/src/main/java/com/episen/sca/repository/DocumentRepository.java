package com.episen.sca.repository;

import com.episen.sca.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends MongoRepository<Document,String> {

    @Override
    Page<Document> findAll(Pageable pageable);
}
