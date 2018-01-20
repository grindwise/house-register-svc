/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

/**
 * Represents something significant that has happened in the domain.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public interface Event
{
    /**
     * Publish an event indicating that something important has
     * occurred to the domain.
     */
    void publish();
}
