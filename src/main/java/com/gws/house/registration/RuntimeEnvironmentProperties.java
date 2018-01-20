/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

/**
 * Represents a set of environment properties needed by the process at runtime.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public interface RuntimeEnvironmentProperties
{
    String RUNTIME_ENVIRONMENT_PROPERTIES_ARG = "runtimeEnvironmentProperties";
    
    /**
     * Get an environment variable from the environment.
     * 
     * @param propertyName name of property to get value for.
     * 
     * @return String value of the property, null can be returned. 
     */
    String getPropertyValue(final String propertyName);
}
