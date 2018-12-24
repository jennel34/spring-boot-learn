package com.connext.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public abstract class AbstractDao implements BatchDao {
    @PersistenceContext
    protected EntityManager em;

    @Transactional
    public void batchInsert(List list){
        for (int i=0;i<list.size();i++){
            em.persist(list.get(i));
            if(i%30 == 0){
                em.flush();
                em.clear();
            }
        }
    }

    @Transactional
    public void batchUpdate(List list){
        for (int i=0;i<list.size();i++){
            em.merge(list.get(i));
            if(i%30 == 0){
                em.flush();
                em.clear();
            }
        }
    }
}
