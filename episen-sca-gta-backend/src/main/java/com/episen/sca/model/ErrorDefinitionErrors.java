package com.episen.sca.model;

import java.util.Objects;


public class ErrorDefinitionErrors   {

  private String errorCode = null;

  private String errorMessage = null;

  public ErrorDefinitionErrors errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }
  
    public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public ErrorDefinitionErrors errorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
    return this;
  }
  
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorDefinitionErrors errorDefinitionErrors = (ErrorDefinitionErrors) o;
    return Objects.equals(this.errorCode, errorDefinitionErrors.errorCode) &&
        Objects.equals(this.errorMessage, errorDefinitionErrors.errorMessage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorMessage);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ErrorDefinitionErrors {\n");
    
    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorMessage: ").append(toIndentedString(errorMessage)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
