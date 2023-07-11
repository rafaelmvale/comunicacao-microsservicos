package br.com.cursoudemy.productapi.modules.produto.model;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRODUCT")

public class Product {
  private Integer id;
  private String name;
  private Supplier supplier;
  private Category category;
}