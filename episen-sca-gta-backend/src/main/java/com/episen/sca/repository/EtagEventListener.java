package com.episen.sca.repository;


import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.util.DigestUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Slf4j
public class EtagEventListener extends AbstractMongoEventListener<com.episen.sca.model.Document> {

    @Override
    public void onAfterConvert(AfterConvertEvent<com.episen.sca.model.Document> event) {
        event.getSource().setEtag(calculateEtag(event.getDocument()));
    }

    @Override
    public void onAfterSave(AfterSaveEvent<com.episen.sca.model.Document> event) {
        event.getSource().setEtag(calculateEtag(event.getDocument()));
    }

   
    private String calculateEtag(Document document) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            DigestUtils.appendMd5DigestAsHex(new ByteArrayInputStream(document.toJson().getBytes()), stringBuilder);
        } catch (IOException e) {
            throw new DataRetrievalFailureException("Unable to create etag", e);
        }
        return stringBuilder.toString();
    }
}
