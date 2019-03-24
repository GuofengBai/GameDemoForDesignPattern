package cn.edu.nju.Controller;

import cn.edu.nju.Entity.Battle;
import cn.edu.nju.Entity.Character;
import cn.edu.nju.Entity.TableVO;
import cn.edu.nju.Item.Magic;
import cn.edu.nju.Variable.Variable;
import cn.edu.nju.View.Main;
import cn.edu.nju.View.SingleGameBoard;
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
import java.util.*;


public class SingleGameController implements EventHandler<KeyEvent>,Initializable,Observer {

    Stage stage;
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
    private ProgressBar emHp2;
    @FXML
    private Pane magic;


    private static final String RED_BAR ="red-bar";
    private static final String YELLOW_BAR ="yellow-bar";
    private static final String ORANGE_BAR ="orange-bar";
    private static final String GREEN_BAR ="green-bar";
    private static final String [] barColorStyleClasses = {RED_BAR,ORANGE_BAR,YELLOW_BAR,GREEN_BAR};

    Map<String,String> attributeMap = new HashMap<String,String>(){
        {
            put("def_chop","防御力——劈砍攻击") ;
            put("def_stab","防御力——刺击攻击") ;
            put("def_crush","防御力——钝击攻击") ;
            put("def_ice","防御力——冰属性魔法") ;
            put("def_flame","防御力——火属性魔法") ;
            put("def_earth","防御力——土属性魔法") ;
            put("def_lightning","防御力——雷属性魔法") ;
            put("accuracy","命中率") ;
            put("chop","劈砍攻击") ;
            put("stab","刺击攻击") ;
            put("crush","钝击攻击") ;

        }
    };


private  ObservableList<TableVO> data =
            FXCollections.observableArrayList();



    // key down esc back to login
    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            //这里是取到Stage的具体代码

//            stage.hide();


        }
    }


    @Override
     public void initialize(URL url, ResourceBundle rb) {






//        stage = (Stage) rootLayoutInGameBoard.getScene().getWindow();
        Variable.setInformationBoard(this);
        Battle.createBattle();

        showList();

        Iterator<Magic> list = Variable.getPlayer().getMagics();



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
        myHp1.setProgress(0.9);
    }

    private void showList() {

      playerTableColumn1.setCellValueFactory(new PropertyValueFactory("key"));
      playerTableColumn2.setCellValueFactory(new PropertyValueFactory("value"));

      playerTable.setItems(data);

    }


    public void useMagic(MouseEvent mouseEvent) {
//        Image image = new Image("cn/edu/nju/View/Layout/res/test.gif");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        magic.getChildren().add(imageView);

        Variable.getCurrentBattle().playerWeaponTurn();
        if(Variable.getCurrentBattle()==null){
            System.out.println("done");
//            if(Variable.getPlayer()==null){
//                try {
//                    new Main().start(new Stage());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                stage.close();
//            }

        }else{
            setHp();
        }



    }

    private void setHp(){
        Double em_hp =Variable.getCurrentBattle().getEnemy().getAttribute("hp")/Variable.getCurrentBattle().getEnemy().getAttribute("fullHp");
        Double my_hp =Variable.getPlayer().getAttribute("hp")/Variable.getPlayer().getAttribute("fullHp");



        Double em_mp =Variable.getCurrentBattle().getEnemy().getAttribute("mp")/Variable.getCurrentBattle().getEnemy().getAttribute("fullMp");
        Double my_mp =Variable.getPlayer().getAttribute("mp")/Variable.getPlayer().getAttribute("fullMp");

        Character p = Variable.getPlayer();
        myHp1.setProgress(my_hp);
        myHp2.setProgress(my_mp);

        emHp1.setProgress(em_hp);
        emHp2.setProgress(em_mp);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(arg.toString());
    }
}
