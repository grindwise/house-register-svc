/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * End of life indicates if an aggregate root domain object is at the end of its life
 * due to some public behavior.
 * 
 * <p>Why is this needed?  Since aggregate root domain objects are only written (no
 * reads, updates, or deletes), the end of life attribute indicates a domain invocation
 * has occurred and is marking the aggregate root domain object has reached end of life
 * (no longer valid).
 * 
 * Write-Only allows us to see every invocation that has occurred on that particular instance
 * of an aggregate root.  This means long after the object is no longer valid we can still
 * perform analytics against it.</p>
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class EndOfLifeIndicator
{
    private static final Logger LOG = LoggerFactory.getLogger(EndOfLifeIndicator.class);
    
    private final boolean endOfLife;
    
    /**
     * Constructor.
     * 
     * @param endOfLife indicates if the domain object is still valid (false) or an
     *     invocation has occurred which invalidates (true) the object.
     */
    public EndOfLifeIndicator(final Boolean endOfLife)
    {
        LOG.trace("entry");
        
        this.endOfLife = endOfLife;
        
        LOG.trace("exit");
    }
}
