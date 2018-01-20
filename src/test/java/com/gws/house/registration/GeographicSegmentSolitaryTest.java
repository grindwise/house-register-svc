/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.GeographicSegment;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class GeographicSegmentSolitaryTest
{
    private final String geographicSegmentString = "6757";
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void validObject()
    {
        Assert.assertTrue(new GeographicSegment(geographicSegmentString) instanceof GeographicSegment);
    }
    
    @Test
    public void invalidObjectNullGeographicSegment()
    {
        try
        {
            final GeographicSegment geographicSegment = new GeographicSegment(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectEmptyGeographicSegment()
    {
        try
        {
            final GeographicSegment geographicSegment = new GeographicSegment("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectGeographicSegmentTooShort()
    {
        try
        {
            final GeographicSegment geographicSegment = new GeographicSegment("123");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);    
        }
    }

    @Test
    public void invalidObjectGeographicSegmentTooLong()
    {
        try
        {
            final GeographicSegment geographicSegment = new GeographicSegment("12345");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);    
        }
    }
    
    @Test
    public void invalidObjectGeographicSegmentIsNotNumeric()
    {
        try
        {
            final GeographicSegment geographicSegment = new GeographicSegment("4i44");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);    
        }
    }
    
}
