package ru.geekbraines.api.product.api;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.service.ProductsService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;
    
    

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long Id){

        Product p = productsService.findById(Id).orElseThrow(() ->
        new ResourceNotFoundException("Product not found,id: "+Id));

        return productConverter.entityToDto(p); 
    }

    @GetMapping 
    public Page<ProductDto> getAllProducts(
        @RequestParam(name="p",defaultValue = "1") Integer page,
        @RequestParam(name="min_cost", required=false) Integer minCost,
        @RequestParam(name="max_cost", required=false) Integer maxCost,
        @RequestParam(name="title_part", required=false) String titlePart
    )  {
         if (page<1) {
            page=1;
         }   

            return productsService.findAll(minCost, maxCost, titlePart, page).map(
                
            s ->  productConverter.entityToDto(s)
            );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long Id){

        productsService.deleteById(Id);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto){
            
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.save(product);

        return productConverter.entityToDto(product);
    } 
        @PutMapping
        public ProductDto updateProduct(@RequestBody ProductDto productDto){

            productValidator.validate(productDto);
            Product product = productsService.update(productDto);
            return productConverter.entityToDto(product);

        }



}
