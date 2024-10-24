package sample.cafekiosk.spring.domain.product;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sample.cafekiosk.spring.domain.BaseEntity;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

    private String productNumber;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    @Enumerated(EnumType.STRING)
    private ProductSellingStatus sellingStatus;

    private String name;

    private int price;

    @Builder
    public Product(String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }

    public static class ProductBuilder {
        private Long id;
        private String productNumber;
        private ProductType type;
        private ProductSellingStatus sellingStatus;
        private String name;
        private int price;

        public ProductBuilder id(Long id){
            this.id = id;
            return this;
        }
        public ProductBuilder productNumber(String productNumber){
            this.productNumber = productNumber;
            return this;
        }

        public ProductBuilder type(ProductType type){
            this.type = type;
            return this;
        }

        public ProductBuilder sellingType(ProductSellingStatus sellingType){
            this.sellingStatus = sellingType;
            return this;
        }

        public ProductBuilder name(String name){
            this.name = name;
            return this;
        }

        public ProductBuilder price(int price){
            this.price = price;
            return this;
        }

        public Product build(){
            return new Product(id, productNumber, type, sellingStatus, name, price);
        }
    }

    public static ProductBuilder builder(){
        return new ProductBuilder();
    }

    private Product(Long id, String producNumber, ProductType type, ProductSellingStatus sellingType, String name, int price){
        this.id = id;
        this.productNumber = producNumber;
        this.type = type;
        this.sellingStatus = sellingType;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productNumber='" + productNumber + '\'' +
                ", type=" + type +
                ", sellingType=" + sellingStatus +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
