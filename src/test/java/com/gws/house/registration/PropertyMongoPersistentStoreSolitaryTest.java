/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.house.registration;

import org.junit.After;
import org.junit.Before;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class PropertyMongoPersistentStoreSolitaryTest
{
    private final String persistentStoreUserName = "user";
    private final String persistentStorePassword = "";
    private final String persistentStoreHost = "localhost";
    private final String persistentStorePort = "17067";
    private final String persistentStoreName = "store";
            
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
 
//    @Test
//    public void validConstruction()
//    {
//        // ,
//            //this.persistentStoreName, this.domainPackages
//        Assert.assertTrue(new PropertyMongoPersistentStore(
//            this.persistentStoreUserName, this.persistentStorePassword,
//            this.persistentStoreHost, this.persistentStorePort, this.persistentStoreName) instanceof HouseRepository);
//    }
//    
//    @Test
//    public void invalidConstructionNullUserName()
//    {
//        try
//        {
//            new PropertyMongoPersistentStore(null, this.persistentStorePassword,
//            this.persistentStoreHost, this.persistentStorePort, this.persistentStoreName);
//            
//            Assert.assertFalse(true);
//        }
//        catch (final NullPointerException exception)
//        {
//            Assert.assertTrue(true);
//        }
//    }
}
