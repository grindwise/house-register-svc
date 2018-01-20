/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.EndOfLifeIndicator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class EndOfLifeIndicatorSolitaryTest
{
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
        Assert.assertTrue(new EndOfLifeIndicator(true) instanceof EndOfLifeIndicator);
    }

    @Test
    public void invalidObject()
    {
        try
        {
            new EndOfLifeIndicator(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
