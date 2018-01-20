/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.StateName;
import com.gws.house.registration.StateAbbreviationCode;
import com.gws.house.registration.State;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class StateSolitaryTest
{
    private final StateAbbreviationCode stateAbbreviationCode = Mockito.mock(StateAbbreviationCode.class);
    private final StateName stateName = Mockito.mock(StateName.class);
 
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
        Assert.assertTrue(new State(this.stateAbbreviationCode, this.stateName) instanceof State);
    }
    
    @Test
    public void invalidObjectStateAbbreviationCodeNull()
    {
        try
        {
            final State state = new State(null, this.stateName);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectStateNameNull()
    {
        try
        {
            final State state = new State(this.stateAbbreviationCode, null);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
