package com.example.viewandreplytweets.Dao.Hibernate;


import com.example.viewandreplytweets.Response.lstPostsResponse;
import org.hibernate.Session;

import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@EnableTransactionManagement
@Repository
public class PostsHibernateImpl implements PostsHibernate {

    @PersistenceContext()
    private final EntityManager entityManager;

    public PostsHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public List<lstPostsResponse> findAllByUserAndOrderByDateDesc(String user) {

        Session session = entityManager.unwrap(Session.class);

        String sql = "select posts.*, count(comments.postid) as commentcount from posts left join comments on posts.postid = comments.postid where posts.user=:user GROUP BY posts.postid order by posts.date desc;";
        NativeQuery query = session.createSQLQuery(sql);
        query.setParameter("user", user);

        List<Object> result = (List<Object>) query.list();

        List<lstPostsResponse> lstposts = new ArrayList<>();
        Iterator<Object> itr = result.iterator();
        return getLstPostsResponses(lstposts, itr);
    }

    @Transactional
    public List<lstPostsResponse> findAllPosts() {

        Session session = entityManager.unwrap(Session.class);

        String sql = "select posts.*, count(comments.postid) as commentcount from posts left join comments on posts.postid = comments.postid GROUP BY posts.postid order by posts.date desc;";
        NativeQuery query = session.createSQLQuery(sql);

        List<Object> result = (List<Object>) query.list();
        List<lstPostsResponse> lstposts = new ArrayList<>();
        Iterator itr = result.iterator();
        return getLstPostsResponses(lstposts, itr);
    }

    private List<lstPostsResponse> getLstPostsResponses(List<lstPostsResponse> lstposts, Iterator itr) {
        while (itr.hasNext()) {
            Object[] obj = (Object[]) itr.next();
            lstPostsResponse response = new lstPostsResponse((String) obj[0], (String) obj[1], (String) obj[2], (String) obj[3], (String) obj[4], (String) obj[5], (BigInteger) obj[6]);
            lstposts.add(response);
        }

        return lstposts;
    }
}
