/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author nauzetaduen
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {

    @PersistenceContext(unitName = "CoWorking-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    public List<Users> getUsersbyNameAndRole(String name) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Users> createQuery = criteriaBuilder.createQuery(Users.class);
        Root<Users> user = createQuery.from(Users.class);
        Expression<String> nombre = user.get("name");
        Predicate predicate = criteriaBuilder.like(nombre, "%" + name + "%");
        createQuery.where(predicate);
        createQuery.orderBy(criteriaBuilder.asc(user.get("role")));
        createQuery.select(user);
        TypedQuery query = em.createQuery(createQuery);
        List<Users> result = query.getResultList();
        return result;
    }
    
}
