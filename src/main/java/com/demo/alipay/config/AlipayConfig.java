package com.demo.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092300577648";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCKUS3TCzgiktAWU9AtTfk1Ef/iJp1oACgE8jwnsf0lPrripykleyrx4cm+6juW355fm/LXP9rxRqlYp7GsnwOLYi3IP1bUMRM2EyVHQuUY1JRw/Kzd7Kp/4BITRC6721+ZG2Jc+pDAoba+zRHVjvOSB7F+CLiCY4lxGmf7Mqh9u9mnTaooaNnJIuqrusjsBGdo3CTsqB0MyAIgnmHXVNmzRpLEgXZbGsJ1u6bE+uqPgD68q+VkA4ETTJeLCFRZhOgS11eIHFWiSBsGBaG77sVHRXbmobakswgR80uhZmclHzJWQHOCudHqTUEvMXaDXdFNvBlx0RqoY9vgO8N/IncJAgMBAAECggEAf51afCcTycJOvurhXYtdbDuJLOyXuga65aVhHva3Kw/veYLlEbHL85Nwgfa2uALlqizGJxbBOG8Le8JsyWmE6arRtrnJsMj+gpuzf0YW/YvU0VIvZCohPQ5NXv0/aQ+D6k07ubFssG4Dn5Kw66qSy57Rggklwxd3DaVhXoBkuGunRW2emz9hgUe08iAg/ZiVK5KFt+7bKxo2/73EA3r6ez+sb32S8PJcSEG2ETxLso3d8Ku8qickC4F/7WYIuVrNulTB+ozCGMOtSA0uYkJH3DnHcN3ALjb3+9wRtD3LWbsOLxOhFiYnfom+GGOSx5tVDRfc3MW91lC+T8F3jm1T6QKBgQDFm2Mh90re/96Rwo/fa+QH/MxlZ+9wBKldVo1qhta0nX1pjc8PIFp4819UZtjz1vqAY5OJk5exMr8/m4pZKQaeUyD8YrOXijk6wC9sQx6mdfX9ifrTmjDGr1sp0B/mPPAKYabWjJMSQS+0k13LEr5zfQQ7RO6B3d9Ijx/EJasgtwKBgQCzMJ6JEZ4ok3V70JlBuapXDjVSXHBQ3MbeU55iTAAwoByACQF/OXENB/7uevI+u950x45SupndxQr82JiRoHhuslCIP1EZLn+FGogaKG1ygki+Z1u7lz5PkJcB/JBRKzhgq7HwlGeryMdlxHSkRXiojoX8UTMycX7xHlJjN2fmPwKBgEauqCz0GbHecCxPHcta+jvpLutu2/SDllkupnLePFpqovtooBHXDsWIuCamix/dW8kk1Rc21eGqN0J3TmtKA3YKYhgk7fxcks/zAoOFFqeQNic0faXG13+aJc+1BFQEpA/ykgzYR5sV+gxrUfdbgwgoRaU1qOKHDGEV4NT2bFnNAoGAEA+BhiOQG27sDsbplZyAigD6AoHKh1TidjbkcUcClVkkdM1Z1v9dBJ/8138piznnfGwOc5fKBriMfZEkS5kocjOFSFD0gd9LCrEQFdgdxy+qOXamIDA0I4mAH2t1u/C1wuRgtIF4sUrzpElxcu/PGrx7gdv9TL4jdJUVKVNj+p0CgYA7fAOfBFpsodbkqyxw6tGsfIT9w18fpvk1bTsQr5/YwD1/u7vxkOnT7eYmb5BDQT4P40TItIDzMN5bu0JvBocRHL+efeNEjNOOEmiZz6FlG+a6jol/VymF+ythMF5VkIWI4YWmrAv1PDu7AGfsXmyeiG6fCKKzeVSTm3udd9XFFw==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAilEt0ws4IpLQFlPQLU35NRH/4iadaAAoBPI8J7H9JT664qcpJXsq8eHJvuo7lt+eX5vy1z/a8UapWKexrJ8Di2ItyD9W1DETNhMlR0LlGNSUcPys3eyqf+ASE0Quu9tfmRtiXPqQwKG2vs0R1Y7zkgexfgi4gmOJcRpn+zKofbvZp02qKGjZySLqq7rI7ARnaNwk7KgdDMgCIJ5h11TZs0aSxIF2WxrCdbumxPrqj4A+vKvlZAOBE0yXiwhUWYToEtdXiBxVokgbBgWhu+7FR0V25qG2pLMIEfNLoWZnJR8yVkBzgrnR6k1BLzF2g13RTbwZcdEaqGPb4DvDfyJ3CQIDAQAB";

	// 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	// public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";

	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do"; // 沙箱环境支付宝网关

	// 日志文件存储路径
	public static String log_path = "D:/logs";

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	/**
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 *
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis() + ".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
