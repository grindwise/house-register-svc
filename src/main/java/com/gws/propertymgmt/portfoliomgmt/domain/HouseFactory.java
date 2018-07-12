/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.google.common.base.Preconditions;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.productionenvy.framework.AggregateRootObjectCreationException;
import com.gws.productionenvy.framework.AggregateRootObjectIDInformation;
import com.gws.productionenvy.framework.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Client facing House Factory.
 * 
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class HouseFactory
{
    private static final transient Logger LOG = LoggerFactory.getLogger(HouseFactory.class);

    private static final String INVALID_ADDRESS_AUTHENTICATION_SERVICE =
        "address authentication service cannot be null.";
    private static final String INVALID_PERSISTENCE = "persistence cannot be null";
    private static final String INVALID_HOUSE_INFORMATION = "house information cannot be null";
    private static final String INVALID_OBJECT_ID_INFORMATION = "aggregate root object ID cannot be null when provided";

    private final transient AddressAuthenticator addressAuthenticator;
    private final transient Persistence persistence;

    /**
     * Constructor.
     * 
     * @param addressAuthenticator authenticates addresses.
     * @param persistence permanent store for a house.
     */
    public HouseFactory(final AddressAuthenticator addressAuthenticator,
                        final Persistence persistence)
    {
        LOG.trace("entry");
        
        this.validate(addressAuthenticator, persistence);
          
        this.addressAuthenticator = addressAuthenticator;
        this.persistence = persistence;

        LOG.trace("exit");
    }
    
    /**
     * Create an instance of a property - a house.
     * 
     * @param houseInformation information needed to successfully construct a house.
     * 
     * @return Property - instances of a house.
     * 
     * @throws AggregateRootObjectCreationException if a house cannot be successfully created.
     */
    public Property create(final HouseInformation houseInformation)
        throws AggregateRootObjectCreationException
    {
        LOG.trace("entry");
        
        this.validate(houseInformation);
        
        final Property property;
        
        try
        {
            property = new House(houseInformation, this.addressAuthenticator, this.persistence);
        }
        catch (final RuntimeException exception)
        {
            LOG.warn(exception.getMessage());
            throw new AggregateRootObjectCreationException(exception);
        }
        catch (final Exception exception)
        {
            LOG.warn(exception.getMessage());
            throw new AggregateRootObjectCreationException(exception);
        }
                
        LOG.trace("exit");

        return property;
    }

    /**
     * Create an instance of a property - a house.
     * 
     * @param aggregateRootObjectIDInformation object ID of an existing house.
     * @param houseInformation information to instantiate the house.
     * 
     * @return Property house instance.
     * 
     * @throws AggregateRootObjectCreationException if house cannot be instantiated.
     */
    public Property create(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
                           final HouseInformation houseInformation)
        throws AggregateRootObjectCreationException
    {
        LOG.trace("entry");
        
        this.validate(aggregateRootObjectIDInformation, houseInformation);
        
        final Property property;
        
        try
        {
            property = new House(
                aggregateRootObjectIDInformation, houseInformation, this.addressAuthenticator, this.persistence);
        }
        catch (final RuntimeException exception)
        {
            LOG.warn(exception.getMessage());
            throw new AggregateRootObjectCreationException(exception);
        }
        catch (final Exception exception)
        {
            LOG.warn(exception.getMessage());
            throw new AggregateRootObjectCreationException(exception);
        }
                
        LOG.trace("exit");

        return property;
    }
    
    /**
     * Validate invariants.
     * 
     * @param addressAuthenticator authenticator of addresses.
     * @param persistence permanent storage for a house.
     */
    private void validate(final AddressAuthenticator addressAuthenticator,
                          final Persistence persistence)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATION_SERVICE);
        Preconditions.checkNotNull(persistence, INVALID_PERSISTENCE);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
    
    /**
     * Validate invariants.
     * 
     * @param houseInformation information structure needed to instantiate a house.
     */
    private void validate(final HouseInformation houseInformation)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(houseInformation, INVALID_HOUSE_INFORMATION);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
        
    }

    /**
     * Validate invariants.
     * 
     * @param aggregateRootObjectIDInformation information structure to establish object ID.
     * @param houseInformation information structure necessary to instantiate a house.
     */
    private void validate(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
                          final HouseInformation houseInformation)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(aggregateRootObjectIDInformation, INVALID_OBJECT_ID_INFORMATION);
        this.validate(houseInformation);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
        
    }
}
