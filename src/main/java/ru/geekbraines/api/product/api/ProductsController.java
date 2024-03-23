package ru.geekbraines.api.product.api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbraines.api.product.converters.ProductConverter;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.exceptions.ResourceNotFoundException;
import ru.geekbraines.api.product.service.ProductsService;
import ru.geekbraines.api.product.validators.ProductValidator;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

 /*   @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }*/

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long Id){
        Product p = productsService.findById(Id).orElseThrow(()->
                new ResourceNotFoundException("Product not found,id: "+Id));//ResponseStatusException(HttpStatus.NOT_FOUND)); -- как было с общим , не очень красивым исключением
        return productConverter.entityToDto(p);
    }



    @GetMapping
    public Page<ProductDto> getAllProducts(
        @RequestParam(name = "p",defaultValue = "1") Integer page,
        @RequestParam(name="min_cost", required = false) Integer minCost,
        @RequestParam(name="max_cost", required = false) Integer maxCost,
        @RequestParam(name="title_part", required = false) String titlePart
    ) {

        if (page<1){
            page=1;
        }

        return productsService.findAll(minCost, maxCost, titlePart, page).map(

                s -> productConverter.entityToDto(s)
        );
    }

        /*@GetMapping("/by_title")
        public Product findByTitle(@RequestParam String title){
            return productsService.findByTitle(title).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/mostexpensive")
        public Product findMostExp(){
            return productsService.findMostExpensive().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }*/


        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long Id){

            productsService.deleteById(Id);
        }

        @PostMapping
        public ProductDto saveNewProduct(@RequestBody ProductDto productDto){

        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto); // пришла ДТО-шка, преобразовали ее в сущность
        product = productsService.save(product); // поработали с сущностью (сохранили ее)

        return productConverter.entityToDto(product);  // вернули обратно ДТОшку

        }

        @PutMapping
        public ProductDto updateProduct(@RequestBody ProductDto productDto){

        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);

        }





}
