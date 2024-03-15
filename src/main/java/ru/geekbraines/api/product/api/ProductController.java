package ru.geekbraines.api.product.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.service.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public ProductDto getproductById(@PathVariable Long Id){

        return productService.findById(Id).map((s)->new ProductDto(s)).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @GetMapping
    public Page<ProductDto> getAll(
        @RequestParam(name = "p",defaultValue = "1") Integer page,
        @RequestParam(name="min_mark", defaultValue = "0") Integer minMark,
        @RequestParam(name="max_mark", required = false) Integer maxMark,
        @RequestParam(name="title_part", required = false) String titlePart
    ) {

        if (page<1){
            page=1;
        }

        return productService.find(minMark, maxMark, titlePart, page).map(
                s -> new ProductDto(s)
        );
    }

        @GetMapping("/by_title")
        public Product findByTitle(@RequestParam String title){
            return productService.findByTitle(title).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }

        @GetMapping("/mostexpensive")
        public Product findMostExp(){
            return productService.findMostExpensive().orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
        }


        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long Id){

            productService.deleteById(Id);
        }

        @PostMapping
        public ProductDto saveNewProduct(@RequestBody ProductDto productDto){

        productDto.setId(null);
        Product product = productService.save(new Product(productDto));

        return new ProductDto(product);

        }

        @PutMapping
        public ProductDto updateProduct(@RequestBody ProductDto productDto){

        Product product = productService.save(new Product(productDto));
        return new ProductDto(product) ;

        }





}
