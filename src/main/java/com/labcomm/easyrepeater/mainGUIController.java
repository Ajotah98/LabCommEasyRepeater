package com.labcomm.easyrepeater;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import com.labcomm.easyrepeater.constants.ApplicationConstants;
import com.labcomm.easyrepeater.constants.ApplicationLiterals;

public class mainGUIController {
    @FXML
    private TextField iplabel;
    @FXML
    private TextField portlabel;
    @FXML
    private ChoiceBox cbox;
    ObservableList<String> connectionType = FXCollections.observableArrayList(ApplicationConstants.SERVER, ApplicationConstants.CLIENT);
    @FXML
    private TextArea trama;

    private Socket socket;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

    private void initSocket() {
        String ip = iplabel.getText();
        String port = portlabel.getText();
        String type = cbox.getValue().toString();
        switch (type) {
            case ApplicationConstants.SERVER:
                socketServer(ip, port, type);
                break;
            case ApplicationConstants.CLIENT:
                socketClient(ip, port, type);
                break;
            default:
                System.out.println(ApplicationLiterals.MASSIVE_ERROR);
                break;
        }
    }

    private void socketServer(String ip, String port, String type) {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
                setSocket(serverSocket.accept());
            } catch (IOException e) {
                createAlert(ApplicationLiterals.ERROR, ApplicationLiterals.CONNECTION_FAILED,
                        ApplicationLiterals.FAILED_START_SERVER, AlertType.ERROR);
            }
        }).start();
        createAlert(ApplicationLiterals.SUCCESS, ApplicationLiterals.CONNECTION_ESTABLISHED,
                ApplicationLiterals.CONNECTION_FILE_CREATED, AlertType.INFORMATION);
        System.out.println(socket);
    }

    private void socketClient(String ip, String port, String type) {
        try {
            setSocket(new Socket(ip, Integer.parseInt(port)));
            createAlert(ApplicationLiterals.SUCCESS, ApplicationLiterals.CONNECTION_ESTABLISHED,
                    ApplicationLiterals.CONNECTION_FILE_CREATED, AlertType.INFORMATION);
            System.out.println(socket);
        } catch (IOException e) {
            createAlert(ApplicationLiterals.ERROR, ApplicationLiterals.CONNECTION_FAILED,
                    ApplicationLiterals.FAILED_TO_CONNECT.concat(e.getMessage()), AlertType.ERROR);
        }
    }

    private void createAlert(String title, String header, String content, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void choiceBoxType() {
        cbox.setItems(connectionType);
    }

    @FXML
    protected void handleSetConnectionAction() {
        initSocket();
    }

    @FXML
    private void handleSendTraceAction() {
        String tramaText = trama.getText();
        socket = getSocket();
        System.out.println(socket);
        if (socket != null && socket.isConnected()) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(tramaText.getBytes());
            } catch (IOException e) {
                createAlert(ApplicationLiterals.ERROR, ApplicationLiterals.FAILED_SEND_TRACE,
                        ApplicationLiterals.FAILED_SEND_TRACE.concat(e.getMessage()), AlertType.ERROR);
            }
        } else {
            createAlert(ApplicationLiterals.ERROR, ApplicationLiterals.NO_CONNECTION_ESTABLISHED,
                    ApplicationLiterals.ESTABLISH_CONNECTION, AlertType.ERROR);
        }
    }
}