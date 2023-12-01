package br.com.cursoudemy.productapi.modules.product.service;

import br.com.cursoudemy.productapi.config.exception.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.category.service.CategoryService;
import br.com.cursoudemy.productapi.modules.product.dto.ProductRequest;
import br.com.cursoudemy.productapi.modules.product.dto.ProductResponse;
import br.com.cursoudemy.productapi.modules.product.model.Product;
import br.com.cursoudemy.productapi.modules.product.repository.ProductRepository;
import br.com.cursoudemy.productapi.modules.supplier.service.SupplierService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class ProductService {
  
  private static final Integer ZERO = 0;
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private SupplierService supplierService;
  @Autowired
  private CategoryService categoryService;
  public List<ProductResponse> findAll() {
      return productRepository
              .findAll()
              .stream()
              .map(ProductResponse::of)
              .collect(Collectors.toList());
  }

  public List<ProductResponse> findByName(String name){
      if(isEmpty(name)) {
          throw new ValidationException("The product name must be informed");

      }

      return productRepository
              .findByNameIgnoreCaseContaining(name)
              .stream()
              .map(ProductResponse::of)
              .collect(Collectors.toList());
  }
    public List<ProductResponse> findBySupplierId(Integer supplierId){
        if(isEmpty(supplierId)) {
            throw new ValidationException("The product supplier ID must be informed");

        }

        return productRepository
                .findBySupplierId(supplierId)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }
    public List<ProductResponse> findByCategoryId(Integer categoryId){
        if(isEmpty(categoryId)) {
            throw new ValidationException("The product category ID must be informed");

        }

        return productRepository
                .findByCategoryId(categoryId)
                .stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }

  public ProductResponse findByIdResponse(Integer id){
      return ProductResponse.of(findById(id));
  }

  public Product findById(Integer id){
      if(isEmpty(id)){
          throw new ValidationException("The product ID must be informed");
      }
      return productRepository
              .findById(id)
              .orElseThrow(() -> new ValidationException("There´s no product for the given ID."));
  }

  public ProductResponse save(ProductRequest request) {
    validateProductDataInformed(request);
   validateCategoryAndSupplierIdInformed(request);
   var category = categoryService.findById(request.getCategoryId());
   var supplier = supplierService.findById(request.getSupplierId());
   var product = productRepository.save(Product.of(request, supplier, category));
   return ProductResponse.of(product);


  }
    public ProductResponse update(ProductRequest request, Integer id) {
        validateProductDataInformed(request);
        validateInformedId(id);
        validateCategoryAndSupplierIdInformed(request);
        var category = categoryService.findById(request.getCategoryId());
        var supplier = supplierService.findById(request.getSupplierId());
        var product = productRepository.save(Product.of(request, supplier, category));
        product.setId(id);
        productRepository.save(product);
        return ProductResponse.of(product);
    }

  private void validateProductDataInformed(ProductRequest request) {
    if (isEmpty(request.getName())) {
     throw new ValidationException("The product ID was not informed");
   }
   if(isEmpty(request.getQuantityAvailable() )){
      throw new ValidationException("The products quantity available was not informed");
   }
   if(isEmpty(request.getQuantityAvailable() <= ZERO)){
    throw new ValidationException("The quantity should not be less or equal to zero");
   }
  }

  private void validateCategoryAndSupplierIdInformed(ProductRequest request) {
    if (request.getCategoryId() == null) {
     throw new ValidationException("The category ID was not informed");
   }
   if (request.getSupplierId() == null) {
      throw new ValidationException("The supplier ID was not informed");
    }
    categoryService.findById(request.getCategoryId());
    supplierService.findById(request.getSupplierId());
  }

  public Boolean existsByCategoryId(Integer categoryId) {
      return productRepository.existsByCategoryId(categoryId);
  }

  public Boolean existsBySupplierId(Integer categoryId) {
      return productRepository.existsBySupplierId(categoryId);
  }
  public SuccessResponse delete(Integer id) {
      validateInformedId(id);
      productRepository.deleteById(id);
      return SuccessResponse.create("The product was deleted");
  }

  private void validateInformedId(Integer id) {
      if(isEmpty(id)){
          throw new ValidationException("The product ID must be informed");
      }
  }
}
