/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.DeliveryArea;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class DeliveryAreaSolitaryTest
{
    private final String deliveryAreaString = "48124";
    
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
        Assert.assertTrue(new DeliveryArea(deliveryAreaString) instanceof DeliveryArea);
    }
    
    @Test
    public void invalidObjectNullDeliveryArea()
    {
        try
        {
            final DeliveryArea deliveryArea = new DeliveryArea(null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectEmptyDeliveryArea()
    {
        try
        {
            final DeliveryArea deliveryArea = new DeliveryArea("");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void invalidObjectDeliveryAreaTooShort()
    {
        try
        {
            final DeliveryArea deliveryArea = new DeliveryArea("1234");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);    
        }
    }
    
    @Test
    public void invalidObjectDeliveryAreaTooLong()
    {
        try
        {
            final DeliveryArea deliveryArea = new DeliveryArea("123456");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);    
        }
    }
    
    @Test
    public void invalidObjectDeliveryAreaIsNotNumeric()
    {
        try
        {
            final DeliveryArea deliveryArea = new DeliveryArea("48i24");
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);    
        }
    }

    
}
