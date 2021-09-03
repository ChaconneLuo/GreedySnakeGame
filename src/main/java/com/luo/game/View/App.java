package com.luo.game.View;

import com.luo.game.Controller.CountController;
import com.luo.game.Controller.Impl.CountControllerImpl;
import com.luo.game.Controller.Impl.LoginControllerImpl;
import com.luo.game.Controller.LoginController;
import com.luo.game.Base.Config;
import com.luo.game.Base.Show;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class App extends Show {

    @Override
    protected void after() {
        Image logo = new Image("/logo/logo.png");
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(600);
        imageView.setFitHeight(130);
        imageView.setLayoutX(85);
        imageView.setLayoutY(100);
        getGroup().getChildren().add(imageView);

        FlowPane flowPane = new FlowPane();

        HBox hBoxID = new HBox();
        Label labelID = new Label("用户:");
        labelID.setFont(Font.font("YouYuan", 16));
        TextField name = new TextField();
        name.setPrefSize(150, 30);
        name.setEditable(true);
        name.setPromptText("Type Your Name");
        name.setAlignment(Pos.CENTER_LEFT);
        name.setPrefColumnCount(11);
        name.setStyle(" -fx-background-radius: 20;");
        hBoxID.getChildren().addAll(labelID, name);

        HBox hBoxPassword = new HBox();
        Label labelPassword = new Label("密码:");
        labelPassword.setFont(Font.font("YouYuan", 16));
        PasswordField password = new PasswordField();
        password.setPrefSize(150, 30);
        password.setEditable(true);
        password.setPromptText("Type Your Password");
        password.setAlignment(Pos.CENTER_LEFT);
        password.setPrefColumnCount(11);
        password.setStyle(" -fx-background-radius: 20;");
        hBoxPassword.getChildren().addAll(labelPassword, password);

        Button LoginButton = new Button();
        LoginButton.setText("登录");
        LoginButton.setStyle("-fx-font: 18 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBoxID, hBoxPassword, LoginButton);
        vBox.setAlignment(Pos.CENTER);

        flowPane.getChildren().addAll(vBox);
        flowPane.setLayoutX(280);
        flowPane.setLayoutY(300);
        getGroup().getChildren().addAll(flowPane);
        Button startButton = new Button();
        startButton.setText("开始");
        startButton.setStyle("-fx-font: 18 arial; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: #ff4e4e; -fx-background-radius: 20; ");
        startButton.setLayoutX(360);
        startButton.setLayoutY(300);
        LoginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                LoginController loginController = new LoginControllerImpl();
                Boolean login = loginController.Login(name.getText(), password.getText());
                if (login.equals(true)) {
                    getGroup().getChildren().removeAll(flowPane);
                    getGroup().getChildren().addAll(startButton);
                }
            }
        });
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                getGroup().getChildren().removeAll(startButton,imageView);
                CountController countController = new CountControllerImpl();
                GamePage gameStartPage = new GamePage(Config.WIDTH,Config.HEIGHT,countController.getMaxCount(name.getText()));
                getGroup().getChildren().add(gameStartPage);
                gameStartPage.start();
                gameStartPage.setName(name.getText());
                gameStartPage.initEvents();
                getScene().setFill(Color.web("#ececf4"));
                gameStartPage.setGameState(1);
            }
        });
    }
}