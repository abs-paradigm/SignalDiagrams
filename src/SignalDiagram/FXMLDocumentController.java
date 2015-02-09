/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalDiagram;

import Renderer.Renderer;
import Signal.Signal;
import Signal.Signal.modulationType;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author Dom
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas m_canvas;
    @FXML
    private Pane m_pane;
    @FXML
    private CheckBox ChkBox_Show;
    @FXML
    private ComboBox cmbBox_Type;
    @FXML
    private SplitPane splitPane_Main;
    @FXML
    private TextField txtField_binaryInput;

    private Renderer m_renderer;
    private Signal m_signal;
    private GraphicsContext gc;
    private String TestMessage = "10100011110101010";

    private ObservableList<Point2D> observableList = FXCollections.observableArrayList();

    @FXML
    private void handleChkBoxAction(ActionEvent event) {
        System.out.println("You clicked me!");

        if (ChkBox_Show.isSelected()) {
            txtField_binaryInput.setEditable(true);
            txtField_binaryInput.setText(m_signal.getMessage());
            m_renderer.draw();
        } else {
            txtField_binaryInput.setText("");
            txtField_binaryInput.setEditable(false);
            m_renderer.resetCanvas();
        }
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        m_canvas.widthProperty().bind(
//                m_pane.widthProperty());
//        m_canvas.heightProperty().bind(
//                m_pane.heightProperty());

        m_signal = new Signal(TestMessage, modulationType.NRZ);
        m_renderer = new Renderer(m_canvas, m_signal);
        txtField_binaryInput.setText(m_signal.getMessage());

        cmbBox_Type.setItems(FXCollections.observableList(m_signal.getModulationTypes()));
        initListeners();

    }

    private void initListeners() {
        observableList = FXCollections.observableList(m_signal.getPoints());
        observableList.addListener(new ListChangeListener<Point2D>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Point2D> c) {

            }
        });

        txtField_binaryInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            m_signal.setMessage(newValue);
            observableList.setAll(m_signal.getPoints());
            m_renderer.draw();
        });

        cmbBox_Type.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                m_signal.setType(t1);
                m_renderer.draw();
            }
        });

        cmbBox_Type.getItems().addListener((ListChangeListener.Change c) -> {
            System.out.println("c" + c.toString());
        });
    }

}
