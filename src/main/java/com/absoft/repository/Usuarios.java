package com.absoft.repository;

import com.absoft.model.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Diego Arantes
 */
@Stateless
public class Usuarios extends BaseRepository<Usuario, Long> {

    @PersistenceContext
    EntityManager em;

    public Usuarios() {
        super(Usuario.class);
    }

    public Usuario verificarUsuario(String usuario, String senha) {
        Query q = em.createQuery("from Usuario where usuario = :user AND senha = :pass");
        q.setParameter("user", usuario);
        q.setParameter("pass", senha);
        if (!q.getResultList().isEmpty()) {
            return (Usuario) q.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
