/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.base.Preconditions;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import java.util.UUID;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * House implementation of a Property.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
final class House
{
    private static final Logger LOG = LoggerFactory.getLogger(House.class);
    
    private static final String INVALID_ADDRESS = "the provided address cannot be null.";
    private static final String INVALID_RUNTIME_PROPERTIES = "the provided runtime properties cannot be null.";
    private static final String HOUSE_REGISTERED_SUCCESSFUL_MESSAGE = "house registration was successful.";
    private static final String HOUSE_REGISTERED_FAILURE_INVALID_ADDRESS_MESSAGE = "house address failed validation.";
    
    private final transient HouseRepository houseRepository;
    private final transient RuntimeEnvironmentProperties runtimeEnvironmentProperties;
    private final AggregateRootObjectID objectID;
    private final ResidentialAddress address;
    private String _id;
    
    /**
     * Constructor.
     * 
     * @param address address of property.
     * @param runtimeEnvironmentProperties runtime properties.
     */
    protected House(final ResidentialAddress address,
                    final RuntimeEnvironmentProperties runtimeEnvironmentProperties)
    {
        super();
        
        LOG.trace("entry");
        
        this.validate(address, runtimeEnvironmentProperties);
        
        this.objectID = new AggregateRootObjectID();
        this.address = address;
        this.runtimeEnvironmentProperties = runtimeEnvironmentProperties;
        this.houseRepository = new Repository();
        
        LOG.trace("exit");
    }

    @JsonCreator
    /**
     * For jongo use only.
     */
    private House()
    {
        LOG.trace("entry");

        this.address = null;
        this.houseRepository = null;
        this.objectID = null;
        this.runtimeEnvironmentProperties = null;

        LOG.trace("exit");
    }
    
    /**
     * Register a house.  This is the entry point into the domain.
     * 
     * @throws Exception is thrown if the invocation encounters an unexpected error.
     */
    public DomainInvocationOutcome register() throws Exception
    {
        LOG.trace("entry");
        
        final DomainInvocationOutcome registerDomainInvocationOutcome;
  
        try
        {
            // make sure the property is real (authentic)
//            if (this.address.authentic())
            if (true)
            {
                LOG.debug("house address is valid");

                this.houseRepository.persist(this);
                LOG.info("house registered");

                registerDomainInvocationOutcome = new DomainInvocationOutcomeImpl(
                    DomainInvocationOutcome.InvocationOutcome.SUCCESS, HOUSE_REGISTERED_SUCCESSFUL_MESSAGE);

                // fire significate domain event: HouseRegistered
            }
            else
            {
                LOG.debug("house address is invalid");

                registerDomainInvocationOutcome = new DomainInvocationOutcomeImpl(
                    DomainInvocationOutcome.InvocationOutcome.FAILURE,
                    HOUSE_REGISTERED_FAILURE_INVALID_ADDRESS_MESSAGE);
            }
        }
        catch (final Exception exception)
        {
            throw new Exception("Unable to register house", exception);
        }
        
        LOG.trace("exit");
        
        return registerDomainInvocationOutcome;
    }

    /**
     * Validate constructor invariants.
     * 
     * @param address address of property.
     * @param runtimeEnvironmentProperties runtime properties.
     */
    private void validate(final ResidentialAddress address,
                          final RuntimeEnvironmentProperties runtimeEnvironmentProperties)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(address, INVALID_ADDRESS);
        Preconditions.checkNotNull(runtimeEnvironmentProperties, INVALID_RUNTIME_PROPERTIES);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
    
    /**
     * Create the domain entry point.
     * 
     * @param domainEntryPointMethod method invoked on the domain.
     * 
     * @return instance of a DomainEntryPoint. 
     */
    private DomainEntryPoint createDomainEntryPoint(final String domainEntryPointMethod)
    {
        LOG.trace("entry");
        
        final DomainEntryPoint domainEntryPoint = new DomainEntryPointNoArguments(domainEntryPointMethod);
        
        LOG.trace("exit");
        
        return domainEntryPoint;
    }
    
    private final class Repository extends HouseRepository
    {
        private static final String COLLECTION_NAME = "houses";
        
        private MongoCollection houses;
        
        private Repository()
        {
            super(runtimeEnvironmentProperties.getPropertyValue(
                  RegisterHouseServiceRuntimeEnvironmentProperties.PERSISTENT_STORE_USERNAME_ENV_VAR),
                  runtimeEnvironmentProperties.getPropertyValue(
                  RegisterHouseServiceRuntimeEnvironmentProperties.PERSISTENT_STORE_PASSWORD_ENV_VAR),
                  runtimeEnvironmentProperties.getPropertyValue(
                  RegisterHouseServiceRuntimeEnvironmentProperties.PERSISTENT_STORE_NAME_ENV_VAR),
                  runtimeEnvironmentProperties.getPropertyValue(
                  RegisterHouseServiceRuntimeEnvironmentProperties.PERSISTENT_STORE_HOST_ENV_VAR),
                  runtimeEnvironmentProperties.getPropertyValue(
                  RegisterHouseServiceRuntimeEnvironmentProperties.PERSISTENT_STORE_PORT_ENV_VAR));
            
            LOG.trace("entry");
            
            this.establishDatabase();            

            LOG.trace("exit");
        }
        
        @Override
        protected void persist(final House house) throws Exception
        {
            
            LOG.trace("entry");
            
            house._id = UUID.randomUUID().toString();
            
            houses.save(house);
            LOG.info("house state persisted");
            
            LOG.trace("exit");
        }
        
        /**
         * Establish database.
         */
        private void establishDatabase()
        {
            LOG.trace("entry");

            if (this.houses == null)
            {
                final MongoClient mongoClient =
                    new MongoClient(this.persistentStoreHost, Integer.valueOf(this.persistentStorePort));
                
                @SuppressWarnings({"unchecked", "deprecation"})
                final DB db = mongoClient.getDB(this.persistentStoreName);

                final Jongo store = new Jongo(db);

                this.houses = store.getCollection(COLLECTION_NAME);
                LOG.debug("houses collection established");
            }

            LOG.trace("exit");   
        }
    }
}