/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * House Information represents the data needed to create a
 * house property.
 * 
 * <p>Although represented as an object in the language this is a
 * simple data container - not an object. All data is public and
 * requires NO validation since it will be validated by the domain.</p>
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class HouseInformation
{
    @JsonIgnore
    private static final long serialVersionUID = 8650432243887657692L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public AddressInformation addressInformation;

    /**
     * Constructor.
     */
    public HouseInformation()
    {
        super();
    }
}
