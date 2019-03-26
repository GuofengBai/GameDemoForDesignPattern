package cn.edu.nju.Controller;

import cn.edu.nju.Entity.Battle;
import cn.edu.nju.Entity.Character;
import cn.edu.nju.Entity.TableVO;
import cn.edu.nju.Item.CompositeMagic;
import cn.edu.nju.Item.Magic;
import cn.edu.nju.Variable.Variable;
import cn.edu.nju.View.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URL;
import java.util.*;


public class SingleGameController implements EventHandler<KeyEvent>,Initializable,Observer {

    Stage stage ;
    @FXML
    private SplitPane rootLayoutInGameBoard;
    @FXML
    private BorderPane battle;
    @FXML
    private HBox leftBoard;
    @FXML
    private HBox rightBoard;


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
    private ImageView bossImage;
    @FXML
    private ProgressBar emHp1;
    @FXML
    private ProgressBar emHp2;
    @FXML
    private Pane magic;
    @FXML
    private Button beginButton;
    @FXML
    private Label myName;

    @FXML
    private ImageView gifImage;
    @FXML
    private ImageView equipImage;
    @FXML
    private ImageView amerImage;
    @FXML
    private Label battleInformation;
    @FXML
    private Label myLevel;
    @FXML
    private VBox magicboard;
    @FXML
    private HBox useMagicBoard;
    @FXML
    private ImageView playerImage;
    @FXML
    private ImageView playerBattleImage;


    private static final String RED_BAR ="red-bar";
    private static final String YELLOW_BAR ="yellow-bar";
    private static final String ORANGE_BAR ="orange-bar";
    private static final String GREEN_BAR ="green-bar";
    private static final String [] barColorStyleClasses = {RED_BAR,ORANGE_BAR,YELLOW_BAR,GREEN_BAR};
    private static final List<String> bossImageList = new ArrayList<String>(){{
                add( "/cn/edu/nju/View/Layout/res/boss1.jpg");
                add( "/cn/edu/nju/View/Layout/res/boss2.jpg");
                add( "/cn/edu/nju/View/Layout/res/boss3.png");
                add( "/cn/edu/nju/View/Layout/res/boss4.jpg");
                add( "/cn/edu/nju/View/Layout/res/boss5.png");
                add( "/cn/edu/nju/View/Layout/res/boss6.png");
                add( "/cn/edu/nju/View/Layout/res/boss7.png");
                add( "/cn/edu/nju/View/Layout/res/boss8.jpg");
    }};
    private static final List<String> playerImageList = new ArrayList<String>(){{
        add( "/cn/edu/nju/View/Layout/res/player1.png");
        add( "/cn/edu/nju/View/Layout/res/player2.png");
        add( "/cn/edu/nju/View/Layout/res/player3.png");
        add( "/cn/edu/nju/View/Layout/res/player4.png");
        add( "/cn/edu/nju/View/Layout/res/player5.png");
    }

    };





