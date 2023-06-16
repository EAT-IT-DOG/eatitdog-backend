package com.eatitdog.eatitdog.domain.product.service;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.domain.repository.FoodRepository;
import com.eatitdog.eatitdog.domain.food.exception.FoodNotFoundException;
import com.eatitdog.eatitdog.domain.product.domain.Product;
import com.eatitdog.eatitdog.domain.product.domain.repository.ProductRepository;
import com.eatitdog.eatitdog.domain.product.exception.ExternalProductNotFoundException;
import com.eatitdog.eatitdog.domain.product.exception.ProductAlreadyExistsException;
import com.eatitdog.eatitdog.domain.product.exception.ProductNotFoundException;
import com.eatitdog.eatitdog.domain.product.presentation.dto.api.ProductAPIDto;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import com.eatitdog.eatitdog.global.infra.RestRequest;
import com.eatitdog.eatitdog.global.lib.JsonStringToObjectMapper;
import com.eatitdog.eatitdog.global.properties.OpenAPIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OpenAPIProperties openApiProperties;
    private final FoodRepository foodRepository;
    private final RestRequest restRequest;

    public ProductAPIDto getExternalProductList(int page, int size) {
        String url = getOpenAPIDefaultUriBuilder()
                .queryParam("pageNo", page)
                .queryParam("numOfRows", size)
                .build().toUriString();
        String response = restRequest.get(url, String.class);
        return JsonStringToObjectMapper.convert(response, ProductAPIDto.class);
    }

    @Cacheable(value = "externalProductByProductNameCaching", key = "#productName")
    public ProductAPIDto getExternalProductByName(String productName) {
        String url = getOpenAPIDefaultUriBuilder()
                .queryParam("prdlstNm", URLEncoder.encode(productName, StandardCharsets.UTF_8))
                .build().toUriString();
        String response = restRequest.get(url, String.class);
        return JsonStringToObjectMapper.convert(response, ProductAPIDto.class);
    }

    @Cacheable(value = "productListByFoodCaching", key = "#foodName")
    public List<Product> getProductListByFood(String foodName) {
        Food food = foodRepository.findByName(foodName)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);
        return productRepository.findAllByFood(food);
    }

    public Product getProductByBarcode(String barcode) {
        return productRepository.findByBarcode(barcode)
                .orElseThrow(() -> ProductNotFoundException.EXCEPTION);
    }

    // productId는 Open API의 품목보고번호 (prdlstReportNo)
    @Transactional(rollbackFor = Exception.class)
    public void mapProductAndFood(String productId, long foodId) {
        String url = getOpenAPIDefaultUriBuilder()
                .queryParam("prdlstReportNo", productId)
                .build().toUriString();
        String response = restRequest.get(url, String.class);
        ProductAPIDto dto = JsonStringToObjectMapper.convert(response, ProductAPIDto.class);

        if (dto.getBody().getItems().isEmpty()) {
            throw ExternalProductNotFoundException.EXCEPTION;
        }

        if (productRepository.existsById(productId)) {
            throw ProductAlreadyExistsException.EXCEPTION;
        }

        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);

        ProductAPIDto.Item item = dto.getBody().getItems().get(0).getItem();
        Product product = Product.builder()
                .id(productId)
                .name(item.getPrdlstNm())
                .rawMaterials(item.getRawmtrl())
                .allergy(item.getAllergy())
                .kind(item.getPrdkind())
                .capacity(item.getCapacity())
                .image(item.getImgurl1())
                .barcode(item.getBarcode())
                .food(food)
                .build();
        productRepository.save(product);
    }

    private UriComponentsBuilder getOpenAPIDefaultUriBuilder() {
        return UriComponentsBuilder
                .fromUriString(openApiProperties.getBaseUrl())
                .queryParam("returnType", "json")
                .queryParam("ServiceKey", openApiProperties.getKey());
    }


}
