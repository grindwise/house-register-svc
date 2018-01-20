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
 * State implementation.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
class State
{
    private static final Logger LOG = LoggerFactory.getLogger(State.class);
    
    private static final String INVALID_STATE_ABBREVIATION_CODE = "state abbreviation code cannot be null";
    private static final String INVALID_STATE_NAME = "state name cannot be null";
    
    private final StateAbbreviationCode stateAbbreviationCode;
    private final StateName stateName;
    
    protected static State create(final StateAbbreviationCode stateAbbreviationCode, final StateName stateName)
    {
        return new State(stateAbbreviationCode, stateName);
    }
    
    /**
     * Constructor.
     * 
     * @param stateAbbreviationCode code representing state abbreviation.
     * @param stateName full name of state.
     */
    protected State(final StateAbbreviationCode stateAbbreviationCode, final StateName stateName)
    {
        LOG.trace("entry");
        
        this.validate(stateAbbreviationCode, stateName);
        
        this.stateAbbreviationCode = stateAbbreviationCode;
        this.stateName = stateName;
        
        LOG.trace("exit");
    }

    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.stateAbbreviationCode.provideAddressValue();
    }
    
    /**
     * Validate constructor invariants.
     * 
     * @param stateAbbreviationCode abbreviation code of state.
     * @param stateName name of state.
     */
    private void validate(final StateAbbreviationCode stateAbbreviationCode, final StateName stateName)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(stateAbbreviationCode, INVALID_STATE_ABBREVIATION_CODE);
        Preconditions.checkNotNull(stateName, INVALID_STATE_NAME);
        
        LOG.debug("passed validation");
        
        LOG.trace("exit");        
    }
}
