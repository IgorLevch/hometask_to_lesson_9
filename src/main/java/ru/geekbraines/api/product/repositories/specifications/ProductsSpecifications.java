package ru.geekbraines.api.product.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbraines.api.product.data.Product;

public class ProductsSpecifications {

        public static Specification<Product> markGreaterOrEqualThan (Integer mark) {

            return(Specification<Product>)(root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("mark"),mark);
        }


        public static Specification<Product> markLessOrEqualThan (Integer mark){

            return (Specification<Product>) (root,criteriaQuery, criteriaBuilder)  ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("mark"), mark);
        }


        public static Specification<Product> titleLike(String titlePart){

            return (Specification<Product>) (root,criteriaQuery,criteriaBuilder) ->
                    criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
        }





}
