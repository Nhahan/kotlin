package com.example.kotlinbp.repository

import com.example.kotlinbp.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {
    @Modifying
    @Query("DELETE FROM User u WHERE u.id = ?1")
    fun deleteByIdAndReturnCount(id: Long): Int
}