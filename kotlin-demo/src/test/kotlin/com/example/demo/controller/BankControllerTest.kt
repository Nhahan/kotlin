package com.example.demo.controller

import com.example.demo.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

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
}
