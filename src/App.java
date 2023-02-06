import database.MainConnector;
import pages.*;

public class App {
    public static void main(String[] args) throws Exception {
        new Login();
        new MainConnector(null);
    }
}
