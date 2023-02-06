# LabCommEasyRepeater

A JavaFX application for establishing a connection and sending traces between a server and a client, created with SceneBuilder and JavaFX.

## Features
- Connect to a server or act as a client
- Send traces in the form of a string of characters
- Connection parameters specified through two TextFields named "IP" and "Port" and a ChoiceBox named "Type"

## Requirements
- Java 8 or higher
- JavaFX library
- JDK 19 or higher
- SceneBuilder for creating the FXML document

## How to run
1. Clone or download the repository
2. Navigate to the root directory of the project
3. Compile and run using the following command:
```javac initMain.java && java initMain```

## Screenshot
![image](https://user-images.githubusercontent.com/91145535/217058534-58491462-0f9b-499d-9b1c-843a3ce5c735.png)


## Usage
1. Use the `Set Connection` button to establish a connection with either a server or a client.
2. Fill in the parameters in the two TextFields and select either `Server` or `Client` from the ChoiceBox.
3. Use the `Send Trace` button to send a string of characters to the established connection.

## Class Structure
- Main class: `initMain.java` (starts the `main()` in `LabCommEasyRepeater.java`)
- Controller class: `mainGUIcontroller.java`
- Start class (extendeds Application): `LabCommEasyRepeater.java`

## Contributing
Contributions are welcome! Feel free to submit a pull request.

## License
This project is licensed under the MIT License. 

The icon used in the program was downloaded from [Flaticon](https://www.flaticon.com) and is licensed under [Flaticon Basic License](https://file000.flaticon.com/downloads/license/license.pdf). The attribution should be given as follows:

