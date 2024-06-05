package com.example.demo.common.exception

/**
 * 에러코드.
 */
enum class ErrorCode(
    val code: String,
    val status: Int,
    val message: String,
) {
    // 공통 오류
    BAD_REQUEST("DEMO_0000", 400, "서버가 요청 구문을 인식하지 못했습니다."),
    UNSUPPORTED_MEDIA_TYPE("DEMO_0001", 400, "지원하지 않는 미디어 타입입니다."),
    NOT_ACCEPTABLE("DEMO_0002", 400, "지원하지 않는 컨텐츠 타입입니다."),
    REQUEST_DATA_NOT_FOUND("DEMO_0003", 400, "요청값이 존재하지 않습니다."),
    FEIGN_CLIENT_REQUEST_FAIL("DEMO_0004", 400, "Feign Client 요청이 실패했습니다."),
    UNAUTHORIZED("DEMO_0005", 401, "인증이 필요합니다."),
    FORBIDDEN("DEMO_0006", 403, "권한이 없습니다."),
    ENTITY_NOT_FOUND("DEMO_0007", 404, "요청한 리소스를 찾을 수 없습니다."),
    REQUEST_MAPPING_NOT_FOUND("DEMO_0008", 404, "허용되지 않은 URL입니다."),
    INVALID_INPUT_VALUE("DEMO_0009", 422, "입력 값이 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR("DEMO_0010", 500, "서버에 오류가 발생하여 요청을 수행하지 못했습니다."),
    GATEWAY_TIMEOUT("SHOP_0011", 504, "서버가 응답하지 않습니다."),
    ;

    companion object {
        /**
         * Http Status Code에 알맞은 공통 오류 코드 객체 code 값을 리턴.
         *
         * @param httpStatusCode Http Status Code
         * @return 공통 오류 코드 객체 code 값
         * */
        fun of(httpStatusCode: Int): ErrorCode {
            return when (httpStatusCode) {
                400 -> BAD_REQUEST
                401 -> UNAUTHORIZED
                403 -> FORBIDDEN
                404 -> ENTITY_NOT_FOUND
                406 -> NOT_ACCEPTABLE
                415 -> UNSUPPORTED_MEDIA_TYPE
                422 -> INVALID_INPUT_VALUE
                500 -> INTERNAL_SERVER_ERROR
                504 -> GATEWAY_TIMEOUT
                else -> INTERNAL_SERVER_ERROR
            }
        }
    }
}
