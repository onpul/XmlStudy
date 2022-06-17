/*===================================================================================
	XmlDomTest07.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 원격(remote) XML 읽어내기
	  (https://fs.jtbc.joins.com/RSS/newsflash.xml)
===================================================================================*/

/*
title> JTBC News
link> https://fs.jtbc.joins.com/RSS/newsflash.xml
description> 속보 RSS
copyright> Copyright(C) JTBC All rights reserved.

주요 기사 --------------------------------------------------------
title > [날씨] 전국 흐리다가 오후부터 차차 갬…서울 낮 기온 27도
description > 목요일인 오늘(16일)은 전국이 대체로 흐리다가 오후부터 차차 갤 것으로 보입니다.오전까지 강원 지역에는 5㎜ 미만의 비가 이어지고 경기 동부와 경북, 충북 일부 지역에도 적은 양의 비가 내리겠습니다.비가 갠
link > https://news.jtbc.joins.com/article/article.aspx?news_id=NB12062697
pubDate : 2022.06.16

title > [날씨] 전국 흐리다가 오후부터 차차 갬…서울 낮 기온 27도
description > 목요일인 오늘(16일)은 전국이 대체로 흐리다가 오후부터 차차 갤 것으로 보입니다.오전까지 강원 지역에는 5㎜ 미만의 비가 이어지고 경기 동부와 경북, 충북 일부 지역에도 적은 양의 비가 내리겠습니다.비가 갠
link > https://news.jtbc.joins.com/article/article.aspx?news_id=NB12062697
pubDate > 2022.06.16

title > [날씨] 전국 흐리다가 오후부터 차차 갬…서울 낮 기온 27도
description > 목요일인 오늘(16일)은 전국이 대체로 흐리다가 오후부터 차차 갤 것으로 보입니다.오전까지 강원 지역에는 5㎜ 미만의 비가 이어지고 경기 동부와 경북, 충북 일부 지역에도 적은 양의 비가 내리겠습니다.비가 갠
link > https://news.jtbc.joins.com/article/article.aspx?news_id=NB12062697
pubDate > 2022.06.16
*/

package com.test;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlDomTest07
{
	public static void main(String[] args)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			String str = "https://fs.jtbc.joins.com/RSS/newsflash.xml";
			
			// URL 객체 구성 
			URL url = new URL(str);
			
			// 데이터 내용 읽어오기
			InputSource is = new InputSource(url.openStream());
			xmlObj = builder.parse(is);
			
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			// 테스트
			//System.out.println(root.getNodeName());
			
			// 특정 하위 엘리먼트에 접근
			NodeList item = root.getElementsByTagName("item");
			
			// 테스트
			//System.out.println(item.getLength());
			
			// channel 엘리먼트 접근
			NodeList channel = root.getElementsByTagName("channel");
			//System.out.println(channel.getLength());
			
			Node channelTitleNode = channel.item(0);
			
			Element channelTitleElement = (Element)channelTitleNode;
			System.out.printf("%ntitle > %s", XMLDOM.getText(channelTitleElement, "title"));
			
			Node channelLinkNode = channel.item(0);
			
			Element channelLinkElement = (Element)channelLinkNode;
			System.out.printf("%nlink > %s", XMLDOM.getText(channelLinkElement, "link"));
			
			Node channelDescriptionNode = channel.item(0);
			
			Element channelDescriptionElement = (Element)channelDescriptionNode;
			System.out.printf("%ndescription > %s", XMLDOM.getText(channelDescriptionElement, "description"));
			
			Node copyrightNode = channel.item(0);
			
			Element copyrightElement = (Element)copyrightNode;
			System.out.printf("%ncopyright > %s%n", XMLDOM.getText(copyrightElement, "copyright"));
			
			for (int i = 0; i < item.getLength(); i++)
			{
				// ○title
				Node titleNode = item.item(i);
				
				// 캐스팅
				Element titleElement = (Element)titleNode;
				System.out.printf("%ntitle > %s", XMLDOM.getText(titleElement, "title"));
				
				// ○description
				Node descriptionNode = item.item(i);
				
				// 캐스팅
				Element descriptionElement = (Element)descriptionNode;
				System.out.printf("%ndescription > %s", XMLDOM.getText(descriptionElement, "description"));
		
				// ○link
				Node linkNode = item.item(i);
				
				// 캐스팅
				Element linkElement = (Element)linkNode;
				System.out.printf("%nlink > %s", XMLDOM.getText(linkElement, "link"));
				
				// ○ pubDate
				Node pubDateNode = item.item(i);
				
				// 캐스팅
				Element pubDateElement = (Element)pubDateNode;
				System.out.printf("%npubDate > %s%n", XMLDOM.getText(pubDateElement, "pubDate"));
				
			}
			
			
			
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}
}
