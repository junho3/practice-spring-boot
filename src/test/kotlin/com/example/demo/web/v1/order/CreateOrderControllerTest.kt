package com.example.demo.web.v1.order

import com.example.demo.RestDocsTest
import com.example.demo.RestdocsUtils
import io.kotest.core.spec.DisplayName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.PayloadDocumentation
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RestDocsTest
@DisplayName("CreateOrderController")
internal class CreateOrderControllerTest(
    private val mockMvc: MockMvc,
) : DescribeSpec(
        {
            extensions(SpringExtension)

            describe("responseStatus()") {

                context("올바른 요청이 들어오면") {

                    it("201 응답을 리턴한다.") {
                        mockMvc
                            .perform(
                                RestDocumentationRequestBuilders.post("/v1/orders/response-status")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON),
                            ).andExpect(MockMvcResultMatchers.status().isCreated)
                            .andDo(
                                MockMvcRestDocumentation.document(
                                    "post/v1/orders/response-status",
                                    PayloadDocumentation
                                        .responseFields(*RestdocsUtils.objectResponse())
                                        .and(
                                            PayloadDocumentation
                                                .fieldWithPath("data.id")
                                                .description("주문 id"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.memberName")
                                                .description("회원명"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[]")
                                                .description("상품 목록"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[].id")
                                                .description("상품 id"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[].name")
                                                .description("상품 이름"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[].quantity")
                                                .description("상품 수량"),
                                        ),
                                ),
                            )
                            .andReturn()
                    }
                }
            }

            describe("responseEntity()는") {

                context("올바른 요청이 들어오면") {

                    it("201 응답을 리턴한다.") {
                        mockMvc
                            .perform(
                                RestDocumentationRequestBuilders.post("/v1/orders/response-entity")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON),
                            ).andExpect(MockMvcResultMatchers.status().isCreated)
                            .andDo(
                                MockMvcRestDocumentation.document(
                                    "post/v1/orders/response-entity",
                                    PayloadDocumentation
                                        .responseFields(*RestdocsUtils.objectResponse())
                                        .and(
                                            PayloadDocumentation
                                                .fieldWithPath("data.id")
                                                .description("주문 id"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.memberName")
                                                .description("회원명"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[]")
                                                .description("상품 목록"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[].id")
                                                .description("상품 id"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[].name")
                                                .description("상품 이름"),
                                            PayloadDocumentation
                                                .fieldWithPath("data.products[].quantity")
                                                .description("상품 수량"),
                                        ),
                                ),
                            )
                            .andReturn()
                    }
                }
            }
        },
    )
