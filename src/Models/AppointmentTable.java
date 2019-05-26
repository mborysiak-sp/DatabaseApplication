package Models;

import java.sql.Date;

public class AppointmentTable {
    private Integer id_pacjent;
    private String pacjent_nazwisko;
    private Date data_wizyty;
    private String lekarz_nazwisko, lekarz_specjalizacja;
    private Integer id_pokoj;

    public AppointmentTable(Integer id_pacjent, String pacjent_nazwisko, Date data_wizyty, String lekarz_nazwisko, String lekarz_specjalizacja, Integer id_pokoj) {
        this.id_pacjent = id_pacjent;
        this.pacjent_nazwisko = pacjent_nazwisko;
        this.data_wizyty = data_wizyty;
        this.lekarz_nazwisko = lekarz_nazwisko;
        this.lekarz_specjalizacja = lekarz_specjalizacja;
        this.id_pokoj = id_pokoj;
    }

    public Integer getId_pacjent() {
        return id_pacjent;
    }

    public void setId_pacjent(Integer id_pacjent) {
        this.id_pacjent = id_pacjent;
    }

    public String getPacjent_nazwisko() {
        return pacjent_nazwisko;
    }

    public void setPacjent_nazwisko(String pacjent_nazwisko) {
        this.pacjent_nazwisko = pacjent_nazwisko;
    }

    public Date getData_wizyty() {
        return data_wizyty;
    }

    public void setData_wizyty(Date data_wizyty) {
        this.data_wizyty = data_wizyty;
    }

    public String getLekarz_nazwisko() {
        return lekarz_nazwisko;
    }

    public void setLekarz_nazwisko(String lekarz_nazwisko) {
        this.lekarz_nazwisko = lekarz_nazwisko;
    }

    public String getLekarz_specjalizacja() {
        return lekarz_specjalizacja;
    }

    public void setLekarz_specjalizacja(String lekarz_specjalizacja) {
        this.lekarz_specjalizacja = lekarz_specjalizacja;
    }

    public Integer getId_pokoj() {
        return id_pokoj;
    }

    public void setId_pokoj(Integer id_pokoj) {
        this.id_pokoj = id_pokoj;
    }
}
