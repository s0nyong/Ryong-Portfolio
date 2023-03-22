package com.bowwow.common.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_details")
public class OrderDetail {
   
   @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "order_id")
   private Order order;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "product_id")
   private Product product;
   
   @Column
   private int count;
   
   @Column
   private int price;
   
   @Enumerated(EnumType.STRING)
   private OrderStatus status;
   
   @Column(name = "update_date")
   private LocalDateTime updateDate;
   
   

   public OrderDetail() {
   }

   public OrderDetail(int count, int price, OrderStatus status) {
      this.count = count;
      this.price = price;
      this.status = status;
      this.updateDate = LocalDateTime.now();
   }

   public OrderDetail(Order order, Product product, int count, int price, OrderStatus status) {
         super();
         this.order = order;
         this.product = product;
         this.count = count;
         this.price = price;
         this.status = status;
         this.updateDate = LocalDateTime.now();
      }

   public Integer getId() {
      return id;
   }


   public void setId(Integer id) {
      this.id = id;
   }


   public Order getOrder() {
      return order;
   }


   public void setOrder(Order order) {
      this.order = order;
   }


   public Product getProduct() {
      return product;
   }


   public void setProduct(Product product) {
      this.product = product;
   }


   public int getCount() {
      return count;
   }


   public void setCount(int count) {
      this.count = count;
   }


   public int getPrice() {
      return price;
   }


   public void setPrice(int price) {
      this.price = price;
   }


   public OrderStatus getStatus() {
      return status;
   }


   public void setStatus(OrderStatus status) {
      this.status = status;
   }


   public LocalDateTime getUpdateDate() {
      return updateDate;
   }


   public void setUpdateDate(LocalDateTime updateDate) {
      this.updateDate = updateDate;
   }


   public OrderDetail(Integer id, OrderStatus status) {
      this.id = id;
      this.status = status;
   }

   @Override
   public String toString() {
      return "OrderDetail [id=" + id + ", order=" + order + ", product=" + product + ", count=" + count + ", price="
            + price + ", status=" + status + "]";
   }


}
