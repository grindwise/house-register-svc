/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.gws.house.registration.City;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class CitySolitaryTest
{
    private final String city = "Dearborn";
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Test
    public void validObject()
    {
        Assert.assertTrue(new City(this.city) instanceof City);
    }
    
    @Test
    public void InvalidObjectNullCity()
    {
        try
        {
            final City city = new City(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void InvalidObjectEmptyCity()
    {
        try
        {
            final City city = new City("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
