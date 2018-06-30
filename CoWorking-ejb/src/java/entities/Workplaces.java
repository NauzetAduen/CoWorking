/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "WORKPLACES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Workplaces.findAll", query = "SELECT w FROM Workplaces w"),
    @NamedQuery(name = "Workplaces.findByIdworkplace", query = "SELECT w FROM Workplaces w WHERE w.idworkplace = :idworkplace"),
    @NamedQuery(name = "Workplaces.findByName", query = "SELECT w FROM Workplaces w WHERE w.name = :name"),
    @NamedQuery(name = "Workplaces.findByAddress", query = "SELECT w FROM Workplaces w WHERE w.address = :address"),
    @NamedQuery(name = "Workplaces.findBySlots", query = "SELECT w FROM Workplaces w WHERE w.slots = :slots"),
    @NamedQuery(name = "Workplaces.findByDescription", query = "SELECT w FROM Workplaces w WHERE w.description = :description")})
public class Workplaces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDWORKPLACE")
    private Integer idworkplace;
    @Size(max = 255)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "SLOTS")
    private Integer slots;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "DESCRIPTION")
    private String description;

    public Workplaces() {
    }

    public Workplaces(Integer idworkplace) {
        this.idworkplace = idworkplace;
    }

    public Workplaces(Integer idworkplace, String description) {
        this.idworkplace = idworkplace;
        this.description = description;
    }

    public Integer getIdworkplace() {
        return idworkplace;
    }

    public void setIdworkplace(Integer idworkplace) {
        this.idworkplace = idworkplace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getSlots() {
        return slots;
    }

    public void setSlots(Integer slots) {
        this.slots = slots;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idworkplace != null ? idworkplace.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Workplaces)) {
            return false;
        }
        Workplaces other = (Workplaces) object;
        if ((this.idworkplace == null && other.idworkplace != null) || (this.idworkplace != null && !this.idworkplace.equals(other.idworkplace))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Workplaces[ idworkplace=" + idworkplace + " ]";
    }
    
}
