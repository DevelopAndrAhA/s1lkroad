package kg.projectr1.projectr1.domain.comments;

import kg.projectr1.projectr1._generic.Mapper;
import kg.projectr1.projectr1.domain.comments.dto.CommentResponseDto;
import kg.projectr1.projectr1.domain.comments.dto.CommentSaveDto;
import kg.projectr1.projectr1.domain.comments.dto.CommentUpdateDto;

public interface CommentMapper extends
        Mapper<Comment, CommentResponseDto, CommentSaveDto, CommentUpdateDto> {
}
