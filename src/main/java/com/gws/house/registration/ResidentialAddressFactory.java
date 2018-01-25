/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client facing House Factory.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class ResidentialAddressFactory
{
    private static final Logger LOG = LoggerFactory.getLogger(ResidentialAddressFactory.class);

    private static final String INVALID_ADDRESS_AUTHENTICATION_SERVICE = "address authentication service cannot be null.";
    private static final String INVALID_RUNTIME_PROPERTIES = "runtime properties cannot be null";
            
    private final AddressAuthenticator addressAuthenticator;

    public ResidentialAddressFactory(final AddressAuthenticator addressAuthenticator)
    {
        LOG.trace("entry");
        
        this.validate(addressAuthenticator);
          
        this.addressAuthenticator = addressAuthenticator;

        LOG.trace("exit");
    }
    
    protected ResidentialAddress create(final AddressInformation addressInformation)
    {
        LOG.trace("entry");
        
        final StreetNumber streetNumber = new StreetNumber(addressInformation.streetNumber);
        final StreetName streetName = new StreetName(addressInformation.streetName);
        final Street street = new Street(streetNumber, streetName);
        final City city = new City(addressInformation.cityName);
        final StateAbbreviationCode stateAbbreviationCode =
            new StateAbbreviationCode(addressInformation.stateAbbreviationCode);
        final StateName stateName =
            new StateName(addressInformation.stateName);
        final State state = new State(stateAbbreviationCode, stateName);
        final DeliveryArea deliveryArea = new DeliveryArea(addressInformation.deliveryArea);
        final GeographicSegment geographicSegment = new GeographicSegment(addressInformation.geographicSegment);
        final ZipCode zipCode = new ZipCode(deliveryArea, geographicSegment);
        
        final ResidentialAddress address = new ResidentialAddress(street, city, state, zipCode, addressAuthenticator);
        
        LOG.trace("exit");

        return address;
    }

    private void validate(final AddressAuthenticator addressAuthenticator)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATION_SERVICE);

        LOG.trace("exit");
    }
}
