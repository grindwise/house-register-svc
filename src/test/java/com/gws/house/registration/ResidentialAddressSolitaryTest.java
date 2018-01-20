/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.ResidentialAddress;
import com.gws.house.registration.State;
import com.gws.house.registration.Street;
import com.gws.house.registration.City;
import com.gws.house.registration.ZipCode;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class ResidentialAddressSolitaryTest
{
    private final Street street = Mockito.mock(Street.class);
    private final City city = Mockito.mock(City.class);
    private final State state = Mockito.mock(State.class);
    private final ZipCode zipCode = Mockito.mock(ZipCode.class);
    private final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Test
    public void validObject()
    {
        Assert.assertTrue(new ResidentialAddress(
            this.street, this.city, this.state, this.zipCode, this.addressAuthenticator)
                instanceof ResidentialAddress);
    }
    
    @Test
    public void invalidObjectNullStreet()
    {
        try
        {
            final ResidentialAddress address =
                new ResidentialAddress(null, this.city, this.state, this.zipCode, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNullCity()
    {
        try
        {
            final ResidentialAddress address =
                new ResidentialAddress(this.street, null, this.state, this.zipCode, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNullState()
    {
        try
        {
            final ResidentialAddress address =
                new ResidentialAddress(this.street, this.city, null, this.zipCode, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNullZipCode()
    {
        try
        {
            final ResidentialAddress address =
                new ResidentialAddress(this.street, this.city, this.state, null, this.addressAuthenticator);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidObjectNullAddressValidator()
    {
        try
        {
            final ResidentialAddress address =
                new ResidentialAddress(this.street, this.city, this.state, this.zipCode, null);
            Assert.assertFalse(true);
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
