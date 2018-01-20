/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Deregister House Information represents the data needed to create a
 * house aggregate root.
 * 
 * <p>Although represented as an object in the language this is a
 * simple data container - not an object. All data is public and
 * requires NO validation since it will be validated by the domain.</p>
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class AggregateRootObjectIDInformation implements Serializable
{
    @JsonIgnore
    private static final long serialVersionUID = 2966671189396234829L;
    
    @JsonProperty
    @SuppressWarnings("checkstyle:VisibilityModifier")
    public String aggregateRootObjectID;

    /**
     * Constructor.
     */
    public AggregateRootObjectIDInformation()
    {
    }
}
