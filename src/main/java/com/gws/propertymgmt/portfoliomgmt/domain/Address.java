/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.grindwise.addressauthenticator.AddressAuthenticationException;

/**
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
interface Address
{
    /**
     * Checks the address for authenticity.
     * 
     * @return true if it can be verified otherwise false
     * 
     * @throws AddressAuthenticationException if any unexpected error occurs. 
     */
    Boolean isAuthentic() throws AddressAuthenticationException;
}
