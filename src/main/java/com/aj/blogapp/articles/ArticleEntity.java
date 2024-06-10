package com.aj.blogapp.articles;

import com.aj.blogapp.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Builder
@AllArgsConstructor
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(nullable = false)
	private Long id;

	@NonNull
	private String title;

	@NonNull
	@Column(unique = true)
	private String slug;

	private String subtitle;

	@NonNull
	private String body;

	@CreatedDate
	private Date createdAt;

	@ManyToOne
	@JoinColumn(name = "authorId", nullable = false)
	private UserEntity author;

}
