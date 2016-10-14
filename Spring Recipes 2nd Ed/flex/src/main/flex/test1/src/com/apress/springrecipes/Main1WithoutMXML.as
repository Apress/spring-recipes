package com.apress.springrecipes
{
import mx.controls.Button;
import mx.core.Application;
import mx.events.FlexEvent;
import mx.skins.halo.HaloBorder;
import mx.styles.CSSStyleDeclaration;
import mx.styles.StyleManager;

public class Main1WithoutMXML extends Application
{

    private function setupStyles() : void
    {

        var style : CSSStyleDeclaration = new CSSStyleDeclaration();
        style.setStyle("borderSkin", mx.skins.halo.HaloBorder);
        StyleManager.setStyleDeclaration("Application", style, false);

    }

    private function handleComplete(event : FlexEvent) : void
    {
        setupStyles();

        var button : Button = new Button();
        button.label = "labelBtn";
        addChild(button);

    }

    public function Main1WithoutMXML()
    {

        super();
        this.layout = "absolute";
        this.addEventListener(FlexEvent.CREATION_COMPLETE, handleComplete);

    }

}
}
