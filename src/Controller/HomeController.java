package Controller;

import DBConnecter.DBConnecter;

public class HomeController {

    public void initialize(){
        DBConnecter dbConnecter = new DBConnecter();
        dbConnecter.connect();
    }
}
