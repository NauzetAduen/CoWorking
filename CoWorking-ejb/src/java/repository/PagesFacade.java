/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Pages;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author nauzetaduen
 */
@Stateless
public class PagesFacade extends AbstractFacade<Pages> {

    @PersistenceContext(unitName = "CoWorking-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagesFacade() {
        super(Pages.class);
    }
    
}
