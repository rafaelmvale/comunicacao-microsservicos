package br.com.cursoudemy.productapi.modules.supplier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cursoudemy.productapi.config.exception.ValidationException;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import br.com.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;
import br.com.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;


@Service
public class SupplierService {
  
  @Autowired
  private SupplierRepository supplierRepository;
  
  public Supplier findById(Integer id) {
    return supplierRepository.findById(id)
      .orElseThrow(() -> new ValidationException("There´s no supplier for the given id"));
  }


  public SupplierResponse save(SupplierRequest request) {
    validateCategoryNameInformed(request);
    var supplier = supplierRepository.save(Supplier.of(request));
    return SupplierResponse.of(supplier);
  }
  
  private void validateCategoryNameInformed(SupplierRequest request) {
    if (request.getName() == null) {
      throw new ValidationException("The category description was not informed");
    }
  }
}