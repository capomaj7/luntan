/**
 * 
 */
package com.phn.test;

import java.util.ArrayList;
import java.util.Collection;




public class TestContain {
	 public static void main(String[] args) {
	        //建立集合
	        Collection<Object>  C = new ArrayList<Object>();
	        //添加元素
	        C.add(1);
	        //判断是否包含
	        System.out.println(C.contains(1));//true
	         
	        Integer m = new Integer(50);
	        C.add(m);
	        Integer n = new Integer(50);
	        //
	        System.out.println(C.contains(n));//true 说明底层已经重写了equals方法
	        Manager m1 = new Manager(100, "jack");
	        Zhushou zhushou=new Zhushou("tom");
	        m1.zhushou=zhushou;
	        C.add(m1);
	        Manager m2 = new Manager(100, "marry");
	        Zhushou zhushou1=new Zhushou("tom");
	        m2.zhushou=zhushou1;
	        //如果两个引用的编号和姓名一致，那说明包含，这样把底层的equals方法进行重写
	        //而contains在底层的时间使用equals方法判断是否重复
	        System.out.println(m1.equals(m2));
	        System.out.println(C.contains(m2));
	    }
	}
	 
	class Manager{
	     int number;
	     String name;
	     Zhushou zhushou;
	     public Manager(int number,String name) {
	        this.number=number;
	        this.name=name;
	    }
	     public boolean equals(Object o){
	         if(this==o)
	             return true;
	         if (o instanceof Manager){
	             Manager s =(Manager)o;
	             if(s.number==this.number && s.name.equals(this.name)){
	                 return true;
	             }else if (this.zhushou.equals(s.zhushou)) {
					return true;
				}
	         }
	         return false;
	     }
	}
	class Zhushou{
		 String name;
		 public Zhushou(String name) {
			 this.name=name;
		 }
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if(obj instanceof Zhushou) {
				Zhushou zhushou2=(Zhushou)obj;
				if(this.name.equals(zhushou2.name))
					return true;
			}
			return false;
		}
		 
	}