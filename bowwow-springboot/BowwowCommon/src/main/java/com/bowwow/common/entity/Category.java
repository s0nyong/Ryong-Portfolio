package com.bowwow.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="categories")
public class Category {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(length = 128, nullable = false, unique = true)
	private String categoryName;
	
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Category parent;
	
	@OneToMany(mappedBy = "parent")
	private Set<Category> children = new HashSet<>();
	
	public Category() {
	}

	public Category(Integer id) {
		this.id = id;
	}

	public Category(String categoryName) {
		this.categoryName = categoryName;
	}


	public Category(String categoryName, Category parent) {
		this(categoryName);
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
//		return parent == null ? categoryName : parent.getCategoryName() + " > " + categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getChildren() {
		return children;
	}

	public void setChildren(Set<Category> children) {
		this.children = children;
	}

	public static Category copyIdAndName(Integer id2, String name) {
	      Category copyCategory = new Category();
	      copyCategory.setId(id2);
	      copyCategory.setCategoryName(name);
	      return copyCategory;
	   }
	
	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();
//	    if (parent != null) {
	        builder.append(parent.getCategoryName());
	        builder.append(" >> ");
	        builder.append(categoryName);
//	    }
	    return builder.toString();
	}
	
}
