import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class M_Player extends Application {
    
    
    Media media;
    MediaPlayer mpl;
    MediaView mv;
    FileChooser fc;
    File file; 
    
    
    @Override
    public void start(Stage stage) throws FileNotFoundException {
        
        try{
           MenuBar mb = new MenuBar();
           mb.setStyle("-fx-background-color: #383838");  
           Menu m1 = new Menu("File");
           m1.setStyle("-fx-text-base-color: white");
           Menu m2 = new Menu("Open");
           m2.setStyle("-fx-text-base-color: black");
           Menu m3 = new Menu("Quit");
           m3.setStyle("-fx-text-base-color: black");
           m3.setOnAction((event) -> { stage.close(); });
           m1.getItems().addAll(m2,m3);
           mb.getMenus().add(m1);
           
                ToolBar tb = new ToolBar();
                Slider vs = new Slider(0.0, 1.0, 0.0);
                BorderPane vp = new BorderPane();
                fc = new FileChooser();
                Slider sl = new Slider();
                sl.setStyle("-fx-control-inner-background: yellow;");
                vs.setStyle("-fx-control-inner-background: yellow;");
                
                
                FileChooser.ExtensionFilter ext = new FileChooser.ExtensionFilter("mp4 & mp3","*.mp4","*.mp3");
                fc.getExtensionFilters().add(ext);
                
                Label l = new Label();
               m2.setOnAction((event) -> 
                {
                    File file = fc.showOpenDialog(stage);
                    media = new Media(file.toURI().toString());
                    mpl = new MediaPlayer(media);
                    mv = new MediaView(mpl);
                    mv.setFitWidth(1100);
                    vp.setCenter(mv);
                    stage.setTitle("" + file.getName());
                    
                    //SliderBar 
                    Double time = mpl.getTotalDuration().toSeconds();
                    mpl.currentTimeProperty().addListener((ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue)
                    -> { 
                    sl.setValue(newValue.toSeconds());
                    });
                    sl.setOnMouseClicked((MouseEvent mouseEvent)
                    -> {
                    mpl.seek(Duration.seconds(sl.getValue()));
                    });
                    sl.maxProperty().bind(Bindings.createDoubleBinding(() -> mpl.getTotalDuration().toSeconds(), mpl.totalDurationProperty()));
                    
                
                    if(file!=null)
                    {
                        mpl.play();
                    }
                });
                   
                
                

                fc.setTitle("Open file");
                

                //Value of Volumebar
                vs.setValue(1);
                vs.valueProperty().addListener((Observable observable)
                        -> {
                    mpl.setVolume(vs.getValue());
                });
                
                
                //Applying Actions On Buttons
                
                
                vs.setTranslateX(1000);
                Image i1 = new Image("images\\play.png");
                ImageView b1 = new ImageView(i1);
                
                b1.setStyle("-fx-border-color: white");
                b1.setTranslateX(50);
                b1.setOnMousePressed((event)-> 
                {
                    mpl.play();
                });
                 
                
                Image i2 = new Image("images\\pause.png");
                ImageView b2 = new ImageView(i2);
                b2.setTranslateX(200);
                b2.setOnMousePressed((event)-> 
                {
                    mpl.pause();
                });

                
                Image i3 = new Image("images\\stop.png");
                ImageView b3 = new ImageView(i3);
                b3.setTranslateX(350);
                b3.setOnMousePressed((event)-> 
                {
                    mpl.stop();
                });
                
                
                Image i4 = new Image("images\\forword.png");
                ImageView b4 = new ImageView(i4);
                b4.setTranslateX(500);
                b4.setOnMousePressed((event)-> 
                {
                    mpl.setRate(2);
                });

                
                Image i5 = new Image("images\\normal.png");
                ImageView b5 = new ImageView(i5);
                b5.setTranslateX(650);
                b5.setOnMousePressed((event)-> 
                {
                    mpl.setRate(1);
                });

                
                Image i6 = new Image("images\\back.png");
                ImageView b6 = new ImageView(i6);
                b6.setTranslateX(800);
                b6.setOnMousePressed((event)-> 
                {
                    mpl.setRate(0.5);
                });
                

                


                //Adding ToolBar
                tb.setStyle("-fx-base: #383838");
                tb.getItems().addAll(b1, b2, b3, b4, b5, b6, vs,l);
                DropShadow shadow = new DropShadow();

                b1.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {b1.setEffect(shadow);});
                b2.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {b2.setEffect(shadow);});
                b3.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {b3.setEffect(shadow);});
                b4.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {b4.setEffect(shadow);});
                b5.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {b5.setEffect(shadow);});
                b6.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {b6.setEffect(shadow);});

                b1.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {b1.setEffect(null);});
                b2.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {b2.setEffect(null);});
                b3.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {b3.setEffect(null);});
                b4.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {b4.setEffect(null);});
                b5.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {b5.setEffect(null);});
                b6.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {b6.setEffect(null);});

                

                

                VBox vb = new VBox();
                vb.getChildren().addAll(sl, tb);

                vp.setStyle("-fx-background : cornsilk");
                
                vp.setStyle("-fx-background-color: black");
                vp.setBottom(vb);
                vp.setTop(mb);

                Scene scene = new Scene(vp, 1366, 700);
                scene.setOnKeyPressed((KeyEvent event) -> {
                    if(event.getCode() == KeyCode.F)
                    {
                        
                        stage.setFullScreen(true);
                    }
                });
                stage.setTitle("MEDIA Player");
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNIFIED);
                stage.show();
            
        
       
        
        
        }catch(NullPointerException e)
        {
            System.out.println(e);
        }
    }

    
    
    public static void main(String[] args) 
    {
        launch(args);
    }

}
