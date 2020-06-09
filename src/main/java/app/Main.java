package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
            MyApp m = MyApp.getInstance();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
