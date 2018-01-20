/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.HouseFactory;
import com.gws.house.registration.House;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.house.registration.AddressInformation;
import com.gws.house.registration.HouseInformation;
import com.gws.house.registration.RegisterHouseRuntimeProperties;
import com.gws.house.registration.RuntimeEnvironmentProperties;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Hidden;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class RegisterHouseGiven extends Stage<RegisterHouseGiven>
{
    private Boolean validAddressIndicator;
    
    @ScenarioState
    private House house;
    
    /**
     *
     */
    public RegisterHouseGiven()
    {
    }
    
    public RegisterHouseGiven aNewHouseNeedsToBeManage()
        throws Exception
    {
        HouseInformation houseInformation = new HouseInformation();
        AddressInformation addressInformation = new AddressInformation();
        
        addressInformation.streetNumber = "24109";
        addressInformation.streetName = "Carlysle";
        addressInformation.cityName = "Dearborn";
        addressInformation.stateAbbreviationCode = "MI";
        addressInformation.stateName = "Michigan";
        addressInformation.deliveryArea = "48124";
        addressInformation.geographicSegment = "5767";
        
        houseInformation.addressInformation = addressInformation;
        
        final Answer answer = new AddressVerificationAnswer(this.validAddressIndicator);
        final AddressAuthenticator addressAuthenticator = Mockito.mock(AddressAuthenticator.class, answer);
        
        final RuntimeEnvironmentProperties properties =
            new RegisterHouseRuntimeProperties();
        
        final HouseFactory propertyFactory = new HouseFactory(addressAuthenticator, properties);
        this.house = propertyFactory.create(houseInformation);
        
        return this;
    }
    
    @Hidden
    public void setAddressValidityIndicator(final Boolean validAddressIndicator)
    {
        this.validAddressIndicator = validAddressIndicator;
    }
}
