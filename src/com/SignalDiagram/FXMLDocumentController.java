/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram;

import com.SignalDiagram.Diagram.Diagram;
import com.SignalDiagram.Renderer.Renderer;
import com.SignalDiagram.Signal.DigitalSignal;
import com.SignalDiagram.Signal.DigitalSignal.modulationType;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

/**
 *
 * @author Dom
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas m_canvas;
    @FXML
    private ScrollPane m_scrollPane;
    @FXML
    private CheckBox ChkBox_Show;
    @FXML
    private ComboBox cmbBox_Type;
    @FXML
    private SplitPane splitPane_Main;
    @FXML
    private TextField txtField_binaryInput;

    private Renderer m_renderer;
    private DigitalSignal m_analogSignal;
    private GraphicsContext gc;
    private String TestMessage = "10100011110101010";
    private Diagram m_diagram;

    private ObservableList<Point2D> observableList = FXCollections.observableArrayList();

    @FXML
    private void handleChkBoxAction(ActionEvent event) {

        if (ChkBox_Show.isSelected()) {
            txtField_binaryInput.setEditable(true);
            txtField_binaryInput.setText(m_analogSignal.getMessage());
            m_renderer.draw();
        } else {
            txtField_binaryInput.setText("");
            txtField_binaryInput.setEditable(false);
            //m_renderer.resetCanvas();
        }
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        m_canvas.widthProperty().bind(
//                m_scrollPane.widthProperty());
//        m_canvas.heightProperty().bind(
//                m_scrollPane.heightProperty());

        m_analogSignal = new DigitalSignal(TestMessage, modulationType.NRZ);
        m_diagram = new Diagram("NRZ Diagram", 500, 300);
        m_renderer = new Renderer(m_canvas, m_analogSignal, m_diagram);

        cmbBox_Type.setItems(FXCollections.observableList(m_analogSignal.getModulationTypes()));
        txtField_binaryInput.setText(m_analogSignal.getMessage());

        initListeners();

    }

    private void initListeners() {
        observableList = FXCollections.observableList(m_analogSignal.getPoints());
        observableList.addListener(new ListChangeListener<Point2D>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends Point2D> c) {

            }
        });

        txtField_binaryInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            m_analogSignal.setMessage(newValue);
            observableList.setAll(m_analogSignal.getPoints());
            m_renderer.draw();
        });

        cmbBox_Type.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                m_analogSignal.setType(t1);
                m_renderer.draw();
            }
        });

        cmbBox_Type.getItems().addListener((ListChangeListener.Change c) -> {
            System.out.println("c" + c.toString());
        });
    }

}
