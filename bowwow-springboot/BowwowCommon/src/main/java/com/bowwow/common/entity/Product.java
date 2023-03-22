package com.bowwow.common.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="products")
public class Product {
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "main_name", length = 64, nullable = false)
	private String mainName;
	
	@Column(name = "sub_name", length = 128, nullable = false, unique = true)
	private String subName;
	
	@Column(nullable = false)
	private int price;
	
	@Column
	private float discount;
	
	@Column
	private int stock;
	
	@Column(name = "main_image")
	private String mainImage;
	
	@Column(name = "desc_image")
	private String descImage;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", nullable = false)
	private Category categoryId;
	
	@Column
	private String brand;
	
	private boolean enabled;
	
	@Column(name = "reg_date", nullable = false)
	private LocalDateTime regDate;
	
	@Column
	private float score;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "like_it",
			joinColumns = @JoinColumn(name = "product_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	
	private Set<User> likes = new HashSet<>();

	public void addLike(User user) {
		this.likes.add(user);
	}
	
	public void deleteLike(User user) {
		this.likes.remove(user);
	}
	
	
	public Product(Integer id, String mainName, String subName, int price, float discount, int stock, String mainImage,
			String descImage, String description, Category categoryId, String brand, boolean enabled,
			LocalDateTime regDate, float score, Set<User> likes) {
		super();
		this.id = id;
		this.mainName = mainName;
		this.subName = subName;
		this.price = price;
		this.discount = discount;
		this.stock = stock;
		this.mainImage = mainImage;
		this.descImage = descImage;
		this.description = description;
		this.categoryId = categoryId;
		this.brand = brand;
		this.enabled = enabled;
		this.regDate = regDate;
		this.score = score;
		this.likes = likes;
	}



	public Product() {
		this.regDate = LocalDateTime.now();
	}

	public Product(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public String getDescImage() {
		return descImage;
	}

	public void setDescImage(String descImage) {
		this.descImage = descImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}
	
	public Set<User> getLikes() {
		return likes;
	}

	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", mainName=" + mainName + ", subName=" + subName + ", price=" + price
				+ ", discount=" + discount + ", stock=" + stock + ", mainImage=" + mainImage + ", descImage="
				+ descImage + ", description=" + description + ", categoryId=" + categoryId + ", brand=" + brand
				+ ", enabled=" + enabled + ", regDate=" + regDate + ", score=" + score + ", likes=" + likes + "]";
	}

	@Transient
    public String getMainImagePath() {
      if(id == null || mainImage == null || mainImage.equals("")) return"/images/image-thumbnail.png";
      return"/product-main-images/"+ this.id+"/"+this.mainImage;
    }
   
    @Transient
    public String getDescImagePath() {
       if(id == null || descImage == null || descImage.equals("")) return"/images/image-thumbnail.png";
       return"/product-desc-images/"+ this.id+"/"+this.descImage;
    }
}
