package com.alex.bao.ReviewGen.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * ReviewId生成器
 *
 * @Author Alex_Bao
 * @create 2017-09-12
 * create by IntelliJ IDEA
 */

@SpringBootApplication
@Controller
@RequestMapping("/review")
public class ReviewGenApplication{

	public static void main(String[] args) {
		SpringApplication.run(ReviewGenApplication.class, args);
	}

	@RequestMapping("/index")
	public String index(){
		return "review";
	}

	@RequestMapping("/generateID")
	public void generateID(HttpServletRequest request, HttpServletResponse response){
		try {
			Process p = Runtime.getRuntime().exec("python I://python/auto_rb.py");
			BufferedInputStream bufferedInputStream = new BufferedInputStream(p.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
			String readLine;
			String result = "";
			while((readLine = bufferedReader.readLine()) != null){
				result += readLine;
			}
			bufferedInputStream.close();
			bufferedReader.close();
			//输出结果.
			result = "{\"result\":\""+result+"\"}";
			this.sendAjaxResponse(result,response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送ajax结果
	 *
	 * @param result
	 * @param response
	 */
	private void sendAjaxResponse(String result, HttpServletResponse response) {
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter writer = response.getWriter();
			writer.write(result);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
