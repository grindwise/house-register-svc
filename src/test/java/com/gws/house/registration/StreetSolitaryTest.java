/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.Street;
import com.gws.house.registration.StreetName;
import com.gws.house.registration.StreetNumber;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class StreetSolitaryTest
{
    private final StreetNumber streetNumber = Mockito.mock(StreetNumber.class);
    private final StreetName streetName = Mockito.mock(StreetName.class);
    
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
        Assert.assertTrue(new Street(this.streetNumber, this.streetName) instanceof Street);
    }
    
    @Test
    public void invalidObjectNullStreetNumber()
    {
        try
        {
            final Street street = new Street(null, this.streetName);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectNullStreetName()
    {
        try
        {
            final Street street = new Street(this.streetNumber, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
