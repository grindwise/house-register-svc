/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt.domain;

import com.google.common.base.Preconditions;
import com.grindwise.addressauthenticator.AddressAuthenticationException;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.gws.productionenvy.framework.ChildObjectStateRepresentation;
import com.gws.productionenvy.framework.ChildObjectStateRepresentationImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Residential address.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
final class ResidentialAddress implements Address
{
    private static final transient Logger LOG = LoggerFactory.getLogger(ResidentialAddress.class);
    
    private static final String INVALID_STREET_NUMBER =
        "Street number cannot be null, empty, and must be numeric.";
    private static final String INVALID_STREET_NAME = "Street name cannot be null or empty.";
    private static final String INVALID_CITY =
        "city name cannot be null or empty, can be aA through zZ as well as contain spaces and a period.";
    private static final String VALID_CITY_REG_EX = "[a-zA-Z .]+";
    private static final int STATE_ABBREVIATION_CODE_LENGTH = 2;
    private static final String INVALID_STATE_ABBREVIATION_CODE =
        "State abbreviation code cannot be null, empty and length must be ";
    private static final String INVALID_STATE_NAME =
        "State name cannot be null or empty and must be alpha characters and contain space.";
    private static final String INVALID_DELIVERY_AREA =
        "5 digit delivery area cannot be null or empty and must be 5 numeric characters";
    private static final int DELIVERY_AREA_LENGTH = 5;
    private static final String INVALID_GEOGRAPHIC_SEQMENT =
        "Geographic segment cannot be null or empty and must be 4 numeric characters";
    private static final int GEOGRAPHIC_SEGMENT_LENGTH = 4;
    private static final String INVALID_ADDRESS_AUTHENTICATOR = "The address authenticator cannot be null.";

    private final String streetNumber;
    private final String streetName;
    private final String cityName;
    private final String stateAbbreviationCode;
    private final String stateName;
    private final String deliveryArea;
    private final String geographicSegment;
    private final transient AddressAuthenticator addressAuthenticator;
    
    /**
     * Constructor.
     * 
     * @param addressInformation address information necessary to instantiate an address.
     * @param addressAuthenticator authenticator of addresses.
     */
    protected ResidentialAddress(final AddressInformation addressInformation,
                                 final AddressAuthenticator addressAuthenticator)
    {
        super();
        
        LOG.trace("entry");
        
        this.validate(addressInformation, addressAuthenticator);
        
        this.streetNumber = addressInformation.streetNumber;
        this.streetName = addressInformation.streetName;
        this.cityName = addressInformation.cityName;
        this.stateAbbreviationCode = addressInformation.stateAbbreviationCode;
        this.stateName = addressInformation.stateName;
        this.deliveryArea = addressInformation.deliveryArea;
        this.geographicSegment = addressInformation.geographicSegment;
        this.addressAuthenticator = addressAuthenticator;
        
        LOG.trace("exit");
    }
    
    /**
     * Make sure the address is valid (able to be authenticated by 3rd party).
     * 
     * @throws AddressAuthenticationException if the address provided cannot be
     *     authenticated as real - a real address is expected.
     */
    @Override
    public Boolean isAuthentic() throws AddressAuthenticationException
    {
        LOG.trace("entry");
        LOG.trace("exit");

        return this.addressAuthenticator.isAuthentic(this.streetNumber, this.streetName, this.cityName,
                                                     this.stateAbbreviationCode, this.deliveryArea);
    }

