package main;

import gui.CheckoutWindow;
import gui.DatabaseWindow;
import services.CasaDeMarcat;

public class Main {
    public static void main(String[] args) {


        CasaDeMarcat CASA = new CasaDeMarcat();
        CASA.init();

        new DatabaseWindow(CASA);
        new CheckoutWindow(CASA);
        /*
        CasaDeMarcat CASA = new CasaDeMarcat();
        //CASA.showDatabase();
        CASA.init();
        CASA.showDatabase();
        CASA.newClient();
        CASA.scanProductById();
        CASA.scanProductById();
        CASA.scanProductById();
        CASA.scanProductById();
        CASA.removeProductById();
        CASA.showCart();
        CASA.totalPrice();
        CASA.pay();

        CASA.newClient();
        CASA.scanProductById();
        CASA.scanProductById();
        CASA.scanProductById();
        CASA.showCart();
        CASA.totalPrice();
        CASA.pay();*/
    }
}

