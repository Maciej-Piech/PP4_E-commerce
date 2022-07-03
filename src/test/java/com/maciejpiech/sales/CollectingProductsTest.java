//package com.maciejpiech.sales;
//
//import org.junit.jupiter.api.Test;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class CollectingProductsTest {
//
//    CartStorage cartStorage;
//    List<ProductDetails> productDetails;
//
//    @BeforeEach
//    void setup() {
//        cartStorage = new CartStorage();
//        productDetails = new ArrayList<>();
//    }
//
//    @Test
//    String newOfferIsEmpty() {
//        String customerId = thereIsCustomer();
//        Sales sales = thereIsSalesModule();
//
//        Offer offer  = sales.getCurrentOffer(customerId);
//
//        assertEquals(BigDecimal.ZERO, offer.getTotal());
//        assertEquals(0, offer.getItemsCount());
//
//        @Test
//        void allowsToAddProduct () {
//            String productId = thereIsProduct("001", BigDecimal.TEN);
//            String customerId = thereIsCustomer();
//            Sales sales = thereIsSalesModule();
//
//            sales.addToCart(customerId, productId);
//
//            thereIsXProductsInCustomersCart(customerId, 1);
//        }
//
//        @Test
//        void allowsToAddSameProductTwice () {
//            String productId = thereIsProduct("001", BigDecimal.TEN);
//            String customerId = thereIsCustomer();
//            Sales sales = thereIsSalesModule();
//
//            sales.addToCart(customerId, productId);
//            sales.addToCart(customerId, productId);
//
//            thereIsXProductsInCustomersCart(customerId, 1);
//        }
//
//        @Test
//        void allowsToAddProductAndRetrieveAnOffer() {
//            String productId = thereIsProduct("lego", BigDecimal.TEN);
//            String customerId = thereIsCustomer();
//            Sales sales = thereIsSalesModule();
//
//            sales.addToCart(customerId, productId);
//            Offer offer  = sales.getCurrentOffer(customerId);
//
//            assertEquals(BigDecimal.TEN, offer.getTotal());
//            assertEquals(1, offer.getItemsCount());
//        }
//
//        @Test
//        void itPresentsCurrentOffer () {
//            String customerId = thereIsCustomer();
//            Sales sales = thereIsSalesModule();
//
//            Offer offer = sales.getCurrentOffer(customerId);
//
//            assertEquals(BigDecimal.valueOf(30.00), offer.getTotal());
//            assertEquals(0, offer.getItemsCount());
//        }
//
//        @Test
//        void itAllowsToAddMultipleProductsToCart () {
//            String customerId = thereIsCustomer();
//            String productId1 = thereIsProduct("001", BigDecimal.valueOf(24.0));
//            String productId2 = thereIsProduct("002", BigDecimal.valueOf(30.0));
//            Sales sales = thereIsSalesModule();
//
//            sales.addToCart(customerId, productId1);
//            sales.addToCart(customerId, productId2);
//
//            Offer offer = sales.getCurrentOffer(customerId);
//
//            assertEquals(BigDecimal.valueOf(20.00), offer.getTotal());
//            assertEquals(2, offer.getItemsCount());
//        }
//
//        private String thereIsCustomer() {
//            return UUID.randomUUID().toString();
//        }
//
//        private void thereIsXProductsInCustomersCart (String customerId,int expectedItemsCount){
//            int realItemsCount = cartStorage.getForCustomer(customerId)
//                    .get()
//                    .getItemsCount();
//            assertEquals(expectedItemsCount, realItemsCount);
//        }
//
//        private Sales thereIsSalesModule() {
//            return new Sales(
//                    cartStorage,
//                     new ListProductDetailsProvider(productDetails),
//                     new DummyPaymentGateway(),
//                     new ReservationStorage()
//            );
//        }
//
//        private String thereIsProduct(String id, BigDecimal price){
//            productDetails.add(
//                    new ProductDetails(id, id, price)
//            );
//            return id;
//        }
//    }
