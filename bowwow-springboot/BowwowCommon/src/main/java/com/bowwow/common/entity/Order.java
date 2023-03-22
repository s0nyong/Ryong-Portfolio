package com.bowwow.common.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order {
   
   @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;
   
   @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
   private List<OrderDetail> order_detail;
   
   @Column(name = "reg_date", nullable = false)
   private LocalDateTime regDate;
   
   
   public Order() {
      this.regDate = LocalDateTime.now();
   }

   public Order(Integer id) {
      this.id = id;
   }
   
   public Order(Integer id, User user, List<OrderDetail> order_detail, LocalDateTime regDate) {
      super();
      this.id = id;
      this.user = user;
      this.order_detail = order_detail;
      this.regDate = regDate;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public LocalDateTime getRegDate() {
      return regDate;
   }

   public void setRegDate(LocalDateTime regDate) {
      this.regDate = regDate;
   }
   
   public List<OrderDetail> getOrder_detail() {
      return order_detail;
   }

   public void setOrder_detail(List<OrderDetail> order_detail) {
      this.order_detail = order_detail;
   }

   @Override
   public String toString() {
      return "Order [id=" + id + ", user=" + user + ", regDate=" + regDate + "]";
   }

   
   
   
}
