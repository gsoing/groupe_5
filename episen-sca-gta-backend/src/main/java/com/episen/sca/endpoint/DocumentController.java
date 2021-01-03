package com.episen.sca.endpoint;

import com.episen.sca.dto.UserDto;
import com.episen.sca.model.Document;
import com.episen.sca.model.DocumentsList;
import com.episen.sca.model.Lock;
import com.episen.sca.service.DocumentService;
import com.episen.sca.service.LockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(DocumentController.PATH)
@Slf4j
public class DocumentController {

    public static final String PATH = "/documents";
    @Autowired
    private LockService lockService;

    // Oui c'est pour ???
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DocumentService documentService;

    @RequestMapping(
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<DocumentsList> documentsGet( @PageableDefault(page = 0, size = 20) Pageable pageable,
                                                UriComponentsBuilder uriComponentsBuilder){
        log.info("GET /documents :  (" + pageable.getPageNumber() + "), pageSize(" + pageable.getPageSize() + ")");
        // Vérifier la taille max de la page aurait été sympa
        DocumentsList pageResult = documentService.documentsGet(pageable);

        return ResponseEntity.ok(pageResult);
    }


    @RequestMapping(
            consumes = { "application/json" },
            produces = { "application/json" },
            method = RequestMethod.POST)
    // Pourquoi retourner une liste ici ????
    ResponseEntity<DocumentsList> documentsPost(@RequestBody Document document){
        log.info("POST /documents : document " + document.toString());
        // On aurait pu retourner l'etag lors de la création
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(documentService.createDocument(document));
    }

    @RequestMapping(value = "/{documentId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Object> documentsDocumentIdGet(@PathVariable("documentId") String documentId){
        log.info("GET /documents/{documentId} :  document id : " + documentId);
        Document document = documentService.getDocumentById(documentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .eTag(document.getEtag())
                .body(document);
    }


    @PutMapping(value = "/{documentId}")
    ResponseEntity<Object> documentsDocumentIdPut(@PathVariable("documentId") String documentId,
                                                  @RequestBody Document document,
                                                  @RequestHeader(value = "etag", defaultValue = "0") String etag){
        log.info("PUT /documents/{documentId} : document id '" + documentId + "' and document '" + document.toString());
        log.info("PUT /documents/{documentId} : etag: " + etag);

        Document updatedDocument = documentService.updateDocumentById(documentId, document, etag);
        return ResponseEntity
                .status(HttpStatus.OK)
                .eTag(updatedDocument.getEtag())
                .body(updatedDocument);
    }

    @RequestMapping(value = "/{documentId}/status",
            consumes = { "text/plain" },
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity documentsDocumentIdStatusPut(@PathVariable("documentId") String documentId, @RequestBody String documentStatus){
        log.info("PUT /documents/{documentId}/status : document id '" + documentId + "' and status '" + documentStatus + "'");
        documentService.updateDocumentStatusById(documentId, Document.StatusEnum.fromValue(documentStatus));
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(
            value="/{documentId}/lock",
            produces = { "application/json" },
            method = RequestMethod.GET)
    // Si on veut être complet il faudrait vérifier si le document existe
    ResponseEntity<Lock> documentsDocumentIdLockGet(@PathVariable("documentId") String documentId){
        log.info("GET /documents/{documentId}/lock :  document id :" + documentId);
        Optional<Lock> lock = lockService.getLock(documentId);
        if(lock.isPresent()) {
            log.info("Get Lock Document:"+ lock.toString());
            return ResponseEntity.status(HttpStatus.OK).body(lock.get());
        }
        else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    @PutMapping(
            value="/{documentId}/lock")
    ResponseEntity<Lock> documentsDocumentIdLockPut(@PathVariable("documentId") String documentId){
        log.info("PUT /documents/{documentId}/lock : document id '" + documentId );
        Lock lock = lockService.lock(documentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lock);
    }

    @DeleteMapping(
            value="/{documentId}/lock")
    ResponseEntity<Lock> documentsDocumentIdLockDelete(@PathVariable("documentId") String documentId){
        log.info("DELETE /documents/{documentId}/lock :  document id '" + documentId);
        lockService.unLock(documentId);
        return new ResponseEntity<Lock>(HttpStatus.ACCEPTED);
    }

    //TODO SUPPRESSWARNING
    @GetMapping
    public ResponseEntity<UserDto> getCurrentUser(Authentication authentication) {
        return ResponseEntity
                .ok(
                        UserDto.builder()
                                .username(authentication.getName())
                                .roles(
                                        authentication.getAuthorities()
                                                .stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.toList())
                                )
                                .build());
    }
    //TODO SUPPRESSWARNING
    @GetMapping
    @RequestMapping("/auth")
    public ResponseEntity<UserDto> getCurrentUser2(Authentication authentication) {
        return ResponseEntity
                .ok(
                        UserDto.builder()
                                .username(authentication.getName())
                                .roles(
                                        authentication.getAuthorities()
                                                .stream()
                                                .map(GrantedAuthority::getAuthority)
                                                .collect(Collectors.toList())
                                )
                                .build());
    }
}

