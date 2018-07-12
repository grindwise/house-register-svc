/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.propertymgmt.portfoliomgmt.domain;

import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.productionenvy.framework.AggregateRootObjectIDInformation;
import com.gws.productionenvy.framework.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class HouseSolitaryTest
{
    private final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);
    private final Persistence persistence = Mockito.mock(Persistence.class);
    
    private AggregateRootObjectIDInformation aggregateRootObjectIDInformation;
    private HouseInformation houseInformation;
    
    @Before
    public void setUp()
    {
        final AddressInformation addressInformation = new AddressInformation();

        this.aggregateRootObjectIDInformation = new AggregateRootObjectIDInformation();
        this.aggregateRootObjectIDInformation.aggregateRootObjectID = "123";

        this.houseInformation = new HouseInformation();
        
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "0000";
        
        this.houseInformation.addressInformation = addressInformation;
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void validConstructor()
    {
        Assert.assertTrue(new House(houseInformation, this.addressAuthenticator, this.persistence) instanceof House);
    }

    @Test
    public void validConstructorOverloaded()
    {
        Assert.assertTrue(new House(this.aggregateRootObjectIDInformation, this.houseInformation,
                                    this.addressAuthenticator, this.persistence) instanceof House);
    }

    @Test
    public void nullObjectIDNotAllowed()
    {
        try
        {
            new House(null, this.houseInformation, this.addressAuthenticator, this.persistence);
            
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void nullHouseInformationNotAllowed()
    {
        try
        {
            new House(this.aggregateRootObjectIDInformation, null, this.addressAuthenticator, this.persistence);
            
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullAddressAuthenticatorNotAllowed()
    {
        try
        {
            new House(this.aggregateRootObjectIDInformation, this.houseInformation, null, this.persistence);
            
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullPropertiesNotAllowed()
    {
        try
        {
            new House(this.aggregateRootObjectIDInformation, this.houseInformation,
                      this.addressAuthenticator, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