    ArrayList<String> battle_Information =new ArrayList<String>();
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
        Random r = new Random();
        int i = r.nextInt(5);
        playerImage.setImage(new Image(playerImageList.get(i)));
        playerBattleImage.setImage(new Image(playerImageList.get(i)));
        gifImage.setVisible(false);
        myName.setText(Variable.getPlayer().getName());
        leftBoard.setVisible(false);
        rightBoard.setVisible(false);
        bossImage.setVisible(false);
        BackgroundImage myBI= new BackgroundImage(new Image("/cn/edu/nju/View/Layout/res/back.jpg",800,550,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        battle.setBackground(new Background(myBI));

        Variable.setInformationBoard(this);
        showMagic();

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
        myLevel.setText("当前等级:"+Variable.getPlayer().getLevel()+"（点击升级）"+"\n"+"当前经验:"+Variable.getPlayer().getExperience()+"\n"+"当前金币:"+Variable.getPlayer().getMoney());
        List<TableVO> list = Variable.getPlayer().getDecoratedAttributesView();
        data.clear();
        for (TableVO v:list ) {
            data.add(v);

        }
      playerTableColumn1.setCellValueFactory(new PropertyValueFactory("key"));
      playerTableColumn2.setCellValueFactory(new PropertyValueFactory("value"));

      playerTable.setItems(data);


      Tooltip tooltip = new Tooltip();
      tooltip.setText("当前装备\n"+Variable.getPlayer().getWeapon().getAttributes().toString());

      Tooltip.install(amerImage, tooltip);

      Tooltip tooltip2 = new Tooltip();
      tooltip2.setText("当前武器\n"+Variable.getPlayer().getArmour().getAttributes().toString());
      Tooltip.install(equipImage, tooltip2);


        Iterator<Magic> itmes = Variable.getPlayer().getMagics();
        int index = 0;
        magicboard.getChildren().clear();
        for (Iterator<Magic> it = itmes; it.hasNext(); ) {
            Magic c = it.next();

            Tooltip tooltip3 = new Tooltip();
            tooltip3.setText("名称："+c.getName()+"\n"+"伤害："+c.getValue()+"\n"+"消耗HP："+c.getConsumedMp()+"\n"+"当前等级："+c.getLevel());
            ImageView newMagic = new ImageView();
            newMagic.setFitHeight(50);
            newMagic.setFitWidth(50);

            Image image = getImage(c);


            newMagic.setImage(image);
            Tooltip.install(newMagic, tooltip3);
            int finalIndex = index;
            newMagic.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    magicLevelUp(finalIndex);
                }
            });
            magicboard.getChildren().add(newMagic);
            index++;

        }




    }
    private void showMagic() {

        useMagicBoard.getChildren().clear();
        Tooltip tooltip3 = new Tooltip();
        tooltip3.setText("名称：普通攻击"+"\n"+"伤害：1点"+"\n"+"消耗HP：无"+"\n");
        ImageView newMagic = new ImageView();
        newMagic.setFitHeight(50);
        newMagic.setFitWidth(50);
        Image image;
        image = new Image("cn/edu/nju/View/Layout/res/equip5.png");
        newMagic.setImage(image);
        Tooltip.install(newMagic, tooltip3);
        newMagic.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                useMagic(-1);
            }
        });
        useMagicBoard.getChildren().add(newMagic);



        Iterator<Magic> itmes = Variable.getPlayer().getMagics();
        int index = 0;
        for (Iterator<Magic> it = itmes; it.hasNext(); ) {
            Magic c = it.next();

            Tooltip tooltip4= new Tooltip();
            tooltip4.setText("名称："+c.getName()+"\n"+"伤害："+c.getValue()+"\n"+"消耗HP："+c.getConsumedMp()+"\n"+"当前等级："+c.getLevel());
             newMagic = new ImageView();
            newMagic.setFitHeight(50);
            newMagic.setFitWidth(50);
            image = getImage(c);
            newMagic.setImage(image);
            Tooltip.install(newMagic, tooltip4);
            int finalIndex = index;
            newMagic.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    useMagic(finalIndex);
                }
            });
            useMagicBoard.getChildren().add(newMagic);
            index++;
        }
    }

    private Image getImage(Magic c) {
        Image image;
        if(c.getName().contains("+")){
            image = new Image("cn/edu/nju/View/Layout/res/magic-all.png");
        }else if(c.getName().contains("冰系")){
            image = new Image("cn/edu/nju/View/Layout/res/magic-ice.png");
        }else if (c.getName().contains("火系")){
            image = new Image("cn/edu/nju/View/Layout/res/magic-fire.png");
        }else if (c.getName().contains("雷系")){
            image = new Image("cn/edu/nju/View/Layout/res/magic-light.png");
        }else if (c.getName().contains("土系")){
            image = new Image("cn/edu/nju/View/Layout/res/magic-earth.png");
        }else{
            image = new Image("cn/edu/nju/View/Layout/res/magic-all.png");
        }
        return image;
    }


    public void useMagic(int index) {
//        Image image = new Image("cn/edu/nju/View/Layout/res/test.gif");
//        ImageView imageView = new ImageView();
//        imageView.setImage(image);
//        magic.getChildren().add(imageView);

//        System.out.println(arg.toString());
       gifImage.setVisible(true);
//
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                gifImage.setVisible(false);
            }
        }, 2000 , 1000);



        //判断是哪个按钮

        if(index == -1){
            Variable.getCurrentBattle().playerWeaponTurn();
        }
        else{
            Variable.getCurrentBattle().playerMagicTurn(index);
        }
