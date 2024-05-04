package kg.projectr1.projectr1.domain.comments.impl;

import kg.projectr1.projectr1.domain.comments.CommentMapper;
import kg.projectr1.projectr1.domain.comments.CommentRepository;
import kg.projectr1.projectr1.domain.comments.CommentService;
import kg.projectr1.projectr1.domain.comments.dto.CommentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentMapper mapper;
    @Override
    public List<CommentResponseDto> findComments(Long productId) {
        return commentRepository.findAllByProductId(productId).stream().map(mapper::toResponseDto).toList();
    }
}
