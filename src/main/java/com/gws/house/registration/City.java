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
 * Implementation of a CityImpl domain object.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
class City
{
    private static final Logger LOG = LoggerFactory.getLogger(City.class);
    
    private static final String INVALID_CITY =
        "city name cannot be null or empty, can be aA through zZ as well as contain spaces and a period.";
    private static final String VALID_CITY_REG_EX = "[a-zA-Z .]+";

    private final String city;
    
    protected static City create(final String city)
    {
        return new City(city);
    }

    /**
     * Constructor.
     * 
     * @param cityName name of city. 
     */
    protected City(final String city)
    {
        LOG.trace("entry");
        
        this.validate(city);
        
        this.city = city;

        LOG.trace("exit");
    }
    
    protected String provideAddressValue()
    {
        LOG.trace("entry");
        LOG.trace("exit");
        
        return this.city;
    }
    
    /**
     * Validate constructor invariants necessary to successfully
 create an instance of CityImpl.
     * 
     * @param cityName name of city.
     */
    private void validate(final String city)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(city, INVALID_CITY);
        Preconditions.checkArgument(!"".equals(city), INVALID_CITY);
        Preconditions.checkArgument(city.matches(VALID_CITY_REG_EX), INVALID_CITY);

        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
