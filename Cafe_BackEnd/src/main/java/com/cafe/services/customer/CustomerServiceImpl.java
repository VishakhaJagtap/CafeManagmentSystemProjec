package com.cafe.services.customer;

import com.cafe.dto.CategoryDto;
import com.cafe.dto.ProductDto;
import com.cafe.dto.TableReservationDto;
import com.cafe.entity.Category;
import com.cafe.entity.Product;
import com.cafe.entity.Reservation;
import com.cafe.entity.User;
import com.cafe.enums.ReservationStatus;
import com.cafe.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final UserRepo userRepo;

    private final CategoryRepo categoryRepo;

    private final ProductRepo productRepo;

    private final TableReservationRepo tableReservationRepo;


    //// Categories Operations

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepo.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }

    @Override
    public List<CategoryDto> searchCategoryByTitle(String title) {
        return categoryRepo.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
    }


    //// Product Operations

    @Override
    public List<ProductDto> getProductsByCategory(Long categoryId) {
        return productRepo.findAllByCategoryId(categoryId).stream().map(Product::getProductDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchProductByCategoryAndTitle(Long categoryId, String title) {
        return productRepo.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product::getProductDto).collect(Collectors.toList());
    }


    ////// Reserve Table Operations

    @Override
    public TableReservationDto applyForReservation(TableReservationDto tableReservationDto) {
        Optional<User> optionalUser = userRepo.findById(tableReservationDto.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Reservation reservation = new Reservation();
            reservation.setTableType(tableReservationDto.getTableType());
            reservation.setDescription(tableReservationDto.getDescription());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            reservation.setDateTime(tableReservationDto.getDateTime());
            reservation.setUser(user);
            Reservation requestedReservation = tableReservationRepo.save(reservation);
            TableReservationDto requestedTableReservationDto = new TableReservationDto();
            requestedTableReservationDto.setId(requestedReservation.getId());
            return requestedTableReservationDto;
        } else {
            return null;
        }
    }

    @Override
    public List<TableReservationDto> getAllReservationByUserId(Long userId) {
        return tableReservationRepo.findAllByUserId(userId).stream().map(Reservation::getTableReservationDto).collect(Collectors.toList());
    }

}

