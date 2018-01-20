/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

/**
 * Indicates the domain invocation results.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public interface DomainInvocationOutcome
{
    /**
     * Indicates the outcome of the invocation.
     */
    enum InvocationOutcome
    {
        /**
         * invocation was successful.
         */
        SUCCESS,
        /**
         * invocation failed.
         */
        FAILURE
    }
}
