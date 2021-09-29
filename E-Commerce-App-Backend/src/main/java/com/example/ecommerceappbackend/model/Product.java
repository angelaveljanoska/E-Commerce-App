package com.example.ecommerceappbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "product_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer price;

    @NotBlank
    @Column(length = 3000000)
    private String photo;

    //One product can be added in many carts and one cart can contain many products.
    @ManyToMany(mappedBy = "products", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Cart> carts;

//    @ManyToMany(mappedBy = "products", cascade = CascadeType.MERGE)
//    @JsonIgnore
//    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<ProductOrder> productOrders = new HashSet<ProductOrder>();

    public Product() {
    }

    public Product(String name, String description, Integer quantity, Integer price, String photo) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void setOrders(List<Order> orders) {
//        this.orders = orders;
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
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(quantity, product.quantity) && Objects.equals(price, product.price) && Objects.equals(photo, product.photo) && Objects.equals(carts, product.carts) && Objects.equals(productOrders, product.productOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, quantity, price, photo, carts, productOrders);
    }
}
