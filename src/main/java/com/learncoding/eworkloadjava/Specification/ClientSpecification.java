package com.learncoding.eworkloadjava.Specification;

import com.learncoding.eworkloadjava.model.Client;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ClientSpecification implements Specification<Client> {

    private Client filter;

    public ClientSpecification(Client filter) {
        super();
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> cq,
                                 CriteriaBuilder cb) {
        var p = cb.disjunction();

        if (filter.getLastName() != null) {
            p.getExpressions().add(cb.like(root.get("lastName"), "%" + filter.getLastName() + "%"));
        }
        if (filter.getEmail() != null) {
            p.getExpressions().add(cb.like(root.get("email"), "%" + filter.getEmail() + "%"));
        }
        return p;
    }
}
