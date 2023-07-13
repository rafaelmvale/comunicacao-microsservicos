package br.com.cursoudemy.productapi.modules.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductRequest {
   private String name;
   @JsonProperty("created_at")
  private Integer quantityAvailable;
  private Integer supplier;
  private Integer category;
}
