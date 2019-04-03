package com.musicbar.second.comm.xss;

import java.io.IOException;

import org.apache.tomcat.util.buf.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

public class JsoupUtil {
	/**
	 * 使用自带的basicWithImages 白名单
	 * 允许的便签有a,b,blockquote,br,cite,code,dd,dl,dt,em,i,li,ol,p,pre,q,small,span,
	 * strike,strong,sub,sup,u,ul,img
	 * 以及a标签的href,img标签的src,align,alt,height,width,title属性
	 */
	private static final Whitelist whitelist = Whitelist.basicWithImages();
	/** 配置过滤化参数,不对代码进行格式化 */
	private static final Document.OutputSettings outputSettings = new Document.OutputSettings().prettyPrint(false);
	static {
		// 富文本编辑时一些样式是使用style来进行实现的
		// 比如红色字体 style="color:red;"
		// 所以需要给所有标签添加style属性
		whitelist.addAttributes(":all", "style")
		.removeProtocols("a", "href", "ftp", "http", "https", "mailto");
		/*.addAttributes("a", "onclick")
		.addTags("div")
		
		.addAttributes(":all", "data-url");*/
		
		//.addAttributes("a", "href");
		
	}
 
	public static String clean(String content) {
	    if(content!= null && !content.isEmpty()){
	        content = content.trim();
        }
        return Jsoup.clean(content, "", whitelist, outputSettings);
	}
	
	public static void main(String[] args) throws IOException {
		String text = "<div onclick=\"alert(1);\" data-url=\"ccc\"> \n  <a href=\"www.baidu.com/a\" onclick=\"alert(1);\">sss</a><script>alert(0);</script>sss</div>   ";
		System.out.println(clean(text));
	}
}
