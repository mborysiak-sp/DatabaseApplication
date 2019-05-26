package Models;

import java.sql.Date;
import java.sql.Time;

public class LoggingTable {
    private Integer id_logging, id_user;
    private Date date;
    private Time hour;

    public LoggingTable(Integer id_logging, Date date, Time hour, Integer id_user) {
        this.id_logging = id_logging;
        this.id_user = id_user;
        this.date = date;
        this.hour = hour;
    }

    public Integer getId_logging() {
        return id_logging;
    }

    public void setId_logging(Integer id_logging) {
        this.id_logging = id_logging;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getHour() {
        return hour;
    }

    public void setHour(Time hour) {
        this.hour = hour;
    }
}
