/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.gws.productionenvy.framework.RuntimeEnvironmentProperties;
import java.util.HashMap;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Establishes the process properties for the purchase House service.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class HouseRuntimeProperties implements RuntimeEnvironmentProperties
{
    public static final String PERSISTENT_STORE_NAME_ENV_VAR = "persistent_store_name";
    public static final String PERSISTENT_STORE_USERNAME_ENV_VAR = "persistent_store_username";
    public static final String PERSISTENT_STORE_PASSWORD_ENV_VAR = "persistent_store_password";
    public static final String PERSISTENT_STORE_HOST_ENV_VAR = "persistent_store_host";
    public static final String PERSISTENT_STORE_PORT_ENV_VAR = "persistent_store_port";    
    public static final String PERSISTENT_COLLECTION_NAME_ENV_VAR = "persistent_collection_name";
    public static final String ADDRESS_AUTHENTICATOR_AUTH_ID_ENV_VAR = "address_authenticator_auth_id";
    public static final String ADDRESS_AUTHENTICATOR_AUTH_TOKEN_ENV_VAR = "address_authenticator_auth_token";
    
    private static final transient Logger LOG = LoggerFactory.getLogger(HouseRuntimeProperties.class);    
    
    // messages
    private static final String INVALID_NAME = "the process property name cannot be null or empty.";
    private static final String INVALID_PERSISTENT_STORE_NAME =
        "the provided persistent store name cannot be null or empty.";
    private static final String INVALID_PERSISTENT_STORE_USERNAME =
        "the provided persistent store username cannot be null or empty.";
    private static final String INVALID_PERSISTENT_STORE_HOST =
        "the provided persistent store host cannot be null or empty.";
    private static final String INVALID_PERSISTENT_STORE_PORT =
        "the provided persistent store port cannot be null or empty and must be numeric,";
    private static final String INVALID_PERSISTENT_COLLECTION_NAME =
        "the provided persistent collection name cannot be null or empty.";
    
    // collection of properties
    private final Map<String, String> properties;
    
    /**
     * Constructor.
     */
    public HouseRuntimeProperties()
    {
        LOG.trace("entry");
        
        this.properties = new HashMap<>();
        
        final String persistentStoreName = System.getenv(PERSISTENT_STORE_NAME_ENV_VAR);
        final String persistentStoreUserName = System.getenv(PERSISTENT_STORE_USERNAME_ENV_VAR);
        final String persistentStorePassword = System.getenv(PERSISTENT_STORE_PASSWORD_ENV_VAR);
        final String persistentStoreHost = System.getenv(PERSISTENT_STORE_HOST_ENV_VAR);
        final String persistentStorePort = System.getenv(PERSISTENT_STORE_PORT_ENV_VAR);
        final String persistentCollectionName = System.getenv(PERSISTENT_COLLECTION_NAME_ENV_VAR);

        this.validate(persistentStoreName, persistentStoreUserName, persistentStoreHost, persistentStorePort,
                      persistentCollectionName);
        
        this.addPropertiesToCollection(persistentStoreName, persistentStoreUserName, persistentStorePassword,
                                       persistentStoreHost, persistentStorePort, persistentCollectionName);
        
        LOG.trace("exit");
    }

    /**
     * Adds each property to the collection of properties.
     * 
     * @param persistentStoreName name of persistent store.
     * @param persistentStoreUserName username for store access.
     * @param persistentStorePassword password for store access.
     * @param persistentStoreHost host of persistent store. 
     * @param persistentStorePort port of persistent store.
     * @param persistentCollectionName name of collection to store properties in.
     */
    private void addPropertiesToCollection(final String persistentStoreName,
                                           final String persistentStoreUserName,
                                           final String persistentStorePassword,
                                           final String persistentStoreHost,
                                           final String persistentStorePort,
                                           final String persistentCollectionName)
    {
        LOG.trace("entry");
        
        this.properties.put(PERSISTENT_STORE_NAME_ENV_VAR, persistentStoreName);
        this.properties.put(PERSISTENT_STORE_USERNAME_ENV_VAR, persistentStoreUserName);
        this.properties.put(PERSISTENT_STORE_PASSWORD_ENV_VAR, persistentStorePassword);
        this.properties.put(PERSISTENT_STORE_HOST_ENV_VAR, persistentStoreHost);
        this.properties.put(PERSISTENT_STORE_PORT_ENV_VAR, persistentStorePort);
        this.properties.put(PERSISTENT_COLLECTION_NAME_ENV_VAR, persistentCollectionName);

        LOG.trace("exit");
    }
    
    @Override
    public String getPropertyValue(final String name)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(name, INVALID_NAME);
        Preconditions.checkArgument(!"".equals(name), INVALID_NAME);
        
        LOG.debug("passed validation");
                
        LOG.trace("exit");
        
        return this.properties.get(name);
    }
    
    /**
     * Validate constructor invariants.
     * 
     * @param persistentStoreName name of persistent store.
     * @param persistentStoreUserName username for store access.
     * @param persistentStoreHost host of persistent store. 
     * @param persistentStorePort port of persistent store.
     * @param persistentCollectionName collection name to store properties in.
     */
    private void validate(final String persistentStoreName,
                          final String persistentStoreUserName,
                          final String persistentStoreHost,
                          final String persistentStorePort,
                          final String persistentCollectionName)
    {
        LOG.trace("entry");
        
        // ** user password is intentionally not validated since it can be empty **
        
        Preconditions.checkNotNull(persistentStoreName, INVALID_PERSISTENT_STORE_NAME);
        Preconditions.checkArgument(!"".equals(persistentStoreName), INVALID_PERSISTENT_STORE_NAME);
        Preconditions.checkNotNull(persistentStoreUserName, INVALID_PERSISTENT_STORE_USERNAME);
        Preconditions.checkNotNull(persistentStoreHost, INVALID_PERSISTENT_STORE_HOST);
        Preconditions.checkArgument(!"".equals(persistentStoreHost), INVALID_PERSISTENT_STORE_HOST);
        Preconditions.checkNotNull(persistentStorePort, INVALID_PERSISTENT_STORE_PORT);
        Preconditions.checkArgument(!"".equals(persistentStorePort), INVALID_PERSISTENT_STORE_PORT);
        Preconditions.checkNotNull(persistentCollectionName, INVALID_PERSISTENT_COLLECTION_NAME);
        Preconditions.checkArgument(!"".equals(persistentCollectionName), INVALID_PERSISTENT_COLLECTION_NAME);
        LOG.debug("passed validation (validate)");
        
        LOG.trace("exit");
    }
}
