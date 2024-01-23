package cna;

import cna.consumption.EnergyConsumption;
import cna.consumption.EnergyType;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;
import cna.invoice.Amount;
import cna.invoice.InvoiceAmountCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class InvoiceAmountCalculatorShould {

    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_INDIVIDUAL_CUSTOMER = BigDecimal.valueOf(0.133d);
    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_INDIVIDUAL_CUSTOMER = BigDecimal.valueOf(0.108d);

    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION = BigDecimal.valueOf(0.110d);
    public static final BigDecimal ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION = BigDecimal.valueOf(0.112d);

    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION = BigDecimal.valueOf(0.123d);
    public static final BigDecimal GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION = BigDecimal.valueOf(0.117d);

    @Nested
    class ForIndividualCustomer {

        private IndividualCustomer customer = new IndividualCustomer("EKW263689034", "Mr", "Doe", "John");

        @Test
        void compute_electricity_amount() {
            var consumedKwh = BigDecimal.valueOf(10.1d);

            var invoicedAmount = new InvoiceAmountCalculator().calculate(
                    customer,
                    new EnergyConsumption(EnergyType.ELECTRICITY, consumedKwh));

            Assertions.assertEquals(new Amount(consumedKwh.multiply(ELECTRICITY_PRICE_PER_KWH_FOR_INDIVIDUAL_CUSTOMER)), invoicedAmount);
        }

        @Test
        void compute_gas_amount() {
            var consumedKwh = BigDecimal.valueOf(5.36789d);

            var invoicedAmount = new InvoiceAmountCalculator().calculate(
                    customer,
                    new EnergyConsumption(EnergyType.GAS, consumedKwh));

            Assertions.assertEquals(new Amount(consumedKwh.multiply(GAS_PRICE_PER_KWH_FOR_INDIVIDUAL_CUSTOMER)), invoicedAmount);
        }
    }

    @Nested
    class ForProCustomerWithSalesOver1Million {

        private ProCustomer customer = new ProCustomer("EKW146830056", "23646789543457", "Big Company", 1_000_001);

        @Test
        void compute_electricity_amount() {
            var consumedKwh = BigDecimal.valueOf(4_670.24754d);

            var invoicedAmount = new InvoiceAmountCalculator().calculate(
                    new ProCustomer("EKW146830056", "23646789543457", "Big Company", 1_000_001),
                    new EnergyConsumption(EnergyType.ELECTRICITY, consumedKwh));

            Assertions.assertEquals(new Amount(consumedKwh.multiply(ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION)), invoicedAmount);
        }

        @Test
        void compute_gas_amount() {
            var consumedKwh = BigDecimal.valueOf(88_000.5436d);

            var invoicedAmount = new InvoiceAmountCalculator().calculate(
                    new ProCustomer("EKW146830056", "23646789543457", "Big Company", 1_000_001),
                    new EnergyConsumption(EnergyType.GAS, consumedKwh));

            Assertions.assertEquals(new Amount(consumedKwh.multiply(GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_OVER_1_MILLION)), invoicedAmount);
        }

    }

    @Nested
    class ForProCustomerWithSalesBellow1Million {

        private ProCustomer customer = new ProCustomer("EKW230468390", "93045928492730", "Little Company", 999_999);

        @Test
        void compute_electricity_amount() {
            var consumedKwh = BigDecimal.valueOf(4_670.24754d);

            var invoicedAmount = new InvoiceAmountCalculator().calculate(
                    new ProCustomer("EKW230468390", "93045928492730", "Little Company", 999_999),
                    new EnergyConsumption(EnergyType.ELECTRICITY, consumedKwh));

            Assertions.assertEquals(new Amount(consumedKwh.multiply(ELECTRICITY_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELLOW_1_MILLION)), invoicedAmount);
        }

        @Test
        void compute_gas_amount() {
            var consumedKwh = BigDecimal.valueOf(4_670.24754d);

            var invoicedAmount = new InvoiceAmountCalculator().calculate(
                    new ProCustomer("EKW230468390", "93045928492730", "Little Company", 999_999),
                    new EnergyConsumption(EnergyType.GAS, consumedKwh));

            Assertions.assertEquals(new Amount(consumedKwh.multiply(GAS_PRICE_PER_KWH_FOR_PRO_CUSTOMER_WITH_SALES_BELOW_1_MILLION)), invoicedAmount);
        }
    }

    @Test
    void compute_electricity_and_gas_amount() {
        var customer = new IndividualCustomer("EKW263689034", "Mr", "Doe", "John");
        var gasConsumption = new EnergyConsumption(EnergyType.GAS, BigDecimal.valueOf(10));
        var electricityConsumption = new EnergyConsumption(EnergyType.ELECTRICITY, BigDecimal.valueOf(100));

        var invoicedAmount = new InvoiceAmountCalculator().calculate(customer, gasConsumption, electricityConsumption);

        Assertions.assertEquals(
                new Amount(gasConsumption.kwh()
                                         .multiply(GAS_PRICE_PER_KWH_FOR_INDIVIDUAL_CUSTOMER)
                                         .add(electricityConsumption.kwh()
                                                                    .multiply(ELECTRICITY_PRICE_PER_KWH_FOR_INDIVIDUAL_CUSTOMER))),
                invoicedAmount);

    }

}
