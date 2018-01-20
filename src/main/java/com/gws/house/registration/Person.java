//IMPORTANT
//By default all POJOs must include a public or protected, empty, no arguments, constructor.
// The BsonCreator annotation can be used to support constructors or public static methods to
// create new instances of a POJO.
//
//All properties in a POJO must have a Codec registered in the CodecRegistry so that their
// values can be encoded and decoded.

package com.gws.house.registration;

import org.bson.types.ObjectId;

/**
 *
 * @author Jim Fiolek jim.fiolek@grindwise.com
 */
public class Person
{
    private ObjectId id;
    private String name;
    private int age;

    public Person() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(final ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(final int age) {
        this.age = age;
    }
}
