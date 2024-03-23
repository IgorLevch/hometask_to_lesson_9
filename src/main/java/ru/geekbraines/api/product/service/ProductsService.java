package ru.geekbraines.api.product.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ru.geekbraines.api.product.data.Product;
import ru.geekbraines.api.product.dto.ProductDto;
import ru.geekbraines.api.product.exceptions.ResourceNotFoundException;
import ru.geekbraines.api.product.repositories.ProductsRepository;
import ru.geekbraines.api.product.repositories.specifications.ProductsSpecifications;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {

    // Инжекшны тоже можно заменять с помощью ломбока: в бин добавялем модификатор final
    // (потому что мы все равно заинжектили это поле и менять больше не будем никогда)
    // и ставим аннотацию @RequiredArgsConstructor (это ломбоковская аннотация)  -- она для всех полей, которые
    // final в Бине делает конструктор с этим набором полей (по сути то, что мы до этого делали вручную и мы сейчас можем
    // убрать из класса конструктор -- я его закомментил, а не удалил)

    private final ProductsRepository productsRepository;
   /* @Autowired
    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }*/

    public Page<Product> findAll(Integer minCost, Integer maxCost, String titlePart, Integer page){


        Specification<Product> spec = Specification.where(null);

        if (minCost != null) {
            spec = spec.and(ProductsSpecifications.costGreaterOrEqualThan(minCost));
        }
        if (maxCost !=null){
            spec = spec.and(ProductsSpecifications.costLessOrEqualThan(maxCost));

            }
        if (titlePart !=null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }

        return productsRepository.findAll(spec, PageRequest.of(1,50)); // исправил с -1, как было у препа,
        // потому что в логах шла ошибка, что IllegalArgumentException :  страница не может быть -1

    }
        public Optional<Product> findById(Long Id){return productsRepository.findById(Id);}

     /*   public Optional<Product> findByTitle(String title){return productsRepository.findByTitle(title);}

        public Optional<Product> findMostExpensive(){return productsRepository.findMostExpensive();}*/

        public void deleteById (Long Id){ productsRepository.deleteById(Id);}


        public Product  save(Product product){

            return  productsRepository.save(product);
        }
    @Transactional    // метод транзакционный . ОБъект productsRepository.findById(productDto.getId()).orElseThrow(()
    // находится в контексте постоянства и автоматом сохранится, как только мы выйдем из данного метода
    public Product  update(ProductDto productDto){
        Product product = productsRepository.findById(productDto.getId()).orElseThrow(()->
                new ResourceNotFoundException("невозможно обновить продукт, не найден в базе id: "+productDto.getId()));
                product.setCost(productDto.getCost());
                product.setTitle(productDto.getTitle());
        return  product;   // айдишник ни в коем случае не меняем
    }




}
