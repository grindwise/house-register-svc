/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.google.common.base.Preconditions;
import com.grindwise.addressauthenticator.AddressAuthenticationException;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.productionenvy.framework.AggregateRootObjectID;
import com.gws.productionenvy.framework.AggregateRootObjectIDInformation;
import com.gws.productionenvy.framework.AggregateRootObjectStateRepresentation;
import com.gws.productionenvy.framework.AggregateRootPersistenceException;
import com.gws.productionenvy.framework.ChildObjectStateRepresentation;
import com.gws.productionenvy.framework.ChildObjectStateRepresentationImpl;
import com.gws.productionenvy.framework.DomainEntryPointException;
import com.gws.productionenvy.framework.DomainInvocationOutcome;
import com.gws.productionenvy.framework.Format;
import com.gws.productionenvy.framework.ObjectStateRepresentation;
import com.gws.productionenvy.framework.OutputException;
import com.gws.productionenvy.framework.Persistence;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * House implementation of a Property.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */

@SuppressWarnings("checkstyle:ClassDataAbstractionCoupling")
final class House implements Property
{
    private static final transient Logger LOG = LoggerFactory.getLogger(House.class);
    
    private static final String INVALID_OBJECT_ID_INFORMATION = "aggregate root object ID cannot be null when provided";
    private static final String INVALID_HOUSE_INFORMATION = "the provided house information cannot be null.";
    private static final String INVALID_ADDRESS_AUTHENTICATOR = "the provided address authenticator cannot be null.";
    private static final String INVALID_PERSISTENCE = "the provided persistence cannot be null.";
    private static final String OBJECT_ID_TYPE = "String";
    private static final String OBJECT_ID_NAME = "objectID";

    private final AggregateRootObjectID objectID;
    private final Address address;

    private final transient Persistence persistence;

    /**
     * Constructor.
     * 
     * @param houseInformation structure of information used to instantiate the state of this.
     * @param addressAuthenticator authenticates the address.
     * @param persistence permanent storage for this.
     */
    protected House(final HouseInformation houseInformation,
                    final AddressAuthenticator addressAuthenticator,
                    final Persistence persistence)
    {
        super();
        
        LOG.trace("entry");
        
        this.validate(houseInformation, addressAuthenticator, persistence);

        this.objectID = new AggregateRootObjectID();
        this.address = new ResidentialAddress(houseInformation.addressInformation, addressAuthenticator);
        
        this.persistence = persistence;

        LOG.trace("exit");
    }

    /**
     * Constructor.
     * 
     * @param aggregateRootObjectIDInformation the unique identifier of this.
     * @param houseInformation structure of information used to instantiate the state of this.
     * @param addressAuthenticator authenticates the address.
     * @param persistence permanent storage for this.
     */
    protected House(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
                    final HouseInformation houseInformation,
                    final AddressAuthenticator addressAuthenticator,
                    final Persistence persistence)
    {
        super();
        
        LOG.trace("entry");
        
        this.validate(aggregateRootObjectIDInformation, houseInformation, addressAuthenticator, persistence);

        this.objectID = new AggregateRootObjectID(aggregateRootObjectIDInformation.aggregateRootObjectID);        
        this.address = new ResidentialAddress(houseInformation.addressInformation, addressAuthenticator);

        this.persistence = persistence;
        
        LOG.trace("exit");
    }
    
