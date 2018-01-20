/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Aggregate Root Object ID.  This is a data member of every Aggregate Root domain object to
 * uniquely identify each instance.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class AggregateRootObjectID
{
    private static final Logger LOG = LoggerFactory.getLogger(AggregateRootObjectID.class);
    private static final String INVALID_OBJECT_ID = "the provided object id cannot be null or empty.";
    
    private final String objectID;
 
    /**
     * Create a aggregate root object ID for a new aggregate root domain object.
     */
    protected AggregateRootObjectID()
    {
        LOG.trace("entry");
        
        this.objectID = UUID.randomUUID().toString();
        
        LOG.trace("exit");
    }
    
    /**
     * Create an aggregate root object ID for an existing aggregate root domain object.
     * 
     * @param objectID ID of an existing aggregate root domain object. 
     */
    protected AggregateRootObjectID(final String objectID)
    {
        LOG.trace("entry");
        
        this.validate(objectID);
        
        this.objectID = objectID;

        LOG.trace("exit");
    }

    /**
     * Validate the Aggregate Root invariants.
     * 
     * @param objectID aggregate root object ID. 
     */
    private void validate(final String objectID)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(objectID, INVALID_OBJECT_ID);
        Preconditions.checkArgument(!"".equals(objectID), INVALID_OBJECT_ID);
        
        LOG.debug("passed validation");
        
        LOG.trace("exit");        
    }
}
