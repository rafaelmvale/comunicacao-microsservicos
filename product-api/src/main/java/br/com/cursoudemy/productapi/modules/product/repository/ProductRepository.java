package br.com.cursoudemy.productapi.modules.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursoudemy.productapi.modules.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
  
}
