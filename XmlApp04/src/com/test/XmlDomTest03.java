/*==================================================
	XmlDomTest03.java
	- 콘솔 기반 자바 프로그램
	- XML DOM 활용 → 로컬(local) XML 읽어내기
	  (breakfast_menu.xml)
==================================================*/

// breakfast_menu.xml 파일을 대상으로
/*
■[Belgian Waffles]	$5.95	650칼로리
 - Two of our famous Belgian Waffles with plenty of real maple syrup
----------------------------------------------------------------------
■[Belgian Waffles]	$5.95	650칼로리
 - Two of our famous Belgian Waffles with plenty of real maple syrup
----------------------------------------------------------------------
■[Belgian Waffles]	$5.95	650칼로리
 - Two of our famous Belgian Waffles with plenty of real maple syrup
----------------------------------------------------------------------
*/
// 이와 같이 결과 출력이 이루어질 수 있도록 프로그램을 작성한다.
package com.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlDomTest03
{
	public static void main(String[] args)
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlObj = null;
			
			// XML 파일을 메모리에 로드
			String url = "breakfast_menu.xml";
			xmlObj = builder.parse(url);
			
			// 루트 엘리먼트 접근
			Element root = xmlObj.getDocumentElement();
			
			// 테스트
			//System.out.println(root.getNodeName());
			//--==>> breakfast_menu
			
			// 특정 하위 엘리먼트에 접근
			NodeList foodList = root.getElementsByTagName("food");
			
			// 테스트
			//System.out.println(foodList.getLength());
			//--==>> 5
			
			for (int i = 0; i < foodList.getLength(); i++)
			{
				Node foodNode = foodList.item(i);
				
				// 캐스팅
				Element foodElement = (Element)foodNode;
				
				/*
				■[Belgian Waffles]	$5.95	650칼로리
				 - Two of our famous Belgian Waffles with plenty of real maple syrup
				---------------------------------------------------------------------- 
				*/
				
				System.out.printf("■[%s] %s	%s칼로리%n - %s"
						         , getText(foodElement, "name")
						         , getText(foodElement, "price")
						         , getText(foodElement, "calories")
						         , getText(foodElement, "description"));
				System.out.println("\n-----------------------------------------------------------------------------------------");
				
			}
			
		} catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	
	private static String getText(Element parent, String tagName)
	{
		// 반환할 결과값
		String result = "";
		
		// 특정 태그 이름을 가진 첫 번째 자식 얻어오기
		Node node = parent.getElementsByTagName(tagName).item(0);
		Element element = (Element)node;
		
		// 특정 엘리먼트의 자식 노드 값을 가져오기
		result = element.getChildNodes().item(0).getNodeValue();
		
		// 결과값 반환
		return result;
	}
	
}
