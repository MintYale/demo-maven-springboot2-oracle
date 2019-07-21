package com.demo.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝接口对接测试工具类
 */
@Controller
@RequestMapping(value = "/alipay")
public class AlipayDemoController {

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	private String app_id = "2016092300577648";

	// 商户私钥，您的PKCS8格式RSA2私钥
	private String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKUS3TCzgiktAWU9AtTfk1Ef/iJp1oACgE8jwnsf0lPrripykleyrx4cm+6juW355fm/LXP9rxRqlYp7GsnwOLYi3IP1bUMRM2EyVHQuUY1JRw/Kzd7Kp/4BITRC6721+ZG2Jc+pDAoba+zRHVjvOSB7F+CLiCY4lxGmf7Mqh9u9mnTaooaNnJIuqrusjsBGdo3CTsqB0MyAIgnmHXVNmzRpLEgXZbGsJ1u6bE+uqPgD68q+VkA4ETTJeLCFRZhOgS11eIHFWiSBsGBaG77sVHRXbmobakswgR80uhZmclHzJWQHOCudHqTUEvMXaDXdFNvBlx0RqoY9vgO8N/IncJAgMBAAECggEAf51afCcTycJOvurhXYtdbDuJLOyXuga65aVhHva3Kw/veYLlEbHL85Nwgfa2uALlqizGJxbBOG8Le8JsyWmE6arRtrnJsMj+gpuzf0YW/YvU0VIvZCohPQ5NXv0/aQ+D6k07ubFssG4Dn5Kw66qSy57Rggklwxd3DaVhXoBkuGunRW2emz9hgUe08iAg/ZiVK5KFt+7bKxo2/73EA3r6ez+sb32S8PJcSEG2ETxLso3d8Ku8qickC4F/7WYIuVrNulTB+ozCGMOtSA0uYkJH3DnHcN3ALjb3+9wRtD3LWbsOLxOhFiYnfom+GGOSx5tVDRfc3MW91lC+T8F3jm1T6QKBgQDFm2Mh90re/96Rwo/fa+QH/MxlZ+9wBKldVo1qhta0nX1pjc8PIFp4819UZtjz1vqAY5OJk5exMr8/m4pZKQaeUyD8YrOXijk6wC9sQx6mdfX9ifrTmjDGr1sp0B/mPPAKYabWjJMSQS+0k13LEr5zfQQ7RO6B3d9Ijx/EJasgtwKBgQCzMJ6JEZ4ok3V70JlBuapXDjVSXHBQ3MbeU55iTAAwoByACQF/OXENB/7uevI+u950x45SupndxQr82JiRoHhuslCIP1EZLn+FGogaKG1ygki+Z1u7lz5PkJcB/JBRKzhgq7HwlGeryMdlxHSkRXiojoX8UTMycX7xHlJjN2fmPwKBgEauqCz0GbHecCxPHcta+jvpLutu2/SDllkupnLePFpqovtooBHXDsWIuCamix/dW8kk1Rc21eGqN0J3TmtKA3YKYhgk7fxcks/zAoOFFqeQNic0faXG13+aJc+1BFQEpA/ykgzYR5sV+gxrUfdbgwgoRaU1qOKHDGEV4NT2bFnNAoGAEA+BhiOQG27sDsbplZyAigD6AoHKh1TidjbkcUcClVkkdM1Z1v9dBJ/8138piznnfGwOc5fKBriMfZEkS5kocjOFSFD0gd9LCrEQFdgdxy+qOXamIDA0I4mAH2t1u/C1wuRgtIF4sUrzpElxcu/PGrx7gdv9TL4jdJUVKVNj+p0CgYA7fAOfBFpsodbkqyxw6tGsfIT9w18fpvk1bTsQr5/YwD1/u7vxkOnT7eYmb5BDQT4P40TItIDzMN5bu0JvBocRHL+efeNEjNOOEmiZz6FlG+a6jol/VymF+ythMF5VkIWI4YWmrAv1PDu7AGfsXmyeiG6fCKKzeVSTm3udd9XFFw==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAilEt0ws4IpLQFlPQLU35NRH/4iadaAAoBPI8J7H9JT664qcpJXsq8eHJvuo7lt+eX5vy1z/a8UapWKexrJ8Di2ItyD9W1DETNhMlR0LlGNSUcPys3eyqf+ASE0Quu9tfmRtiXPqQwKG2vs0R1Y7zkgexfgi4gmOJcRpn+zKofbvZp02qKGjZySLqq7rI7ARnaNwk7KgdDMgCIJ5h11TZs0aSxIF2WxrCdbumxPrqj4A+vKvlZAOBE0yXiwhUWYToEtdXiBxVokgbBgWhu+7FR0V25qG2pLMIEfNLoWZnJR8yVkBzgrnR6k1BLzF2g13RTbwZcdEaqGPb4DvDfyJ3CQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost/alipay/notifyUrl";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost/alipay/returnUrl";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 沙箱网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 仅支持JSON
	public static String format = "JSON";

