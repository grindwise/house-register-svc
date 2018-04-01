/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt;

import com.google.common.base.Preconditions;
import com.gws.productionenvy.framework.DomainInvocationOutcome;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register House Property REST Resource.  The responsibility
 * of this class is to simply handle the REST request and invoke the domain.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
@Path("/house/register")
public final class RegisterHouseResource
{
    private static final transient Logger LOG = LoggerFactory.getLogger(RegisterHouseResource.class);

    private static final String INVALID_HOUSE_FACTORY = "house factory cannot be null.";
            
    private final transient HouseFactory houseFactory;
    
    /**
     * Constructor.
     * 
     * @param houseFactory factory to create house.
     */
    public RegisterHouseResource(final HouseFactory houseFactory)
    {
        LOG.trace("entry");
        
        this.validate(houseFactory);
          
        this.houseFactory = houseFactory;

        LOG.trace("exit");
    }

    /**
     * Register house.
     * 
     * @param houseInformation information about the house being registered.
     * 
     * @return HTTP response based on outcome of invoking the house domain object.
     */
    @POST
    @Consumes({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON})   
    public Response registerHouse(final HouseInformation houseInformation)
    {
        LOG.trace("entry");

        Response response;
        final DomainInvocationOutcome registerDomainInvocationOutcome;
        
        try
        {
            final Property houseBeingRegistered = this.houseFactory.create(houseInformation);
            
            if (houseBeingRegistered instanceof NullHouse)
            {
                registerDomainInvocationOutcome = houseBeingRegistered.register();
                
                response = Response.status(Response.Status.OK).entity(
                    registerDomainInvocationOutcome.toString()).build();
                
                LOG.info("null house was created due to" + registerDomainInvocationOutcome.toString());
            }
            else
            {
                LOG.debug("new house created");
        
                /* entry point into the domain via aggregate root (invoke domain behavior) */
                registerDomainInvocationOutcome = houseBeingRegistered.register();
                LOG.debug("register invocation successful");
            
                response = Response.status(Response.Status.OK).entity(
                    registerDomainInvocationOutcome.toString()).build();
            }
        }
        catch (final Exception exception)
        {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(exception.getLocalizedMessage()).build();            
        }

        LOG.trace("exit");
        
        return response;
    }

    /**
     * Validate constructor invariants.
     * 
     * @param addressAuthenticator service to authenticate address of property.
     * @param runtimeProperties properties needed at runtime.
     */    
    private void validate(final HouseFactory houseFactory)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(houseFactory, INVALID_HOUSE_FACTORY);
        LOG.debug("passed validation");

        LOG.trace("exit");
    }
}
