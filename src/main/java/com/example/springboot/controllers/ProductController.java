package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


// @Valid garante a validação que foi feita no ProductRecordDto
// @RequestBody significa que ele vai receber como corpo o nosso productRecordDto
@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel); // Copia o valor do productRecordDto e seta no productModel. O var facilita a instanciação substituindo a nescessidade de escrever ProductModel() no lado esquerdo
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts(){
        List<ProductModel> productsList = productRepository.findAll();
        if (!productsList.isEmpty()){
            for (ProductModel product : productsList){
                UUID id = product.getIdProducts();
                product.add(linkTo(methodOn(ProductController.class).getProductById(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable(value = "id") UUID id){ // O @PathVariable pega o valor que está na rota {id} e define que o UUID id é este valor
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        if (optionalProductModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");
        }
        optionalProductModel.get().add(linkTo(methodOn(ProductController.class).getAllProducts()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(optionalProductModel.get());
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRecordDto productRecordDto, @PathVariable(value = "id") UUID id){
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        if (optionalProductModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");
        }
        var productModel = optionalProductModel.get();

        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable (value = "id") UUID id ){
        Optional<ProductModel> optionalProductModel = productRepository.findById(id);
        if (optionalProductModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not found");
        }
        productRepository.delete(optionalProductModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully"); // Como o objeto é deletado, ele não é retornado. Portanto, o productRepository.delete(optionalProductModel.get()) é separado
    }
}
