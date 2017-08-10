package com.wheejuni.spring;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.*;

import com.wheejuni.spring.domain.Answer;
import com.wheejuni.spring.domain.AnswerRepository;
import com.wheejuni.spring.domain.Question;
import com.wheejuni.spring.domain.QuestionRepository;
import com.wheejuni.spring.domain.User;
import com.wheejuni.spring.domain.UserRepository;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class AjaxController {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private UserRepository userRepo;

	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String content, HttpSession session) throws JSONException {
		if (session.getAttribute("loginuser") == null) {
			return null;
		}

		User loginUser = (User) session.getAttribute("loginuser");
		User inputUser = (User) userRepo.findOne(loginUser.getUniqueId());
		Question question = questionRepository.findOne(questionId);
		Answer answer = new Answer(inputUser, question, content);

		answerRepository.save(answer);
		return answer;

	}
}
