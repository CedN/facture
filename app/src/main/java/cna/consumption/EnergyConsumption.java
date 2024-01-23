package cna.consumption;

import java.math.BigDecimal;

public record EnergyConsumption(EnergyType energyType, BigDecimal kwh) {
}
