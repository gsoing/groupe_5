package com.episen.sca.model;

import java.util.Objects;

public class PageData   {
  private Integer page = null;

  private Integer nbElements = null;

  public PageData page(Integer page) {
    this.page = page;
    return this;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public PageData nbElements(Integer nbElements) {
    this.nbElements = nbElements;
    return this;
  }

  public Integer getNbElements() {
    return nbElements;
  }

  public void setNbElements(Integer nbElements) {
    this.nbElements = nbElements;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageData pageData = (PageData) o;
    return Objects.equals(this.page, pageData.page) &&
        Objects.equals(this.nbElements, pageData.nbElements);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, nbElements);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageData {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    nbElements: ").append(toIndentedString(nbElements)).append("\n");
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
