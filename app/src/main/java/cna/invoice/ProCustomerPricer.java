package cna.invoice;

import cna.consumption.EnergyType;

import java.math.BigDecimal;

class ProCustomerPricer {

    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION = BigDecimal.valueOf(0.11d);
    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION = BigDecimal.valueOf(0.112d);
    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION = BigDecimal.valueOf(0.123d);
    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION = BigDecimal.valueOf(0.117d);

    BigDecimal getPricePerKwh(EnergyType energyType, Integer sales) {
        if (energyType == EnergyType.ELECTRICITY) {
            return getElectricityPricePerKwh(sales);
        }
        return getGasPricePerKwh(sales);
    }

    private BigDecimal getElectricityPricePerKwh(Integer sales) {
        if (sales > 1_000_000) {
            return ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION;
        }
        return ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION;
    }

    private BigDecimal getGasPricePerKwh(Integer sales) {
        if (sales > 1_000_000) {
            return GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION;
        }
        return GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION;
    }
}
