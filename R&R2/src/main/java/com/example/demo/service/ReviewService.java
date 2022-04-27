package com.example.demo.service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReviewService  {
    @Autowired
    private ReviewRepository repository;

    @Autowired
    private SessionFactory sf;

    public void insert(Review r){
        repository.save(r);
    }

    public List<Review> find() {
        Session s=sf.openSession();
        Query q=s.createQuery("from Review");
        List l=q.list();
        return l;
    }

    public List ambienceAvg(){
        Session s=sf.openSession();
        Query q=s.createQuery("select AVG(ambience) from Review");
       return  q.list();
    }
    public List foodAvg(){
        Session s=sf.openSession();
        Query q=s.createQuery("select AVG(food) from Review");
        return  q.list();
    }
    public List cleanAvg(){
        Session s=sf.openSession();
        Query q=s.createQuery("select AVG(clean) from Review");
        return  q.list();
    }
    public List drinksAvg(){
        Session s=sf.openSession();
        Query q=s.createQuery("select AVG(drinks) from Review");
        return  q.list();
    }
    public List serviceAvg(){
        Session s=sf.openSession();
        Query q=s.createQuery("select AVG(service) from Review");
        return  q.list();
    }
    public List overAllAvg(){
        Session s=sf.openSession();
        Query q=s.createQuery("select AVG((ambience+food+clean+drinks+food+service)/5) from Review");
        return  q.list();
    }
}

