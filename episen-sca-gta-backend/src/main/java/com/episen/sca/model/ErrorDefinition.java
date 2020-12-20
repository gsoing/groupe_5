package com.episen.sca.model;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Builder
public class ErrorDefinition   {

  public enum ErrorTypeEnum {
    TECHNICAL("TECHNICAL"),
    
    FUNCTIONAL("FUNCTIONAL");

    public String value;

    ErrorTypeEnum(String value) {
      this.value = value;
    }

    public String toString() {
      return String.valueOf(value);
    }

    public static ErrorTypeEnum fromValue(String text) {
      for (ErrorTypeEnum b : ErrorTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }



  public ErrorTypeEnum errorType = null;

  public List<ErrorDefinitionErrors> errors = null;

  public ErrorDefinition errorType(ErrorTypeEnum errorType) {
    this.errorType = errorType;
    return this;
  }

  public ErrorTypeEnum getErrorType() {
    return errorType;
  }

  public void setErrorType(ErrorTypeEnum errorType) {
    this.errorType = errorType;
  }

  public ErrorDefinition errors(List<ErrorDefinitionErrors> errors) {
    this.errors = errors;
    return this;
  }

  public ErrorDefinition addErrorsItem(ErrorDefinitionErrors errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<ErrorDefinitionErrors>();
    }
    this.errors.add(errorsItem);
    return this;
  }

  public List<ErrorDefinitionErrors> getErrors() {
    return errors;
  }

  public void setErrors(List<ErrorDefinitionErrors> errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDefinition errorDefinition = (ErrorDefinition) o;
    return Objects.equals(this.errorType, errorDefinition.errorType) &&
        Objects.equals(this.errors, errorDefinition.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorType, errors);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDefinition {\n");
    
    sb.append("    errorType: ").append(toIndentedString(errorType)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
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
