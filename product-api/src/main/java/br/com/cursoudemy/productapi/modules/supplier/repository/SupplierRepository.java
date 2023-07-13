package br.com.cursoudemy.productapi.modules.supplier.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.supplier.model.Supplier;


public interface SupplierRepository extends JpaRepository<Supplier, Integer>{
  
}
