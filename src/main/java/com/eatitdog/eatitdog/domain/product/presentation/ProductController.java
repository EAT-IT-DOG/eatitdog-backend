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

    @GetMapping("/search")
    @Operation(summary = "get product list from Open API searching with name of product")
    public ProductAPIDto searchProduct(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @GetMapping("/")
    @Operation(summary = "get product list from DB")
    public List<Product> getProductListByFood(@RequestParam String name) {
        return productService.getProductListByFood(name);
    }

    @PostMapping
    public void mapProductAndFood(
            @RequestParam String productId,
            @RequestParam long foodId) {
        productService.mapProductAndFood(productId, foodId);
    }
}
