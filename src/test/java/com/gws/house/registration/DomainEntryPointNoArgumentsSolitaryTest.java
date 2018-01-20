/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.DomainEntryPoint;
import com.gws.house.registration.DomainEntryPointNoArguments;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class DomainEntryPointNoArgumentsSolitaryTest
{
    private final String methodName = "method";
    
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
        Assert.assertTrue(new DomainEntryPointNoArguments(methodName) instanceof DomainEntryPoint);
    }

    @Test
    public void invalidObjectNullMethodName()
    {
        try
        {
            new DomainEntryPointNoArguments(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectEmptyMethodName()
    {
        try
        {
            new DomainEntryPointNoArguments("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectSpacesInMethodName()
    {
        try
        {
            new DomainEntryPointNoArguments("method Name");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void validObjectMethodNameNumbers()
    {
        try
        {
            new DomainEntryPointNoArguments("method123");
            Assert.assertTrue(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertFalse(true);
        }
    }
}
