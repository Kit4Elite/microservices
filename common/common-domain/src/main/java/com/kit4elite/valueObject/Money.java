package com.kit4elite.valueObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Money {
    //this class is immutable now
    private final BigDecimal amount;
    public static final Money ZERO = new Money(BigDecimal.ZERO);
    //because amout is final
    public Money(BigDecimal amount) {
        this.amount = amount;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public Boolean isGreaterZero() {
        return this.amount!=null && this.amount.compareTo(BigDecimal.ZERO) > 0;
    }
    public Boolean isGreaterThan(Money other) {
        return this.amount!=null && this.amount.compareTo(other.getAmount()) > 0;
    }
    public Money add(Money other) {
        return new Money(this.amount.add(other.getAmount()).setScale(2,RoundingMode.HALF_DOWN));
    }
    public Money substract(Money other) {
        return new Money(this.amount.subtract(other.getAmount()).setScale(2,RoundingMode.HALF_DOWN));
    }
    public Money multiply(int quantity) {
        return new Money(this.amount.multiply(BigDecimal.valueOf(quantity)).setScale(2,RoundingMode.HALF_DOWN));
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
