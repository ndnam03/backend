package com.example.service.cart;

import com.example.entity.CartItem;
import com.example.entity.Product;
import com.example.entity.ShoppingCart;
import com.example.entity.User;
import com.example.exception.NotFoundException;
import com.example.repository.CartItemRepository;
import com.example.repository.ShoppingCartRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;

    @Override
    public ShoppingCart getCartByUserId(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User is not exits"));
        ShoppingCart cartItem = user.getShoppingCart();
        return cartItem;
    }

    @Override
    public ShoppingCart addItemToCart(Product product, Long quantity, User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        System.out.println(shoppingCart);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
        }
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        System.out.println(cartItems);

        CartItem cartItem = findCartItem(cartItems, product.getId());
        System.out.println(cartItem);
        if (cartItem == null) {
            cartItems = new HashSet<>();
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(shoppingCart);
            cartItem.setTotalPrice(quantity * product.getPrice());
            cartItem.setStatus(true);
            cartItems.add(cartItem);

            cartItemRepository.save(cartItem);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItem.setTotalPrice(cartItem.getTotalPrice() + (quantity * product.getPrice()));
            cartItemRepository.save(cartItem);
        }

        shoppingCart.setCartItems(cartItems);
        Long totalItems = totalItems(shoppingCart.getCartItems());
        Double totalPrice = totalPrice(shoppingCart.getCartItems());

        shoppingCart.setTotalItems(totalItems);
        shoppingCart.setTotalPrices(totalPrice);
        shoppingCart.setUser(user);
        shoppingCart.setStatus("Đang chờ");


        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart updateItemToCart(Product product, Long quantity, User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        Set<CartItem> cartItems = shoppingCart.getCartItems();

        CartItem item = findCartItem(cartItems, product.getId());
        item.setQuantity(quantity);
        item.setTotalPrice(quantity * product.getPrice());
        cartItemRepository.save(item);

        long totalItems = totalItems(cartItems);
        Double totalPrices = totalPrice(cartItems);
        shoppingCart.setTotalPrices(totalPrices);
        shoppingCart.setTotalItems(totalItems);
        shoppingCart.setStatus("Đang chờ");
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public ShoppingCart deleteItem(Product product, User user) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        Set<CartItem> cartItems = shoppingCart.getCartItems();

        CartItem cartItem = findCartItem(cartItems,product.getId());
        cartItems.remove(cartItem);
        cartItemRepository.delete(cartItem);
        long totalItems = totalItems(cartItems);
        Double totalPrices = totalPrice(cartItems);
        shoppingCart.setTotalItems(totalItems);
        shoppingCart.setTotalPrices(totalPrices);
        shoppingCart.setCartItems(cartItems);
        shoppingCart.setStatus("");
        return shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public CartItem findCartItem(Set<CartItem> cartItems, Long productId) {
        if (cartItems == null) {
            return null;
        }
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId) ) {
                return item;
            }
        }
        return null;
    }

    @Override
    public Long totalItems(Set<CartItem> cartItems) {
        if (cartItems == null) {
            return null;
        }
        return cartItems.stream()
                .filter(item -> item.getStatus() != null && item.getStatus())
                .mapToLong(CartItem::getQuantity)
                .sum();
    }

    @Override
    public Double totalPrice(Set<CartItem> cartItems) {
        if (cartItems == null) {
            return null;
        }
        return cartItems.stream()
                .filter(item -> item.getStatus() != null && item.getStatus())
                .mapToDouble(CartItem::getTotalPrice).sum();
    }


}
