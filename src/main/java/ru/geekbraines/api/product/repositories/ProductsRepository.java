package ru.geekbraines.api.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ru.geekbraines.api.product.data.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long >, JpaSpecificationExecutor<Product>{




}
