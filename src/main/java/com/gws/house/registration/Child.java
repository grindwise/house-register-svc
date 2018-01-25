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
public class Child
{
    protected JsonObjectStateRepresentation establishState()
    {
        final JsonObjectStateRepresentation cityObjectStateRepresentation =
            new JsonObjectStateRepresentation(this.getClass().getSimpleName(), true);

        cityObjectStateRepresentation.addState("city", "Dearborn", "String");

        return cityObjectStateRepresentation;
    }
}
