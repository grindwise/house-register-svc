/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.grindwise.addressauthenticator.AddressAuthenticationException;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

/**
 * Unit test for residential test.
 * 
 * Mocking limited to the address authentication service.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class ResidentialAddressUnitTest
{
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void validAddress()
    {
        AddressInformation addressInformation = new AddressInformation();
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";
        
        final Answer answer = new AddressVerificationAnswer(true);
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class, answer);
        
        ResidentialAddress address = new ResidentialAddress(addressInformation, addressAuthenticator);
        
        try
        {
            if (address.isAuthentic())
            {
                Assert.assertTrue(true);        
            }
            else
            {
                Assert.assertFalse("answer is configured wrong for mock address authenticator", true);
            }
        }
        catch (final AddressAuthenticationException exception)
        {
            Assert.assertFalse(exception.getMessage(), true);
        }
    }
    
    @Test
    public void unknownAddress()
    {
        AddressInformation addressInformation = new AddressInformation();
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "ZCarlysle";
        addressInformation.cityName = "XDearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "6757";
        
        final Answer answer = new AddressVerificationAnswer(false);
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class, answer);
        
        ResidentialAddress address = new ResidentialAddress(addressInformation, addressAuthenticator);
        
        try
        {
            if (address.isAuthentic())
            {
                Assert.assertFalse("address is unknown and is considered invalid", true);                
            }
            else
            {
                Assert.assertTrue(true);
            }
        }
        catch (final AddressAuthenticationException exception)
        {
            Assert.assertFalse(exception.getMessage(), true); 
        }
    }
}
