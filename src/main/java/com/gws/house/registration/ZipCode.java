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
 * Zip Code implementation.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
class ZipCode
{
    private static final Logger LOG = LoggerFactory.getLogger(ZipCode.class);
    
    private static final String INVALID_DELIVERY_AREA = "delivery area cannot be null.";
    private static final String INVALID_GEOGRAPHIC_SEGMENT = "geographic segment cannot be null.";
    
    private final DeliveryArea deliveryArea;
    private final GeographicSegment geographicSegment;
    
    /**
     * Constructor.
     * 
     * @param deliveryArea delivery area of the zip code.
     * @param geographicSegment geographic segment of the zip code.
     */
    protected ZipCode(final DeliveryArea deliveryArea, final GeographicSegment geographicSegment)
    {
        LOG.trace("entry");
        
        this.validate(deliveryArea, geographicSegment);
        
        this.deliveryArea = deliveryArea;
        this.geographicSegment = geographicSegment;
        
        LOG.trace("exit");
    }

    protected String provideAddressValue()
    {
        LOG.trace("entry");
        
        final String deliveryAreaOfZipCode = this.deliveryArea.provideAddressValue();
 
        //ignore geographicSegment
        final String zipCode = deliveryAreaOfZipCode;
        
        LOG.trace("exit");
        
        return zipCode;
    }
    
    protected JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation deliveryAreaObjectStateRepresentation =
            this.deliveryArea.establishState();

        final JsonObjectStateRepresentation geographicSegmentObjectStateRepresentation =
            this.geographicSegment.establishState();
        
        final JsonObjectStateRepresentation zipCodeObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);

        zipCodeObjectStateRepresentation.addState(
            deliveryAreaObjectStateRepresentation, geographicSegmentObjectStateRepresentation);
        
        return zipCodeObjectStateRepresentation;
    }

    /**
     * Validate zip code invariants.
     * 
     * @param deliveryArea zip code portion representing the area of delivery.
     * @param geographicSegment geographic location segment of the zip code.
     */
    private void validate(final DeliveryArea deliveryArea, final GeographicSegment geographicSegment)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(deliveryArea, INVALID_DELIVERY_AREA);
        Preconditions.checkNotNull(geographicSegment, INVALID_GEOGRAPHIC_SEGMENT);

        LOG.debug("passed validation");
        
        LOG.trace("entry");
    }
}
