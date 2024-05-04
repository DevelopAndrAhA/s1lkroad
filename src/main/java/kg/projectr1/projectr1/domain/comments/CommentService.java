package kg.projectr1.projectr1.domain.comments;

import kg.projectr1.projectr1.domain.comments.dto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    List<CommentResponseDto> findComments(Long productId);
}
