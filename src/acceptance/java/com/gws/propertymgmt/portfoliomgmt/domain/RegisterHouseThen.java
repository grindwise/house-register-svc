/*
 * Property of Grind Wise Inc.
 * 
 * Copyright (C) 2016 Grind Wise Inc. - Software Engineering Services
 */
package com.gws.propertymgmt.portfoliomgmt.domain;

import com.gws.productionenvy.framework.RuntimeEnvironmentProperties;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tngtech.jgiven.Stage;
import org.bson.Document;
import org.junit.Assert;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class RegisterHouseThen extends Stage<RegisterHouseThen>
{
    public RegisterHouseThen theHouseInformationHasBeenSaved()
    {
        final RuntimeEnvironmentProperties purchaseHouseEnvironmentProperties =
            new HouseRuntimeProperties();

        MongoClient mongoClient = new MongoClient(
            purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR),
            Integer.parseInt(purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR)));
        
        MongoDatabase housesDatabase = mongoClient.getDatabase(purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR));
        
        MongoCollection<Document> housesCollection = housesDatabase.getCollection(purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_COLLECTION_NAME_ENV_VAR));

        System.out.println("collection: " + purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_COLLECTION_NAME_ENV_VAR));
        
        if (housesCollection.count() == 1)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse("matching record not found because there are " + housesCollection.count() + " records found", true);
        }
        
        return this;
    }
    
    public RegisterHouseThen theHouseInformationHasNotBeenSaved()
    {
        final RuntimeEnvironmentProperties purchaseHouseEnvironmentProperties =
            new HouseRuntimeProperties();

        MongoClient mongoClient = new MongoClient(
            purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_STORE_HOST_ENV_VAR),
            Integer.parseInt(purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_STORE_PORT_ENV_VAR)));
        
        MongoDatabase housesDatabase = mongoClient.getDatabase(purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_STORE_NAME_ENV_VAR));
        MongoCollection<Document> housesCollection = housesDatabase.getCollection(purchaseHouseEnvironmentProperties.getPropertyValue(HouseRuntimeProperties.PERSISTENT_COLLECTION_NAME_ENV_VAR));
        
        if (housesCollection.count() == 0)
        {
            Assert.assertTrue(true);
        }
        else
        {
            Assert.assertFalse("house record found when there shouldn't be, " + housesCollection.count() + " records found", true);
        }
        
        return this;
    }
}
