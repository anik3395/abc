package org.example.digitalWalletSystem.common.pagination;
import org.example.digitalWalletSystem.common.response.ApiResponse;
import org.springframework.data.domain.Page;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseUtils {

    private ResponseUtils() {
        // prevent instantiation
    }

    public static <T> ApiResponse<Map<String, Object>> buildPageableSuccessResponse(Page<T> requestPage) {
        ApiResponse apiResponse = new ApiResponse();
        Map<String, Object> responseData = new LinkedHashMap<>();
        responseData.put("content", requestPage.getContent());
        responseData.put("pageable", requestPage.getPageable());
        responseData.put("last", requestPage.isLast());
        responseData.put("totalPages", requestPage.getTotalPages());
        responseData.put("totalElements", requestPage.getTotalElements());
        responseData.put("size", requestPage.getSize());
        responseData.put("number", requestPage.getNumber());
        responseData.put("sort", requestPage.getSort());
        responseData.put("first", requestPage.isFirst());
        responseData.put("numberOfElements", requestPage.getNumberOfElements());
        responseData.put("empty", requestPage.isEmpty());

        apiResponse.setData(responseData);

        return apiResponse;
    }
}
