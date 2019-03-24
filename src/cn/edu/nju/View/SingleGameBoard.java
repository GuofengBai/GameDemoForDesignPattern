package cn.edu.nju.View;

import cn.edu.nju.Entity.TableVO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SingleGameBoard extends Application {


//    private static String type;
//    private static String name;
//
//    public SingleGameBoard(String name, String type){
//        SingleGameBoard.name = name;
//        SingleGameBoard.type = type;
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Layout/MainBoard.fxml"));
        primaryStage.setTitle("Hello Game");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();

    }

//    public static void main(String[] args) {
//        launch(args);
//    }


}
