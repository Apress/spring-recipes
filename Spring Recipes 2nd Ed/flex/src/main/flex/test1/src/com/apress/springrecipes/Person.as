package com.apress.springrecipes
{
import mx.controls.Alert;

public class Person
{

    private var _firstName : String;
    private var _lastName : String;
    private var _age : int;
    private var _pet : Pet;

    public function get pet() : Pet {
        return _pet;
    }

    public function set pet(p : Pet) : void {
        _pet = p;
    }

    public function get age() : int {
        return _age;
    }

    public function set age(age : int) : void {
        _age = age;
    }

    public function get firstName() : String {
        return _firstName;
    }

    public function set firstName(fn : String) : void {
        _firstName = fn;
    }

    public function get lastName() : String {
        return _lastName;
    }

    public function set lastName(ln : String) : void {
        _lastName = ln;
    }

    public function say(msg : String) : void {
        Alert.show(msg);
    }

    public function Person() {
    }

}
}
