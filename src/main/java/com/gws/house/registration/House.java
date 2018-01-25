/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import com.gws.behavior.framework.AggregateRootObject;
import com.gws.behavior.framework.AggregateRootObjectID;
import com.gws.behavior.framework.DomainInvocationFactory;
import com.gws.behavior.framework.DomainInvocationOutcome;
import com.gws.behavior.framework.DomainInvocationOutcomeFactory;
import com.gws.behavior.framework.EndOfLifeIndicator;
import com.gws.behavior.framework.PersistenceRepository;
import com.gws.behavior.framework.RuntimeEnvironmentProperties;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.UUID;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * House implementation of a Property.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
final class House extends AggregateRootObject
{
    private static final Logger LOG = LoggerFactory.getLogger(House.class);
    
    private static final String INVALID_OBJECT_ID = "the aggregate root object ID cannot be null.";
    private static final String INVALID_ADDRESS = "the provided address cannot be null.";
    private static final String INVALID_RUNTIME_PROPERTIES = "the provided runtime properties cannot be null.";
    private static final String HOUSE_REGISTERED_SUCCESSFUL_MESSAGE = "house registration was successful.";
    private static final String HOUSE_REGISTERED_FAILURE_INVALID_ADDRESS_MESSAGE = "house address failed validation.";

    private final transient HouseRepository houseRepository;
    private final ResidentialAddress address;
    
    /**
     * Constructor.
     * 
     * @param aggregateRootObjectID unique object ID for this.
     * @param address address of property.
     * @param runtimeEnvironmentProperties runtime properties.
     */
    protected House(final AggregateRootObjectID aggregateRootObjectID,
                    final ResidentialAddress address,
                    final RuntimeEnvironmentProperties runtimeEnvironmentProperties)
    {
        super(aggregateRootObjectID, EndOfLifeIndicator.False);
        
        LOG.trace("entry");
        
        this.validate(aggregateRootObjectID, address, runtimeEnvironmentProperties);
        
        this.address = address;
        this.houseRepository = new HouseRepository(runtimeEnvironmentProperties);

        LOG.trace("exit");
    }

    /**
     * For jongo use only.
     */
//    @JsonCreator
//    private House()
//    {
//        super();
//        
//        LOG.trace("entry");
//
//        this.address = null;
//        this.houseRepository = null;
//
//        LOG.trace("exit");
//    }
    
