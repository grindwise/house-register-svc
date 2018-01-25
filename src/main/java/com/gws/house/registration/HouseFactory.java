/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.behavior.framework.AggregateRootObjectID;
import com.gws.behavior.framework.AggregateRootObjectIDFactory;
import com.gws.behavior.framework.RuntimeEnvironmentProperties;
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
    
    protected House create(final HouseInformation houseInformation)
    {
        LOG.trace("entry");
        
        final ResidentialAddressFactory residentialAddressFactory =
            new ResidentialAddressFactory(this.addressAuthenticator);
        
        final ResidentialAddress address = residentialAddressFactory.create(houseInformation.addressInformation);
        final AggregateRootObjectID aggregateRootObjectID = AggregateRootObjectIDFactory.create();
        
        final House house = new House(aggregateRootObjectID, address, this.runtimeProperties);
        
        LOG.trace("exit");

        return house;
    }

    private void validate(final AddressAuthenticator addressAuthenticator,
                          final RuntimeEnvironmentProperties runtimeProperties)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATION_SERVICE);
        Preconditions.checkNotNull(runtimeProperties, INVALID_RUNTIME_PROPERTIES);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
