/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gws.house.registration;

import com.gws.house.registration.ResidentialAddress;
import com.gws.house.registration.Street;
import com.gws.house.registration.State;
import com.gws.house.registration.StateAbbreviationCode;
import com.gws.house.registration.City;
import com.gws.house.registration.ZipCode;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class ResidentialAddressTest
{
    private final StateAbbreviationCode stateAbbreviationCode = Mockito.mock(StateAbbreviationCode.class);
    private final State state = Mockito.mock(State.class);
    private final Street street = Mockito.mock(Street.class);
    private final City city = Mockito.mock(City.class);
    private final ZipCode zipCode = Mockito.mock(ZipCode.class);
    
    @Before
    public void setUp()
    {
//        this.stateAbbreviationCode = new StateAbbreviationCodeImpl("MI");
//        this.stateName = new StateNameImpl("Michigan");
//        this.state = new StateImpl(stateAbbreviationCode, stateName);
//        this.streetNumber = new StreetNumberImpl("24109");
//        this.streetName = new StreetNameImpl("Carlysle");
//        this.street = new StreetImpl(streetNumber, streetName);
//        this.cityName = new XCityNameImpl("Dearborn");
//        this.city = new CityImpl(cityName);
//        this.deliveryArea = new DeliveryAreaImpl("48124");
//        this.geographicSegment = new GeographicSegmentImpl("6757");
//        this.zipCode = new ZipCodeImpl(deliveryArea, geographicSegment);
    }
    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void validAddress()
    {
        final Answer answer = new AddressVerificationAnswer(true);
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class, answer);
        ResidentialAddress address =
            new ResidentialAddress(this.street, this.city, this.state, this.zipCode, addressAuthenticator);
        
        try
        {
            Assert.assertTrue(address.authentic());        
        }
        catch (final Exception exception)
        {
            Assert.assertFalse(exception.getLocalizedMessage(), true);
        }
    }
    
    @Test
    public void invalidAddressNotAuthenticated()
    {
        final Answer answer = new AddressVerificationAnswer(false);
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class, answer);
        ResidentialAddress address =
            new ResidentialAddress(this.street, this.city, this.state, this.zipCode, addressAuthenticator);
        
        try
        {
            Assert.assertFalse(address.authentic());                
        }
        catch (final Exception exception)
        {
            Assert.assertTrue(exception.getLocalizedMessage(), false);
        }
    }
    
    @Test
    public void invalidAddressNullStreet()
    {
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);
        
        try
        {
            new ResidentialAddress(null, this.city, this.state, this.zipCode, addressAuthenticator);
            Assert.assertFalse("Street cannot be null", true);                
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidAddressNullCity()
    {
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);
        
        try
        {
            new ResidentialAddress(this.street, null, this.state, this.zipCode, addressAuthenticator);
            Assert.assertFalse("Street cannot be null", true);                
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidAddressNullState()
    {
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);
        
        try
        {
            new ResidentialAddress(this.street, this.city, null, this.zipCode, addressAuthenticator);
            Assert.assertFalse("Street cannot be null", true);                
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
    
    @Test
    public void invalidAddressNullZipCode()
    {
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class);
        
        try
        {
            new ResidentialAddress(this.street, this.city, this.state, null, addressAuthenticator);
            Assert.assertFalse("Street cannot be null", true);                
        }
        catch (final NullPointerException exception)
        {
            Assert.assertTrue(true);
        }
    }
}
