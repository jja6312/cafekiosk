package sample.cafekiosk.spring.domain.product;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static sample.cafekiosk.spring.domain.product.ProductSellingStatus.*;
import static sample.cafekiosk.spring.domain.product.ProductType.*;

//@SpringBootTest //
@ActiveProfiles("test")
@DataJpaTest // 가볍다 jpa 관련 빈들만 주입 springbootTest보다 속도가 빠르다.
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @DisplayName("원하는 판매 상태를 가진 상품들을 조회한다.")
    @Test
    void findAllBuSelliongStatusIn() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = Product.builder()
                .productNumber("002")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .name("팥빙수")
                .price(7000)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .type(HANDMADE)
                .sellingStatus(HOLD)
                .name("카페라떼")
                .price(4500)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));

        // when

        List<Product> products = productRepository.findAllBySellingStatusIn(List.of(SELLING, STOP_SELLING));

        // then

//        assertThat(products).hasSize(2)
//                .extracting("productNumber", "name", "sellingStatus")
//                .containsExactlyInAnyOrder(
//                        tuple("001", "아메리카노", SELLING),
//                        tuple("002", "팥빙수", STOP_SELLING)
//                );

        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "팥빙수", STOP_SELLING)
                );
    }

    @DisplayName("상품번호 리스트로 상품들을 조회한다.")
    @Test
    void findAllByProductNumberIn() {
        // given
        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = Product.builder()
                .productNumber("002")
                .type(HANDMADE)
                .sellingStatus(STOP_SELLING)
                .name("팥빙수")
                .price(7000)
                .build();
        Product product3 = Product.builder()
                .productNumber("003")
                .type(HANDMADE)
                .sellingStatus(HOLD)
                .name("카페라떼")
                .price(4500)
                .build();

        productRepository.saveAll(List.of(product1, product2, product3));

        // when

        List<Product> products = productRepository.findAllByProductNumberIn(List.of("001", "002"));


        assertThat(products).hasSize(2)
                .extracting("productNumber", "name", "sellingStatus")
                .containsExactlyInAnyOrder(
                        tuple("001", "아메리카노", SELLING),
                        tuple("002", "팥빙수", STOP_SELLING)
                );
    }

    @DisplayName("가장 마지막으로 저장한 상품의 상품 번호를 불러 온다.")
    @Test
    void findLatestProductNumber() {
        // given
        String targetProductNumber = "003";

        Product product1 = createProduct("001", HANDMADE, SELLING, "아메리카노", 4000);
        Product product2 = createProduct("002", HANDMADE, STOP_SELLING, "팥빙수", 7000);
        Product product3 = createProduct(targetProductNumber, HANDMADE, HOLD, "카페라떼", 4500);
        productRepository.saveAll(List.of(product1, product2, product3));

        // when
        String latestProductNumber = productRepository.findLatestProductNumber();


        assertThat(latestProductNumber).isEqualTo("003");
    }

    @DisplayName("가장 마지막으로 저장한 상품 번호를 읽어올 떄, 상품이 하나도 없는 경우 null을 반환한다.")
    @Test
    void findLatestProductNumberWhenProductIsEmpty() {
        //given
        //when
        String latestProductNumber = productRepository.findLatestProductNumber();

        //then
        assertThat(latestProductNumber)
                .isNull();
    }

    private Product createProduct(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        return Product.builder()
                .productNumber(productNumber)
                .type(type)
                .sellingStatus(sellingStatus)
                .name(name)
                .price(price)
                .build();
    }


}