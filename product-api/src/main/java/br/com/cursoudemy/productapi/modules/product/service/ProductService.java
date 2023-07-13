package br.com.cursoudemy.productapi.modules.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;

@Service
public class ProductService {
  
  private static final Integer ZERO = 0;
  @Autowired
  private ProductRepository productRepository;
  
  @Autowired
  private SupplierService supplierService;

  @Autowired
  private CategoryService categoryService;

  public ProductResponse save(ProductRequest request) {
    validateProductDataInformed(request);
    validateCategoryAndSupplierIdInformed(request);
    var category = categoryService.findById(request.getCategory());
    var supplier = supplierService.findById(request.getSupplier());
    var product = productRepository.save(Product.of(request, supplier, category));
    return ProductResponse.of(product);
    
   
  }

  private void validateProductDataInformed(ProductRequest request) {
    if (request.getName() == null) {
      throw new ValidationException("The product ID was not informed");
    }
    if(request.getQuantityAvailable() == null ){
      throw new ValidationException("The products quantity available was not informed");
    }
    if(request.getQuantityAvailable() <= ZERO){
      throw new ValidationException("The quantity should not be less or equal to zero");
    }
  }

  private void validateCategoryAndSupplierIdInformed(ProductRequest request) {
    if (request.getCategory() == null) {
      throw new ValidationException("The category ID was not informed");
    }
    if (request.getSupplier() == null) {
      throw new ValidationException("The supplier ID was not informed");
    }
    categoryService.findById(request.getCategory());
    supplierService.findById(request.getSupplier());
  }

  
}
