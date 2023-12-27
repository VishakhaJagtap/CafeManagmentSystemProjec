package com.cafe.services.customer;

import com.cafe.dto.CategoryDto;
import com.cafe.dto.ProductDto;
import com.cafe.dto.TableReservationDto;

import java.util.List;

public interface CustomerService {


    //// Categories Operations

    List<CategoryDto> getAllCategories();

    List<CategoryDto> searchCategoryByTitle(String title);


    //// Product Operations

    List<ProductDto> getProductsByCategory(Long categoryId);

    List<ProductDto> searchProductByCategoryAndTitle(Long categoryId, String title);


    ////// Reserve Table Operations

    TableReservationDto applyForReservation(TableReservationDto tableReservationDto);

    List<TableReservationDto> getAllReservationByUserId(Long userId);

}
