package cn.edu.nju.Controller;

import cn.edu.nju.Entity.Character;
import cn.edu.nju.Factory.CharacterFactory;
import cn.edu.nju.Variable.Variable;
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
import java.util.HashMap;
import java.util.Map;
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
                    "assassin","knight","mage");



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

        Character player =CharacterFactory.getInstance().createCharacter(namefield.getText(),choiceType.getValue(),1);
        Variable.setPlayer(player);

        try {
            new SingleGameBoard().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        stage.close();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceType.setItems(types);
    }



}
