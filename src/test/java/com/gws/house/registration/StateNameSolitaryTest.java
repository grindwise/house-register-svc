/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.StateName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class StateNameSolitaryTest
{
    private final String stateName = "Florida";
    
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
        Assert.assertTrue(new StateName(this.stateName) instanceof StateName);
    }

    @Test
    public void invalidObjectNameNull()
    {
        try
        {
            final StateName stateName = new StateName(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNameEmpty()
    {
        try
        {
            final StateName stateName = new StateName("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNameNotAlpha()
    {
        try
        {
            final StateName stateName = new StateName("Flor1da");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void validObjectNameHasSpace()
    {
        try
        {
            final StateName stateName = new StateName("North Carolina");
            Assert.assertTrue(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertFalse(true);
        }
    }
}
