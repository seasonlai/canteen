package com.season.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

/**
 * Created by season on 2018/4/16.
 */

@Entity
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Table(name = "t_food_kind")
public class FoodKind extends BaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kind_code")
    private int kindCode;

    @Column(name = "kind_name")
    private String kindName;

    @Column(name = "parent_code")
    private String parentCode;

    public int getKindCode() {
        return kindCode;
    }

    public void setKindCode(int kindCode) {
        this.kindCode = kindCode;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
}
