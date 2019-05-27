package Models;

import java.sql.Date;

public class PrescriptionTable {
    private String pacjent_nazwisko;
    private String lekarz_nazwisko;
    private String opis;
    private Date data_wystawienia;

    public PrescriptionTable(String pacjent_nazwisko, String lekarz_nazwisko, Date data_wystawienia,String opis) {
        this.pacjent_nazwisko = pacjent_nazwisko;
        this.lekarz_nazwisko = lekarz_nazwisko;
        this.opis = opis;
        this.data_wystawienia = data_wystawienia;
    }

    public String getPacjent_nazwisko() {
        return pacjent_nazwisko;
    }

    public void setPacjent_nazwisko(String pacjent_nazwisko) {
        this.pacjent_nazwisko = pacjent_nazwisko;
    }

    public String getLekarz_nazwisko() {
        return lekarz_nazwisko;
    }

    public void setLekarz_nazwisko(String lekarz_nazwisko) {
        this.lekarz_nazwisko = lekarz_nazwisko;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Date getData_wystawienia() {
        return data_wystawienia;
    }

    public void setData_wystawienia(Date data_wystawienia) {
        this.data_wystawienia = data_wystawienia;
    }
}
