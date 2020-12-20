package com.episen.sca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
public class PageData   {
  protected int page;
  protected int nbElements;

  public PageData(int page, int nbElements) {
    this.page = page;
    this.nbElements = nbElements;
  }
}
