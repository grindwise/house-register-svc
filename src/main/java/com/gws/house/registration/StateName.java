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
 * State Name implementation.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class StateName
{
    private static final Logger LOG = LoggerFactory.getLogger(StateName.class);
    
    private static final String INVALID_STATE_NAME =
        "State name cannot be null or empty and must be alpha characters and contain space.";
    
    private final String stateName;

    /**
     * Constructor.
     * 
     * @param stateName name of state.
     */
    protected StateName(final String stateName)
    {
        LOG.trace("entry");
        
        this.validate(stateName);
        
        this.stateName = stateName;

        LOG.trace("exit");
    }

    protected JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation stateNameObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);

        stateNameObjectStateRepresentation.addState("stateName", this.stateName, "String");
        
        return stateNameObjectStateRepresentation;
    }
    
    /**
     * Validate constructor invariants.
     * 
     * @param stateName name of state.
     */
    private void validate(final String stateName)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(stateName, INVALID_STATE_NAME);
        Preconditions.checkArgument(!"".equals(stateName), INVALID_STATE_NAME);
        Preconditions.checkArgument(StringUtils.isAlphaSpace(stateName), INVALID_STATE_NAME);
        
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
