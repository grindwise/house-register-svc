/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2018 Grind Wise Inc. - Software Engineering Services
 */

package com.gws.propertymgmt;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.gws.productionenvy.framework.MongoPersistence;
import com.gws.productionenvy.framework.Persistence;
import com.gws.productionenvy.framework.PersistenceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dependency injection module.
 * 
 * @author Jim Fiolek  jim.fiolek@grindwise.com
 */
public final class RegisterModule extends AbstractModule
{
    public static final String OBJECT_ID_ARG = "objectID";
    
    private static final transient Logger LOG = LoggerFactory.getLogger(RegisterModule.class);

    /**
     * Constructor.
     */
    public RegisterModule()
    {
        LOG.trace("entry");
        LOG.trace("exit");
    }
    
    /**
     * Configure injection.
     */
    @Override
    protected void configure()
    {
        LOG.trace("entry");
        
        install(new FactoryModuleBuilder().
            implement(Persistence.class, MongoPersistence.class).
                build(PersistenceFactory.class));
        
        LOG.trace("exit");
    }
}
