package ru.geekbraines.api.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.geekbraines.api.product.data.Product;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

  //  Optional<Product> findByTitle(String title);


/*    @Query("select c from Product c where c.cost=(select max(c2.cost) from Product c2)")
    Optional<Product> findMostExpensive();*/


 //   public Product save(Product product);


}
