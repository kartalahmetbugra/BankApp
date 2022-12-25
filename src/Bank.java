import java.io.IOException;
import java.sql.*;

import java.util.Scanner;

public class Bank {
    public static final String ANSI_MOR = "\u001B[35m";

    public static void main(String[] args) throws SQLException, IOException {
        Entrance entrance = new Entrance(); //Entrance classından bir entrance nesnesi oluşturduk
        entrance.entrance();// entrance metodunu çağırdık
    }
}
