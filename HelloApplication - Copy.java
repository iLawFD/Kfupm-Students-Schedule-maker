package com.example.icscourse;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    int currentIndx= 0;
    Image image1 = new Image("C:\\Users\\ggfor\\IdeaProjects\\108Course\\src\\main\\java\\assets\\R.jpg");
    Image image2 = new Image("C:\\Users\\ggfor\\IdeaProjects\\108Course\\src\\main\\java\\assets\\B.jpg");
    Image image3 = new Image("C:\\Users\\ggfor\\IdeaProjects\\108Course\\src\\main\\java\\assets\\S.jpg");
    Image images[] = {image1, image2 , image3};

    ImageView holder = new ImageView(images[currentIndx]);
    @Override
    public void start(Stage stage) throws IOException {



        Button b1 = new Button("Next image");
        Button b2 = new Button("Previous image");
        BorderPane p = new BorderPane(holder);


        HBox bottom = new HBox(b1,b2);
        bottom.setPadding(new Insets(5));

        p.setBottom( bottom);

        b1.setOnAction(new handler());
        b2.setOnAction(new handler2());
        Scene scene = new Scene(p);
        stage.setTitle("Lab15");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    private class handler implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){


            currentIndx++;
            if(currentIndx != images.length-1)
                holder.setImage(images[currentIndx+1]);
        }
    }
    private class handler2 implements EventHandler<ActionEvent>{
        public void handle(ActionEvent e){
            currentIndx --;


            if(currentIndx != 0)
                holder.setImage(images[currentIndx-1]);
        }
    }
}