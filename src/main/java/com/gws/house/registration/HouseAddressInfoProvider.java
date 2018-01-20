/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

/**
 * Interface implemented by objects that provide part of an address.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
abstract class HouseAddressInfoProvider
{
    /**
     * Provide part of an address.
     * 
     * @return String representing part of the address. 
     */
    protected abstract String provideAddressValue();
}
