package com.episen.sca.service;

import com.episen.sca.exception.ForbiddenException;
import com.episen.sca.exception.LockException;
import com.episen.sca.exception.NotFoundException;
import com.episen.sca.model.Lock;
import com.episen.sca.repository.DocumentRepository;
import com.episen.sca.repository.LockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;

@Slf4j
@Service
public class LockService {
    @Autowired
    DocumentRepository documentRepository;

    @Autowired
    LockRepository lockRepository;

    public Optional<Lock> getLock(String documentId) {
        return lockRepository.findByDocumentId(documentId);
    }

    public Lock lock(String documentId) {
        documentRepository.findById(documentId).orElseThrow(NotFoundException::new);
        if(lockRepository.findByDocumentId(documentId).isPresent()) {
            Lock lock = lockRepository.findByDocumentId(documentId).get();
            log.info("Lock Document:"+ lock.toString());
            throw LockException.DEFAULT;
        }else{
            OffsetDateTime offsetDateTime = OffsetDateTime.now();
            Lock lock = new Lock(documentId, getUserDetails().getUsername(), offsetDateTime);
            log.info("Lock Document:"+ lock.toString());
            return lockRepository.save(lock);
        }
    }

    public void unLock(String documentId) {
        Lock lock = lockRepository.findByDocumentId(documentId).orElseThrow(NotFoundException::new);
        if(lock.getOwner().equals(getUserDetails().getUsername())) {
            lockRepository.deleteBydocumentId(documentId);
            log.info("UnLock Document:"+ lock.toString());
        }
        else throw ForbiddenException.DEFAULT;
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }
}