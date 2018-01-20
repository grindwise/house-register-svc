/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Purchase house service configuration.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class RegisterHouseServiceConfiguration extends Configuration
{
    private static final Logger LOG = LoggerFactory.getLogger(RegisterHouseServiceConfiguration.class);
    
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
    
    @JsonProperty
    public String getAddressAuthenticatorID()
    {
        return this.addressAuthenticatorID;
    }

    @JsonProperty
    public void setAddressAuthenticatorID(final String addressAuthenticatorID)    
    {
        this.addressAuthenticatorID = addressAuthenticatorID;
    }

    @JsonProperty
    public String getAddressAuthenticatorToken() {
        return this.addressAuthenticatorToken;
    }

    @JsonProperty
    public void setAddressAuthenticatorToken(final String addressAuthenticatorToken)
    {
        this.addressAuthenticatorToken = addressAuthenticatorToken;
    }
}
