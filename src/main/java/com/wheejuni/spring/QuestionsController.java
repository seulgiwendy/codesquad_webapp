package com.wheejuni.spring;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wheejuni.spring.domain.Answer;
import com.wheejuni.spring.domain.AnswerRepository;
import com.wheejuni.spring.domain.Question;
import com.wheejuni.spring.domain.QuestionRepository;
import com.wheejuni.spring.domain.User;

@Controller
public class QuestionsController {
	
	@Autowired
	QuestionRepository questionRepo;
	@Autowired
	AnswerRepository answerRepo;
	
	static ArrayList<Question>questions = new ArrayList<>();
	ArrayList <Answer> answers = new ArrayList<>();
	AnswersStore as = new AnswersStore();
	@PostMapping("/qna")
	public ModelAndView create(Question question, HttpSession session) {
		
		User loggedInUser = (User) session.getAttribute("loginuser");
		question.setTime();
		question.setWriter(loggedInUser);
		//question.setAnswers(AnswerController.answers);
		questionRepo.save(question);
		questions.add(question);
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/qna")
	public ModelAndView list() {
		
		ModelAndView mav = new ModelAndView("qna/list");
		mav.addObject("questions", questionRepo.findAll());
		return mav;
	}
	
	@GetMapping("/qna/{index}")
	
	public ModelAndView show(@PathVariable int index) {
		
		//Question question = questions.get(index);
		Question question = questionRepo.findOne((long)index);
		User writer = question.getWriter();
		ModelAndView showDetailQuestion = new ModelAndView("qna/show");
		showDetailQuestion.addObject("qnainfo", question);
		showDetailQuestion.addObject("destIndex", index);
		showDetailQuestion.addObject("qnawriter", writer.getUsername());
		showDetailQuestion.addObject("answerinfo",question.getAnswerDb());
		//System.out.println(question.getAnswers().get(0).getContent());
		showDetailQuestion.addObject("answercount", question.getAnswerDb().size());
		//showDetailQuestion.addObject("writer", question.getWriter());

		
		return showDetailQuestion;
	}
	
	@GetMapping("/qna/{index}/modify")
	public String modifyQuestion(@PathVariable long index, HttpSession session, Model model) {
		Question question = questionRepo.findOne(index);
		User loginUser = (User)session.getAttribute("loginuser");
		if (loginUser == null) {
			return "qna/modifyFailMessage";
		}
		User originalWriter = question.getWriter();
		System.out.println("logged in user is : " + loginUser.getUsername());
		System.out.println("article writer was : " + originalWriter.getUsername());
		if (loginUser.getUniqueId() != originalWriter.getUniqueId()) {
			return "qna/modifyFailMessage";
		}
		model.addAttribute("originalContents", question);
		return "qna/modifyForm";
	}
	
	@PostMapping("/qna/{index}")
	public ModelAndView addAnswer(@PathVariable int index, Answer answer, HttpSession session) {
		User answerWriteUser = (User)session.getAttribute("loginuser");
		answer.setTime();
		answer.setAuthor();
		answer.setQuestion(questionRepo.questionid((long) index));
		answer.setWriter(answerWriteUser);
		answerRepo.save(answer);
		
		Question temp = questionRepo.questionid((long)index);
		//temp.setAnswerDb(answer);
		questionRepo.save(temp);
		
		System.out.println(answer.getContent());
		
		
		//questions.remove(index);
		//questions.add(index, temp);
		//System.out.println(questions.get(index).getAnswers().get(0).getContent());
		
		return new ModelAndView("redirect:/qna/{index}");
	}
	
	@PostMapping("/qna/{index}/modify")
	public ModelAndView modifyQuestion(@PathVariable long index, Question question) {
		
		Question originalQuestion = questionRepo.findOne(index);
		originalQuestion.update(question);
		questionRepo.save(originalQuestion);
		System.out.println("MODIFY REQUEST RECEIVED");
		return new ModelAndView("redirect:/qna/{index}");
	}
	
	@GetMapping ("/qna/add")
	public ModelAndView addQuestion(HttpSession session) {
		if (session.getAttribute("loginuser") == null) {
			return new ModelAndView("qna/gologin");
		}
		return new ModelAndView("qna/form");
	}

}
