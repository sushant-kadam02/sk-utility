package com.sk.code.utility.repository;

import com.sk.code.utility.model.FoodSales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodSalesRepository extends MongoRepository<FoodSales, String> {
}
