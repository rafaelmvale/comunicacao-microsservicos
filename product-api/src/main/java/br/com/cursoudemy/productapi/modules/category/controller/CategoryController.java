package br.com.cursoudemy.productapi.modules.category.controller;
import java.util.List;

import br.com.cursoudemy.productapi.config.exception.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import br.com.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

  @Autowired
  private CategoryService categoryService;
  
  @PostMapping
  public CategoryResponse save(@RequestBody CategoryRequest request) {
    return categoryService.save(request);
  }

  @GetMapping
  public List<CategoryResponse> findAll() {
    return categoryService.findAll();
  }
  @GetMapping("{id}")
  public CategoryResponse findById(@PathVariable Integer id) {
    return categoryService.findByIdResponse(id);
  }
   
  @GetMapping("description/{description}")
  public List<CategoryResponse> findByDescription(@PathVariable String description) {
    return categoryService.findByDescription(description);
  }

  @PutMapping("{id}")
  public CategoryResponse update(@RequestBody CategoryRequest request,
                                 @PathVariable Integer id) {
    return  categoryService.update(request, id);
  }

  @DeleteMapping("{id}")
  public SuccessResponse delete(@PathVariable Integer id) {
    return categoryService.delete(id);
  }
}
