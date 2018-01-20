/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.ResidentialAddress;
import com.gws.house.registration.House;
import com.gws.house.registration.RuntimeEnvironmentProperties;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class HouseSolitaryTest
{
    private final ResidentialAddress address = Mockito.mock(ResidentialAddress.class);
    private final RuntimeEnvironmentProperties properties = Mockito.mock(RuntimeEnvironmentProperties.class);
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void validConstructor()
    {
        Assert.assertTrue(new House(this.address, this.properties) instanceof House);
    }

    @Test
    public void invalidObjectNullAddress()
    {
        try
        {
            new House(null, this.properties);
            
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNullProperties()
    {
        try
        {
            new House(this.address, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