//


        if(Variable.getCurrentBattle()==null){


            System.out.println("done");
            stage = (Stage) rootLayoutInGameBoard.getScene().getWindow();
            if(Variable.getPlayer()==null){

                myHp1.setProgress(0);


            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("可惜的一局游戏");
//            alert.setHeaderText("很遗憾，"+Variable.getPlayer().getName()+"");
            alert.setContentText("你在对战中被杀死，游戏结束，请重新开始");

            alert.showAndWait();
                try {
                    new Main().start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                stage.close();
            }else{

                emHp1.setProgress(0);

                System.out.println("done");
                stage = (Stage) rootLayoutInGameBoard.getScene().getWindow();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("你结束了这场战斗");
//            alert.setHeaderText("很遗憾，"+Variable.getPlayer().getName()+"");
                alert.setContentText("获得金钱1，经验1，继续探索哦");
                alert.showAndWait();
                showList();
                beginStay();
            }

        }else{
            setHp();
        }



    }

    private void beginStay() {
        useMagicBoard.setVisible(false);
        beginButton.setVisible(true);
        leftBoard.setVisible(false);
        rightBoard.setVisible(false);

        BackgroundImage myBI= new BackgroundImage(new Image("/cn/edu/nju/View/Layout/res/back.jpg",800,550,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        battle.setBackground(new Background(myBI));
        bossImage.setVisible(false);


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
        battle_Information.add(arg.toString());

        battleInformation.setWrapText(true);
        StringBuilder text = new StringBuilder();
        int i =0;
        if(battle_Information.size()<=3){
            for(String x:battle_Information){
                text.append(x);
            }
        }else{
            while (i<3){
                text.append(battle_Information.get(battle_Information.size() - 1 - i));
                i++;
            }
        }


        battleInformation.setText(text.toString());

    }


    public void beginBattle(){
        useMagicBoard.setVisible(true);
        beginButton.setVisible(false);
        leftBoard.setVisible(true);
        rightBoard.setVisible(true);

        BackgroundImage myBI= new BackgroundImage(new Image("/cn/edu/nju/View/Layout/res/game_black.jpg",800,550,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        battle.setBackground(new Background(myBI));

        Random r = new Random();
        Image boss = new Image(bossImageList.get(r.nextInt(8)));
        bossImage.setImage(boss);
        bossImage.setVisible(true);

        Battle.createBattle();
        battle_Information = new ArrayList<>();
        setHp();
    }


    public void levelUp(MouseEvent mouseEvent) {
        System.out.println("ets");
        boolean can = Variable.getPlayer().canLevelUp();
        if(can){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"累计经验达到"+10*Math.pow(1.2, Variable.getPlayer().getLevel())+"，是否确认升级?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                    Variable.getPlayer().levelUp();
                    showList();
            }
            if(result.isPresent() && result.get() == ButtonType.CANCEL){

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("可惜的一局游戏");
            alert.setHeaderText("很遗憾，"+Variable.getPlayer().getName()+",您的经验不足以升级，请耐心打怪=。=");
//            alert.setContentText("你在对战中被杀死，游戏结束，请重新开始");

            alert.showAndWait();
        }
    }


    public void equipLevelUp(MouseEvent mouseEvent) {
        System.out.println("ets");
        boolean can = Variable.getPlayer().canArmourLevelUp();
        if(can){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"是否确认花费"+10*Math.pow(1.2, Variable.getPlayer().getLevel())+"金币升级装备?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                Variable.getPlayer().armourLevelUp();
                showList();
            }
            if(result.isPresent() && result.get() == ButtonType.CANCEL){

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("可惜的一局游戏");
            alert.setHeaderText("很遗憾，"+Variable.getPlayer().getName()+",您的金钱不足以升级，请耐心打怪=。=");
//            alert.setContentText("你在对战中被杀死，游戏结束，请重新开始");

            alert.showAndWait();
        }

    }

    public void amerLevelUp(MouseEvent mouseEvent) {
        System.out.println("ets");
        boolean can = Variable.getPlayer().canWeaponLevelUp();
        if(can){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"是否确认花费"+10*Math.pow(1.2, Variable.getPlayer().getLevel())+"金币升级武器?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                Variable.getPlayer().weaponLevelUp();
                showList();
            }
            if(result.isPresent() && result.get() == ButtonType.CANCEL){

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("可惜的一局游戏");
            alert.setHeaderText("很遗憾，"+Variable.getPlayer().getName()+",您的金钱不足以升级，请耐心打怪=。=");
//            alert.setContentText("你在对战中被杀死，游戏结束，请重新开始");

            alert.showAndWait();
        }
    }
    public void magicLevelUp(int index) {
        System.out.println("ets");
        boolean can = Variable.getPlayer().canMagicLevelUp(index);
        if(can){
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,"是否确认花费"+10*Math.pow(1.2, Variable.getPlayer().getLevel())+"金币升级技能?");
            Optional<ButtonType> result = confirmation.showAndWait();
            if(result.isPresent() && result.get() == ButtonType.OK){
                Variable.getPlayer().magicLevelUp(index);
                showList();
                showMagic();
            }
            if(result.isPresent() && result.get() == ButtonType.CANCEL){

            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("可惜的一局游戏");
            alert.setHeaderText("很遗憾，"+Variable.getPlayer().getName()+",您的金钱不足以升级，请耐心打怪=。=");
//            alert.setContentText("你在对战中被杀死，游戏结束，请重新开始");

            alert.showAndWait();
        }
    }

    public void combineMagic(MouseEvent mouseEvent) {

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("组合技能");
        dialog.setHeaderText("请选择技能进行组合");

        // Set the icon (must be included in the project).
//        dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        // Set the button types.
//        ButtonType loginButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

// Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ChoiceBox<String> username = new ChoiceBox<>();

        ChoiceBox<String> password = new ChoiceBox<>();
        ChoiceBox<String> magic3 = new ChoiceBox<>();
        ObservableList<String> magics = FXCollections.observableArrayList();
        Iterator<Magic> reallist = Variable.getPlayer().getMagics();
        for (Iterator<Magic> it = reallist; it.hasNext(); ) {
            Magic i = it.next();
            magics.add(i.getName());
        }
        username.setItems(magics);
        password.setItems(magics);
//        magic3 .setItems(magics);
        grid.add(new Label("技能1:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("技能2:"), 0, 1);
        grid.add(password, 1, 1);
//        grid.add(new Label("技能3:"), 0, 1);
//        grid.add(magic3, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(ButtonType.OK);


        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                return new Pair<>(username.getValue(), password.getValue());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("技能1=" + usernamePassword.getKey() + ", 技能2=" + usernamePassword.getValue());
            CompositeMagic tmp = new CompositeMagic();
            tmp.add(Variable.getPlayer().getMagicByName(usernamePassword.getKey()));
            tmp.add(Variable.getPlayer().getMagicByName(usernamePassword.getValue()));

            Variable.getPlayer().addMagic(tmp);
            showList();
            showMagic();
        });




    }
}
