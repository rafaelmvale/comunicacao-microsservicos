package br.com.cursoudemy.productapi.modules.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.model.Category;
import br.com.cursoudemy.productapi.modules.category.repository.CategoryRepository;

import static org.springframework.util.StringUtils.isEmpty;

@Service
public class CategoryService {

  @Autowired
  private CategoryRepository categoryRepository;

    
  public Category findById(Integer id) {
    return categoryRepository.findById(id)
      .orElseThrow(() -> new ValidationException("There is no category for the given id"));
  }


  public CategoryResponse save(CategoryRequest request) {
    validateCategoryNameInformed(request);
    var category = categoryRepository.save(Category.of(request));
    return CategoryResponse.of(category);
  }

  private void validateCategoryNameInformed(CategoryRequest request) {
    if (isEmpty(request.getDescription())) {
      throw new ValidationException("The category description was not informed");
    }
  }
    
}
