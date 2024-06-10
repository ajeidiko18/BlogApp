package com.aj.blogapp.articles;

import com.aj.blogapp.articles.dtos.CreateArticleRequest;
import com.aj.blogapp.articles.dtos.UpdateArticleRequest;
import com.aj.blogapp.users.UsersRepository;
import com.aj.blogapp.users.UsersService;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {

    private ArticlesRepository articlesRepository;
    private UsersRepository usersRepository;

    public ArticlesService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    public Iterable<ArticleEntity> getAllArticles() {
        return articlesRepository.findAll();
    }

    public ArticleEntity getArticleBySlug(String slug) {
        var article = articlesRepository.findBySlug(slug);
        if(article == null) {
            throw new ArticleNotFoundException(slug);
        }
        return article;
    }



    public ArticleEntity createArticle(CreateArticleRequest a, Long autherId){

        var author = usersRepository.findById(autherId).orElseThrow(()-> new UsersService.UserNotFoundException(autherId));

       return articlesRepository.save(ArticleEntity.builder()
               .title(a.getTitle())
               // TODO create a proper slugification function
               .slug(a.getTitle().toLowerCase().replaceAll("\\s+","- "))
               .subtitle(a.getSubtitle())
               .body(a.getBody())
               .author(author)
               .build());
    }

    public ArticleEntity updateArticle(Long articleId, UpdateArticleRequest a){
        var article=articlesRepository.findById(articleId).orElseThrow(()->new ArticlesService.ArticleNotFoundException(articleId));

        if(a.getTitle() != null){
            article.setTitle(a.getTitle());
        }
        if(a.getBody() != null){
            article.setBody(a.getBody());
        }
        if(a.getSubtitle()!= null){
            article.setSubtitle(a.getSubtitle());
        }
        return articlesRepository.save(article);
    }

    public static class ArticleNotFoundException extends IllegalArgumentException{

        public ArticleNotFoundException(String slug){

            super("Article" + slug + "not found");
        }

        public ArticleNotFoundException(Long artileId){

            super("Article with Id:" + artileId + "not found");
        }
    }
}
