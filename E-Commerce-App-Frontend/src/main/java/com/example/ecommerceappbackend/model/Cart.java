package com.example.ecommerceappbackend.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    //One user has only one cart and vice versa. When a customer places an order, the cart becomes empty.
    @OneToOne
    private User user;

    //One cart can contain more than one product, and one product can be added to many carts. That's why this relation will have it's own table.
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "products_in_cart",
            joinColumns = @JoinColumn(name = "cart_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;


    public Cart() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
