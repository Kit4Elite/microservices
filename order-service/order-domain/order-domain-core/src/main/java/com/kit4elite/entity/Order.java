package com.kit4elite.entity;

import com.kit4elite.execption.OrderDomainException;
import com.kit4elite.valueObject.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final ProductId productId;
    private final StreetAddress  streetAddress;
    private final Money price;
    private final List<OrderItem> orderItems;

    private TrackingId trackingId;
    private  OrderStatus orderStatus;
    private List<String> failureMessage;

    //constr private for builder
    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        productId = builder.productId;
        streetAddress = builder.streetAddress;
        price = builder.price;
        orderItems = builder.orderItems;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessage = builder.failureMessage;
    }

    public static Builder builder() {
        return new Builder();
    }

    //initializers
    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    private void initializeOrderItems() {
        AtomicLong itemId= new AtomicLong(1);
        orderItems.forEach(orderItem -> {
            orderItem.initializeOrderItem(this.getId(),new OrderItemId(itemId.getAndIncrement()));
        });
    }

    //lis of state mutators
    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }
    public void pay(){
        if(orderStatus != OrderStatus.PENDING){
            throw  new OrderDomainException("Order status is correct state !");
        }
        orderStatus = OrderStatus.PAID;
    }
    public void initCancel(List<String> failureMessage){
        if(!(orderStatus==OrderStatus.PAID)){
            throw  new OrderDomainException("Order status is correct state !");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessage);
    }
    public void approve(){
        if(!(orderStatus==OrderStatus.PAID )){
            throw  new OrderDomainException("Order status is correct state !");
        }
        orderStatus = OrderStatus.APPROVED;
    }
    public void cancel(){
        if (!(orderStatus==OrderStatus.CANCELLING || orderStatus==OrderStatus.PENDING)){
            throw  new OrderDomainException("Order status is correct state !");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessage);
    }

    // validation helpers
    private void updateFailureMessages(List<String> failureMessage) {
        if(failureMessage!=null&& this.failureMessage!=null){
            this.failureMessage.addAll(failureMessage);
        }
        if(this.failureMessage==null){
            this.failureMessage = failureMessage;
        }
    }

    // list of validators
    private void validateItemsPrice() {
        Money totalMoney = orderItems.stream().map((orderItem)-> {
            validateItemPrice(orderItem);
            return orderItem.getSubTotal();
        }).reduce(Money.ZERO,Money::add);
        throw  new OrderDomainException("Order item price is " + price.getAmount() +
                " id not equal to order item total "+ totalMoney.getAmount());
    }
    private void validateItemPrice(OrderItem orderItem) {
        if(!orderItem.validateOrderItem()){
            throw  new OrderDomainException("Order  price is " + price.getAmount() +
                    " id not equal to order item total "+ orderItem.getPrice().getAmount() );
        }
    }
    private void validateTotalPrice() {
        if (price == null || !price.isGreaterZero()) {
            throw  new OrderDomainException("Total price must be greater than zero");
        }
    }

    private  void  validateInitialOrder() {
        if(orderStatus ==null || getId() == null){
            throw  new OrderDomainException("order is incorrect for initialization");
        }
    }

    // lidt of  getters
    public CustomerId getCustomerId() {
        return customerId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public StreetAddress getStreetAddress() {
        return streetAddress;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public List<String> getFailureMessage() {
        return failureMessage;
    }

    //builder implementation
    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private ProductId productId;
        private StreetAddress streetAddress;
        private Money price;
        private List<OrderItem> orderItems;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessage;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder productId(ProductId val) {
            productId = val;
            return this;
        }

        public Builder streetAddress(StreetAddress val) {
            streetAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder orderItems(List<OrderItem> val) {
            orderItems = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessage(List<String> val) {
            failureMessage = val;
            return this;
        }
        public Order build() {
            return new Order(this);
        }
    }
}
