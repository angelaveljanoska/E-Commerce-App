package com.example.ecommerceappbackend.model;

import com.example.ecommerceappbackend.model.enumerations.OStatus;
import com.example.ecommerceappbackend.repository.ProductOrderRepository;
import com.example.ecommerceappbackend.repository.ProductRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private OStatus status = OStatus.PENDING;

    private LocalDateTime date = LocalDateTime.now();

    //One user can make many orders, but one specific order is made by only one user.
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinTable(name = "products_in_order",
//            joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id")
//    )

    @OneToMany(mappedBy = "order")
    @JsonIgnore
    private Set<ProductOrder> productOrders = new HashSet<ProductOrder>();

//    private List<Product> products;

    public Order() {
    }

    public Order(OStatus status, LocalDateTime date) {
        this.status = status;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OStatus getStatus() {
        return status;
    }

    public void setStatus(OStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

//    public Long getUserId() {
//        return user.getId();
//    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

//    public List<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }


//    public Set<ProductOrder> getProductOrders() {
//        return productOrders;
//    }

//    public void setProductOrders(Set<ProductOrder> productOrders) {
//        this.productOrders = productOrders;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && status == order.status && Objects.equals(date, order.date) && Objects.equals(user, order.user) && Objects.equals(productOrders, order.productOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, date, user, productOrders);
    }
}
