/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt.portfoliomgmt;

import com.google.common.base.Preconditions;
import com.gws.productionenvy.framework.AggregateRootObjectCreationException;
import com.gws.productionenvy.framework.DomainEntryPointException;
import com.gws.propertymgmt.portfoliomgmt.domain.HouseFactory;
import com.gws.propertymgmt.portfoliomgmt.domain.HouseInformation;
import com.gws.propertymgmt.portfoliomgmt.domain.Property;
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
    private static final String HOUSE_REGISTERED_SUCCESSFULLY = "house registered successfully.";
            
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
        
        try
        {
            // create the house (property)
            final Property houseBeingRegistered = this.houseFactory.create(houseInformation);
            LOG.debug("new house created");

            houseBeingRegistered.register();
            LOG.debug(HOUSE_REGISTERED_SUCCESSFULLY);

            response = Response.status(Response.Status.OK).entity(HOUSE_REGISTERED_SUCCESSFULLY).build();
        }
        catch (final AggregateRootObjectCreationException | DomainEntryPointException exception)
        {
            response = Response.status(Response.Status.BAD_REQUEST).entity(exception.getMessage()).build();
        }
        
        LOG.trace("exit");
        
        return response;
    }

    /**
     * Validate constructor invariants.
     * 
     * @param houseFactory factory to create house instances.
     */    
    private void validate(final HouseFactory houseFactory)
    {
        LOG.trace("entry");
        
        Preconditions.checkNotNull(houseFactory, INVALID_HOUSE_FACTORY);
        LOG.debug("passed validation");

        LOG.trace("exit");
    }
}
