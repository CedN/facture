package cna.invoice;

import cna.consumption.EnergyConsumption;
import cna.customer.Customer;
import cna.customer.IndividualCustomer;
import cna.customer.ProCustomer;

import java.math.BigDecimal;
import java.util.stream.Stream;

public class InvoiceAmountCalculator {

    private ElectricityPricer electricityPricer = new ElectricityPricer();
    private GasPricer gasPricer = new GasPricer();

    public Amount calculate(Customer customer, EnergyConsumption... energyConsumptions) {
        return new Amount(Stream.of(energyConsumptions)
                                .map(energyConsumption -> calculateOneEnergyConsumption(customer, energyConsumption))
                                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

    private BigDecimal calculateOneEnergyConsumption(Customer customer, EnergyConsumption energyConsumption) {
        var pricePerKwh = getPricePerKwh(customer, energyConsumption);
        return energyConsumption.kwh().multiply(pricePerKwh);
    }

    private BigDecimal getPricePerKwh(Customer customer, EnergyConsumption energyConsumption) {
        return switch (energyConsumption.energyType()) {
            case ELECTRICITY -> electricityPricer.getPricePerKwh(customer);
            case GAS -> gasPricer.getPricePerKwh(customer);
        };
    }
}
