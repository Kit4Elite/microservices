package com.kit4elite.entity;

import com.kit4elite.valueObject.Money;
import com.kit4elite.valueObject.OrderId;
import com.kit4elite.valueObject.OrderItemId;


public class OrderItem  extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private Product product;
    private int quantity;
    private Money price;
    private Money subTotal;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        orderId = builder.orderId;
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initializeOrderItem(OrderId orderId,OrderItemId orderItemId) {
        this.orderId = orderId;
        this.setId(orderItemId);
    }

    public OrderId getOrderId() {
        return orderId;
    }
    public boolean validateOrderItem() {
        return isPriceValid()&&this.product.isAvailable();
    }

    public  boolean isPriceValid() {
        return this.price.isGreaterZero()&&this.price.multiply(quantity).equals(subTotal);
    }

    public int getQuantity() {
        return quantity;
    }

    public Money getPrice() {
        return price;
    }

    public Money getSubTotal() {
        return subTotal;
    }



    public static final class Builder {
        private OrderItemId orderItemId;
        private OrderId orderId;
        private Product product;
        private int quantity;
        private Money price;
        private Money subTotal;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderId val) {
            orderId =val;
            return this;
        }
        public Builder orderItemId(OrderItemId val) {
            orderItemId =val;
            return this;
        }
        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(int val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
