package example.mykarsolcrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import example.mykarsolcrud.entities.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
