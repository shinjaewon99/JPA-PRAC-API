package jpabook.jpashop.domain.repository;


import jpabook.jpashop.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    /*
        스프링에서 @RequiredArgsConstructor을 사용함으로써
        @PersistenceContext, @Autowired 다 지원해준다.
        @Autowired @PersistenceContext
        public MemberRepository(EntityManager em) {
            this.em = em;
        }
    */
    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        /*
        JPQL을 만들어 Member 클래스에서 List로 뿌려줬던거를 조회
        SQL이랑 다른점은 Entity 객체를 대상으로 쿼리한다.
        */
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
