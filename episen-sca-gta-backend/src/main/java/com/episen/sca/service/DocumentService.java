package com.episen.sca.service;

import com.episen.sca.exception.CannotBeModifiedException;
import com.episen.sca.exception.NotFoundException;
import com.episen.sca.model.Document;
import com.episen.sca.model.DocumentSummary;
import com.episen.sca.model.DocumentsList;
import com.episen.sca.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public DocumentsList documentsGet(Pageable pageable) {
        Page<Document> pages = documentRepository.findAll(pageable);
        return fromPageToDocumentsList(pages);
    }

    public DocumentsList fromPageToDocumentsList(Page page) {
        DocumentsList results = new DocumentsList();
        List<Document> docs = page.getContent();
        ArrayList<DocumentSummary> sums = new ArrayList<>();
        for (Document d: docs) {
            sums.add(d.toSummary());
        }
        results.setData(sums);
        results.setPage(page.getNumber());
        results.setNbElements((int) page.getTotalElements());
        return results;
    }
    public DocumentsList createDocument(Document document) {
        UserDetails userDetails = getUserDetails();
        document.setStatus(Document.StatusEnum.CREATED);
        document.setCreated(OffsetDateTime.now());
        document.setUpdated(OffsetDateTime.now());
        document.setCreator(userDetails.getUsername());
        document.setEditor(userDetails.getUsername());
        Document createdDocument = documentRepository.insert(document);
        ArrayList<DocumentSummary> data = new ArrayList<>();
        data.add(createdDocument.toSummary());
        DocumentsList documentsList = new DocumentsList(0,1,data);
        return documentsList;
    }
    public Document getDocumentById(String documentId) {
        return documentRepository.findById(documentId).orElseThrow(NotFoundException::new);
    }
    public Document updateDocumentById(String documentId, Document document) {
        Document toUpdateDocument = documentRepository.findById(documentId).orElseThrow(NotFoundException::new);
        if (toUpdateDocument.getStatus().equals(Document.StatusEnum.VALIDATED))
            throw new CannotBeModifiedException();
        toUpdateDocument.setTitle(document.getTitle());
        toUpdateDocument.setBody(document.getBody());
        toUpdateDocument.setUpdated(OffsetDateTime.now());
        toUpdateDocument.setEditor(getUserDetails().getUsername());
        return documentRepository.save(toUpdateDocument);
    }
    public void updateDocumentStatusById(String documentId, Document.StatusEnum documentStatus) {
        Document toUpdateDocument = documentRepository.findById(documentId).orElseThrow(NotFoundException::new);
        if (toUpdateDocument.getStatus().equals(Document.StatusEnum.VALIDATED))
            throw new CannotBeModifiedException();
        toUpdateDocument.setStatus(documentStatus);
        return;
    }
    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserDetails) authentication.getPrincipal();
    }

}
