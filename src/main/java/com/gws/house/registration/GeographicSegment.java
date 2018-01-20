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
 * Geographic Segment implementation.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class GeographicSegment
{
    private static final Logger LOG = LoggerFactory.getLogger(GeographicSegment.class);
    
    private static final String INVALID_GEOGRAPHIC_SEQMENT =
        "Geographic segment cannot be null or empty and must be 4 numeric characters";
    private static final int GEOGRAPHIC_SEGMENT_LENGTH = 4;
    
    private final String geographicSegment;

    protected static GeographicSegment create(final String geographicSegment)
    {
        return new GeographicSegment(geographicSegment);
    }
    
    /**
     * Constructor.
     * 
     * @param geographicSegment geographic segment of the zip code.
     */
    protected GeographicSegment(final String geographicSegment)
    {
        LOG.trace("entry");
        
        this.validate(geographicSegment);

        this.geographicSegment = geographicSegment;
        
        LOG.trace("exit");
    }

    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");

        return this.geographicSegment;
    }
    
    /**
     * Validate that the constructor invariants are valid.
     * 
     * @param geographicSegment geographic segment of the zip code.
     */
    private void validate(final String geographicSegment)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(geographicSegment, INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkArgument(!"".equals(geographicSegment), INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkArgument(geographicSegment.length()
            == GEOGRAPHIC_SEGMENT_LENGTH, INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkArgument(StringUtils.isNumeric(geographicSegment), INVALID_GEOGRAPHIC_SEQMENT);
        
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
