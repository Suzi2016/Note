package cn.ihsuzi.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ValidateImg extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=UTF-8");
		response.setDateHeader("Expires", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		
		// 构建一张图片
		int width = 100;
		int height = 30;
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		// 往图片上写字
		// 拿到画布
		Graphics2D graphics = (Graphics2D) img.getGraphics();
		// 设置背景色
		graphics.setColor(new Color(222, 222, 222));
		graphics.fillRect(0, 0, width, height);
		// 设置边框
		graphics.setColor(Color.WHITE);
		graphics.drawRect(0, 0, width-1, height-1);
		// 画干扰线
		
		// 写字
		graphics.setColor(Color.black);
		graphics.setFont(new Font("Courier New", Font.PLAIN, 22));
		String valistr = getValidateImgNum();
		graphics.drawString(valistr, 20, 22);
		
		// 将验证码存到 session，方便后面验证
		request.getSession().setAttribute("valistr",valistr);
		
		// 输出验证码图片
		ImageIO.write(img, "jpg", response.getOutputStream());
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}

	private Random random = new Random();
	private String getValidateImgNum()
	{
		String s = "";
		for (int i = 0; i < 5; i++)
		{
			s += random.nextInt(10);
		}
		System.out.println(s);
		return s;
	}
}
