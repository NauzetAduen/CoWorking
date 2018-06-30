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
@Table(name = "PAGES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pages.findAll", query = "SELECT p FROM Pages p"),
    @NamedQuery(name = "Pages.findByIdpage", query = "SELECT p FROM Pages p WHERE p.idpage = :idpage"),
    @NamedQuery(name = "Pages.findByName", query = "SELECT p FROM Pages p WHERE p.name = :name"),
    @NamedQuery(name = "Pages.findByVisits", query = "SELECT p FROM Pages p WHERE p.visits = :visits")})
public class Pages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPAGE")
    private Integer idpage;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VISITS")
    private int visits;

    public Pages() {
    }

    public Pages(Integer idpage) {
        this.idpage = idpage;
    }

    public Pages(Integer idpage, String name, int visits) {
        this.idpage = idpage;
        this.name = name;
        this.visits = visits;
    }

    public Integer getIdpage() {
        return idpage;
    }

    public void setIdpage(Integer idpage) {
        this.idpage = idpage;
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
        hash += (idpage != null ? idpage.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pages)) {
            return false;
        }
        Pages other = (Pages) object;
        if ((this.idpage == null && other.idpage != null) || (this.idpage != null && !this.idpage.equals(other.idpage))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Pages[ idpage=" + idpage + " ]";
    }
    
}
