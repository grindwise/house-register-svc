/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Invocation outcome implementation.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class DomainInvocationOutcomeImpl implements DomainInvocationOutcome
{
    private static final Logger LOG = LoggerFactory.getLogger(DomainInvocationOutcomeImpl.class);
    
    private final InvocationOutcome invocationOutcome;
    private final String message;
   
    /**
     * Constructor.
     * 
     * @param invocationOutcome enumerated values representing outcome of invocation. 
     * @param message outcome message.
     */
    public DomainInvocationOutcomeImpl(final InvocationOutcome invocationOutcome, final String message)
    {
        LOG.trace("entry");
        
        this.invocationOutcome = invocationOutcome;
        this.message = message;
        
        LOG.trace("exit");
    }
    
    @Override
    public final String toString()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.message;
    }
}
