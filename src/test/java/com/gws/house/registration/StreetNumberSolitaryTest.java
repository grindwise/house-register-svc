/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.StreetName;
import com.gws.house.registration.StreetNumber;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class StreetNumberSolitaryTest
{
    private final String streetNumber = "24109";
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void validObject()
    {
        Assert.assertTrue(new StreetNumber(this.streetNumber) instanceof StreetNumber);
    }
    
    @Test
    public void invalidObjectNullStreetNumber()
    {
        try
        {
            final StreetNumber streetNumber = new StreetNumber(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectEmptyStreetNumber()
    {
        try
        {
            final StreetNumber streetNumber = new StreetNumber("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectStreetNumberNotNumeric()
    {
        try
        {
            final StreetName streetName = new StreetName("24I09");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
