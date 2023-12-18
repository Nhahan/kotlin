package com.delivery.controller.display.home.dto

import com.delivery.controller.catalog.category.dto.CategoryDTO

data class DeliveryHomeResponse(
    val catalogs: List<CategoryDTO>,
    val stores: List<StoreDTO>,
)