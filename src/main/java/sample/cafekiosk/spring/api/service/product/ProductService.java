package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sample.cafekiosk.spring.api.controller.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getSellingProducts(){
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());

    }





    public static void main(String[] args) {
        Product product = Product.builder()
                .name("happy")
                .productNumber("1")
                .type(ProductType.HANDMADE)
                .sellingType(ProductSellingStatus.HOLD)
                .price(2000)
                .build();

        System.out.println(product);


//        List<Integer> numbers = List.of(5, 10, 15, 20);
//
//        // filter 조건에 맞는 요소를 필터링
//        List<Integer> filteredList = numbers.stream()
//                .filter(n -> n>10)
//                .collect(Collectors.toList());
//        // map 각 요소를 변환
//        List<Integer> squaredNumbers = numbers.stream()
//                .map(n -> n*n)
//                .collect(Collectors.toList());
//
//        //  sored 요소를 정렬
//        List<Integer> number = List.of(15, 5, 20, 10);
//        List<Integer> sortedList = number.stream()
//                .sorted()
//                .collect(Collectors.toList());
//
//        // distinct() 중복요소 제거
//        List<Integer> num = List.of(1, 2, 2, 3, 3, 3, 4);
//        List<Integer> distinctList = num.stream()
//                .distinct()
//                .collect(Collectors.toList());
//
//        // colect() 스트림의 결과를 컬렉션으로 변환
//        List<String> names = List.of("Jone", "Jane", "Jake");
//        List<String> collectedList = names.stream()
//                .collect(Collectors.toList());
//        // forEach() 각 요소에 대해 작업을 수행
//        List<String> name = List.of("John" , "Jane", "Jake");
//        name.stream().forEach(System.out::println);
//
//        // reduce(): 모든 요소를 하나의 값으로 축소
//        List<Integer> numb = List.of(1,2,3,4);
//        int sum = numb.stream()
//                .reduce(0, Integer::sum);
//
//        // count() : 스트림의 요소 개수를 반환
//        List<String> nam = List.of("John", "Jane", "Jake");
//        long count = names.stream().count();
    }
}
