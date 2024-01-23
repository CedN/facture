package cna.invoice;

import cna.customer.Customer;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;

import java.math.BigDecimal;

class GasPricer {

    private static final BigDecimal GAS_PRICE_PER_KWH = BigDecimal.valueOf(0.108d);
    private static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OF_1_MILLION_OR_OVER = BigDecimal.valueOf(0.123d);
    private static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION = BigDecimal.valueOf(0.117d);

    BigDecimal getPricePerKwh(Customer customer) {
        return switch (customer) {
            case IndividualCustomer c -> GAS_PRICE_PER_KWH;
            case ProCustomer c -> getPricePerKwh(c.sales());
        };
    }

    private BigDecimal getPricePerKwh(Integer sales) {
        if (sales >= 1_000_000) {
            return GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OF_1_MILLION_OR_OVER;
        }
        return GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION;
    }
}
