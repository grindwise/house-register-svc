/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class AddressVerificationAnswer implements Answer
{
    private final Boolean answer;
    
    public AddressVerificationAnswer(final Boolean answer)
    {
        this.answer = answer;    
    }
    
    @Override
    public Object answer(InvocationOnMock invocation) throws Throwable
    {
        return this.answer;
    }    
}
