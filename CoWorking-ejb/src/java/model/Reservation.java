package model;

public class Reservation {
    private static int id = 0;
    private int reservationID;
    private String user;
    private String date; //Cambiar
    private String time;
    private String workplace;
    private float cash;

    public Reservation(String user, String date,String time, String workplace, Strategy strategy) {
        this.reservationID= id;
        Reservation.id++;
        this.user = user;
        this.date = date;
        this.time = time;
        this.workplace = workplace;
        this.cash = strategy.calculateRevenue(workplace);
    }

    public int getReservationID() {
        return reservationID;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }
    
    
    
}
