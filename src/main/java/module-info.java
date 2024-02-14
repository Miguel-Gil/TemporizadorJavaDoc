module es.ieslosmontecillos.temporizador {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.ieslosmontecillos.temporizador to javafx.fxml;
    exports es.ieslosmontecillos.temporizador;
}