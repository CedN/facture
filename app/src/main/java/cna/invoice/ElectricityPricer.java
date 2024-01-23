package cna.invoice;

import cna.customer.Customer;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;

import java.math.BigDecimal;

public class ElectricityPricer {

    private static final BigDecimal ELECTRICITY_PRICE_PER_KWH = BigDecimal.valueOf(0.133d);
    private static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OF_1_MILLION_OR_OVER = BigDecimal.valueOf(0.11d);
    private static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION = BigDecimal.valueOf(0.112d);

    BigDecimal getPricePerKwh(Customer customer) {
        return switch (customer) {
            case IndividualCustomer c -> ELECTRICITY_PRICE_PER_KWH;
            case ProCustomer c -> getPricePerKwh(c.sales());
        };
    }

    private BigDecimal getPricePerKwh(Integer sales) {
        if (sales >= 1_000_000) {
            return ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OF_1_MILLION_OR_OVER;
        }
        return ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION;
    }
}
