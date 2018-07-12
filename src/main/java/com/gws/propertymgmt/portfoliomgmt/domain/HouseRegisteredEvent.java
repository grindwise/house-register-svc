/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.google.common.base.Preconditions;
import com.gws.productionenvy.framework.AggregateRootObjectIDInformation;
import com.gws.productionenvy.framework.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Indicates a significant event has occurred - A house has been registered.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
final class HouseRegisteredEvent implements Event
{
    private static final transient Logger LOG = LoggerFactory.getLogger(HouseRegisteredEvent.class);
    private static final String INVALID_OBJECTID_INFORMATION = "provided aggregate root object ID cannot be null.";
    private static final String INVALID_HOUSE_INFORMATION = "provided house information cannot be null.";
    
    private final AggregateRootObjectIDInformation aggregateRootObjectIDInformation;
    private final HouseInformation houseInformation;
    
    /**
     * Constructor.
     * 
     * @param aggregateRootObjectIDInformation information structure necessary to instantiate an object ID.
     * @param houseInformation information structure necessary to instantiate a house.
     */
    HouseRegisteredEvent(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
                         final HouseInformation houseInformation)
    {
        LOG.trace("entry");
        
        this.validate(aggregateRootObjectIDInformation, houseInformation);
        
        this.aggregateRootObjectIDInformation = aggregateRootObjectIDInformation;
        this.houseInformation = houseInformation;

        LOG.trace("exit");
    }
    
    @Override
    public void publish()
    {
        LOG.trace("entry");
        
        LOG.debug(this.aggregateRootObjectIDInformation.toString());
        LOG.debug(this.houseInformation.toString());
        
        // TODO: add implementation
        
        LOG.trace("exit");
    }
    
    /**
     * Validate invariants.
     * 
     * @param aggregateRootObjectIDInformation information structure necessary to instantiate an object ID.
     * @param houseInformation information structure necessary to instantiate a house.
     */
    private void validate(final AggregateRootObjectIDInformation aggregateRootObjectIDInformation,
                          final HouseInformation houseInformation)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(aggregateRootObjectIDInformation, INVALID_OBJECTID_INFORMATION);
        Preconditions.checkNotNull(houseInformation, INVALID_HOUSE_INFORMATION);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
