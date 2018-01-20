/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.StreetName;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class StreetNameSolitaryTest
{
    private final String streetName = "Carlysle";
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void validObject()
    {
        Assert.assertTrue(new StreetName(this.streetName) instanceof StreetName);
    }
    
    @Test
    public void invalidObjectNullStreetName()
    {
        try
        {
            final StreetName streetName = new StreetName(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectEmptyStreetName()
    {
        try
        {
            final StreetName streetName = new StreetName("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void validObjectAlphaWithSpacesStreetName()
    {
        try
        {
            final StreetName streetName = new StreetName("Pellicer Rd.");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
}
