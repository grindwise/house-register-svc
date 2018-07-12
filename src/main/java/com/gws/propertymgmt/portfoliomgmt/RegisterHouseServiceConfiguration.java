/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register house service configuration.  Unfortunately the framework
 * requires getters and setters.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class RegisterHouseServiceConfiguration extends Configuration
{
    private static final transient Logger LOG = LoggerFactory.getLogger(RegisterHouseServiceConfiguration.class);
    
    @NotEmpty
    private String addressAuthenticatorID;
    @NotEmpty
    private String addressAuthenticatorToken;

    /**
     * Constructor.
     */
    RegisterHouseServiceConfiguration()
    {
        LOG.trace("entry");
        LOG.trace("exit");
    }
    
    /**
     * Provide address authentication id.
     * 
     * @return authentication ID.
     */
    @JsonProperty
    public String getAddressAuthenticatorID()
    {
        return this.addressAuthenticatorID;
    }

    /**
     * Establish the address authentication ID.
     * 
     * @param addressAuthenticatorID address authentication ID.
     */
    @JsonProperty
    public void setAddressAuthenticatorID(final String addressAuthenticatorID)    
    {
        this.addressAuthenticatorID = addressAuthenticatorID;
    }

    /**
     * Provide the address authentication token.
     * 
     * @return address authentication token.
     */
    @JsonProperty
    public String getAddressAuthenticatorToken()
    {
        return this.addressAuthenticatorToken;
    }

    /**
     * Establish the address authentication token.
     * 
     * @param addressAuthenticatorToken address authentication token.
     */
    @JsonProperty
    public void setAddressAuthenticatorToken(final String addressAuthenticatorToken)
    {
        this.addressAuthenticatorToken = addressAuthenticatorToken;
    }
}
