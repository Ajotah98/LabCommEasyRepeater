package com.labcomm.easyrepeater;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class mainGUIController {
    @FXML
    private TextField iplabel;
    @FXML
    private TextField portlabel;
    @FXML
    private ChoiceBox cbox;
    ObservableList<String> connectionType = FXCollections.observableArrayList("Server","Client");
    @FXML
    private TextArea trama;

    public Socket socket;
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
            if (type.equals("Server")) {
                new Thread(() -> {
                    try {
                        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(port));
                        setSocket(serverSocket.accept());
                    } catch (IOException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Connection failed");
                        alert.setContentText("Failed to start server: " + e.getMessage());
                        alert.showAndWait();
                    }
                }).start();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText("Connection established");
                alert.setContentText("The connection file has been created successfully.");
                alert.showAndWait();
                System.out.println(socket);
            }
            if (type.equals("Client")) {
                try {
                    setSocket(new Socket(ip, Integer.parseInt(port)));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("Connection established");
                    alert.setContentText("The connection file has been created successfully.");
                    alert.showAndWait();
                    System.out.println(socket);
                } catch (IOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Connection failed");
                    alert.setContentText("Failed to connect to the server: " + e.getMessage());
                    alert.showAndWait();
                }
            }
        }
    @FXML
    private void choiceBoxType(){
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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Failed to send trace");
                alert.setContentText("Failed to send trace: " + e.getMessage());
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No connection established");
            alert.setContentText("Please establish a connection before sending the trace.");
            alert.showAndWait();
        }
    }
}