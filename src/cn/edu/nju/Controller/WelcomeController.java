package cn.edu.nju.Controller;

import cn.edu.nju.View.SingleGameBoard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable{

    @FXML
    private AnchorPane rootLayout;
    @FXML
    private ChoiceBox<String> choiceType;

    @FXML
    private TextField namefield;

    private  ObservableList<String> types =
            FXCollections.observableArrayList(
                    "选择你的职业","战士","火系法师","水系法师","雷系法师","土系法师","战士","弓箭手");


    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
    //network use netty
    public void beginDoubleGame(ActionEvent actionEvent) {
        System.out.println("begin double game");
    }

    //game enter
    public void beginSingleGame(ActionEvent actionEvent) {
        System.out.println("begin single game");
        //这里是取到Stage的具体代码
        Stage stage = (Stage) rootLayout.getScene().getWindow();

        //存name和type
        try {
            new SingleGameBoard().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.hide();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceType.setItems(types);
    }
}
