package com.demo.alipay.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.demo.alipay.config.AlipayConfig;

@Controller
@RequestMapping("/pay")
public class AlipayController {

	@RequestMapping("/paypage")
	public String gotoPaypage() {
		return "payindex"; // 跳转到的支付页面名称
	}

	@RequestMapping("/gopay")
	public void goPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 付款金额，必填
		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
		// 订单名称，必填
		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
		// 商品描述，可空
		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		// + "\"total_amount\":\""+ total_amount +"\","
		// + "\"subject\":\""+ subject +"\","
		// + "\"body\":\""+ body +"\","
		// + "\"timeout_express\":\"10m\","
		// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		// 请求
		String head = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'></head>";
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		String buttom = "<body></body></html>";

		// 输出
		response.getWriter().println(head + result + buttom);
	}

}
