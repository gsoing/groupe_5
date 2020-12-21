package com.episen.sca.model;



import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.time.OffsetDateTime;


@Data
public class Document extends DocumentSummary implements Serializable {
  private String creator;
  private String editor;
  private String body;
  private StatusEnum status;
  @Transient
  private String etag;

  Document(String documentId, OffsetDateTime created, OffsetDateTime updated, String title) {
    super(documentId, created, updated, title);
  }

  public enum StatusEnum {
    CREATED("CREATED"),

    VALIDATED("VALIDATED");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  public DocumentSummary toSummary() {
    DocumentSummary documentSummary = new DocumentSummary(title,created,updated,documentId);
    return documentSummary;
  }


}


