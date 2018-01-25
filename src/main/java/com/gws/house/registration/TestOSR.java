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
public class TestOSR
{
    public static void main(final String[] args)
    {
        Parent parent = new Parent();
        
        final JsonObjectStateRepresentation state = parent.establishState();
        
        System.out.println(state.format());
    }
}
