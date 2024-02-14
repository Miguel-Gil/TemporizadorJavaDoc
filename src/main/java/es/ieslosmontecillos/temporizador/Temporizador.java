package es.ieslosmontecillos.temporizador;

import java.io.IOException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Esta clase es un simple temporizador. <br>
 * Es decir cuenta hacia atras desde el numero que se le da al ser llamada. <br>
 * Al finalizar la cuenta atras devueve un evento para la gestion personalizada del usuario.
 * @author Miguel
 * @version 1.0
 * @since 17.0
 */

public final class Temporizador extends VBox{
    //Objetos del fxml
    @FXML private Label lbl;
    private Integer time;

    /**
     * Contructor de clase especificando el tiempo desde el que contar hacia atras
     * @param time tipo Integer, referencia al tiempo inicial del temporizador en segundos
     */
    //Constructor
    public Temporizador(Integer time){
        this.setTime(time);
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Temporizador.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        
        lbl.setText(time.toString());
        
        Timeline tl = new Timeline();
        
        tl.setCycleCount(getTime());
        
        KeyFrame kf =new KeyFrame(Duration.seconds(1), e -> {
           setTime(getTime()-1);
           lbl.setText(getTime().toString());
        });
        
        tl.getKeyFrames().addAll(kf);
        
        //Pasamos esta propiedad desde el constructor
        tl.setOnFinished((ActionEvent event) -> {
            onFinishedProperty().get().handle(event);
        });

        tl.play();
    }

    /**
     * Constructor de clase vacio
     */
    //Constructor vacio
    public Temporizador(){}


    //Getters y setters

    /**
     * Devuelve el valor que se le asignara a la variable time. <br>
     * el cual corresponde al valor del temporizador en segundos usando una variable tipo int
     *
     * @return time
     */
    public Integer getTime() {
        return time;
    }

    /**
     * Recoge el valor que se le asignara a la variable time. <br>
     * el cual corresponde al valor inicial del temporizador en segundos usando una variable tipo int
     *
     * @param time tipo Integer
     */
    private void setTime(Integer time) {
        this.time = time;
    }
    
    //Creamos un objeto privado de un Action Event
    private ObjectProperty<EventHandler<ActionEvent>> propertyOnFinished
            = new SimpleObjectProperty<EventHandler<ActionEvent>>();
    
    //Creamos un metodo para recuperar ese objeto privado de tipo object property action event
    public final ObjectProperty<EventHandler<ActionEvent>> onFinishedProperty() {
        return propertyOnFinished;
    }
    
    //Creamos un setter de esta propiedad
    public final void setOnFinished(EventHandler<ActionEvent> handler) {
        propertyOnFinished.set(handler);
    }
    
    //Creamos un getter
    public final EventHandler<ActionEvent> getOnFinished() {
        return propertyOnFinished.get();
    }
    
}
