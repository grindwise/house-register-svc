/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.behavior.framework.AggregateRootObjectID;
import com.gws.behavior.framework.RuntimeEnvironmentProperties;
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
    private final AggregateRootObjectID aggregateRootObjectID = Mockito.mock(AggregateRootObjectID.class);
    private final ResidentialAddress address = Mockito.mock(ResidentialAddress.class);
    private final RuntimeEnvironmentProperties properties = new RegisterHouseRuntimePropertiesFake();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void validConstructor()
    {
        Assert.assertTrue(new House(this.aggregateRootObjectID, this.address, this.properties) instanceof House);
    }

    @Test
    public void invalidObjectNullObjectID()
    {
        try
        {
            new House(null, this.address, this.properties);
            
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNullAddress()
    {
        try
        {
            new House(this.aggregateRootObjectID, null, this.properties);
            
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
            new House(this.aggregateRootObjectID, this.address, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
