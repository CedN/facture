package cna.invoice;

import cna.consumption.EnergyConsumption;
import cna.customer.Customer;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;

import java.math.BigDecimal;

public class InvoiceAmountCalculator {

    private IndividualCustomerPricer individualCustomerPricer = new IndividualCustomerPricer();
    private ProCustomerPricer proCustomerPricer = new ProCustomerPricer();

    public Amount calculate(Customer customer, EnergyConsumption energyConsumption) {
        var pricePerKwh = getPricePerKwh(customer, energyConsumption);
        return new Amount(energyConsumption.kwh().multiply(pricePerKwh));
    }

    private BigDecimal getPricePerKwh(Customer customer, EnergyConsumption energyConsumption) {
        return switch (customer) {
            case IndividualCustomer c -> individualCustomerPricer.getPricePerKwh(energyConsumption.energyType());
            case ProCustomer c -> proCustomerPricer.getPricePerKwh(energyConsumption.energyType(), c.sales());
        };
    }
}
