package com.example.controller.cart;

import com.example.entity.Product;
import com.example.entity.ShoppingCart;
import com.example.entity.User;
import com.example.exception.NotFoundException;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import com.example.service.cart.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin("http://localhost:8080/")
@RequiredArgsConstructor
public class CartController {

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/create")
    public ShoppingCart createShoppingCart(@RequestParam("productId") Long productId,
                                           @RequestParam("quantity") Long quantity,
                                           @RequestParam("userId") Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id " + productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));

        return shoppingCartService.addItemToCart(product, quantity, user);
    }

    @GetMapping("/cartByUserId/{id}")
    public ShoppingCart getCartByUserId(@PathVariable("id") Long id) {
        return shoppingCartService.getCartByUserId(id);
    }

    @PutMapping("/updateCart")
    public ShoppingCart updateCart(@RequestParam("productId") Long productId,
                                   @RequestParam("quantity") Long quantity,
                                   @RequestParam("userId") Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id " + productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));

        return shoppingCartService.updateItemToCart(product, quantity, user);
    }

    @DeleteMapping("/delete")
    public ShoppingCart deleteCart(@RequestParam("productId") Long productId,
                                   @RequestParam("userId") Long userId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product not found with id " + productId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with id " + userId));

        return shoppingCartService.deleteItem(product, user);
    }

}
