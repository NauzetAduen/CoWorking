/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "RESERVATIONS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservations.findAll", query = "SELECT r FROM Reservations r"),
    @NamedQuery(name = "Reservations.findByIdreservation", query = "SELECT r FROM Reservations r WHERE r.idreservation = :idreservation"),
    @NamedQuery(name = "Reservations.findByIdworkplace", query = "SELECT r FROM Reservations r WHERE r.idworkplace = :idworkplace"),
    @NamedQuery(name = "Reservations.findByIduser", query = "SELECT r FROM Reservations r WHERE r.iduser = :iduser"),
    @NamedQuery(name = "Reservations.findByTimestamp", query = "SELECT r FROM Reservations r WHERE r.timestamp = :timestamp"),
    @NamedQuery(name = "Reservations.findByPrice", query = "SELECT r FROM Reservations r WHERE r.price = :price")})
public class Reservations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDRESERVATION")
    private Integer idreservation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDWORKPLACE")
    private int idworkplace;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDUSER")
    private int iduser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRICE")
    private double price;

    public Reservations() {
    }

    public Reservations(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public Reservations(Integer idreservation, int idworkplace, int iduser, Date timestamp, double price) {
        this.idreservation = idreservation;
        this.idworkplace = idworkplace;
        this.iduser = iduser;
        this.timestamp = timestamp;
        this.price = price;
    }

    public Integer getIdreservation() {
        return idreservation;
    }

    public void setIdreservation(Integer idreservation) {
        this.idreservation = idreservation;
    }

    public int getIdworkplace() {
        return idworkplace;
    }

    public void setIdworkplace(int idworkplace) {
        this.idworkplace = idworkplace;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreservation != null ? idreservation.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservations)) {
            return false;
        }
        Reservations other = (Reservations) object;
        if ((this.idreservation == null && other.idreservation != null) || (this.idreservation != null && !this.idreservation.equals(other.idreservation))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Reservations[ idreservation=" + idreservation + " ]";
    }
    
}
