package com.example.demo.controller

import com.example.demo.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    @Test
    fun `should return all banks`() {
        mockMvc.get("/api/banks")
            .andDo { print() }
            .andExpect { status { isOk() } }

        /*
        .andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$[0].accountNumber") { value("1234") }
        }
         */
    }

    @Test
    fun `should add the new back`() {
        // given
        val newBank = Bank("acc123", 31.415, 2)

        // when
        mockMvc.post("/api/banks") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newBank)
        }.andDo { print() }
            .andExpect {
                status { isCreated() }
                content { contentType(MediaType.APPLICATION_JSON)
                jsonPath("$.accountNumber") { value(newBank.accountNumber) }
                jsonPath("$.trust") { value(newBank.trust) }
                jsonPath("$.transactionFee") { value(newBank.transactionFee) }
            }
        }
    }

    @Nested
    @DisplayName("PATCH /api/banks/1234")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchExistingBank {

        @Test
        fun `should update an existing bank`() {
            // given
            val updatedBank = Bank("1234", 1.0, 1)

            // when
            mockMvc.patch("/api/banks") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedBank)
            }.andDo { print() }
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON)
                        jsonPath("$.accountNumber") { value(updatedBank.accountNumber) }
                        jsonPath("$.trust") { value(updatedBank.trust) }
                        jsonPath("$.transactionFee") { value(updatedBank.transactionFee) }
                    }
                }

        }
    }

    @Nested
    @DisplayName("DELETE /api/banks/1234")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteExistingBank {

        @Test
        fun `should delete an existing bank`() {
            // given
            val accountNumber = "1234"

            // when
            mockMvc.delete("/api/banks/$accountNumber")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
//                    status { isNoContent() }
                }

//            mockMvc.get("/api/banks/$accountNumber")
//                .andExpect { status { isNotFound() } }
        }
    }
}
