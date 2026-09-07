package pe.edu.upeu.algoritmosgui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MatrizTriangular extends Application {
    GridPane grit;
    Label lblInfo;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        Label titulo=new Label("MATRIZ DE LA FORMA 5");

        Label lblIam=new Label("TAMAÑO(n):");
        Spinner<Integer> spInico=new Spinner<>(2, 30, 5);

        Label lblNumI=new Label("NUMERO DE INICIO:");
        Spinner<Integer> spNumI=new Spinner<>(0, 30, 0);

        Button gMtriz=new Button("generar");


        HBox controles=new HBox(10, lblIam, spInico,lblNumI,spNumI,gMtriz);
        grit=new GridPane();
        grit.setHgap(3);
        grit.setVgap(3);
        gMtriz.setOnAction(event -> {
            matrizF5(spInico.getValue(), spNumI.getValue());
        });

        matrizF5(spInico.getValue(),spNumI.getValue());
        lblInfo=new Label("mostrar informacion");
        VBox root=new VBox(15, controles, grit,lblInfo);
        root.setPadding(new Insets(15));
        ScrollPane scrroll=new ScrollPane(root);

        primaryStage.setScene(new Scene(scrroll));
        primaryStage.setTitle("Ejemplos de Matriz GUI");
        primaryStage.show();

    }

    public void matrizF5(int tam, int numI){
        grit.getChildren().clear();
        for (int f=0; f<tam; f++){
            for(int c=tam-1; c>=tam-1-f; c--){
                Button cuadrito=new Button(String.valueOf(numI));
                cuadrito.setMinSize(48,42);
                cuadrito.setMinSize(48,42);
                grit.add(cuadrito, c, f);
                int ff=f;
                int cc=c;
                cuadrito.setOnAction(event -> {
                    lblInfo.setText("su valor es:"+cuadrito.getText());
                    
                });
                numI++;
            }
        }
    }
}