    /**
     * Register a house.  This is the entry point into the domain.
     * 
     * @throws DomainEntryPointException is thrown if the invocation encounters an unexpected error.
     */
    @Override
    public DomainInvocationOutcome register() throws DomainEntryPointException
    {
        LOG.trace("entry");
        
        final DomainInvocationOutcome domainInvocationOutcome;
        final DateTime dateTime = new DateTime();

        try
        {
            // make sure the property is real (authentic)
            if (this.address.isAuthentic())
            {
                LOG.debug("house address is valid");

                // write object state
                final ObjectStateRepresentation houseObjectState = this.objectState();

                this.persistence.store(houseObjectState, "register", dateTime, Boolean.FALSE);
                LOG.info("house state persisted");

                // removed temporarily
                // fire significate domain event
                //Format houseRegisteredEventFormat = new HouseRegisteredEventFormat();
                //final HouseRegisteredEvent houseRegisteredEvent =
                //    (HouseRegisteredEvent)houseObjectState.outputAs(houseRegisteredEventFormat);
                
                //houseRegisteredEvent.publish();
                
                domainInvocationOutcome =
                    new DomainInvocationOutcome(
                        DomainInvocationOutcome.InvocationOutcome.SUCCESS, "House was successfully registered.");
            }
            else
            {
                domainInvocationOutcome =
                    new DomainInvocationOutcome(
                        DomainInvocationOutcome.InvocationOutcome.FAILURE, "House address is invalid.");
            }
        }
        //OutputException  
        catch (final AddressAuthenticationException | AggregateRootPersistenceException exception)
        {
            throw new DomainEntryPointException(exception);
        }
        
        LOG.trace("exit");
        
        return domainInvocationOutcome;
    }

    @Override
    public DomainInvocationOutcome unregister() throws DomainEntryPointException
    {
        LOG.trace("entry");
        
        final DomainInvocationOutcome domainInvocationOutcome;
        final DateTime dateTime = new DateTime();

        try
        {
            // write state
            final ObjectStateRepresentation houseObjectState = this.objectState();
            
            this.persistence.store(houseObjectState, "unregister", dateTime, Boolean.TRUE);
            LOG.info("house state persisted");

            // fire significate domain event
            final Format houseUnregisteredEventFormat = new HouseUnregisteredEventFormat();
            final HouseUnregisteredEvent houseUnregisteredEvent =
                (HouseUnregisteredEvent) houseObjectState.outputAs(houseUnregisteredEventFormat);
            
            houseUnregisteredEvent.publish();

            domainInvocationOutcome =
                new DomainInvocationOutcome(
                    DomainInvocationOutcome.InvocationOutcome.SUCCESS, "House was successfully unregistered.");
        }
        catch (final AggregateRootPersistenceException | OutputException exception)
        {
            throw new DomainEntryPointException(exception);
        }
        
        LOG.trace("exit");

        return domainInvocationOutcome;
    }

    /**
     * Validate the constructor invariants.
     * 
     * @param houseInformation information structure used to establish the state of this.
     * @param addressAuthenticator address authenticator instance.
     * @param persistence permanent storage.
     */
    private void validate(final HouseInformation houseInformation,
                          final AddressAuthenticator addressAuthenticator,
                          final Persistence persistence)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(houseInformation, INVALID_HOUSE_INFORMATION);
        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATOR);
        Preconditions.checkNotNull(persistence, INVALID_PERSISTENCE);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }

    /**
     * Validate the constructor invariants.
     *
     * @param aggregateRootObjectIDInformation information structure used to establish object ID.
     * @param houseInformation information structure used to establish the state of this.
     * @param addressAuthenticator address authenticator instance.
     * @param persistence permanent storage.
     */
    private void validate(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
                          final HouseInformation houseInformation,
                          final AddressAuthenticator addressAuthenticator,
                          final Persistence persistence)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(aggregateRootObjectIDInformation, INVALID_OBJECT_ID_INFORMATION);
        this.validate(houseInformation, addressAuthenticator, persistence);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
    
    /**
     * Pull the state of this for persistence.
     * 
     * @return ObjectState state of the this.
     * 
     */
    private ObjectStateRepresentation objectState()
    {
        LOG.trace("entry");

        final ChildObjectStateRepresentation objectIDState = new ChildObjectStateRepresentationImpl(
            OBJECT_ID_NAME, this.objectID.toString(), OBJECT_ID_TYPE);

        final ChildObjectStateRepresentation addressObjectState = ((ResidentialAddress) this.address).objectState();

        final AggregateRootObjectStateRepresentation houseObjectState =
            new AggregateRootObjectStateRepresentation(
                this.getClass().getSimpleName(), objectIDState, addressObjectState);
       
        LOG.trace("exit");

        return houseObjectState;
    }
}