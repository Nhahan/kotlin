package com.example.kotlinbp.service

import com.example.kotlinbp.entity.User
import com.example.kotlinbp.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(private val userRepository: UserRepository) {

    fun getUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    @Transactional
    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    @Transactional
    fun updateUser(id: Long, user: User): User {
        val existingUser = userRepository.findById(id).orElseThrow {
            // 예외 발생 시 처리할 로직
            throw RuntimeException("User not found with id: $id")
        }
        val updatedUser = existingUser.copy(email = user.email)
        return userRepository.save(updatedUser)
    }

    @Transactional
    fun deleteUser(id: Long): Int {
        return userRepository.deleteByIdAndReturnCount(id)
    }
}