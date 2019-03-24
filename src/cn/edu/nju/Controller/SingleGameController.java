package cn.edu.nju.Controller;

import cn.edu.nju.Entity.TableVO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class SingleGameController implements EventHandler<KeyEvent>,Initializable {

    @FXML
    private SplitPane rootLayoutInGameBoard;

    @FXML
    private TableView<TableVO> playerTable;
    @FXML
    private TableColumn playerTableColumn1;
    @FXML
    private TableColumn playerTableColumn2;
    @FXML
    private ProgressBar myHp1;
    @FXML
    private ProgressBar myHp2;

    @FXML
    private ProgressBar emHp1;
    @FXML
    private ProgressBar emhp2;
    @FXML
    private Pane magic;


    private static final String RED_BAR ="red-bar";
    private static final String YELLOW_BAR ="yellow-bar";
    private static final String ORANGE_BAR ="orange-bar";
    private static final String GREEN_BAR ="green-bar";
    private static final String [] barColorStyleClasses = {RED_BAR,ORANGE_BAR,YELLOW_BAR,GREEN_BAR};



private  ObservableList<TableVO> data =
            FXCollections.observableArrayList(
                    new TableVO("敏捷值" ,"180"),new TableVO("幸运值" ,"120"),
                    new TableVO("力量值" ,"300")
                    ,new TableVO("洞察力" ,"500"));



    // key down esc back to login
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            //这里是取到Stage的具体代码
            Stage stage = (Stage) rootLayoutInGameBoard.getScene().getWindow();
//            stage.hide();


        }
    }


    @Override
     public void initialize(URL url, ResourceBundle rb) {
         showList();

        myHp1.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progress = newValue == null?0:newValue.doubleValue();
                if(progress< 0.2){
                    setBarStyleClass(myHp1,RED_BAR);
                } else if(progress<0.4){
                    setBarStyleClass(myHp1,ORANGE_BAR);
                } else if(progress <0.6){
                    setBarStyleClass(myHp1,YELLOW_BAR);
                } else {
                    setBarStyleClass(myHp1,GREEN_BAR);
                }
            }
            private void setBarStyleClass(ProgressBar bar, String barStyleClass) {
                bar.getStyleClass().removeAll(barColorStyleClasses);
                bar.getStyleClass().add(barStyleClass);
            }
        });
        myHp1.setProgress(0.99);
    }

    private void showList() {

      playerTableColumn1.setCellValueFactory(new PropertyValueFactory("key"));
      playerTableColumn2.setCellValueFactory(new PropertyValueFactory("value"));

      playerTable.setItems(data);

    }


    public void useMagic(MouseEvent mouseEvent) {
        Image image = new Image("cn/edu/nju/View/Layout/res/test.gif");
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        magic.getChildren().add(imageView);



    }
}
