/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SignalDiagram;

import com.SignalDiagram.Signal.AnalogSignal;
import com.SignalDiagram.Signal.DigitalSignal;
import com.SignalDiagram.Signal.DigitalSignal.modulationType;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Dom
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ScrollPane m_scrollPane;
    @FXML
    private RadioButton radBtn_amplitude;
    @FXML
    private RadioButton radBtn_frequence;
    @FXML
    private RadioButton radBtn_phase;
    @FXML
    private CheckBox ChkBox_Inverted;
    @FXML
    private ComboBox cmbBox_DigitalType;
    @FXML
    private LineChart<Double, Double> m_analogChart;
    @FXML
    private LineChart m_digitalChart;// = new LineChart<>(xAxis, yAxis);
    @FXML
    private Canvas m_canvas;
    @FXML
    private TextField txtField_binaryInput;
    @FXML
    private TextField txtField_bits;
    @FXML
    private TextField txtField_seed;

    private String exampleMessage = "10100011110101010";
    private ObservableList<XYChart.Series<Double, Double>> m_analogChartData;
    private XYChart.Series<Double, Double> m_analogSerie;
    private List<LineChart.Series<Double, Double>> m_analogSerieList;
    private AnalogSignal m_analogSignal;

    //private NumberAxis xAxis = new NumberAxis();
    //private NumberAxis yAxis = new NumberAxis();
    private ObservableList<XYChart.Series<Double, Double>> m_digitalChartData;

    private List<Point2D> m_digitalPoint;
    private LineChart.Series<Double, Double> m_digitalSerie;

    private DigitalSignal m_digitalSignal;

    private ObservableList<Point2D> m_observableList = FXCollections.observableArrayList();

    final ToggleGroup radioAnalogGroup = new ToggleGroup();

    @FXML
    private void close() {
        System.exit(0);
    }

    private void initListeners() {

        txtField_binaryInput.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-1]*")) {
                txtField_binaryInput.setText(oldValue);
            } else if (!newValue.isEmpty()) {

                m_digitalSignal.setMessage(newValue);
                m_analogSignal.setMessage(newValue);

                updateDigitalChart(m_digitalSerie, m_digitalChartData, m_digitalSignal);
                updateAnalogChart(m_analogSerieList, m_analogChartData, m_analogSignal);
            }

        });

        txtField_seed.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[0-9]*")) {
                txtField_seed.setText(oldValue);
            } else if (!newValue.isEmpty()) {

                txtField_seed.setText("" + m_analogSignal.setSeed(Integer.parseInt(newValue)).getSeed());

                updateAnalogChart(m_analogSerieList, m_analogChartData, m_analogSignal);
            }

        });

        txtField_bits.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[1-9][0-9]*")) {
                txtField_bits.setText("1");
            } else if (!newValue.isEmpty()) {

                txtField_bits.setText(""
                        + m_analogSignal.setNbBits(Integer.parseInt(newValue)).getNbBits()
                );

                updateAnalogChart(m_analogSerieList, m_analogChartData, m_analogSignal);
            }

        });

        cmbBox_DigitalType.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String oldType, String newType) {
                m_digitalChart.setTitle("Digital Signal: " + WordUtils.capitalize(newType));
                m_digitalSignal.setType(newType);

                updateDigitalChart(m_digitalSerie, m_digitalChartData, m_digitalSignal);
            }
        });

        m_scrollPane.widthProperty().addListener(evt
                -> {
                    m_digitalChart.setPrefWidth(m_scrollPane.getWidth());
                    m_analogChart.setPrefWidth(m_scrollPane.getWidth());
                });

        m_scrollPane.heightProperty().addListener(evt
                -> {
                    m_digitalChart.setPrefHeight(m_scrollPane.getHeight() / 3);
                    m_analogChart.setPrefHeight(m_scrollPane.getHeight() / 3);
                });

        radioAnalogGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> ov,
                    Toggle old_toggle, Toggle new_toggle) {
                if (radioAnalogGroup.getSelectedToggle() == radBtn_amplitude) {
                    m_analogSignal.setType(AnalogSignal.analogType.AMPLITUDE);
                    updateAnalogChart(m_analogSerieList, m_analogChartData, m_analogSignal);
                }
                if (radioAnalogGroup.getSelectedToggle() == radBtn_frequence) {
                    m_analogSignal.setType(AnalogSignal.analogType.FREQUENCE);
                    updateAnalogChart(m_analogSerieList, m_analogChartData, m_analogSignal);
                }
                if (radioAnalogGroup.getSelectedToggle() == radBtn_phase) {
                    m_analogSignal.setType(AnalogSignal.analogType.PHASE);
                    updateAnalogChart(m_analogSerieList, m_analogChartData, m_analogSignal);
                }
            }
        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        radBtn_amplitude.setToggleGroup(radioAnalogGroup);
        radBtn_frequence.setToggleGroup(radioAnalogGroup);
        radBtn_phase.setToggleGroup(radioAnalogGroup);

        txtField_bits.setText("1");
        txtField_seed.setText("0");

        m_analogSerieList = new ArrayList<>();

        m_digitalSignal = new DigitalSignal(exampleMessage, modulationType.NRZ_L, false);
        m_analogSignal = new AnalogSignal(exampleMessage, AnalogSignal.analogType.AMPLITUDE);

        m_digitalChartData = FXCollections.observableArrayList();
        m_analogChartData = FXCollections.observableArrayList();

        m_digitalSerie = new LineChart.Series<>();
        m_digitalSerie = updatePoints(m_digitalSignal.getPoints());

        m_analogSignal.getPoints().stream().forEach((lp) -> {
            m_analogSerieList.add(updatePoints(lp));
        });

        m_digitalChartData.add(m_digitalSerie);
        m_digitalChart.setData(m_digitalChartData);

        //FIX
        m_analogChartData.addAll(m_analogSerieList);
        m_analogChart.setData(m_analogChartData);

        cmbBox_DigitalType.setItems(FXCollections.observableList(m_digitalSignal.getModulationTypes()));
        txtField_binaryInput.setText(m_digitalSignal.getMessage());

        initListeners();
        cmbBox_DigitalType.valueProperty().set("nrz-m");
        m_analogChart.setTitle("Analog Signal");

    }

    @FXML
    private void showAbout(ActionEvent event) {
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLAbout.fxml"));

            stage.setScene(new Scene(root));
            stage.setTitle("About");
            stage.initStyle(StageStyle.UTILITY);
            stage.initModality(Modality.APPLICATION_MODAL);
            // stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.setResizable(false);
            stage.show();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void invertSignal() {
        m_digitalSignal.setInverted(ChkBox_Inverted.isSelected());
        updateDigitalChart(m_digitalSerie, m_digitalChartData, m_digitalSignal);
    }

    private void updateAnalogChart(List<XYChart.Series<Double, Double>> serieList, ObservableList<XYChart.Series<Double, Double>> chartData, AnalogSignal signal) {

        chartData.clear();
        serieList.clear();

        for (List<Point2D> lp : signal.getPoints()) {
            XYChart.Series<Double, Double> test = updatePoints(lp);
            serieList.add(updatePoints(lp));
        }

        chartData.addAll(serieList);
    }

    private void updateDigitalChart(LineChart.Series<Double, Double> serie, ObservableList<XYChart.Series<Double, Double>> chartData, DigitalSignal signal) {
        serie = updatePoints(signal.getPoints());
        chartData.setAll(chartData);
        chartData.add(serie);
    }

    private LineChart.Series<Double, Double> updatePoints(List<Point2D> digitalPoint) {
        LineChart.Series<Double, Double> pointSerie = new LineChart.Series<>();
        digitalPoint.stream().forEach((p) -> {
            pointSerie.getData().add(new XYChart.Data<>(p.getX(), p.getY()));
        });
        return pointSerie;
    }

}
