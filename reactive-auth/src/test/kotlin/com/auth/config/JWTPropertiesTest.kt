package com.auth.config

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class JWTPropertiesTest: BehaviorSpec({

    val properties = JWTProperties(
        issuer = "test",
        subject = "test",
        expiresTime = 3600,
        secret = "test",
    )

    Given("JWTProperties 객체가 주입되었을 때") {
        // given
        val expectedIssuer = "test"
        val expectedSubject = "test"
        val expectedExpiresTime = 3600L
        val expectedSecret = "test"

        When("객체의 필드값을 조회하면") {
            // when
            val actualIssuer = properties.issuer
            val actualSubject = properties.subject
            val actualExpiresTime = properties.expiresTime
            val actualSecret = properties.secret

            Then("예상한 값과 일치한다") {
                // then
                actualIssuer shouldBe expectedIssuer
                actualSubject shouldBe expectedSubject
                actualExpiresTime shouldBe expectedExpiresTime
                actualSecret shouldBe expectedSecret
            }
        }
    }

})
