/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Street implementation.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
class Street
{
    private static final Logger LOG = LoggerFactory.getLogger(Street.class);
    
    private static final String INVALID_STREET_NUMBER = "Street number cannot be null.";
    private static final String INVALID_STREET_NAME = "Street name cannot be null.";
    
    private final StreetNumber streetNumber;
    private final StreetName streetName;
    
    /**
     * Constructor.
     * 
     * @param streetNumber unique number of street.
     * @param streetName  name of street.
     */
    protected Street(final StreetNumber streetNumber, final StreetName streetName)
    {
        LOG.trace("entry");

        this.validate(streetNumber, streetName);
        
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        
        LOG.trace("exit");        
    }
 
    protected String provideAddressValue()
    {
        LOG.trace("entry");
        
        final String streetNumberOfAddress = this.streetNumber.provideAddressValue();
        final String streetNameOfAddress = this.streetName.provideAddressValue();
        
        final String street = streetNumberOfAddress + " " + streetNameOfAddress;
        
        LOG.trace("exit");
        
        return street;
    }

    protected JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation streetNumberObjectStateRepresentation =
            this.streetNumber.establishState();

        final JsonObjectStateRepresentation streetNameObjectStateRepresentation =
            this.streetName.establishState();
        
        final JsonObjectStateRepresentation streetObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);

        streetObjectStateRepresentation.addState(
            streetNumberObjectStateRepresentation, streetNameObjectStateRepresentation);
        
        return streetObjectStateRepresentation;
    }
    
    /**
     * Validate constructor invariants are valid.
     * 
     * @param streetNumber street number of street.
     * @param streetName name of street.
     */
    private void validate(final StreetNumber streetNumber, final StreetName streetName)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(streetNumber, INVALID_STREET_NUMBER);
        Preconditions.checkNotNull(streetName, INVALID_STREET_NAME);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
