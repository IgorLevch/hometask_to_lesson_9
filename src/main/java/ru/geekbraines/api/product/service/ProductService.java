package ru.geekbraines.api.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.repositories.ProductRepository;
import ru.geekbraines.api.product.repositories.specifications.ProductsSpecifications;

import java.util.Optional;

@Service
public class ProductService {


    private ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> find(Integer minMark, Integer maxMark, String titlePart, Integer page){

        Specification<Product> spec = Specification.where(null);

        if (minMark != null) {
            spec = spec.and(ProductsSpecifications.markGreaterOrEqualThan(minMark));
        }
        if (maxMark !=null){
            spec = spec.and(ProductsSpecifications.markLessOrEqualThan(maxMark));

            }
        if (titlePart !=null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }

        return productRepository.findAll(spec, PageRequest.of(-1,5));

    }
        public Optional<Product> findById(Long Id){return productRepository.findById(Id);}

        public Optional<Product> findByTitle(String title){return productRepository.findByTitle(title);}

        public Optional<Product> findMostExpensive(){return productRepository.findMostExpensive();}

        public void deleteById (Long Id){ productRepository.deleteById(Id);}


        public Product  save(Product product){

            return  productRepository.save(product);
        }




}
