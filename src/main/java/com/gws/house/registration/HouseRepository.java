/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
abstract class HouseRepository
{
    private static final Logger LOG = LoggerFactory.getLogger(HouseRepository.class);
    
//    private static final String UNIQUE_DOCUMENT_ID_NAME = "_id";
    private static final String INVALID_USER_NAME = "user name cannot be null.";
    private static final String INVALID_HOST_NAME = "store host cannot be null or empty.";
    private static final String INVALID_STORE_NAME = "store name cannot be null or empty.";
    private static final String INVALID_PORT =
        "store port cannot be null or empty and must be numeric.";
    private static final String INVALID_PROPERTY = "provided property cannot be null.";

    private static final String INVALID_DOMAIN_ENTRY_POINT = "provided domain entry point cannot be null.";
    private static final String INVALID_END_OF_LIFE_INDICATOR = "provided end of life indicator cannot be null.";

    private static final String COLLECTION_NAME = "houses";
    
    protected final String persistentStoreUserName;
    protected final String persistentStoreUserPassword;
    protected final String persistentStoreHost;
    protected final String persistentStorePort;
    protected final String persistentStoreName;
    //protected MongoDatabase datastore;

    /**
     * Constructor.
     * 
     * @param persistentStoreUsername house store user name.
     * @param persistentStoreUserPassword house store user password.
     * @param persistentStoreName house store name.
     * @param persistentStoreHost house store host.
     * @param persistentStorePort house store port.
     */
    protected HouseRepository(
        final String persistentStoreUsername,
        final String persistentStoreUserPassword,
        final String persistentStoreName,
        final String persistentStoreHost,
        final String persistentStorePort)
    {
        LOG.trace("entry");

        this.validate(persistentStoreUsername,
                      persistentStoreUserPassword,
                      persistentStoreName,
                      persistentStoreHost,
                      persistentStorePort);

        this.persistentStoreUserName = persistentStoreUsername;
        this.persistentStoreUserPassword = persistentStoreUserPassword;
        this.persistentStoreName = persistentStoreName;
        this.persistentStoreHost = persistentStoreHost;
        this.persistentStorePort = persistentStorePort;

        LOG.trace("exit");        
    }

    protected abstract void persist(final House house) throws Exception;
//    /**
//     * Persist the house.
//     * 
//     * @param property house to be persisted.
//     * @param domainEntryPoint invocation on the domain.
//     * @param endOfLife indicates if the house has reached its end of life (no longer valid).
//     * 
//     * @throws Exception if an unexpected error occurs. 
//     */
//    @Override
//    protected void persist(final Property property)
//            //,
//            //               final DomainEntryPoint domainEntryPoint,
//            //               final EndOfLifeIndicator endOfLife)
//        throws Exception
//    {
//        LOG.trace("entry");
//
//        this.validate(property); //, domainEntryPoint, endOfLife);
//
//        if (property instanceof House)
//        {
//            final House house = (House)property;
//        }
//        
//        this.establishDatabase();
//
//        try
//        {
//            //Gson gson = new Gson();
//            //String houseJson = gson.toJson(property);
//            //LOG.info("json created: " + houseJson);
//
////            final Document houseDocument = Document.parse(houseJson);
//                 
//            final StateRepresentation stateRepresentation = new JsonStateRepresentation();
//
//            house.establishState(stateRepresentation);
//            
//            //final Document houseDocument = formatter.format(houseState);
//            
//            final Document houseDocument = new Document();
//
//            LOG.info("houseDocument created");
//            
//            //final Document houseDocument = Document.parse("{\"name\":\"value\"}");
//
//            // we put our own unique identifier for the document instead of mongo
//            houseDocument.put(UNIQUE_DOCUMENT_ID_NAME, UUID.randomUUID().toString());
//
//            final MongoCollection<Document> container = this.datastore.getCollection(COLLECTION_NAME);
//
//            LOG.info("container created successfully");
//
//            container.insertOne(houseDocument);
//
//            LOG.info("new property written successfully");
//        }
//        catch (final Exception exception)
//        {
//            LOG.error("repository is unable to persist house", exception);
//            throw new Exception("repository is unable to persist house", exception);
//        }
//
//        LOG.trace("exit");
//    }
//
//    /**
//     * Find house by it's aggregate root identifier.
//     * 
//     * @param objectID identifier that uniquely identified a specific house.
//     * 
//     * @return Property instance.
//     * 
//     * @throws Exception if an unexpected exception occurs.
//     */
//    @Override
//    protected Map<String,Object> findByAggregateRootID(final AggregateRootObjectID objectID) throws Exception
//    {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//    
//    /**
//     * Establish database.
//     */
//    private void establishDatabase()
//    {
//        LOG.trace("entry");
//
//        if (this.datastore == null)
//        {
//            final MongoClient mongoClient = new MongoClient(
//                this.persistentStoreHost, Integer.parseInt(this.persistentStorePort));
//            
//            this.datastore = mongoClient.getDatabase(this.persistentStoreName);
//            LOG.debug("write only data store established");
//        }
//
//        LOG.trace("exit");   
//    }
//
    /**
     * Validate constructor invariants.
     * 
     * @param persistentStoreUsername house store user name.
     * @param persistentStoreUserPassword house store user password.
     * @param persistentStoreName house store name.
     * @param persistentStoreHost house store host.
     * @param persistentStorePort house store port.
     */
    private void validate(final String persistentStoreUsername,
                          final String persistentStoreUserPassword,
                          final String persistentStoreName,
                          final String persistentStoreHost,
                          final String persistentStorePort)
    {
        LOG.trace("entry");

        // user password is ignored for now since it can be empty.
        Preconditions.checkNotNull(persistentStoreUsername, INVALID_USER_NAME);
        Preconditions.checkNotNull(persistentStoreHost, INVALID_HOST_NAME);
        Preconditions.checkNotNull(persistentStoreName, INVALID_STORE_NAME);
        Preconditions.checkArgument(!"".equals(persistentStoreName), INVALID_STORE_NAME);
        Preconditions.checkArgument(!"".equals(persistentStoreHost), INVALID_HOST_NAME);
        Preconditions.checkNotNull(persistentStorePort, INVALID_PORT);
        Preconditions.checkArgument(!"".equals(persistentStorePort), INVALID_PORT);
        Preconditions.checkArgument(StringUtils.isNumeric(persistentStorePort), INVALID_PORT);
        LOG.debug("passed validation");

        LOG.trace("exit");
    }
//
//    /**
//     * Validate invariants of constructor.
//     * 
//     * @param property property to persist.
//     * @param domainEntryPoint invocation on domain.
//     * @param endOfLife indicates if house has reached end of life.
//     */
//    private void validate(final Property property)
//            //,
//            //              final DomainEntryPoint domainEntryPoint,
//            //              final EndOfLifeIndicator endOfLife)
//    {
//        LOG.trace("entry");
//
//        Preconditions.checkNotNull(property, INVALID_PROPERTY);
//        //Preconditions.checkNotNull(domainEntryPoint, INVALID_DOMAIN_ENTRY_POINT);
//        //Preconditions.checkNotNull(endOfLife, INVALID_END_OF_LIFE_INDICATOR);
//
//        LOG.debug("passed validation");
//
//        LOG.trace("exit");
//    }  
}
