package com.example.repository.criteria;

import com.example.entity.Product;
import com.example.payload.request.product.ProductDTOFilter;
import com.example.payload.response.product.PageDTOResponse;

import com.example.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public PageDTOResponse findAllWithCustomPage(ProductDTOFilter productDTOFilter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> count = cb.createQuery(Long.class);
        count.select(cb.count(count.from(Product.class)));

        // lấy tổng số phần tử để tính toán trang
        Long totalElements = entityManager.createQuery(count).getSingleResult();

        // truy vấn để lấy dữ liệu
        CriteriaQuery<Product> getProductQuery = cb.createQuery(Product.class);

        Root<Product> from = getProductQuery.from(Product.class);

        CriteriaQuery<Product> select = getProductQuery.select(from);

        List<Predicate> predicateList = new ArrayList<>();

        // tìm kiếm
        if (!ObjectUtils.isEmpty(productDTOFilter.getName())) {
            predicateList.add(cb.like(from.get("productName"), "%" + productDTOFilter.getName() + "%"));
        }
        if (!ObjectUtils.isEmpty(productDTOFilter.getDescription())) {
            predicateList.add(cb.like(from.get("description"), "%" + productDTOFilter.getDescription() + "%"));
        }
        if (!ObjectUtils.isEmpty(productDTOFilter.getBrandId())) {
            Path<Long> brandPath = from.get("brand");
            predicateList.add(brandPath.in(productDTOFilter.getBrandId()));
        }
        // Tìm kiếm theo danh sách category
        if (!ObjectUtils.isEmpty(productDTOFilter.getCategoryId())) {
            Path<Long> categoryPath = from.get("category");
            predicateList.add(categoryPath.in(productDTOFilter.getCategoryId()));
        }

        if (productDTOFilter.getPriceFrom() != null && productDTOFilter.getPriceTo() != null) {
            predicateList.add(cb.between(from.get("price"), productDTOFilter.getPriceFrom(), productDTOFilter.getPriceTo()));
        } else if (productDTOFilter.getPriceFrom() != null) {
            predicateList.add(cb.greaterThanOrEqualTo(from.get("price"), productDTOFilter.getPriceFrom()));
        } else if (productDTOFilter.getPriceTo() != null) {
            predicateList.add(cb.lessThanOrEqualTo(from.get("price"), productDTOFilter.getPriceTo()));
        }

        select.select(from).where(predicateList.toArray(new Predicate[]{}));
        // sort
        if (productDTOFilter.getSortByPrice() != null && productDTOFilter.getSortByPrice().equalsIgnoreCase("asc")) {
            getProductQuery.orderBy(cb.asc(from.get("price")));
        } else if (productDTOFilter.getSortByPrice() != null && productDTOFilter.getSortByPrice().equalsIgnoreCase("desc")) {
            getProductQuery.orderBy(cb.desc(from.get("price")));
        }

        //solve page and size
        TypedQuery<Product> typedQuery = entityManager.createQuery(select);

        //count total elements
        long totalCount = typedQuery.getResultList().size();

        Integer offset = (productDTOFilter.getPage() - 1) * productDTOFilter.getSize();
        typedQuery.setFirstResult(offset);
        typedQuery.setMaxResults(productDTOFilter.getSize());

        //set data and return
        PageDTOResponse<Product> pageDTO = PageUtils.calculatePage(productDTOFilter.getSize(), productDTOFilter.getPage(), totalCount);
        pageDTO.setData(typedQuery.getResultList());
        return pageDTO;
    }
}
