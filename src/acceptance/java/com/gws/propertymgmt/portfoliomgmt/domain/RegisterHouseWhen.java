/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.propertymgmt.portfoliomgmt.domain;

import com.gws.productionenvy.framework.DomainEntryPointException;
import com.gws.productionenvy.framework.DomainInvocationOutcome;
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
    private Property house;

    public RegisterHouseWhen theAddressIsAValidUnitedStatesAddress()
    {
        try
        {
            // domain entry point
            DomainInvocationOutcome domainInvocationOutcome = this.house.register();
            
            if (!domainInvocationOutcome.invocationSuccess())
            {
                Assert.assertFalse("valid address provided, should have passed.  check test.", true);
            }
        }
        catch (final DomainEntryPointException exception)
        {
            Assert.assertFalse(exception.getMessage(), true);            
        }
        
        return this;
    }
    
    public RegisterHouseWhen theAddressIsAnInvalidUnitedStatesAddress()
    {
        try
        {
            // domain entry point
            DomainInvocationOutcome domainInvocationOutcome = this.house.register();
            
            if (domainInvocationOutcome.invocationSuccess())
            {
                Assert.assertFalse("invalid address provided, failed test.", true);
            }
        }
        catch (final DomainEntryPointException exception)
        {
            Assert.assertFalse(exception.getMessage(), true);
        }
        
        return this;
    }
}
