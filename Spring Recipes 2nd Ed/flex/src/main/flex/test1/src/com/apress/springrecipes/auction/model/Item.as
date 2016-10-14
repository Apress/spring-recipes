package com.apress.springrecipes.auction.model
{

import flash.events.Event;

[Bindable]
[RemoteClass(alias="com.apress.springrecipes.flex.auction.model.Item")]
public class Item
{
    private var _sellerEmail : String;
    private var _basePrice : Number;
    private var _threshold : Number;
    private var _sold : Date;
    private var _item : String;
    private var _description : String;
    private var _imageUrl : String;
    private var _id : Number;

    public function get sellerEmail() : String {
        return _sellerEmail;
    }

    public function set sellerEmail(value : String) : void {
        _sellerEmail = value;
    }

    public function get basePrice() : Number {
        return _basePrice;
    }

    public function set basePrice(value : Number) : void {
        _basePrice = value;
    }

    public function get threshold() : Number {
        return _threshold;
    }

    public function set threshold(value : Number) : void {
        _threshold = value;
    }

    public function get sold() : Date {
        return _sold;
    }

    public function set sold(value : Date) : void {
        _sold = value;
    }

    public function get item() : String {
        return _item;
    }

    public function set item(value : String) : void {
        _item = value;
    }

    public function get description() : String {
        return _description;
    }

    public function set description(value : String) : void {
        _description = value;
    }

    public function get imageUrl() : String {
        return _imageUrl;
    }

    public function set imageUrl(value : String) : void {
        _imageUrl = value;
    }

    public function get id() : Number {
        return _id;
    }

    public function set id(value : Number) : void {
        _id = value;
    }
}
}
