package com.mycms.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eric on 14-2-18.
 */
@Entity
public class Resource extends BaseObject {
    private Long id;
    private String url;
    private Integer priority;//1-5,1最高，5最低
    private Integer type; //1:菜单；2：按钮，3：其他资源
    private String name;
    private String memo;

    private Resource parent;//如果是菜单，定义其父辈目录的id
    //当前菜单的子菜单项目
    private Set<Resource> subResources = new HashSet<Resource>();


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    public Resource getParent() {
        return parent;
    }

    public void setParent(Resource parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", targetEntity = Resource.class, cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    public Set<Resource> getSubResources() {
        return subResources;
    }

    public void setSubResources(Set<Resource> subResources) {
        this.subResources = subResources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", priority=" + priority +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
