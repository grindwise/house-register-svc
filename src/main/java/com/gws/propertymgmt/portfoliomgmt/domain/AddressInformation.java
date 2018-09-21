/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Address Information represents the data needed to create an
 * address.
 * 
 * <p>Although represented as an object in the language this is a
 * simple data container - not an object. All data is public and
 * requires NO validation since it will be validated by the domain.</p>
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class AddressInformation implements Serializable
{
    private static final transient String COMMA = ", ";
    
    @JsonIgnore
    private static final long serialVersionUID = -8758180528481280744L;
    
    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String streetNumber;
    
    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String streetName;

    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String cityName;

    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String stateAbbreviationCode;

    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String stateName;

    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String deliveryArea;

    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String geographicSegment;

    /**
     * Constructor.
     */
    public AddressInformation()
    {
    }
    
    @Override
    public String toString()
    {
        final String toString =
            this.streetNumber
            + COMMA
            + this.streetName
            + COMMA
            + this.cityName
            + COMMA
            + this.stateAbbreviationCode
            + COMMA
            + this.stateName
            + COMMA
            + this.deliveryArea
            + COMMA
            + this.geographicSegment;
        
        return toString;
    }
}
