package cna.invoice;

import cna.consumption.EnergyConsumption;
import cna.consumption.EnergyType;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;

import java.math.BigDecimal;

public class InvoiceAmountCalculator {

    public Amount calculate(IndividualCustomer individualCustomer, EnergyConsumption energyConsumption) {
        var pricePerKwh = new IndividualCustomerPricer().getPricePerKwh(energyConsumption.energyType());
        return new Amount(energyConsumption.kwh().multiply(pricePerKwh));
    }

    public Amount calculate(ProCustomer proCustomer, EnergyConsumption energyConsumption) {
        var pricePerKwh = new ProCustomerPricer().getPricePerKwh(energyConsumption.energyType(), proCustomer.sales());
        return new Amount(energyConsumption.kwh().multiply(pricePerKwh));
    }
}
