/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gws.house.registration;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class Parent
{
    private Child child = new Child();
    
    public JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation cityObjectStateRepresentation = this.child.establishState();
        final JsonObjectStateRepresentation addressObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);
        addressObjectStateRepresentation.addState(cityObjectStateRepresentation);
        
        return addressObjectStateRepresentation;
    }

}