	@ApiOperation(value = "发起支付", notes = "支付宝")
	@RequestMapping(value = "/pay", method = RequestMethod.GET)
	public void pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse)
			throws ServletException, IOException {
		AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, format, charset,
				alipay_public_key, sign_type); // 获得初始化的AlipayClient
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();// 创建API对应的request
		alipayRequest.setReturnUrl(return_url);
		alipayRequest.setNotifyUrl(notify_url);// 在公共参数中设置回跳和通知地址

		// 封装请求参数，参考 https://docs.open.alipay.com/270/alipay.trade.page.pay

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		// String out_trade_no = new
		// String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		// 付款金额，必填
		// String total_amount = new
		// String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
		// 订单名称，必填
		// String subject = new
		// String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		// 商品描述，可空
		// String body = new
		// String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

		alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"2015032001010100" + (int) (Math.random() * 1000)
				+ "\"," + "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":"
				+ (int) (Math.random() * 10) + 0.88 + "," + "    \"subject\":\"WHUT_KEI\","
				+ "    \"body\":\"PC端支付开发测试\","
				+ "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","
				+ "    \"extend_params\":{" + "    \"sys_service_provider_id\":\"2088511833207846\"" + "    }" + "  }");// 填充业务参数
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		httpResponse.setContentType("text/html;charset=" + charset);
		httpResponse.getWriter().write(form);// 直接将完整的表单html输出到页面
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
	}

	@ApiOperation(value = "支付同步回调", notes = "支付宝")
	@RequestMapping(value = "/returnUrl", method = RequestMethod.GET)
	public void returnUrl(HttpServletRequest request, HttpServletResponse response)
			throws IOException, AlipayApiException {
		System.out.println("=================================同步回调=====================================");

		// 获取支付宝GET过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		System.out.println(params);
		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, sign_type);

		// ——请在这里编写您的程序（以下代码仅作参考）——
		if (signVerified) {
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 付款金额
			String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

			System.out.println("商户订单号=" + out_trade_no);
			System.out.println("支付宝交易号=" + trade_no);
			System.out.println("付款金额=" + total_amount);

			response.getWriter().write(
					"trade_no:" + trade_no + "<br/>out_trade_no:" + out_trade_no + "<br/>total_amount:" + total_amount);
		} else {
			response.getWriter().write("验签失败");
		}
		response.getWriter().flush();
		response.getWriter().close();
	}

	@ApiOperation(value = "支付异步回调", notes = "支付宝")
	@RequestMapping(value = "/notifyUrl", method = RequestMethod.POST)
	public void notifyUrl(HttpServletRequest request, HttpServletResponse response)
			throws AlipayApiException, IOException {
		System.out.println("#################################异步回调######################################");

		// 获取支付宝POST过来反馈信息
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			// 乱码解决，这段代码在出现乱码时使用
			// valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}

		System.out.println(params);
		boolean signVerified = AlipaySignature.rsaCheckV1(params, alipay_public_key, charset, sign_type); // 调用SDK验证签名

		// ——请在这里编写您的程序（以下代码仅作参考）——

		/*
		 * 实际验证过程建议商户务必添加以下校验： 1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
		 * 2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额）， 3、校验通知中的seller_id（或者seller_email)
		 * 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
		 * 4、验证app_id是否为该商户本身。
		 */
		if (signVerified) {// 验证成功
			// 商户订单号
			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 支付宝交易号
			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

			// 交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

			System.out.println("商户订单号=" + out_trade_no);
			System.out.println("支付宝交易号=" + trade_no);
			System.out.println("交易状态=" + trade_status);
			if (trade_status.equals("TRADE_FINISHED")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
			} else if (trade_status.equals("TRADE_SUCCESS")) {
				// 判断该笔订单是否在商户网站中已经做过处理
				// 如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				// 如果有做过处理，不执行商户的业务程序

				// 注意：
				// 付款完成后，支付宝系统发送该交易状态通知
			}

			System.out.println("异步回调验证成功");
			response.getWriter().write("success");

		} else {// 验证失败
			System.out.println("异步回调验证失败");
			response.getWriter().write("fail");

			// 调试用，写文本函数记录程序运行情况是否正常
			// String sWord = AlipaySignature.getSignCheckContentV1(params);
			// AlipayConfig.logResult(sWord);
		}
		response.getWriter().flush();
		response.getWriter().close();
	}

}
