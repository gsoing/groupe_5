package com.episen.sca.model;


import java.time.OffsetDateTime;
import java.util.Objects;


public class Document extends DocumentSummary {
  private String creator = null;

  private String editor = null;

  private String body = null;

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

  private StatusEnum status = null;

  public Document creator(String creator) {
    this.creator = creator;
    return this;
  }

  public DocumentSummary toSummary(){
    DocumentSummary documentSummary = null;
    documentSummary.setTitle(title);
    documentSummary.setCreated(created);
    documentSummary.setDocumentId(documentId);
    documentSummary.setUpdated(updated);
    return documentSummary;
  }

  
  public String getCreator() {
    return creator;
  }

  public void setCreator(String creator) {
    this.creator = creator;
  }

  public Document editor(String editor) {
    this.editor = editor;
    return this;
  }

  public String getEditor() {
    return editor;
  }

  public void setEditor(String editor) {
    this.editor = editor;
  }

  public Document body(String body) {
    this.body = body;
    return this;
  }

  
  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Document status(StatusEnum status) {
    this.status = status;
    return this;
  }

  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Document document = (Document) o;
    return Objects.equals(this.creator, document.creator) &&
        Objects.equals(this.editor, document.editor) &&
        Objects.equals(this.body, document.body) &&
        Objects.equals(this.status, document.status) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(creator, editor, body, status, super.hashCode());
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Document {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    creator: ").append(toIndentedString(creator)).append("\n");
    sb.append("    editor: ").append(toIndentedString(editor)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
