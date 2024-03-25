package ru.geekbraines.api.product.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;

import ru.geekbraines.api.product.data.Product;

public class ProductsSpecifications {

    public static Specification<Product> costGreaterorEqualThan(Integer cost){

            return(Specification<Product>)(root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), cost);


    }



    public static Specification<Product> costLessOrEqualThan(Integer cost){

        return(Specification<Product>)(root, criteriaQuery, criteriaBuilder) ->
            criteriaBuilder.lessThanOrEqualTo(root.get("cost"), cost);


}

    public static Specification<Product>  titleLike(String titlePart){

            return(Specification<Product>)(root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));

    }



}