    /**
     * Register a house.  This is the entry point into the domain.
     * 
     * @return invocation outcome.
     * 
     * @throws Exception is thrown if the invocation encounters an unexpected error.
     */
    public DomainInvocationOutcome register() throws Exception
    {
        LOG.trace("entry");
        
        this.domainInvocation(DomainInvocationFactory.create("register"));
        
        final DomainInvocationOutcome registerDomainInvocationOutcome;

        try
        {
            // make sure the property is real (authentic)
//            if (this.address.authentic())
            if (true)
            {
                LOG.debug("house address is valid");

                
                this.houseRepository.save(this);
                LOG.info("house saved");

                registerDomainInvocationOutcome = DomainInvocationOutcomeFactory.create(
                    DomainInvocationOutcome.InvocationOutcome.SUCCESS, HOUSE_REGISTERED_SUCCESSFUL_MESSAGE);

                // fire significate domain event: HouseRegistered
            }
            else
            {
                LOG.debug("house address is invalid");

                registerDomainInvocationOutcome = DomainInvocationOutcomeFactory.create(
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
     * @param aggregateRootObjectID unique identifier for aggregate root object.
     * @param address address of property.
     * @param runtimeEnvironmentProperties runtime properties.
     */
    private void validate(final AggregateRootObjectID aggregateRootObjectID,
                          final ResidentialAddress address,
                          final RuntimeEnvironmentProperties runtimeEnvironmentProperties)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(aggregateRootObjectID, INVALID_OBJECT_ID);
        Preconditions.checkNotNull(address, INVALID_ADDRESS);
        Preconditions.checkNotNull(runtimeEnvironmentProperties, INVALID_RUNTIME_PROPERTIES);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
    
    private JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation addressObjectStateRepresentation = this.address.establishState();
        final JsonObjectStateRepresentation houseObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);
        
        houseObjectStateRepresentation.addState(addressObjectStateRepresentation);
        
        return houseObjectStateRepresentation;
    }
        
    /**
     * Specialized repository to save house state.  This implementation
     * is part of the class to make sure invocations only happen via
     * domain invocations.
     */
    private final class HouseRepository extends PersistenceRepository<House>
    {
        private static final String COLLECTION_NAME = "houses";
        private static final String PERSISTENT_STORE_ID_ELEMENT = "_id";

        private MongoDatabase datastore;
        //private MongoCollection houses;
        
        /**
         * Constructor.
         * 
         * @param houseRepositoryStoreConfiguration persistent store properties.
         */
        private HouseRepository(final RuntimeEnvironmentProperties runtimeEnvironmentProperties)
        {
            super();
            
            LOG.trace("entry");
            
            this.establishDatabase(runtimeEnvironmentProperties);            

            LOG.trace("exit");
        }
        
        @Override
        protected void save(final House house) throws Exception
        {
            LOG.trace("entry");
            
            try
            {
                final JsonObjectStateRepresentation houseObjectStateRepresentation = house.establishState();
                final String houseObjectState = houseObjectStateRepresentation.format();
                final String someState = "{" + houseObjectState + "}";
                
                LOG.debug("houseObjectState: " + someState);

                final Document houseDocument = Document.parse(someState);
                LOG.debug("house document created...");
                final String persistentStoreID = UUID.randomUUID().toString();

                houseDocument.put(PERSISTENT_STORE_ID_ELEMENT, persistentStoreID);
                LOG.debug("id added");
                final MongoCollection<Document> container = this.datastore.getCollection(COLLECTION_NAME);
                LOG.debug("collection created");
                
                container.insertOne(houseDocument);
            }
            catch (final Exception exception)
            {
                exception.printStackTrace();
                LOG.error("exception while attempting save to persistent store", exception.getMessage());
                
                throw new Exception("exception while attempting save to persistent store", exception);                    
            }
//            LOG.info(houseObjectStateRepresentation.format());
//            //house.id();
//            
//            //this.houses.save(house);
//            LOG.info("house state persisted");
            
            LOG.trace("exit");
        }
        
        /**
         * Establish database.
         */
        private void establishDatabase(final RuntimeEnvironmentProperties runtimeEnvironmentProperties)
        {
            LOG.trace("entry");

            if (this.datastore == null)
            {
                final MongoClient mongoClient = new MongoClient(runtimeEnvironmentProperties.getPropertyValue(
                                        RegisterHouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR),
                                                                Integer.parseInt(runtimeEnvironmentProperties.getPropertyValue(
                                        RegisterHouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR)));
                
                this.datastore = mongoClient.getDatabase(runtimeEnvironmentProperties.getPropertyValue(
                       RegisterHouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR)); 

                LOG.debug("write only data store established");
            }
            
//            if (this.houses == null)
//            {
//                final MongoClient mongoClient =
//                    new MongoClient(runtimeEnvironmentProperties.getPropertyValue(
//                                        RegisterHouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR),
//                                    Integer.parseInt(runtimeEnvironmentProperties.getPropertyValue(
//                                        RegisterHouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR)));
//                
//                @SuppressWarnings({"unchecked", "deprecation"})
//                final DB db = mongoClient.getDB(
//                    runtimeEnvironmentProperties.getPropertyValue(
//                       RegisterHouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR));
//
//                final Jongo store = new Jongo(db);
//
//                this.houses = store.getCollection(COLLECTION_NAME);
//                LOG.debug("houses collection established");
//            }

            LOG.trace("exit");   
        }
    }
}