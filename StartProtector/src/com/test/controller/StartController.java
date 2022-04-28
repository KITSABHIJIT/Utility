package com.test.controller;

import com.test.exceptions.ServiceException;

public class StartController
{
    public static void main(final String... args) {
        try {
            ProtectRevealUtil.initialize();
            Controller.displayData();
        }
        catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}