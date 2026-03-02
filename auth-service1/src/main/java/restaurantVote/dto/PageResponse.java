package restaurantVote.dto;

import lombok.Data;

import java.util.List;

// Класс для ответа с пагинацией
@Data
public class PageResponse<T> {
    private List<T> content;
    private int page;
    private int pageSize;
    private int totalElements;
    private int totalPages;

    public PageResponse(List<T> content, int page, int pageSize, int totalElements) {
        this.content = content;
        this.page = page;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);
    }
}
