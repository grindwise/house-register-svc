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
public final class HouseFactory
{
    private static final Logger LOG = LoggerFactory.getLogger(HouseFactory.class);

    private static final String INVALID_ADDRESS_AUTHENTICATION_SERVICE = "address authentication service cannot be null.";
    private static final String INVALID_RUNTIME_PROPERTIES = "runtime properties cannot be null";
            
    private final AddressAuthenticator addressAuthenticator;
    private final RuntimeEnvironmentProperties runtimeProperties;

    public HouseFactory(final AddressAuthenticator addressAuthenticator,
                           final RuntimeEnvironmentProperties runtimeProperties)
    {
        LOG.trace("entry");
        
        this.validate(addressAuthenticator, runtimeProperties);
          
        this.addressAuthenticator = addressAuthenticator;
        this.runtimeProperties = runtimeProperties;

        LOG.trace("exit");
    }
    
    public House create(final HouseInformation houseInformation)
    {
        LOG.trace("entry");
        
        final ResidentialAddress address =
            ResidentialAddress.create(houseInformation.addressInformation, addressAuthenticator);
        final House house = new House(address, this.runtimeProperties);
        
        LOG.trace("exit");

        return house;
    }

//    public Property create(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
//                           final HouseInformation houseInformation)
//    {
//        LOG.trace("entry");
//
//        final ResidentialAddress address =
//            ResidentialAddress.create(houseInformation.addressInformation, addressAuthenticator);
//        final AggregateRootObjectID houseObjectID =
//            new AggregateRootObjectID(aggregateRootObjectIDInformation.aggregateRootObjectID);
//        final Property property = new House(houseObjectID, address, this.runtimeProperties);
//        
//        LOG.trace("exit");
//
//        return property;
//    }

    private void validate(final AddressAuthenticator addressAuthenticator,
                          final RuntimeEnvironmentProperties runtimeProperties)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATION_SERVICE);
        Preconditions.checkNotNull(runtimeProperties, INVALID_RUNTIME_PROPERTIES);

        LOG.trace("exit");
    }
}
