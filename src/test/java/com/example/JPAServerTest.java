package com.example;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Created by robertwood on 1/20/18.
 */
public class JPAServerTest extends AbstractTest {

    @Test
    public void testNewUser() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("serverWithPW").createEntityManager();

        entityManager.getTransaction().begin();
        User user = new User();
        String name = Long.toString(new Date().getTime());
        user.setName(name);
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        System.out.print("user=" + user + ", user.id=" + user.getId());

        User foundUser = entityManager.find(User.class, user.getId());
        System.out.print("foundUser=" + foundUser);

        assertEquals(user.getName(), foundUser.getName());

        entityManager.close();
    }

}
