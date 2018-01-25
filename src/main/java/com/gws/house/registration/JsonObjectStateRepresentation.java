package com.gws.house.registration;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public final class JsonObjectStateRepresentation
{
    private final String objectName;
    //private final boolean includeObjectName;
    
    private String stateName;
    private String stateValue;
    private String stateType;
    private List<JsonObjectStateRepresentation> children;
    
    public JsonObjectStateRepresentation(final String objectName, final boolean includeObjectName)
    {
        this.objectName = objectName;
        //this.includeObjectName = includeObjectName;
    }
//    private final AggregateRootObject aggregateRootObject;
//    private final List<ObjectStateRepresentation> childrenObjectStates;
    
//    public JsonObjectStateRepresentation(final AggregateRootObject aggregateRootObject,
//                                     final JsonObjectStateRepresentation... childrenObjectStates)
//    {
//        this.aggregateRootObject = aggregateRootObject;
//        this.childrenObjectStates = Arrays.asList(childrenObjectStates);
//    }

    public void addState(final String stateName, final String stateValue, final String stateType)
    {
        this.stateName = stateName;
        this.stateValue = stateValue;
        this.stateType = stateType;
    }
    
    public void addState(final JsonObjectStateRepresentation... children)
    {
        this.children = Arrays.asList(children);
    }
    
    public String format()
    {
        String document = "";
        
        if (this.children != null)
        {
            document += "\"" + this.objectName + "\"";
            document += ": ";
            document += "{";

            final Iterator<JsonObjectStateRepresentation> iterator = this.children.iterator();
            
            while (iterator.hasNext())
            {
                final JsonObjectStateRepresentation child = iterator.next();

                document = document + child.format();
                
                if (iterator.hasNext())
                {
                    document += ", ";
                }
                //document += "}";
            }
            
            document += "}";
        }
        else
        {
            //if (this.includeObjectName)
            //{
            //   document += "\"" + this.objectName + "." + this.stateName + "\"";
            //   document += ": " + "\"" + this.stateValue + "\"";
            //}
            //else
            //{
               document += "\"" + this.stateName + "\"";
               document += ": " + "\"" + this.stateValue + "\"";
            //}
        }

        return document;
    }
}
