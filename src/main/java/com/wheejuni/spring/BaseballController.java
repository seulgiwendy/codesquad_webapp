package com.wheejuni.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;

@Controller
public class BaseballController {
	
	public static ArrayList computerball = BallShuffler.returnRandint();
	public static String lastwinner = null;
	public static int lastwinnerscore = 0;
	public int attempt = 1;
	@RequestMapping(value = "/baseball", method = RequestMethod.GET)
	
	public ModelAndView returnNumber(@RequestParam("inputvalue") String inputnumber, @RequestParam("subvalue") String nextnumber, @RequestParam("lastvalue") String lastnumber) {
		
		
		System.out.println(computerball.toString());
		System.out.println("input data = " + inputnumber + ", next data = " + nextnumber + ", last data =" + lastnumber);
		
		ArrayList <Integer> userInputList = new ArrayList<Integer>();
		
		ModelAndView mav = new ModelAndView("baseball/result");
		ModelAndView wrong = new ModelAndView("baseball/false");
		
		userInputList.add(Integer.parseInt(inputnumber));
		userInputList.add(Integer.parseInt(nextnumber));
		userInputList.add(Integer.parseInt(lastnumber));
		
		int strike = Umpire.Strike(userInputList, computerball);
		int ball = Umpire.Ball(userInputList, computerball);
		String resultString = null;
		
		boolean progswitch = true;
		
			if (strike != 3) {
				resultString = ("정답을 맞추지 못했습니다.볼카운트는: " + ball + "B " + strike + "S 입니다.");
				String answer = "이번 한 번만 답을 알랴줌. 답은: " + computerball.toString();
				wrong.addObject("response", resultString);
				wrong.addObject("answer", answer);
				wrong.addObject("try", attempt);
				attempt++;
			}
			else {
				resultString = "정답을 맞췄습니다! 대단합니다!";
				
				progswitch = false;
				mav.addObject("response", resultString);
				mav.addObject("winnername", lastwinner);
				mav.addObject("winnerscore", lastwinnerscore);
				lastwinnerscore = attempt;
				computerball = BallShuffler.returnRandint();
				attempt = 1;
				return mav;
				
			}
			return wrong;
		
		
		
		//mav.addObject("value", topnumber);
		
		
	}
	
	@RequestMapping(value = "/winner", method = RequestMethod.GET)
	public ModelAndView winnerAdd(@RequestParam("name") String name) {
		ModelAndView resultpage = new ModelAndView("/baseball/success");
		BaseballController.lastwinner = name;
		resultpage.addObject("yourname", name);
		return resultpage;
	}
	@RequestMapping(value = "/ballgame", method = RequestMethod.GET)
	public String returnForm() {
		return "/baseball/form.html";
	}

}
