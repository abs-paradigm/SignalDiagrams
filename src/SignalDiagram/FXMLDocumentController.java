/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SignalDiagram;

import Renderer.Renderer;
import Signal.Signal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

/**
 *
 * @author Dom
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Canvas m_canvas;

    @FXML
    private Label m_label;

    @FXML
    private CheckBox ChkBox_Show;

    private Renderer m_renderer;
    private Signal m_signal;
    private GraphicsContext gc;

    @FXML
    private void handleChkBoxAction(ActionEvent event) {
        System.out.println("You clicked me!");

        if (ChkBox_Show.isSelected()) {
            m_label.setText(m_signal.getMessage());
            m_renderer.draw();
        } else {
            m_label.setText("");
            m_renderer.reset();
        }
    }

    @FXML
    private void reset() {
        m_renderer.reset();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        m_signal = new Signal("10100011110101010", 0);
        m_renderer = new Renderer(m_canvas, m_signal);
        m_label.setText(m_signal.getMessage());
    }

}
