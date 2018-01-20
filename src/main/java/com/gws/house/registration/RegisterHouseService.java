/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.house.registration;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.grindwise.addressauthenticator.AddressAuthenticator;
import com.grindwise.addressauthenticator.AddressAuthenticatorFactory;
import com.grindwise.addressauthenticator.AddressAuthenticatorModule;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Register House Service.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class RegisterHouseService extends Application<RegisterHouseServiceConfiguration>
{
    private static final Logger LOG = LoggerFactory.getLogger(RegisterHouseService.class);
    private static final String SERVICE_IS_READY = "register house service is ready to go...";
    
    private static final String NAME = "Register House Service";
    
    private static Injector injector;
    
    /**
     * Constructor.
     */
    RegisterHouseService()
    {
        LOG.trace("entry");
        LOG.trace("exit");
    }
    
    /**
     * Main.
     * 
     * @param args command line arguments.
     * 
     * @throws Exception for any unexpected issue.
     */
    public static void main(final String[] args)
        throws Exception
    {
        LOG.trace("entry");
        
        injector = Guice.createInjector(new AddressAuthenticatorModule());

        // no exit after run - causes embedded jetty server to exit
        new RegisterHouseService().run(args);
        
        LOG.trace("exit");
    }

    @Override
    public void initialize(final Bootstrap<RegisterHouseServiceConfiguration> bootstrap)
    {
        LOG.trace("entry");
        LOG.trace("exit");
    }
    
    @Override
    public String getName()
    {
        LOG.trace("entry");
        LOG.trace("exit");

        return NAME;
    }

    @Override
    public void run(final RegisterHouseServiceConfiguration registerHouseServiceConfiguration,
                    final Environment environment)
        throws Exception
    {
        LOG.trace("entry");
        
        final RegisterHouseResource registerHouseResource =
            this.createRegisterHouseResource(registerHouseServiceConfiguration, environment);
        
        this.createHealthChecks(environment);
        
        environment.jersey().setUrlPattern("/*");
        environment.jersey().register(registerHouseResource);
        
        LOG.info(SERVICE_IS_READY);

        LOG.trace("exit");
    }
    
    /**
     * Create the register house resource.
     * 
     * @param registerHouseServiceConfiguration configuration for service.
     * @param environment runtime environment for service.
     * 
     * @return an instance of the register house resource. 
     */
    private RegisterHouseResource createRegisterHouseResource(
        final RegisterHouseServiceConfiguration registerHouseServiceConfiguration,
        final Environment environment)
    {
        LOG.trace("entry");
        
        final RuntimeEnvironmentProperties runtimeProperties =
            new RegisterHouseRuntimeProperties();

        final AddressAuthenticatorFactory addressAuthenticatorFactory =
            injector.getInstance(AddressAuthenticatorFactory.class);
        
        final AddressAuthenticator addressAuthenticator = addressAuthenticatorFactory.create(
            registerHouseServiceConfiguration.getAddressAuthenticatorID(),
            registerHouseServiceConfiguration.getAddressAuthenticatorToken());
        
        final HouseFactory propertyFactory = new HouseFactory(addressAuthenticator, runtimeProperties);
        
        final RegisterHouseResource registerHouseResource =
            new RegisterHouseResource(propertyFactory);

        LOG.trace("exit");

        return registerHouseResource;
    }
    /**
     * Create health check.
     * 
     * @param environment environment for this service.
     * 
     */
    private void createHealthChecks(final Environment environment)
    {
        LOG.trace("entry");
        
        //environment.healthChecks().register("persistentStoreHealthChecks",
        //    new RepositoryHealthCheck(houseRepository));
        
        LOG.trace("exit");
    }
}
