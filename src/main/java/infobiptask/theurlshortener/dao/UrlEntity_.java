package infobiptask.theurlshortener.dao;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UrlEntity.class)
public class UrlEntity_ {

    public static volatile SingularAttribute<UrlEntity, String> shortCode;
    public static volatile SingularAttribute<UrlEntity, String> originalUrl;
    public static volatile SingularAttribute<UrlEntity, Integer> redirectType;
    public static volatile SingularAttribute<UrlEntity, Integer> count;
    public static volatile SingularAttribute<UrlEntity, AccountEntity> account;
}
