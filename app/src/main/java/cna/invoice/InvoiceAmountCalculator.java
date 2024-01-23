package cna.invoice;

import cna.consumption.EnergyConsumption;
import cna.consumption.EnergyType;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;

import java.math.BigDecimal;

public class InvoiceAmountCalculator {

    private static final BigDecimal ELECTRICITY_PRICE_PER_KWH = BigDecimal.valueOf(0.133d);
    private static final BigDecimal GAS_PRICE_PER_KWH = BigDecimal.valueOf(0.108d);
    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION = BigDecimal.valueOf(0.11d);
    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION = BigDecimal.valueOf(0.112d);
    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION = BigDecimal.valueOf(0.123d);
    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION = BigDecimal.valueOf(0.117d);

    public Amount calculate(IndividualCustomer individualCustomer, EnergyConsumption energyConsumption) {
        var pricePerKwh = getPricePerKwh(energyConsumption.energyType());
        return new Amount(energyConsumption.kwh().multiply(pricePerKwh));
    }

    private BigDecimal getPricePerKwh(EnergyType energyType) {
        if (energyType == EnergyType.ELECTRICITY) {
            return ELECTRICITY_PRICE_PER_KWH;
        }
        return GAS_PRICE_PER_KWH;
    }

    public Amount calculate(ProCustomer proCustomer, EnergyConsumption energyConsumption) {
        var pricePerKwh = getPricePerKwh(energyConsumption.energyType(), proCustomer.sales());
        return new Amount(energyConsumption.kwh().multiply(pricePerKwh));
    }

    private BigDecimal getPricePerKwh(EnergyType energyType, Integer sales) {
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
