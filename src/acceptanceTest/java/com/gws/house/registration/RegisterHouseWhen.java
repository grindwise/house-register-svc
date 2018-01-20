/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import com.gws.house.registration.House;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.junit.Assert;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class RegisterHouseWhen extends Stage<RegisterHouseWhen>
{
    @ScenarioState
    private House house;

    public RegisterHouseWhen theAddressHasBeenAuthenticated()
    {
        try
        {
            // domain entry point
            this.house.register();
        }
        catch (final Exception exception)
        {
            Assert.assertFalse(exception.getMessage(), true);
        }
        
        return this;
    }
    
    public RegisterHouseWhen theAddressFailedAuthentication()
    {
        try
        {
            // domain entry point
            this.house.register();
        }
        catch (final Exception exception)
        {
            Assert.assertFalse(exception.getMessage(), true);
        }
        
        return this;
    }
}
