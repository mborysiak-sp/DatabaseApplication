package Controllers;

import Models.AppointmentTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class User {
    @FXML
    Button query;
    @FXML
    Button procedure;
    @FXML
    Label result;
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
    public void logout (ActionEvent event) throws Exception {
        Stage stage = (Stage)appointmentTable.getScene().getWindow();
        stage.close();
    }
}
