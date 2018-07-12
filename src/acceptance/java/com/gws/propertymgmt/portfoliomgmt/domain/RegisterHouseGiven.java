/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.propertymgmt.portfoliomgmt.domain;

import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.productionenvy.framework.Persistence;
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
    private Persistence persistence;
    
    @ScenarioState
    private Property house;
    
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
        
        final HouseFactory propertyFactory = new HouseFactory(addressAuthenticator, this.persistence);
        this.house = propertyFactory.create(houseInformation);
        
        return this;
    }
    
    @Hidden
    public void setAddressValidityIndicator(final Boolean validAddressIndicator)
    {
        this.validAddressIndicator = validAddressIndicator;
    }
    
    @Hidden
    public void setPersistence(final Persistence persistence)
    {
        this.persistence = persistence;
    }
}
