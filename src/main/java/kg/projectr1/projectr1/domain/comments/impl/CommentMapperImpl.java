package kg.projectr1.projectr1.domain.comments.impl;

import kg.projectr1.projectr1.domain.comments.Comment;
import kg.projectr1.projectr1.domain.comments.CommentMapper;
import kg.projectr1.projectr1.domain.comments.dto.CommentResponseDto;
import kg.projectr1.projectr1.domain.comments.dto.CommentSaveDto;
import kg.projectr1.projectr1.domain.comments.dto.CommentUpdateDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

@Service
public class CommentMapperImpl implements CommentMapper {
    @Override
    public Comment fromSaveDto(CommentSaveDto commentSaveDto) {
        return null;
    }

    @Override
    public Comment fromUpdateDto(CommentUpdateDto commentUpdateDto) {
        return null;
    }

    @Override
    public CommentResponseDto toResponseDto(Comment comment) {
        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .date(comment.getDate().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .rating(comment.getRating())
                .username(comment.getUsername())
                .build();
    }
}
