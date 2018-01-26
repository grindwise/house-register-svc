/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import com.gws.behavior.framework.ObjectStateRepresentation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Street Name implementation.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class StreetName
{
    private static final Logger LOG = LoggerFactory.getLogger(StreetName.class);
    private static final String INVALID_STREET_NAME = "Street name cannot be null or empty.";

    private final String streetName;
    
    /**
     * Constructor.
     * 
     * @param streetName name of the street.
     */
    protected StreetName(final String streetName)
    {
        LOG.trace("entry");
        
        this.validate(streetName);
        
        this.streetName = streetName;

        LOG.trace("exit");
    }

    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.streetName;
    }
    
    protected ObjectStateRepresentation establishState(
        final House.HouseObjectStateRepresentationFactory houseObjectStateRepresentationFactory)
    {
        final ObjectStateRepresentation streetNameObjectStateRepresentation =
            houseObjectStateRepresentationFactory.create(this.getClass().getSimpleName(), true);

        streetNameObjectStateRepresentation.addState(
            "streetName", this.streetName, "String");
        
        return streetNameObjectStateRepresentation;
    }
    
    /**
     * Validate constructor invariants.
     * 
     * @param streetName name of street.
     */
    private void validate(final String streetName)
    {
        LOG.trace("entry");

        Preconditions.checkNotNull(streetName, INVALID_STREET_NAME);
        Preconditions.checkArgument(!"".equals(streetName), INVALID_STREET_NAME);
        Preconditions.checkArgument(StringUtils.isAlphaSpace(streetName), INVALID_STREET_NAME);

        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
