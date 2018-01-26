/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import com.gws.behavior.framework.ObjectStateRepresentation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Delivery Area of a Zip Code.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
class DeliveryArea
{
    private static final Logger LOG = LoggerFactory.getLogger(DeliveryArea.class);
    
    private static final String INVALID_DELIVERY_AREA =
        "5 digit delivery area cannot be null or empty and must be 5 numeric characters";
    private static final int DELIVERY_AREA_LENGTH = 5;
    
    private final String deliveryArea;
    
    /**
     * Constructor.
     * 
     * @param deliveryArea delivery area of zip code.
     */
    protected DeliveryArea(final String deliveryArea)
    {
        LOG.trace("entry");
        
        this.validate(deliveryArea);
        
        this.deliveryArea = deliveryArea;

        LOG.trace("exit");
    }

    /**
     * Validate the constructor invariants.
     * 
     * @param deliveryArea delivery area of zip code.
     */
    private void validate(final String deliveryArea)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(deliveryArea, INVALID_DELIVERY_AREA);
        Preconditions.checkArgument(!"".equals(deliveryArea), INVALID_DELIVERY_AREA);
        Preconditions.checkArgument(deliveryArea.length() == DELIVERY_AREA_LENGTH, INVALID_DELIVERY_AREA);
        Preconditions.checkArgument(StringUtils.isNumeric(deliveryArea), INVALID_DELIVERY_AREA);

        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }

    /**
     * Provide the delivery area of zip code.
     * 
     * @return delivery area of zip code. 
     */
    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.deliveryArea;
    }
    
    protected ObjectStateRepresentation establishState(
        final House.HouseObjectStateRepresentationFactory houseObjectStateRepresentationFactory)
    {
        final ObjectStateRepresentation deliveryAreaObjectStateRepresentation =
            houseObjectStateRepresentationFactory.create(this.getClass().getSimpleName(), true);

        deliveryAreaObjectStateRepresentation.addState("deliveryArea", this.deliveryArea, "String");
        
        return deliveryAreaObjectStateRepresentation;
    }    
}
