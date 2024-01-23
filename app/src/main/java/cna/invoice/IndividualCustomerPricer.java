package cna.invoice;

import cna.consumption.EnergyType;

import java.math.BigDecimal;

class IndividualCustomerPricer {

    private static final BigDecimal ELECTRICITY_PRICE_PER_KWH = BigDecimal.valueOf(0.133d);
    private static final BigDecimal GAS_PRICE_PER_KWH = BigDecimal.valueOf(0.108d);

    BigDecimal getPricePerKwh(EnergyType energyType) {
        if (energyType == EnergyType.ELECTRICITY) {
            return ELECTRICITY_PRICE_PER_KWH;
        }
        return GAS_PRICE_PER_KWH;
    }
}
