package com.customer_management.customer_pt.dto.response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppResponse<T> {

    private static final Logger logger = LoggerFactory.getLogger(AppResponse.class);

    private T data;
    private MetaDataResponseDto meta;
    private ErrorResponseDto error;
    private PagingResponseDto paging;
    private static final Integer STATUS_CODE_SUCCESS = 1;
    private static final String STATUS_SUCCESS = "Success";
    private static final Integer STATUS_CODE_ERROR = 0;
    private static final String STATUS_ERROR = "Error";

    public static <T> AppResponse<T> created(T data) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(HttpStatus.CREATED.value())
                .message(HttpStatus.CREATED.name()).build();
        logger.info("Returning an accepted response.");
        return AppResponse.<T>builder().data(data).meta(metaDto).build();
    }

    public static <T> AppResponse<T> ok(T data) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS).message(STATUS_SUCCESS)
                .build();
        logger.info("Returning a successful response.");
        return AppResponse.<T>builder().data(data).meta(metaDto).build();
    }

    public static <T> AppResponse<T> okList(T dataList, int pageSize, int pageNumber, long totalRecords) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_SUCCESS).message(STATUS_SUCCESS)
                .build();

        PagingResponseDto pagingDto = PagingResponseDto.builder().pageNumber(pageNumber).pageSize(pageSize)
                .totalRecords(totalRecords).build();
        logger.info("Returning a list response with pagination details.");
        return AppResponse.<T>builder().data(dataList).meta(metaDto).paging(pagingDto).build();
    }

    public static <T> AppResponse<T> error(T data, String status, String title, String code, String detail) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
                .build();

        ErrorResponseDto errorDto = ErrorResponseDto.builder().status(status).title(title).code(code).detail(detail)
                .build();
        logger.error("Returning an error response. Status: {}, Title: {}, Code: {}, Detail: {}", status, title, code,
                detail);

        return AppResponse.<T>builder().data(data).meta(metaDto).error(errorDto).build();
    }


    // new
    public static <T> AppResponse<T> error(String status, String title, String detail) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
                .build();

        ErrorResponseDto errorDto = ErrorResponseDto.builder().status(status).title(title).detail(detail)
                .build();

        return AppResponse.<T>builder().data(null).meta(metaDto).error(errorDto).build();
    }


    public static <T> AppResponse<T> error(String status, String detail) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
                .build();

        ErrorResponseDto errorDto = ErrorResponseDto.builder().status(status).detail(detail)
                .build();

        return AppResponse.<T>builder().data(null).meta(metaDto).error(errorDto).build();
    }


    public static <T> AppResponse<T> error(T data, String title, String detail) {
        MetaDataResponseDto metaDto = MetaDataResponseDto.builder().code(STATUS_CODE_ERROR).message(STATUS_ERROR)
                .build();

        ErrorResponseDto errorDto = ErrorResponseDto.builder().title(title).detail(detail)
                .build();

        return AppResponse.<T>builder().data(data).meta(metaDto).error(errorDto).build();
    }
}

