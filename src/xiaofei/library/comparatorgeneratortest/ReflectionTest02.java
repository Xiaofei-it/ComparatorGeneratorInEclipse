package xiaofei.library.comparatorgeneratortest;

import java.lang.reflect.Field;

public class ReflectionTest02 {
	
	private int i;
	
	public Field get() {
		try {
			Field f = getClass().getDeclaredField("i");
			System.out.println(f.isAccessible());
			/**
			 * Even if it is public, it is false.
			 * 
			 * But here f can be set.
			 * 
			 * Outside only public and default can it be set.
			 */
			if (!f.isAccessible()) {
				//f.setAccessible(true);
			}
			f.set(this, 3);
			
			return f;
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int get2() {
		return i;
	}
	
	private int j;
	
	public void f() {
		try {
			Field f = getClass().getDeclaredField("i");
			System.out.println(f.isAccessible());
			Test02Sub.f(f, this);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Test02Sub {
	static void f(Field field, Object obj) {
		try {
			field.set(obj, 20);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}