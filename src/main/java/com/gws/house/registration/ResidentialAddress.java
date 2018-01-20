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
 * Residential address.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
class ResidentialAddress
{
    private static final Logger LOG = LoggerFactory.getLogger(ResidentialAddress.class);
    
    private static final String INVALID_STREET = "Street cannot be null.";
    private static final String INVALID_CITY = "City cannot be null.";
    private static final String INVALID_STATE = "State cannot be null.";
    private static final String INVALID_ZIP_CODE = "Zip code cannot be null.";
    private static final String INVALID_ADDRESS_AUTHENTICATOR = "The address authenticator cannot be null.";
    
    private final Street street;
    private final City city;
    private final State state;
    private final ZipCode zipCode;
    private final transient AddressAuthenticator addressAuthenticator;
    
    protected static ResidentialAddress create(final AddressInformation addressInformation,
                                               final AddressAuthenticator addressAuthenticator)
    {
        final StreetNumber streetNumber = StreetNumber.create(addressInformation.streetNumber);
        final StreetName streetName = StreetName.create(addressInformation.streetName);
        final Street street = Street.create(streetNumber, streetName);
        final City city = City.create(addressInformation.cityName);
        final StateAbbreviationCode stateAbbreviationCode =
            StateAbbreviationCode.create(addressInformation.stateAbbreviationCode);
        final StateName stateName =
            StateName.create(addressInformation.stateName);
        final State state = State.create(stateAbbreviationCode, stateName);
        final DeliveryArea deliveryArea = DeliveryArea.create(addressInformation.deliveryArea);
        final GeographicSegment geographicSegment = GeographicSegment.create(addressInformation.geographicSegment);
        final ZipCode zipCode = ZipCode.create(deliveryArea, geographicSegment);
        
        return new ResidentialAddress(street, city, state, zipCode, addressAuthenticator);
    }
    
    /**
     * Constructor.
     * 
     * @param street street of address.
     * @param city city of address.
     * @param state state of address.
     * @param zipCode zip code of address.
     * @param addressAuthenticator authenticate this address is real.
     */
    protected ResidentialAddress(final Street street,
                                 final City city,
                                 final State state,
                                 final ZipCode zipCode,
                                 final AddressAuthenticator addressAuthenticator)
    {
        super();
        
        LOG.trace("entry");
        
        this.validate(street, city, state, zipCode, addressAuthenticator);
        
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.addressAuthenticator = addressAuthenticator;
        
        LOG.trace("exit");
    }
    
    protected Boolean authentic() throws Exception
    {
        LOG.trace("entry");

        final String streetOfAddress = this.street.provideAddressValue();
        final String cityOfAddress = this.city.provideAddressValue();
        final String stateCodeOfAddress = this.state.provideAddressValue();
        final String zipCodeOfAddress = this.zipCode.provideAddressValue();
        
        LOG.trace("exit");

        return this.addressAuthenticator.authentic(
            streetOfAddress, cityOfAddress, stateCodeOfAddress, zipCodeOfAddress);        
    }

    /**
     * Validate provided invariants.
     * 
     * @param street of address.
     * @param city of address.
     * @param state of address.
     * @param zipCode of address.
     * @param addressAuthenticator authenticate this address is real.
     */
    private void validate(final Street street,
                          final City city,
                          final State state,
                          final ZipCode zipCode,
                          final AddressAuthenticator addressAuthenticator)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(street, INVALID_STREET);
        Preconditions.checkNotNull(city, INVALID_CITY);
        Preconditions.checkNotNull(state, INVALID_STATE);
        Preconditions.checkNotNull(zipCode, INVALID_ZIP_CODE);
        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATOR);
        
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
