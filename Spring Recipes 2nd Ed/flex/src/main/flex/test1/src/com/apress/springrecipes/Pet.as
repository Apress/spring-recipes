package com.apress.springrecipes
{
public class Pet
{

    private var _name : String;

    public function get name() : String {
        return _name;
    }

    public function set name(n : String) : void {
        _name = n;
    }

    public function Pet(n : String)
    {
        _name = n;
    }

}
}
