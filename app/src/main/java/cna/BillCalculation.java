package cna;

import cna.consumption.EnergyConsumption;
import cna.consumption.EnergyType;
import cna.customer.ProCustomer;
import cna.invoice.InvoiceAmountCalculator;

import java.math.BigDecimal;

public class BillCalculation {

    public static void main(String[] args) {
        var proCustomer = new ProCustomer("EKW123456789", "12345678901234", "My Company", 640_000);
        var electricityConsumption = new EnergyConsumption(EnergyType.ELECTRICITY, BigDecimal.valueOf(100));
        var amount = new InvoiceAmountCalculator().calculate(proCustomer, electricityConsumption);
        System.out.println(String.format("The %s company's %s bill amounts to %s€", proCustomer.corporateName(), electricityConsumption.energyType(), amount.value()));
    }
}
