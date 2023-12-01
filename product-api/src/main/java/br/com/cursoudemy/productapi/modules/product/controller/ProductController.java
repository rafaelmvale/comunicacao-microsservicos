package br.com.cursoudemy.productapi.modules.product.controller;

import br.com.cursoudemy.productapi.config.exception.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.cursoudemy.productapi.modules.product.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

  @Autowired
  private ProductService productService;

  @PostMapping
  public ProductResponse save(@RequestBody ProductRequest request) {
   return productService.save(request);
  }
  @GetMapping
  public List<ProductResponse> findAll() {
    return productService.findAll();
  }
  @GetMapping("{id}")
  public ProductResponse findById(@PathVariable Integer id) {
    return productService.findByIdResponse(id);
  }

  @GetMapping("name/{name}")
  public List<ProductResponse> findByName(@PathVariable String name) {
    return productService.findByName(name);
  }
  @GetMapping("category/{categoryId}")
  public List<ProductResponse> findByName(@PathVariable Integer categoryId) {
    return productService.findByCategoryId(categoryId);
  }
  @GetMapping("supplier/{supplierId}")
  public List<ProductResponse> findBySupplierName(@PathVariable Integer supplierId) {
    return productService.findBySupplierId(supplierId);
  }
  @PutMapping("{id}")
  public ProductResponse update(@RequestBody ProductRequest request,
                                 @PathVariable Integer id) {
    return  productService.update(request, id);
  }
  @DeleteMapping("{id}")
  public SuccessResponse delete(@PathVariable Integer id) {
    return productService.delete(id);
  }
}
