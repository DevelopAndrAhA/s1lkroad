package kg.projectr1.projectr1.common.pagination;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageRequestDto {
    private int page;
    private int size;
    private List<SortDto> sort;


    public Pageable toPageable() {
        Sort sort = Sort.by(this.sort.stream()
                .map(sortDto -> new Sort.Order(Sort.Direction.fromString(sortDto.getDirection()), sortDto.getProperty()))
                .toArray(Sort.Order[]::new));

        return PageRequest.of(page, size, sort);
    }

}
