/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.StateAbbreviationCode;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class StateAbbreviationCodeSolitaryTest
{
    private final String stateAbbreviationCode = "FL";
    
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
        Assert.assertTrue(new StateAbbreviationCode(stateAbbreviationCode) instanceof StateAbbreviationCode);
    }

    @Test
    public void invalidObjectStateAbbreviationCodeNull()
    {
        try
        {
            final StateAbbreviationCode stateAbbreviationCode =
                new StateAbbreviationCode(null);
            
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectStateAbbreviationCodeEmpty()
    {
        try
        {
            final StateAbbreviationCode stateAbbreviationCode =
                new StateAbbreviationCode("");
            
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectStateAbbreviationCodeNotAlpha()
    {
        try
        {
            final StateAbbreviationCode stateAbbreviationCode =
                new StateAbbreviationCode("F1");
            
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectStateAbbreviationCodeTooLong()
    {
        try
        {
            final StateAbbreviationCode stateAbbreviationCode =
                new StateAbbreviationCode("Flo");
            
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectStateAbbreviationCodeTooShort()
    {
        try
        {
            final StateAbbreviationCode stateAbbreviationCode =
                new StateAbbreviationCode("F");
            
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
