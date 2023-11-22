package com.edublog.fixtures;

import com.edublog.domain.dto.comment.CommentPostInputDto;
import com.edublog.domain.model.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class CommentFixture {
    public static Comment gimmeValidCommentFixture() {
        return Comment.builder()
                .comment("This is a test comment.")
                .author("John Doe")
                .createdAt(LocalDateTime.parse("2023-11-09T15:30:00"))
                .account(AccountFixture.gimmeValidAccountFixture())
                .article(ArticleFixture.gimmeValidArticleEntity()) // You may need to adjust this based on your Article creation logic
                .build();
    }

    public static CommentPostInputDto gimmeValidCommentPostInputDtoFixture() {
        return CommentPostInputDto.builder()
                .comment("This is a test comment.")
                .build();
    }

    public static List<Comment> gimmeValidCommentList() {
        return List.of(CommentFixture.gimmeValidCommentFixture());
    }
}
