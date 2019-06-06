package Controllers;

import Models.AppointmentTable;
import Models.PrescriptionTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;

public class User {
    @FXML
    private TableView<AppointmentTable> appointmentTable;
    @FXML
    private TableColumn<AppointmentTable, Integer> id_pacjent;
    @FXML
    private TableColumn<AppointmentTable, Date> data_wizyty ;
    @FXML
    private TableColumn<AppointmentTable, String> pacjent_nazwisko ;
    @FXML
    private TableColumn<AppointmentTable, String> lekarz_nazwisko ;
    @FXML
    private TableColumn<AppointmentTable, String> lekarz_specjalizacja ;
    @FXML
    private TableColumn<AppointmentTable, Integer> id_pokoj ;
    @FXML
    private TableView<PrescriptionTable> prescriptionTable;
    @FXML
    private TableColumn<PrescriptionTable, Integer> opis_prescription;
    @FXML
    private TableColumn<PrescriptionTable, Date> data_wystawienia_prescription ;
    @FXML
    private TableColumn<PrescriptionTable, String> pacjent_nazwisko_prescription ;
    @FXML
    private TableColumn<PrescriptionTable, String> lekarz_nazwisko_prescription ;


    public void useProcedure(ActionEvent event) throws Exception {
        ObservableList<AppointmentTable> objList = FXCollections.observableArrayList();
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("exec wizyty");
            while (rs.next()) {
                objList.add(new AppointmentTable(rs.getInt("id_pacjent"), rs.getString("pacjent_nazwisko"), rs.getDate("data_wizyty"), rs.getString("lekarz_nazwisko"), rs.getString("lekarz_specjalizacja"), rs.getInt("id_pokoj")));
            }
            con.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }

        id_pacjent.setCellValueFactory(new PropertyValueFactory("id_pacjent"));
        id_pokoj.setCellValueFactory(new PropertyValueFactory("id_pokoj"));
        data_wizyty.setCellValueFactory(new PropertyValueFactory("data_wizyty"));
        lekarz_specjalizacja.setCellValueFactory(new PropertyValueFactory("lekarz_specjalizacja"));
        lekarz_nazwisko.setCellValueFactory(new PropertyValueFactory("lekarz_nazwisko"));
        pacjent_nazwisko.setCellValueFactory(new PropertyValueFactory("pacjent_nazwisko"));

        appointmentTable.setItems(objList);
    }
    public void useQuery(ActionEvent event) throws Exception {
        ObservableList<PrescriptionTable> objList = FXCollections.observableArrayList();
        try {
            Connection con = DBConnector.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT pacjent.nazwisko as pacjent_nazwisko, lekarz.nazwisko as lekarz_nazwisko, recepta.opis, data_wystawienia FROM recepta\n" +
                    "INNER JOIN pacjent ON pacjent.id_pacjent = recepta.id_pacjent\n" +
                    "INNER JOIN lekarz on recepta.id_lekarz = lekarz.id_lekarz WHERE LEN(pacjent.nazwisko) > 7 AND data_wystawienia BETWEEN '2018-06-12' AND '2018-11-11'");
            while (rs.next()) {
                objList.add(new PrescriptionTable(rs.getString("lekarz_nazwisko"), rs.getString("pacjent_nazwisko"), rs.getDate("data_wystawienia"), rs.getString("opis")));
            }
            con.close();
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        opis_prescription.setCellValueFactory(new PropertyValueFactory("opis"));
        data_wystawienia_prescription.setCellValueFactory(new PropertyValueFactory("data_wystawienia"));
        lekarz_nazwisko_prescription.setCellValueFactory(new PropertyValueFactory("lekarz_nazwisko"));
        pacjent_nazwisko_prescription.setCellValueFactory(new PropertyValueFactory("pacjent_nazwisko"));

        prescriptionTable.setItems(objList);
    }
    public void logout (ActionEvent event) throws Exception {
        Stage stage = (Stage)appointmentTable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
        Scene scene = new Scene(root, 640, 480);
        stage.getIcons().add(new Image("/Data/favicon.png"));
        stage.setScene(scene);
    }
}
