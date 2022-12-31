package com.example.mpratt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Scanner;

public class HelloApplication extends Application {

    int index = 0;
    Button add, remove;
    Boolean h;
    @Override
    public void start(Stage primaryStage) throws IOException {

        File file1 = new File("C:\\Users\\ggfor\\IdeaProjects\\Labs\\src\\dgree - Copy.csv");
        File file2 = new File("C:\\Users\\ggfor\\IdeaProjects\\Labs\\src\\Finshed.csv");
        File file3 = new File("C:\\Users\\ggfor\\IdeaProjects\\Labs\\src\\CourseOffering2.csv");

        Course1[] x = dgreeMaker(file1);
        FinishedCourses[] finshed = finishedMaker(file2);
        Student student = new Student("male", "freshemn" , finshed);
        Section[] sections = secArrayMaker(file3);
        String[] coursesNames=  registerableCoursesNames(x , finshed);
        Section[] approvedSec = registerableSection(sections , coursesNames);

            //Front-End

            StackPane sp = new StackPane();
            Pane top = new Pane();
            HBox hbox = new HBox(5);

            Button b1 = new Button("Start with a saved scdhule");
            HBox bHolder = new HBox(b1);


            Text text = new Text("Add sections to Basket");

            Font fon =  Font.font("MV Boli" , FontWeight.EXTRA_BOLD, 35);
            text.setFont(fon);
            hbox.getChildren().add(text);
            hbox.getChildren().add(bHolder);
            HBox.setMargin(text, new Insets(24 ,10, 12 ,5));
            hbox.setAlignment(Pos.TOP_CENTER);
            hbox.setPadding(new Insets(5));



            //center

            VBox column = new VBox(5);
            Section sec;

            HBox row;
            Text t;

            Font font =  Font.font("Verdana" , FontWeight.LIGHT, 15);


            Section[] chosen = new Section[approvedSec.length];
            HBox buttons ;

            for(int i =0 ; i < approvedSec.length ; i ++){
                // move the button to the right
                row = new HBox(15);
                buttons = new HBox(5);
                String info = approvedSec[i].toString();
                CheckBox cb = new CheckBox(info);
                t = new Text(info);
                t.setFont(font);
                add = new Button("  Add  ");
                remove = new Button("  Remove  ");
                buttons.getChildren().addAll(add , remove);

                final int indx =i;

                add.setOnAction(e->{
                    h= true;

                    for(int b = 0 ; b < chosen.length ; b++){
                        if(chosen[b] ==approvedSec[indx] ){
                            h = false;
                            break;
                        }
                    }
                    if(h)
                        chosen[index++] = approvedSec[indx];
                });


                remove.setOnAction(e->{

                    for(int r = 0 ; r < chosen.length; r ++){
                           if(chosen[r] == approvedSec[indx])
                               chosen[r] = null;

                    }


                });

                row.getChildren().addAll(t , buttons);
                column.getChildren().add(row);

            }


            b1.setOnAction(e->{
                for(int z = 0 ;z< chosen.length ; z ++){
                    if(chosen[z] != null)
                        System.out.println(chosen[z]);
                }
            });


            //bottom
            Button next = new Button("Next");

            HBox bottom = new HBox(next);
            bottom.setAlignment(Pos.BOTTOM_RIGHT);



            ScrollPane SP = new ScrollPane(column);





            //main
            BorderPane holder = new BorderPane();
        holder.setPadding(new Insets(5));


        holder.setTop(hbox);
        holder.setCenter(SP);
        holder.setPadding(new Insets(25));
        holder.setBottom(bottom);


        holder.setBackground(Background.fill(Color.SKYBLUE));

            //new right

        GridPane left = new GridPane();
        Text text2= new Text("  Schedule   : \n");
        Font font2 =  Font.font("MV Boli" , FontWeight.EXTRA_BOLD, 25);
        text2.setFont(font2);

        left.add(text2 ,0,0);
        left.add(new Text("7am\n") ,0,1);
        left.add(new Text(" 8am \n_____________") ,0,2);
        left.add(new Text(" 9am \n") ,0,3);
        left.add(new Text(" 10am \n") ,0,4);
        left.add(new Text(" 11am \n") ,0,5);
        left.add(new Text(" 12pm \n" ) ,0,6);
        left.add(new Text(" 1pm \n") ,0,7);
        left.add(new Text(" 2pm \n") ,0,8);
        left.add(new Text(" 3pm \n") ,0,8);
        left.add(new Text(" 4pm \n") ,0,10);

        left.add(new Text(" sunday ") ,1,0);
        left.add(new Text(" monady ") ,2,0);
        left.add(new Text(" TuesDay ") ,3,0);
        Rectangle re = new Rectangle(50 ,100);

        left.add(new Text("ICS 104") ,2 , 4);


        VBox cols = new VBox(3);
        HBox rows = new HBox(3);

        String[] times = {"7am" , "8am", "9am" , "10am", "11am" , "12pm" ,"1pm" , "2pm" , "3pm" , "4pm" , "5pm" };
        String[] days = {" Sunday " , " Monday " , " Tuesday " , " Wednesday " , " Thursday " };

        VBox h = new VBox(text2 , left);










        Button save = new Button("  Save Schedule ");
        HBox bottom2 = new HBox(save);


        next.setOnAction(e->{
                VBox right = createBasket(chosen);
                holder.setTop(new Text(""));
                holder.setCenter(new Text(""));
                holder.setRight(right);
                holder.setLeft(h);
                holder.setBottom(bottom2);
                holder.setBackground(Background.fill(Color.GOLD));
            });



            StackPane main = new StackPane(holder);
            Scene scene = new Scene(main , 750 , 650);

            primaryStage.setScene(scene);
            primaryStage.show();



    }




    public static int counter(File file) {
        try (Scanner x = new Scanner(file)) {
            x.nextLine();
            x.useDelimiter(",");
            int counter = 0;
            while (x.hasNextLine()) {
                counter++;
                x.nextLine();
            }
            return counter;
        }
        catch (IOException e) {
            return 0;
        }

    }

    public static Course1 [] dgreeMaker(File file) {
        try (Scanner dgree = new Scanner(file);){

            dgree.nextLine(); // to exclude the header
            int size = counter(file); // finds the size to make an array
            Course1 [] dgreeArray = new Course1[size];
            String [] array;
            for (int j = 0; j < size ;j++){
                array = dgree.nextLine().split(",");
                int i = 0 ;

                dgreeArray[j] = new Course1(array[i],array[++i] , array[++i], array[++i]);

            }
            return dgreeArray;

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static FinishedCourses [] finishedMaker(File file){
        try (Scanner finished = new Scanner(file);){
            int size = counter(file)+1;
            FinishedCourses[] finishedArray = new FinishedCourses[size];
            String array [];

            String par1,par2,par3; // parameters
            for (int j = 0; j < size ;j++) { //finishedReader
                array = finished.nextLine().split(",");
                for (int i = 0; i < array.length; i++) {

                    par1 = array[i];
                    par2 = array[++i];
                    par3 = array[++i];

                    finishedArray[j] = new FinishedCourses(par1, par2, par3);

                }
            }
            return finishedArray;


        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Section[] secArrayMaker(File file){
        try(Scanner sections = new Scanner(file);){
            sections.nextLine();
            int size = counter(file);
            Section[] sectionsArray = new Section[size];
            for (int j = 0; j < size ;j++) { //secMaker()
                String[] array = sections.nextLine().split(",");
                int i = 0;

                sectionsArray[j] = new Section(array[i], array[++i], array[++i] , array[++i] , array[++i] , array[++i] ,
                        array[++i] , array[++i] , array[++i] , array[++i] );

            }
            return sectionsArray;


        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public static boolean isFinished(String name ,FinishedCourses[] courses ){

        for(int i = 0 ; i < courses.length; i ++){
            if(courses[i].getCourseName().equals(name)) {
                return true;
            }
        }
        return false;

    }
    public static String[] registerableCoursesNames(Course1[] x , FinishedCourses[] finshed){

        String  names = "";

        for (int i = 0; i < x.length;i++){ //sections'Names()
            for (int j = 0;j < finshed.length;j++ ){
                if (x[i].getPreRequisite().equals(finshed[j].getCourseName()) &&
                        ! isFinished(x[i].getCourseName(), finshed)){
                    names = names + ',' + x[i].getCourseName();
                }
            }
            if(x[i].getPreRequisite().equals("None") &&  ! isFinished(x[i].getCourseName(), finshed)){
                names = names + ',' + x[i].getCourseName();
            }
        }


        names = names.substring(1 ); // to exclude the first comma
        return names.split(",");

    }

    public static Section secMaker(String name ){

        int i =0;
        String[] holder = name.split("    ");


        Section section;

        if(holder.length == 10){
            section = new Section(holder[i], holder[++i], holder[++i] , holder[++i] , holder[++i] , holder[++i] ,
                    holder[++i] , holder[++i] , holder[++i] , holder[++i] );}

        else {
            section  = new Section("1" , "2" , "3" , "4" , "5",
                    "6" , "7" , "8" , "9" , "10");
        }


        return  section;


    }

    public static Section[] registerableSection(Section[] sections , String[] coursesNames){
        int count = 0;

        String Name= "";

        for ( int i = 0 ; i < sections.length ; i++){
            for(int j = 0 ; j < coursesNames.length ; j ++){
                if(sections[i].getCourseSec().contains(coursesNames[j])) {
                    count++;
                    Name = Name + "@" + sections[i];
                }

            }

        }


        Name = Name.substring(1);
        String[] Names = Name.split("@");

        Section[] approvedSec = new Section[count];

        Section s1 = secMaker(Names[1]);
        for(int i = 0 ; i < Names.length; i ++){
            Section sec = secMaker(Names[i]);
            approvedSec[i] = sec ;
        }
        return approvedSec;

    }

    public static VBox createBasket(Section[] chosen ){
        Image image = new Image("C:\\Users\\ggfor\\IdeaProjects\\mPratt\\src\\main\\java\\com\\example\\mpratt\\R.jpg");
        ImageView holder = new ImageView(image);

        Text t = new Text("Selected section(Please click to add to the schedule )\n______________________________________________________");
        Font fon =  Font.font("MV Boli" , FontWeight.EXTRA_BOLD, 25);
        t.setFont(fon);

        VBox columns = new VBox(t);
        



        Button b;
        for(int i = 0; i < chosen.length ; i ++){
            if(chosen[i] != null){
                String info = (chosen[i].toString());
                b = new Button(info);
                columns.getChildren().add(b);
                b.setOnAction(e->{
                    System.out.println(info);
                });
            }

        }
        return columns;
    }


    public static void main(String[] args) {
        launch();
    }
}