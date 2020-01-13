import io.IO_Access;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        IO_Access ioa = new IO_Access();
        try {
//            ioa.writeToFile();
            ioa.readFromFile();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
