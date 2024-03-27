package ru.geekbraines.api.product.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.exceptions.ResourceNotFoundException;
import ru.geekbraines.api.product.repositories.ProductsRepository;
import ru.geekbraines.api.product.repositories.specifications.ProductsSpecifications;

@Service
@RequiredArgsConstructor
public class ProductsService {

    // сам по себе сервис обязан отдавать сущности, потому что это внутренняя логика 

    private final ProductsRepository productsRepository;


    public Page<Product> findAll (Integer minCost, Integer maxCost, String titlePart, Integer page){

        Specification <Product> spec = Specification.where(null);


            if (minCost!=null) {

                spec = spec.and(ProductsSpecifications.costGreaterorEqualThan(minCost));
                
            }

                if (maxCost!=null) {
                    spec=spec.and(ProductsSpecifications.costLessOrEqualThan(maxCost));
                    
                }    

                if (titlePart!=null) {
                    spec=spec.and(ProductsSpecifications.titleLike(titlePart));
                    
                }

                return productsRepository.findAll(spec,PageRequest.of(1, 50));



    }


public Optional<Product> findById(Long Id){

    return productsRepository.findById(Id);
}

public void deleteById(Long Id){

    productsRepository.deleteById(Id);
}

public Product save(Product product){

    return productsRepository.save(product);
}
@Transactional
public Product update(ProductDto productDto){

        Product product = productsRepository.findById(productDto.getId()).orElseThrow(() ->
        new ResourceNotFoundException("невозможно обновить продукт, не найден в базе id: "+productDto.getId()));
        product.setCost(productDto.getCost());
        product.setTitle(productDto.getTitle());

        return product;

}




}
