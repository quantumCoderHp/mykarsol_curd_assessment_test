package example.mykarsolcrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.mykarsolcrud.entities.Article;
import example.mykarsolcrud.repositories.ArticleRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private EmailService emailService;

	public List<Article> findAll() {
		logger.info("Fetching all articles");
		return articleRepository.findAll();
	}

	public Optional<Article> findById(Long id) {
		logger.info("Fetching article with id: {}", id);
		return articleRepository.findById(id);
	}

	public Article save(Article article) {
		if (article.getId() == null) {
			logger.info("Saving new article: {}", article.getTitle());
		} else {
			logger.info("Updating article with id: {}", article.getId());
		}
		return articleRepository.save(article);
	}

	public void deleteById(Long id) {
		logger.info("Deleting article with id: {}", id);
		articleRepository.deleteById(id);
	}

	public void sendEmailToAllUsers(String subject, String text, List<String> emailAddresses) {
		logger.info("Sending email to all users with subject: {}", subject);
		for (String email : emailAddresses) {
			emailService.sendEmail(email, subject, text);
		}
	}
}