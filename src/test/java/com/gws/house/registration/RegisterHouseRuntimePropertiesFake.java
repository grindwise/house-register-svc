/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import java.util.HashMap;
import java.util.Map;
import jersey.repackaged.com.google.common.base.Preconditions;

/**
 * Establishes the process properties for the register House service.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class RegisterHouseRuntimePropertiesFake implements RuntimeEnvironmentProperties
{
    public static final String PERSISTENT_STORE_NAME_ENV_VAR = "persistent_store_name";
    public static final String PERSISTENT_STORE_USERNAME_ENV_VAR = "persistent_store_username";
    public static final String PERSISTENT_STORE_PASSWORD_ENV_VAR = "persistent_store_password";
    public static final String PERSISTENT_STORE_HOST_ENV_VAR = "persistent_store_host";
    public static final String PERSISTENT_STORE_PORT_ENV_VAR = "persistent_store_port";    
    public static final String PERSISTENT_COLLECTION_NAME_ENV_VAR = "persistent_collection_name";

    private static final String PERSISTENT_STORE_NAME_VALUE = "house_properties";
    private static final String PERSISTENT_STORE_USERNAME_VALUE = "";
    private static final String PERSISTENT_STORE_PASSWORD_VALUE = "";
    private static final String PERSISTENT_STORE_HOST_VALUE = "localhost";
    private static final String PERSISTENT_STORE_PORT_VALUE = "27017";    
    private static final String PERSISTENT_COLLECTION_NAME_VALUE = "houses";
    
    private static final String INVALID_NAME = "name provided cannot be null or empty.";
    
    // collection of properties
    private final Map<String, String> properties;
    
    /**
     * Constructor.
     */
    public RegisterHouseRuntimePropertiesFake()
    {
        this.properties = new HashMap<>();
        
        this.addPropertiesToCollection(PERSISTENT_STORE_NAME_VALUE,
            PERSISTENT_STORE_USERNAME_VALUE, PERSISTENT_STORE_PASSWORD_VALUE, PERSISTENT_STORE_HOST_VALUE,
            PERSISTENT_STORE_PORT_VALUE, PERSISTENT_COLLECTION_NAME_VALUE);
    }

    /**
     * Adds each property to the collection of properties.
     * 
     * @param persistentStoreName name of persistent store.
     * @param persistentStoreUserName username for store access.
     * @param persistentStorePassword password for store access.
     * @param persistentStoreHost host of persistent store. 
     * @param persistentStorePort port of persistent store.
     */
    private void addPropertiesToCollection(final String persistentStoreName,
                                           final String persistentStoreUserName,
                                           final String persistentStorePassword,
                                           final String persistentStoreHost,
                                           final String persistentStorePort,
                                           final String persistentCollectionName)
    {
        this.properties.put(PERSISTENT_STORE_NAME_ENV_VAR, persistentStoreName);
        this.properties.put(PERSISTENT_STORE_USERNAME_ENV_VAR, persistentStoreUserName);
        this.properties.put(PERSISTENT_STORE_PASSWORD_ENV_VAR, persistentStorePassword);
        this.properties.put(PERSISTENT_STORE_HOST_ENV_VAR, persistentStoreHost);
        this.properties.put(PERSISTENT_STORE_PORT_ENV_VAR, persistentStorePort);
        this.properties.put(PERSISTENT_COLLECTION_NAME_ENV_VAR, persistentCollectionName);
    }
    
    @Override
    public String getPropertyValue(final String name)
    {
        Preconditions.checkNotNull(name, INVALID_NAME);
        Preconditions.checkArgument(!"".equals(name), INVALID_NAME);
        
        return this.properties.get(name);
    }
}