    /**
     * Validate provided invariants.
     * 
     * @param addressInformation address information necessary to instantiate an address.
     * @param addressAuthenticator authenticator of addresses.
     */
    private void validate(final AddressInformation addressInformation,
                          final AddressAuthenticator addressAuthenticator)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(addressInformation.streetNumber, INVALID_STREET_NUMBER);
        Preconditions.checkArgument(!"".equals(addressInformation.streetNumber), INVALID_STREET_NUMBER);
        Preconditions.checkArgument(StringUtils.isNumeric(addressInformation.streetNumber), INVALID_STREET_NUMBER);
        Preconditions.checkNotNull(addressInformation.streetName, INVALID_STREET_NAME);
        Preconditions.checkArgument(!"".equals(addressInformation.streetName), INVALID_STREET_NAME);
        Preconditions.checkArgument(StringUtils.isAlphaSpace(addressInformation.streetName), INVALID_STREET_NAME);
        Preconditions.checkNotNull(addressInformation.cityName, INVALID_CITY);
        Preconditions.checkArgument(!"".equals(addressInformation.cityName), INVALID_CITY);
        Preconditions.checkArgument(addressInformation.cityName.matches(VALID_CITY_REG_EX), INVALID_CITY);
        Preconditions.checkNotNull(
            addressInformation.stateAbbreviationCode, INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkArgument(!"".equals(addressInformation.stateAbbreviationCode),
            INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkArgument(addressInformation.stateAbbreviationCode.length() == STATE_ABBREVIATION_CODE_LENGTH,
            INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkArgument(StringUtils.isAlpha(addressInformation.stateAbbreviationCode),
            INVALID_STATE_ABBREVIATION_CODE + STATE_ABBREVIATION_CODE_LENGTH);
        Preconditions.checkNotNull(addressInformation.stateName, INVALID_STATE_NAME);
        Preconditions.checkArgument(!"".equals(addressInformation.stateName), INVALID_STATE_NAME);
        Preconditions.checkArgument(StringUtils.isAlphaSpace(addressInformation.stateName), INVALID_STATE_NAME);
        Preconditions.checkNotNull(addressInformation.deliveryArea, INVALID_DELIVERY_AREA);
        Preconditions.checkArgument(!"".equals(addressInformation.deliveryArea), INVALID_DELIVERY_AREA);
        Preconditions.checkArgument(
            addressInformation.deliveryArea.length() == DELIVERY_AREA_LENGTH, INVALID_DELIVERY_AREA);
        Preconditions.checkArgument(StringUtils.isNumeric(addressInformation.deliveryArea), INVALID_DELIVERY_AREA);
        Preconditions.checkNotNull(addressInformation.geographicSegment, INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkArgument(!"".equals(addressInformation.geographicSegment), INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkArgument(addressInformation.geographicSegment.length()
            == GEOGRAPHIC_SEGMENT_LENGTH, INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkArgument(
            StringUtils.isNumeric(addressInformation.geographicSegment), INVALID_GEOGRAPHIC_SEQMENT);
        Preconditions.checkNotNull(addressAuthenticator, INVALID_ADDRESS_AUTHENTICATOR);
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
    
    /**
     * Pull state from this for persistence.
     * 
     * @return ObjectState state of this.
     */
    protected ChildObjectStateRepresentation objectState()
    {
        final ChildObjectStateRepresentation streetNumberObjectState =
            new ChildObjectStateRepresentationImpl("streetNumber", this.streetNumber, "String");
        final ChildObjectStateRepresentation streetNameObjectState =
            new ChildObjectStateRepresentationImpl("streetName", this.streetName, "String");
        final ChildObjectStateRepresentation cityObjectState =
            new ChildObjectStateRepresentationImpl("city", this.cityName, "String");
        final ChildObjectStateRepresentation stateAbbreviationObjectState =
            new ChildObjectStateRepresentationImpl("stateAbbreviationCode", this.stateAbbreviationCode, "String");
        final ChildObjectStateRepresentation stateNameObjectState =
            new ChildObjectStateRepresentationImpl("stateName", this.stateName, "String");
        final ChildObjectStateRepresentation deliveryAreaObjectState =
            new ChildObjectStateRepresentationImpl("deliveryArea", this.deliveryArea, "String");
        final ChildObjectStateRepresentation geographicSegmentbjectState =
            new ChildObjectStateRepresentationImpl("geographicSegment", this.geographicSegment, "String");

        final ChildObjectStateRepresentation addressObjectState = new ChildObjectStateRepresentationImpl(
            StringUtils.uncapitalize(this.getClass().getSimpleName()), streetNumberObjectState, streetNameObjectState,
            cityObjectState, stateAbbreviationObjectState, stateNameObjectState,
            deliveryAreaObjectState, geographicSegmentbjectState);
        
        return addressObjectState;
    }
}
