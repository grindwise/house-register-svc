/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.gws.productionenvy.framework.AggregateRootObjectIDInformation;
import com.gws.productionenvy.framework.Format;
import com.gws.productionenvy.framework.OutputException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class HouseUnregisteredEventFormat implements Format<HouseRegisteredEvent>
{
    private static final transient Logger LOG = LoggerFactory.getLogger(HouseUnregisteredEventFormat.class);

    private final HouseInformation houseInformation;
    
    /**
     * Constructor.
     */
    HouseUnregisteredEventFormat()
    {
        LOG.trace("entry");
        
        this.houseInformation = new HouseInformation();
        
        LOG.trace("exit");
    }
    
    @Override
    public void format(final String aggregateRootContainerName) throws OutputException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void format(final String stateType, final String stateName,
                       final String stateValue, final String containerName)
        throws OutputException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HouseRegisteredEvent result()
    {
        LOG.trace("entry");

        // TODO: this.aggregateRootObjectIDInformation is not correct and must be replaced
        AggregateRootObjectIDInformation aggregateRootObjectIDInformation = new AggregateRootObjectIDInformation();

        final HouseRegisteredEvent houseRegisteredEvent =
            new HouseRegisteredEvent(aggregateRootObjectIDInformation, this.houseInformation);

        LOG.trace("exit");
        
        return houseRegisteredEvent;
    }
    
}
