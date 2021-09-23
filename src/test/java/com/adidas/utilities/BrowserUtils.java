package com.adidas.utilities;


import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    /*
    Method that will accept int  arg
    Wait for given second duration
    //1sec = 1000 milli seconds
    //1 * 1000 = 1000
        Thread.sleep(1000);
        Thread.sleep(2000);
        Thread.sleep(3000);
        BrowserUtils.sleep(1); --> 1 second
     */

    public static void sleep(int second) {

        second*=1000;

        try {

            Thread.sleep(second);

        } catch (InterruptedException e) {

            System.out.println("something happened in the sleep method");

        }



    }

    public static List<String> getElementsText (List<WebElement> list){
        List<String> elemTexts = new ArrayList<>();
        for (WebElement element : list) {
            elemTexts.add(element.getText());
        }
        return elemTexts;
    }

}
