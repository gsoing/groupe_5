package com.episen.sca.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.OffsetDateTime;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentSummary   {

  @Id
  protected String documentId;
  protected OffsetDateTime created;
  protected OffsetDateTime updated;
  protected String title;

  public String getDocumentId() {
    return documentId;
  }

  public OffsetDateTime getCreated() {
    return created;
  }

  public OffsetDateTime getUpdated() {
    return updated;
  }

  public String getTitle() {
    return title;
  }

  public void setDocumentId(String documentId) {
    this.documentId = documentId;
  }

  public void setCreated(OffsetDateTime created) {
    this.created = created;
  }

  public void setUpdated(OffsetDateTime updated) {
    this.updated = updated;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
