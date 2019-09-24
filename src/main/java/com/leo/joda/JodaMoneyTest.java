package com.leo.joda;


import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.math.RoundingMode;

public class JodaMoneyTest {
    public static void main(String[] args) {
        Money money = Money.parse("USD 23.87");

        CurrencyUnit usd = CurrencyUnit.of("USD");

        money = money.plus(Money.of(usd, 12.43d));
        System.out.println(money);

        money = money.minusMajor(2);
        System.out.println(money);

        money = money.multipliedBy(3.5d, RoundingMode.DOWN);
        System.out.println(money);


    }
}
