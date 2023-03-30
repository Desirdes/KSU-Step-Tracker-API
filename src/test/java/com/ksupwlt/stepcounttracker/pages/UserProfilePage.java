package com.ksupwlt.stepcounttracker.pages;

import com.ksupwlt.stepcounttracker.annotation.PageScope;
import org.springframework.stereotype.Component;

@PageScope
public class UserProfilePage {

    public void ClickEdit(){
        System.out.println("Click on the Edit Button");
    }
}
