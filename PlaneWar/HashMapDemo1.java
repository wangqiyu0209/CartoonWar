package com.neuedu.test9;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
* @ClassName: HashMapDemo1
* @Description: Map集合
* @author wqy
* @date 2019年8月16日 上午11:04:07
*
*/
public class HashMapDemo1 {

	public static void main(String[] args) {
		// key存用户名   value 存放购买信息 放商品
		HashMap<String,Product> hashMap = new HashMap<String,Product>();
		hashMap.put("bob", new Product(1, "北京烤鸭", 132.2F));
		hashMap.put("tom", new Product(2, "大同刀削面",45.00F));
		Product ben = new Product (3 , "鱼香肉丝" ,34.23F);
		System.out.println(hashMap);
		
		//迭代器
		Set<String> keySet = hashMap.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(next);
		}
		
		
		/*
		
		// clear()清除
		//hashMap.clear();
		//System.out.println(hashMap);
		
		// containsKey() key是否在hashMap集合中	 containsValue()  value是否在hashMap集合中
		boolean containsKey = hashMap.containsKey("bob");
		System.out.println(containsKey);		
		boolean containsValue = hashMap.containsValue(ben);
		System.out.println(containsValue);
		
		// get()
		Product pro = hashMap.get("tom");
		System.out.println(pro);
		
		// addAll 后边添加的类一定是他的子类或者同本类 
		// putAll()添加多个HashMap集合
		HashMap<String,Product> hashMap2 = new HashMap<String,Product>();
		hashMap.put("小红",new Product(4,"红旗",1000000F));
		hashMap.put("小王",new Product(4,"长安之星",100000F));
		hashMap.putAll(hashMap2);
		System.out.println(hashMap);
		
		// remove()删除
		Product remove = hashMap.remove("小红");		
		System.out.println(remove);
		System.out.println(hashMap);
		
		// replace()替换
		hashMap.replace("小王", new Product(4,"奔驰",2000000F));
		System.out.println(hashMap);
		
		// size() 集合的大小
		System.out.println(hashMap.size());	
		
		*/
	}
	
}
