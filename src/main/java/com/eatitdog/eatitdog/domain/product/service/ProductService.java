package com.eatitdog.eatitdog.domain.product.service;

import com.eatitdog.eatitdog.domain.food.domain.Food;
import com.eatitdog.eatitdog.domain.food.domain.repository.FoodRepository;
import com.eatitdog.eatitdog.domain.food.exception.FoodNotFoundException;
import com.eatitdog.eatitdog.domain.product.domain.Product;
import com.eatitdog.eatitdog.domain.product.domain.repository.ProductRepository;
import com.eatitdog.eatitdog.domain.product.exception.ExternalProductNotFoundException;
import com.eatitdog.eatitdog.domain.product.presentation.dto.api.ProductAPIDto;
import com.eatitdog.eatitdog.global.annotation.ServiceWithTransactionalReadOnly;
import com.eatitdog.eatitdog.global.infra.RestRequest;
import com.eatitdog.eatitdog.global.lib.JsonStringToObjectMapper;
import com.eatitdog.eatitdog.global.properties.OpenAPIProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
        String url = openApiProperties.getBaseUrl() + "?returnType=json&ServiceKey=" + openApiProperties.getKey() + "&pageNo=" + page + "&numOfRows=" + size;
        String response = restRequest.get(url, String.class);
        return JsonStringToObjectMapper.convert(response, ProductAPIDto.class);
    }

    public ProductAPIDto getProductByName(String productName) {
        String url = openApiProperties.getBaseUrl() + "?returnType=json&ServiceKey=" + openApiProperties.getKey() + "&prdlstNm=" + URLEncoder.encode(productName, StandardCharsets.UTF_8);
        String response = restRequest.get(url, String.class);
        return JsonStringToObjectMapper.convert(response, ProductAPIDto.class);
    }

    public List<Product> getProductListByFood(String foodName) {
        Food food = foodRepository.findByName(foodName)
                .orElseThrow(() -> FoodNotFoundException.EXCEPTION);
        return productRepository.findAllByFood(food);
    }

    // productId는 Open API의 품목보고번호 (prdlstReportNo)
    @Transactional(rollbackFor = Exception.class)
    public void mapProductAndFood(String productId, long foodId) {
        String url = openApiProperties.getBaseUrl() + "?returnType=json&ServiceKey=" + openApiProperties.getKey() + "&prdlstReportNo=" + productId;
        String response = restRequest.get(url, String.class);
        ProductAPIDto dto = JsonStringToObjectMapper.convert(response, ProductAPIDto.class);

        if (dto.getBody().getItems().isEmpty()) {
            throw ExternalProductNotFoundException.EXCEPTION;
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
}
