package infobiptask.theurlshortener.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.stereotype.Repository;

@Repository
public class UrlDAO extends AbstractDAO<UrlEntity, String> {

    public List<UrlEntity> findAllByAccountId(String accountId) {
        final CriteriaQuery<UrlEntity> criteriaQuery = builder.createQuery(UrlEntity.class);
        final Root<UrlEntity> root = criteriaQuery.from(UrlEntity.class);

        AccountEntity account = new AccountEntity(accountId);
        criteriaQuery.multiselect(createDataSelection(root)).where(builder.equal(root.get(UrlEntity_.account), account));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public boolean existsByOriginalUrlAndAccountId(String url, String accountId) {
        List<UrlEntity> result = findByOriginalUrlAndAccountId(url, accountId);
        if (result == null || result.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<UrlEntity> findByOriginalUrlAndAccountId(String url, String accountId) {
        final CriteriaQuery<UrlEntity> criteriaQuery = builder.createQuery(UrlEntity.class);
        final Root<UrlEntity> root = criteriaQuery.from(UrlEntity.class);

        AccountEntity account = new AccountEntity(accountId);
        criteriaQuery.where(builder.equal(root.get(UrlEntity_.account), account), builder.equal(root.get(UrlEntity_.originalUrl), url));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private List<Selection<?>> createDataSelection(final Root<UrlEntity> root) {
        List<Selection<?>> selections = new ArrayList<>();
        selections.add(root.get(UrlEntity_.originalUrl).alias(UrlEntity_.originalUrl.getName()));
        selections.add(root.get(UrlEntity_.count).alias(UrlEntity_.count.getName()));
        return selections;
    }

}