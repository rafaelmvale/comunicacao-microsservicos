package br.com.cursoudemy.productapi.modules.category.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.BeanUtils;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CATEGORY")
public class Category {


  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Integer id;

  @Column(name = "DESCRIPTION", nullable = false)
  private String description;

  public static Category of(CategoryRequest request) {
    var category = new Category();
    BeanUtils.copyProperties(request, category);
    return category;
   }
}