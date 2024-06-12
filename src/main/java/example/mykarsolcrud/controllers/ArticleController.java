package example.mykarsolcrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import example.mykarsolcrud.entities.Article;
import example.mykarsolcrud.services.ArticleService;
import example.mykarsolcrud.services.EmailService;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private EmailService emailService;

	@GetMapping
	public String list(Model model) {
		model.addAttribute("articles", articleService.findAll());
		return "articles/list";
	}

	@GetMapping("/add")
	public String addForm(Model model) {
		model.addAttribute("article", new Article());
		return "articles/add";
	}

	@PostMapping
	public String save(@ModelAttribute Article article) {
		articleService.save(article);

		String subject = "New Article Added";
		String text = "A new article titled '" + article.getTitle() + "' has been added.";
		sendEmailToAllUsers(subject, text);

		return "redirect:/articles";
	}

	@GetMapping("/edit/{id}")
	public String editForm(@PathVariable Long id, Model model) {
		model.addAttribute("article", articleService.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id)));
		return "articles/edit";
	}

	@PostMapping("/update/{id}")
	public String update(@PathVariable Long id, @ModelAttribute Article article) {
		article.setId(id);
		articleService.save(article);

		String subject = "Article Updated";
		String text = "The article titled '" + article.getTitle() + "' has been updated.";
		sendEmailToAllUsers(subject, text);

		return "redirect:/articles";
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		articleService.deleteById(id);

		String subject = "Article Deleted";
		String text = "An article has been deleted.";
		sendEmailToAllUsers(subject, text);

		return "redirect:/articles";
	}

	private void sendEmailToAllUsers(String subject, String text) {
		List<String> emailAddresses = List.of("user1@example.com", "user2@example.com");
		for (String email : emailAddresses) {
			emailService.sendEmail(email, subject, text);
		}
	}
}