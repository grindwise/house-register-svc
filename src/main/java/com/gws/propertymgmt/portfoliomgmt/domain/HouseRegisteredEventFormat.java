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
class HouseRegisteredEventFormat implements Format<HouseRegisteredEvent>
{
    private static final transient Logger LOG = LoggerFactory.getLogger(HouseRegisteredEventFormat.class);

    private final AggregateRootObjectIDInformation aggregateRootObjectIDInformation;
    private HouseInformation houseInformation;
    
    /**
     * Constructor.
     */
    HouseRegisteredEventFormat()
    {
        LOG.trace("entry");
        
        this.aggregateRootObjectIDInformation = new AggregateRootObjectIDInformation();
        
        LOG.trace("exit");
    }
    
    @Override
    public void format(final String aggregateRootContainerName) throws OutputException
    {
        this.houseInformation = new HouseInformation();
    }

    @Override
    public void format(final String stateType, final String stateName,
                       final String stateValue, final String containerName)
        throws OutputException
    {
    }

    @Override
    public HouseRegisteredEvent result()
    {
        LOG.trace("entry");

        // TODO: this.aggregateRootObjectIDInformation is not correct and must be replaced
        final HouseRegisteredEvent houseRegisteredEvent =
            new HouseRegisteredEvent(this.aggregateRootObjectIDInformation, this.houseInformation);

        LOG.trace("exit");
        
        return houseRegisteredEvent;
    }
    
}
