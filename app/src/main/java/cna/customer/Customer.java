package cna.customer;

public sealed interface Customer permits IndividualCustomer, ProCustomer {
    String reference();
}
