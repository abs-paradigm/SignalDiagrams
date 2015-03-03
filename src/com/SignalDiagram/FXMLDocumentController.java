/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram;

import com.SignalDiagram.Signal.AbstractSignal;
import com.SignalDiagram.Signal.AnalogSignal;
import com.SignalDiagram.Signal.DigitalSignal;
import com.SignalDiagram.Signal.DigitalSignal.modulationType;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import org.apache.commons.lang3.text.WordUtils;

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
    private ComboBox cmbBox_DigitalType;
    @FXML
    private ComboBox cmbBox_AnalogType;
    @FXML
    private SplitPane splitPane_Main;
    @FXML
    private TextField txtField_binaryInput;
    @FXML
    private LineChart<Double, Double> m_digitalChart;
    @FXML
    private LineChart<Double, Double> m_analogChart;

//    private NumberAxis xAxis = new NumberAxis();
//    private NumberAxis yAxis = new NumberAxis();

    private List<Point2D> m_digitalPoint;
    private LineChart.Series<Double, Double> m_digitalSerie;
    private LineChart.Series<Double, Double> m_analogSerie;

    private DigitalSignal m_digitalSignal;
    private AnalogSignal m_analogSignal;
    private String exampleMessage = "10100011110101010";

    private ObservableList<Point2D> m_observableList = FXCollections.observableArrayList();
    private ObservableList<XYChart.Series<Double, Double>> m_digitalChartData;
    private ObservableList<XYChart.Series<Double, Double>> m_analogChartData;

    @FXML
    private void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        m_digitalSignal = new DigitalSignal(exampleMessage, modulationType.NRZ_L);
        m_analogSignal = new AnalogSignal(exampleMessage, AnalogSignal.analogType.ANALOG);

        m_digitalChartData = FXCollections.observableArrayList();
        m_analogChartData = FXCollections.observableArrayList();

        m_digitalSerie = new LineChart.Series<>();
        m_digitalSerie = updatePoints(m_digitalSignal.getPoints());

        m_analogSerie = new LineChart.Series<>();
        m_analogSerie = updatePoints(m_analogSignal.getPoints());

        m_digitalChartData.add(m_digitalSerie);
        m_digitalChart.setData(m_digitalChartData);

        m_analogChartData.add(m_analogSerie);
        m_analogChart.setData(m_analogChartData);

        cmbBox_DigitalType.setItems(FXCollections.observableList(m_digitalSignal.getModulationTypes()));
        txtField_binaryInput.setText(m_digitalSignal.getMessage());

        initListeners();
        cmbBox_DigitalType.valueProperty().set("nrz-m");
    }

    private void initListeners() {

        txtField_binaryInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            m_digitalSignal.setMessage(newValue);
            m_analogSignal.setMessage(newValue);

            updateChart(m_digitalSerie, m_digitalChartData, m_digitalSignal);
            updateChart(m_analogSerie, m_analogChartData, m_analogSignal);
        });

        cmbBox_DigitalType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldType, String newType) {
                m_digitalChart.setTitle(WordUtils.capitalize(newType));
                m_digitalSignal.setType(newType);

                updateChart(m_digitalSerie, m_digitalChartData, m_digitalSignal);
            }
        });

        cmbBox_AnalogType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldType, String newType) {
                //m_analogChart.setTitle(WordUtils.capitalize(newType));
                //m_analogSignal.setType(newType);

                updateChart(m_analogSerie, m_analogChartData, m_analogSignal);
            }
        });

        cmbBox_DigitalType.getItems().addListener((ListChangeListener.Change c) -> {
            System.out.println("c" + c.toString());
        });

        m_scrollPane.widthProperty().addListener(evt
                -> {
                    m_digitalChart.setPrefWidth(m_scrollPane.getWidth());
                    m_analogChart.setPrefWidth(m_scrollPane.getWidth());
                });

        m_scrollPane.heightProperty().addListener(evt
                -> {
                    m_digitalChart.setPrefHeight(m_scrollPane.getHeight() / 2);
                    m_analogChart.setPrefHeight(m_scrollPane.getHeight() / 2);
                });

    }

    private LineChart.Series<Double, Double> updatePoints(List<Point2D> digitalPoint) {
        LineChart.Series<Double, Double> pointSerie = new LineChart.Series<>();
        digitalPoint.stream().forEach((p) -> {
            pointSerie.getData().add(new XYChart.Data<>(p.getX(), p.getY()));
        });
        return pointSerie;
    }

    private void updateChart(LineChart.Series<Double, Double> serie, ObservableList<XYChart.Series<Double, Double>> chartData, AbstractSignal signal) {
        serie = updatePoints(signal.getPoints());
        chartData.setAll(chartData);
        chartData.add(serie);
    }

}
