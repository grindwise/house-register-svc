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
 * State Abbreviation Code implementation.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class StateAbbreviationCode
{
    private static final Logger LOG = LoggerFactory.getLogger(StateAbbreviationCode.class);
    
    private static final int STATE_ABBREVIATION_CODE_LENGTH = 2;
    private static final String INVALID_STATE_ABBREVIATION_CODE =
        "State abbreviation code cannot be null, empty and length must be ";

    private final String stateAbbreviationCode;

    /**
     * Constructor.
     *  
     * @param stateAbbreviationCode abbreviation code of state.
     */
    protected StateAbbreviationCode(final String stateAbbreviationCode)
    {
        LOG.trace("entry");
        
        this.validate(stateAbbreviationCode);
        
        this.stateAbbreviationCode = stateAbbreviationCode;

        LOG.trace("exit");
    }

    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.stateAbbreviationCode;
    }
    
    protected JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation stateAbbreviationObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);

        stateAbbreviationObjectStateRepresentation.addState(
            "stateAbbreviationCode", this.stateAbbreviationCode, "String");
        
        return stateAbbreviationObjectStateRepresentation;
    }

    /**
     * Validate constructor invariants are valid.
     * 
     * @param stateAbbreviationCode abbreviation code of state.
     */    
    private void validate(final String stateAbbreviationCode)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(
            stateAbbreviationCode, INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkArgument(!"".equals(stateAbbreviationCode),
            INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkArgument(stateAbbreviationCode.length() == STATE_ABBREVIATION_CODE_LENGTH,
            INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkArgument(StringUtils.isAlpha(stateAbbreviationCode),
            INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);

        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
