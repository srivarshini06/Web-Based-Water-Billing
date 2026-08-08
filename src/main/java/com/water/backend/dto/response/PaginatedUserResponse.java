package com.water.backend.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class PaginatedUserResponse {

    private List<UserResponse> users;
    private int currentPage;
    private int totalPages;
    private long totalItems;
}