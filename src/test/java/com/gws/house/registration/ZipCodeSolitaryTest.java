/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.GeographicSegment;
import com.gws.house.registration.DeliveryArea;
import com.gws.house.registration.ZipCode;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class ZipCodeSolitaryTest
{
    private final DeliveryArea deliveryArea = Mockito.mock(DeliveryArea.class);
    private final GeographicSegment geographicSegment = Mockito.mock(GeographicSegment.class);
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    @Test
    public void validObject()
    {
        Assert.assertTrue(new ZipCode(this.deliveryArea, this.geographicSegment) instanceof ZipCode);
    }
    
    @Test
    public void invalidObjectNullDeliveryArea()
    {
        try
        {
            final ZipCode zipCode = new ZipCode(null, this.geographicSegment);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);    
        }
    }
    
    @Test
    public void invalidObjectNullGeographicSegment()
    {
        try
        {
            final ZipCode zipCode = new ZipCode(this.deliveryArea, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);    
        }
    }
}
