package sample;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.*;

public class Main extends Application {
    private static Connection con;

    public static Connection getCon() {
        return con;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("");
        primaryStage.getIcons().add(new Image("logo.png"));
        primaryStage.setScene(Controller.getSceneWin("sample.fxml"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://DESKTOP-A7U31QM\\SQLSERVER;databaseName=MiddleEarth;integratedSecurity=true;";
            con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException | ClassNotFoundException e){
            Controller.errorPrint(e.toString());
        }

        launch(args);

    }
}
