package com.example.service.cart;

import com.example.entity.CartItem;
import com.example.entity.Product;
import com.example.entity.ShoppingCart;
import com.example.entity.User;

import java.util.Set;

public interface ShoppingCartService {

    ShoppingCart getCartByUserId(Long id);

    ShoppingCart addItemToCart(Product product, Long quantity, User user);

    ShoppingCart updateItemToCart(Product product, Long quantity, User user);

    ShoppingCart deleteItem(Product product, User user);

    CartItem findCartItem(Set<CartItem> cartItems, Long productId);

    Long totalItems(Set<CartItem> cartItems);

    Double totalPrice(Set<CartItem> cartItems);
}
