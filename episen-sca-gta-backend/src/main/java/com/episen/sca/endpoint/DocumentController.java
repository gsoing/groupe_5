package com.episen.sca.endpoint;

import com.episen.sca.dto.UserDto;
import com.episen.sca.model.Document;
import com.episen.sca.model.DocumentsList;
import com.episen.sca.model.Lock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping(DocumentController.PATH)
@Slf4j
public class DocumentController {

    public static final String PATH = "/documents";

    @RequestMapping(
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<DocumentsList> documentsGet(){
        return new ResponseEntity<DocumentsList>(HttpStatus.ACCEPTED);
    }
    @RequestMapping(
            consumes = { "application/json" },
            produces = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<DocumentsList> documentsPost(@RequestBody Document document){
        return new ResponseEntity<DocumentsList>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{documentId}",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Document> documentsDocumentIdGet(@PathVariable("documentId") String documentId){
        return new ResponseEntity<Document>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{documentId}",
            consumes = { "application/json" },
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Document> documentsDocumentIdPut(@PathVariable("documentId") String documentId, @RequestBody Document document){
        return new ResponseEntity<Document>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{documentId}/status",
            consumes = { "text/plain" },
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Document> documentsDocumentIdStatusPut(@PathVariable("documentId") String documentId, @RequestBody Document.StatusEnum document){
        return new ResponseEntity<Document>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(
            value="/{documentId}/lock",
            produces = { "application/json" },
            method = RequestMethod.GET)
    ResponseEntity<Lock> documentsDocumentIdLockGet(){
        return new ResponseEntity<Lock>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(
            value="/{documentId}/lock",
            consumes = { "application/json" },
            produces = { "application/json" },
            method = RequestMethod.PUT)
    ResponseEntity<Lock> documentsDocumentIdLockPut(@PathVariable("documentId") String documentId, @RequestBody Lock lock){
        return new ResponseEntity<Lock>(HttpStatus.ACCEPTED);
    }

    @RequestMapping(
            value="/{documentId}/lock",
            consumes = { "application/json" },
            produces = { "application/json" },
            method = RequestMethod.DELETE)
    ResponseEntity<Lock> documentsDocumentIdLockDelete(@PathVariable("documentId") String documentId){
        return new ResponseEntity<Lock>(HttpStatus.ACCEPTED);
    }

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

