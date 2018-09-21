/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.grindwise.addressauthenticator.AddressAuthenticator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class ResidentialAddressSolitaryTest
{
    private final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);

    private AddressInformation addressInformation;
    private String streetNumber;
    private String streetName;
    private String city;
    private String stateAbbreviationCode;
    private String stateName;
    private String deliveryArea;
    private String geographicSegment;
    
    @Before
    public void setUp()
    {
        this.addressInformation = new AddressInformation();
        
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void validObject()
    {
        Assert.assertTrue(new ResidentialAddress(this.addressInformation, this.addressAuthenticator)
                instanceof ResidentialAddress);
    }
    
    @Test
    public void nullStreetNumberNotAllowed()
    {
        addressInformation.streetNumber = null;
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void nullStreetNameNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = null;
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullCityNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = null;
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullStateAbbreviationCodeNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = null;
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullStateNameNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = null;
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullDeliveryCodeNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = null;
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullGeographicSegmentNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = null;

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void nullAddressValidatorNotAllowed()
    {
        try
        {
            final ResidentialAddress address =  new ResidentialAddress(this.addressInformation, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void emptyStreetNumberNotAllowed()
    {
        addressInformation.streetNumber = "";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void emptyStreetNameNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void emptyCityNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void emptyStateAbbreviationCodeNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void emptyStateNameNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void emptyDeliveryCodeNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "";
        addressInformation.geographicSegment = "6757";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void emptyGeographicSegmentNotAllowed()
    {
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "";

        try
        {
            final ResidentialAddress address = new ResidentialAddress(this.addressInformation, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final IllegalArgumentException exception)
        {
            Assert.assertTrue(true);
        }
    }    
}
