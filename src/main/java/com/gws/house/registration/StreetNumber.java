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
 * Street Number implementation.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
class StreetNumber
{
    private static final Logger LOG = LoggerFactory.getLogger(StreetNumber.class);
    
    private static final String INVALID_STREET_NUMBER =
        "Street number cannot be null, empty, and must be numeric.";
    
    private final String streetNumber;
    
    protected static StreetNumber create(final String streetNumber)
    {
        return new StreetNumber(streetNumber);
    }
    
    /**
     * Constructor.
     * 
     * @param streetNumber street number.
     */
    protected StreetNumber(final String streetNumber)
    {
        LOG.trace("entry");
        
        this.validate(streetNumber);
        
        this.streetNumber = streetNumber;
        
        LOG.trace("exit");
    }

    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.streetNumber;
    }

    /**
     * Validate constructor invariants.
     * 
     * @param streetNumber street number of address.
     */
    private void validate(final String streetNumber)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(streetNumber, INVALID_STREET_NUMBER);
        Preconditions.checkArgument(!"".equals(streetNumber), INVALID_STREET_NUMBER);
        Preconditions.checkArgument(StringUtils.isNumeric(streetNumber), INVALID_STREET_NUMBER);

        LOG.trace("exit");
    }
}
