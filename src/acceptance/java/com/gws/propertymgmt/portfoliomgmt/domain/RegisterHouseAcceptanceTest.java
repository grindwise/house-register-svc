/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.propertymgmt.portfoliomgmt.domain;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.gws.productionenvy.framework.Persistence;
import com.gws.productionenvy.framework.PersistenceFactory;
import com.gws.productionenvy.framework.RuntimeEnvironmentProperties;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tngtech.jgiven.junit.ScenarioTest;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This class contains all the acceptance criteria scenarios for the register method
 * (domain entry point) on the property interface implemented by @see House.
 * 
 * To see the user story and acceptance criteria send an email to: propertyhoncho@grindwise.com,
 * specify "read only access" as the subject.  The reply will provide the details.
 * 
 *     here you can review stories and acceptance criteria.
 * 
 * NOTE: for the acceptance test and scenarios to work a mongodb daemon must be
 * running in the environment.
 * 
 * 
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class RegisterHouseAcceptanceTest extends ScenarioTest<
        RegisterHouseGiven,
        RegisterHouseWhen,
        RegisterHouseThen>
{
    private static final String PERSISTENT_STORE_USERNAME = "";
    private static final String PERSISTENT_STORE_PASSWORD = "";
    private static final String PERSISTENT_STORE_NAME = "house";
    private static final String PERSISTENT_STORE_HOST = "localhost";
    private static final String PERSISTENT_STORE_PORT = "27017";
    private static final String PERSISTENT_COLLECTION_NAME = "houses";
    private static final String PERSISTENCE_ACCEPTANCE_TEST_EXTENTION = "_acceptance";
    
    private Persistence persistence;
    
    @Before
    public void setup()
    {
        Map<String,String> processRuntimeEnvironmentVariables = new HashMap<>();
        processRuntimeEnvironmentVariables.put(
            HouseRuntimeProperties.PERSISTENT_STORE_USERNAME_ENV_VAR, PERSISTENT_STORE_USERNAME);
        processRuntimeEnvironmentVariables.put(
            HouseRuntimeProperties.PERSISTENT_STORE_PASSWORD_ENV_VAR, PERSISTENT_STORE_PASSWORD);
        processRuntimeEnvironmentVariables.put(
            HouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR, PERSISTENT_STORE_HOST);
        processRuntimeEnvironmentVariables.put(
            HouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR, PERSISTENT_STORE_PORT);
        processRuntimeEnvironmentVariables.put(
            HouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR, PERSISTENT_STORE_NAME +
                PERSISTENCE_ACCEPTANCE_TEST_EXTENTION);
        processRuntimeEnvironmentVariables.put(
            HouseRuntimeProperties.PERSISTENT_COLLECTION_NAME_ENV_VAR, PERSISTENT_COLLECTION_NAME);
        
        try
        {
            this.establishRuntimeEnvironmentVariables(processRuntimeEnvironmentVariables);
        
            final RuntimeEnvironmentProperties registerHouseEnvironmentProperties =
                new HouseRuntimeProperties();

            final Injector injector = Guice.createInjector(new PersistenceModule());
            final PersistenceFactory persistenceFactory = injector.getInstance(PersistenceFactory.class);
            this.persistence = persistenceFactory.create(
                registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR),
                Integer.parseInt(registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR)),
                registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR),
                registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_COLLECTION_NAME_ENV_VAR));
            
            final MongoClient mongoClient = new MongoClient(
                registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR),
                Integer.parseInt(registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR)));
        
            final MongoDatabase housesDatabase = mongoClient.getDatabase(
                registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR));
            
            final MongoCollection<Document> housesCollection = housesDatabase.getCollection(
                registerHouseEnvironmentProperties.getPropertyValue(
                    HouseRuntimeProperties.PERSISTENT_COLLECTION_NAME_ENV_VAR));

            housesCollection.drop();
            housesDatabase.drop();
        }
        catch (final IllegalAccessException | ClassNotFoundException |
                     IllegalArgumentException | SecurityException | NoSuchFieldException exception)
        {
            Assert.fail(exception.getMessage());
        }
    }
    
    @Test
    public void scenario1RegisterHouseWithValidAddress()
    {
        try
        {
            given().setPersistence(this.persistence);
            given().setAddressValidityIndicator(Boolean.TRUE);
            given().aNewHouseNeedsToBeManage();
            when().theAddressIsAValidUnitedStatesAddress();
            then().theHouseInformationHasBeenSaved();
        }
        catch (final Exception exception)
        {
            Assert.fail(exception.getMessage());
        }
    }
    
    @Test
    public void scenario2RegisterHouseWithInvalidAddress()
    {
        try
        {
            given().setPersistence(this.persistence);
            given().setAddressValidityIndicator(Boolean.FALSE);
            given().aNewHouseNeedsToBeManage();
            when().theAddressIsAnInvalidUnitedStatesAddress();
            then().theHouseInformationHasNotBeenSaved();
        }
        catch (final Exception exception)
        {
            Assert.fail(exception.getMessage());
        }
    }

    // hack to setup environment variables for acceptance test
    private void establishRuntimeEnvironmentVariables(final Map<String, String> processRuntimeEnvironmentVariables)
        throws IllegalAccessException, ClassNotFoundException, NoSuchFieldException
    {
        try
        {
            Class<?> processEnvironmentClass = Class.forName("java.lang.ProcessEnvironment");
            Field theEnvironmentField = processEnvironmentClass.getDeclaredField("theEnvironment");
            theEnvironmentField.setAccessible(true);
            Map<String, String> env = (Map<String, String>) theEnvironmentField.get(null);
            env.putAll(processRuntimeEnvironmentVariables);
            Field theCaseInsensitiveEnvironmentField = processEnvironmentClass.getDeclaredField("theCaseInsensitiveEnvironment");
            theCaseInsensitiveEnvironmentField.setAccessible(true);
            Map<String, String> cienv = (Map<String, String>)     theCaseInsensitiveEnvironmentField.get(null);
            cienv.putAll(processRuntimeEnvironmentVariables);
        }
        catch (final NoSuchFieldException exception)
        {
            Class[] classes = Collections.class.getDeclaredClasses();
            Map<String, String> env = System.getenv();

            for(Class cl : classes)
            {
                if("java.util.Collections$UnmodifiableMap".equals(cl.getName()))
                {
                    Field field = cl.getDeclaredField("m");
                    field.setAccessible(true);
                    Object obj = field.get(env);
                    Map<String, String> map = (Map<String, String>) obj;
                    map.clear();
                    map.putAll(processRuntimeEnvironmentVariables);
                }
            }
        }
    }    
}
