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
@Table(name = "COMPONENTS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Components.findAll", query = "SELECT c FROM Components c"),
    @NamedQuery(name = "Components.findByIdcomponent", query = "SELECT c FROM Components c WHERE c.idcomponent = :idcomponent"),
    @NamedQuery(name = "Components.findByName", query = "SELECT c FROM Components c WHERE c.name = :name"),
    @NamedQuery(name = "Components.findByVisits", query = "SELECT c FROM Components c WHERE c.visits = :visits")})
public class Components implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDCOMPONENT")
    private Integer idcomponent;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VISITS")
    private int visits;

    public Components() {
    }

    public Components(Integer idcomponent) {
        this.idcomponent = idcomponent;
    }

    public Components(Integer idcomponent, String name, int visits) {
        this.idcomponent = idcomponent;
        this.name = name;
        this.visits = visits;
    }

    public Integer getIdcomponent() {
        return idcomponent;
    }

    public void setIdcomponent(Integer idcomponent) {
        this.idcomponent = idcomponent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcomponent != null ? idcomponent.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Components)) {
            return false;
        }
        Components other = (Components) object;
        if ((this.idcomponent == null && other.idcomponent != null) || (this.idcomponent != null && !this.idcomponent.equals(other.idcomponent))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Components[ idcomponent=" + idcomponent + " ]";
    }
    
}
