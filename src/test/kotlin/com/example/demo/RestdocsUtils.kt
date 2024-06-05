package com.example.demo

import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType.ARRAY
import org.springframework.restdocs.payload.JsonFieldType.BOOLEAN
import org.springframework.restdocs.payload.JsonFieldType.OBJECT
import org.springframework.restdocs.payload.JsonFieldType.STRING
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath

object RestdocsUtils {
    fun objectResponse(): Array<FieldDescriptor> =
        arrayOf(
            fieldWithPath("success").type(BOOLEAN).description("성공 여부"),
            fieldWithPath("code").type(STRING).description("실패 시 에러 코드").optional(),
            fieldWithPath("message").type(STRING).description("실패 시 메시지").optional(),
            fieldWithPath("data").type(OBJECT).description("응답 데이터").optional(),
        )

    fun arrayResponse(): Array<FieldDescriptor> =
        arrayOf(
            fieldWithPath("success").type(BOOLEAN).description("성공 여부"),
            fieldWithPath("code").type(STRING).description("실패 시 에러 코드").optional(),
            fieldWithPath("message").type(STRING).description("실패 시 메시지").optional(),
            fieldWithPath("data").type(ARRAY).description("응답 데이터").optional(),
        )
}
