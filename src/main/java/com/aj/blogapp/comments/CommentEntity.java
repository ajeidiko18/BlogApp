package com.aj.blogapp.comments;

import com.aj.blogapp.articles.ArticleEntity;
import com.aj.blogapp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@Nullable
	private String title;

	@NonNull
	private String body;

	@CreatedDate
	private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "articleId", nullable = false)
    private ArticleEntity article;
    
    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    private UserEntity author;
}
