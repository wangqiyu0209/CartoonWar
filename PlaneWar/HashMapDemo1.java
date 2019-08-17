package com.neuedu.test9;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
* @ClassName: HashMapDemo1
* @Description: Map����
* @author wqy
* @date 2019��8��16�� ����11:04:07
*
*/
public class HashMapDemo1 {

	public static void main(String[] args) {
		// key���û���   value ��Ź�����Ϣ ����Ʒ
		HashMap<String,Product> hashMap = new HashMap<String,Product>();
		hashMap.put("bob", new Product(1, "������Ѽ", 132.2F));
		hashMap.put("tom", new Product(2, "��ͬ������",45.00F));
		Product ben = new Product (3 , "������˿" ,34.23F);
		System.out.println(hashMap);
		
		//������
		Set<String> keySet = hashMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
		}
		
		
		/*
		
		// clear()���
		//hashMap.clear();
		//System.out.println(hashMap);
		
		// containsKey() key�Ƿ���hashMap������	 containsValue()  value�Ƿ���hashMap������
		boolean containsKey = hashMap.containsKey("bob");
		System.out.println(containsKey);		
		boolean containsValue = hashMap.containsValue(ben);
		System.out.println(containsValue);
		
		// get()
		Product pro = hashMap.get("tom");
		System.out.println(pro);
		
		// addAll �����ӵ���һ���������������ͬ���� 
		// putAll()��Ӷ��HashMap����
		HashMap<String,Product> hashMap2 = new HashMap<String,Product>();
		hashMap.put("С��",new Product(4,"����",1000000F));
		hashMap.put("С��",new Product(4,"����֮��",100000F));
		hashMap.putAll(hashMap2);
		System.out.println(hashMap);
		
		// remove()ɾ��
		Product remove = hashMap.remove("С��");		
		System.out.println(remove);
		System.out.println(hashMap);
		
		// replace()�滻
		hashMap.replace("С��", new Product(4,"����",2000000F));
		System.out.println(hashMap);
		
		// size() ���ϵĴ�С
		System.out.println(hashMap.size());	
		
		*/
	}
	
}
