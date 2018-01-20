/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.common.base.Preconditions;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Domain Entry Point.  Represents the invocation on the domain.
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class DomainEntryPointNoArguments implements DomainEntryPoint
{
    private static final Logger LOG = LoggerFactory.getLogger(DomainEntryPointNoArguments.class);
    private static final String INVALID_DOMAIN_INVOICATION_METHOD_NAME =
        "The domain invocation method name cannot be null or empty and cannot contain spaces.";
    private static final String INVOCATION_DATE_TIME_NAME = "invocationDateTime";
    private static final String DOMAIN_ENTRY_POINT_METHOD_NAME = "domainEntryPointMethodName";

    private final String methodName;
    private final String invocationTimestamp;
   
    /**
     * Constructor.
     * 
     * @param methodName name of method invoked.
     */
    public DomainEntryPointNoArguments(final String methodName)
    {
        LOG.trace("entry");
        
        this.validate(methodName);
        
        final Date now = Calendar.getInstance().getTime();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");

        this.methodName = methodName;
        this.invocationTimestamp = dateFormatter.format(now);

        LOG.trace("exit");
    }

    /**
     * Validates the invariants of this.
     * 
     * @param methodName name of method.
     */
    private void validate(final String methodName)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(methodName, INVALID_DOMAIN_INVOICATION_METHOD_NAME);
        Preconditions.checkArgument(!"".equals(methodName), INVALID_DOMAIN_INVOICATION_METHOD_NAME);
        Preconditions.checkArgument(StringUtils.isAlphanumeric(methodName), INVALID_DOMAIN_INVOICATION_METHOD_NAME);
        
        LOG.debug("passed validation");
        
        LOG.trace("exit");
    }
}
