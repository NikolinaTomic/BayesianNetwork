package app;

import view.MainFrame;

import java.sql.SQLException;

public class MyApp {
    public static String username = "root";
    public static String password = "password";
    private static MyApp instance = null;

    public static MyApp getInstance() {
        if (instance == null) {
            instance = new MyApp();
            instance.initialise();
        }
        return instance;
    }

    public void initialise() {
        try {
            MainFrame mn = new MainFrame();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
