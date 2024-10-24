package sample.cafekiosk.spring.api.controller.product.response;

import lombok.Getter;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

@Getter
public class ProductResponse {

    private Long id;

    private String productNumber;


    private ProductType type;


    private ProductSellingStatus sellingStatus;

    private String name;

    private int price;

    static class Builder{

        private Long id;

        private String productNumber;


        private ProductType type;


        private ProductSellingStatus sellingStatus;

        private String name;

        private int price;

        public Builder id(Long id){
            this.id = id;
            return this;
        }

        public Builder productNumber(String productNumber){
            this.productNumber = productNumber;
            return this;
        }

        public Builder type(ProductType type){
            this.type = type;
            return this;
        }

        public Builder sellingStatus(ProductSellingStatus sellingStatus){
            this.sellingStatus = sellingStatus;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder price(int price){
            this.price = price;
            return this;
        }

        public ProductResponse build(){
            return new ProductResponse(id, productNumber, type, sellingStatus, name, price);
        }

    }

    public static Builder builder(){
        return new Builder();
    }


    public ProductResponse(Long id, String productNumber, ProductType type, ProductSellingStatus sellingStatus, String name, int price) {
        this.id = id;
        this.productNumber = productNumber;
        this.type = type;
        this.sellingStatus = sellingStatus;
        this.name = name;
        this.price = price;
    }


    public static ProductResponse of(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productNumber(product.getProductNumber())
                .type(product.getType())
                .sellingStatus(product.getSellingStatus())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }
}
