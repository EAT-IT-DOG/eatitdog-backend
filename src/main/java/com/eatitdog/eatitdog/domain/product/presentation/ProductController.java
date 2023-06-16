package com.eatitdog.eatitdog.domain.product.presentation;

import com.eatitdog.eatitdog.domain.product.domain.Product;
import com.eatitdog.eatitdog.domain.product.presentation.dto.api.ProductAPIDto;
import com.eatitdog.eatitdog.domain.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/external-list")
    @Operation(summary = "get product list from Open API")
    public ProductAPIDto getExternalProductList(
            @RequestParam int page,
            @RequestParam int size) {
        return productService.getExternalProductList(page, size);
    }

    @GetMapping("/external-search")
    @Operation(summary = "get product list from Open API searching with name of product")
    public ProductAPIDto searchExternalProduct(@PathVariable String name) {
        return productService.getExternalProductByName(name);
    }

    @GetMapping("/food")
    @Operation(summary = "get product list from DB")
    public List<Product> getProductListByFood(@PathVariable String name) {
        return productService.getProductListByFood(name);
    }

    @GetMapping("/barcode")
    @Operation(summary = "get product by barcode number")
    public Product getProductByBarcode(@PathVariable String barcode) {
        return productService.getProductByBarcode(barcode);
    }

    @PostMapping
    public void mapProductAndFood(
            @RequestParam String productId,
            @RequestParam long foodId) {
        productService.mapProductAndFood(productId, foodId);
    }
}
