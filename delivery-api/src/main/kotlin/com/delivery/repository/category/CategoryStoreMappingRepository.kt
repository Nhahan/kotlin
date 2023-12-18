package com.delivery.repository.category

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryStoreMappingRepository : JpaRepository<CategoryStoreMapping, Long>{
}