package xiaofei.library.comparatorgeneratortest;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

public class ReflectionTest01 {

	private static void testIsInstance() {
		System.out.println("testIsInstance");
		System.out.println(int.class.isInstance(3));//f
		int i = 4;
		System.out.println(int.class.isInstance(i));//f
		int[] ints = new int[3];
		System.out.println(int[].class.isInstance(ints));//t
		System.out.println(long[].class.isInstance(ints));//f
		class A {
			
		}
		class B extends A {
			
		}
		A[] as = new A[3];
		B[] bs = new B[3];
		System.out.println(A[].class.isInstance(bs));//t
		System.out.println(B[].class.isInstance(as));//f
		A[] abs = new B[3];
		System.out.println(A[].class.isInstance(abs));//t
		System.out.println(B[].class.isInstance(abs));//t
	}
	
	private static void testIsPrimitive() {
		System.out.println("testIsPrimitive");
		System.out.println(Integer.class.isPrimitive());//f
		System.out.println(Integer.TYPE.isPrimitive());//t
		System.out.println(Integer.TYPE == Integer.class);//f
		Class<Integer> clazz = Integer.class;
		System.out.println(Integer.TYPE == clazz);//f
		System.out.println(Integer.class == clazz);//t
		System.out.println(Integer.TYPE == int.class);//t
	}
	
	private static void testIsAssignableFrom() {
		System.out.println("testIsAssignableFrom");
		class A {
			
		}
		class B extends A {
			
		}
		System.out.println(A.class.isAssignableFrom(B.class));//t
		System.out.println(B.class.isAssignableFrom(A.class));//f
	}
	
	interface IIter {
	}
	
	private static void testIsSynthetic() {
		System.out.println("testIsSynthetic");
		IIter a = (IIter) Proxy.newProxyInstance(IIter.class.getClassLoader(), new Class<?>[]{IIter.class},
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						// TODO Auto-generated method stub
						return null;
					}
				});
		System.out.println(a.getClass().getName());
		System.out.println(a.getClass().isSynthetic());//f
	}
	
	private static class GetTypeParameters<T extends Integer, S extends Long> {
		
	}
	
	private static void testGetTypeParameters() {
		System.out.println("testGetTypeParameters");
		GetTypeParameters<Integer, Long> a = new GetTypeParameters<Integer, Long>();
		TypeVariable[] tvs = a.getClass().getTypeParameters();
		for (TypeVariable tv : tvs) {
			System.out.println(tv.getName());
			System.out.println(tv.getGenericDeclaration());
			Type[] tps = tv.getBounds();
			for (Type t : tps) {
				System.out.println(t);
			}
		}
	}
	
	private static class GetGenericSuperclass<T, S> {
		
	}
	
	private static class GetGenericSuperclass2 extends GetGenericSuperclass<String, Integer> {
		
	}
	
	private static class GetGenericSuperclass3<T, S> extends GetGenericSuperclass<T, S> {
		
	}
	
	private static void testGetGenericSuperclass() {
		System.out.println("testGetGenericSuperclass");
		Type t = GetGenericSuperclass2.class.getGenericSuperclass();
		System.out.println(t);
		System.out.println(t.getClass());
		ParameterizedType pt = (ParameterizedType) t;
		System.out.println(pt.getRawType());
		System.out.println(pt.getOwnerType());
		System.out.println(pt.getTypeName());
		System.out.println();
		t = GetGenericSuperclass3.class.getGenericSuperclass();
		System.out.println(t);
		System.out.println(t.getClass());
		pt = (ParameterizedType) t;
		System.out.println(pt.getRawType());
		System.out.println(pt.getOwnerType());
		System.out.println(pt.getTypeName());
		
	}
	
	private static void testGetSigners() {
		System.out.println("testGetSigners");
//		Object[] os = GetSigners.class.getSigners();
//		for (Object o : os) {
//			System.out.println(o);
//		}
	}
	
	private static class Enclosing {
		@SuppressWarnings("rawtypes")
		static Class f() {
			class A{}
			return A.class;
		}
	}
	private static void testEnclosing() {
		System.out.println("testEnclosing");
		System.out.println(Enclosing.class.getEnclosingClass());
		//class xiaofei.library.comparatorgenerator.Test01
		System.out.println(Enclosing.f().getEnclosingClass());
		//class xiaofei.library.comparatorgenerator.Test01$Enclosing
		System.out.println(Enclosing.class.getEnclosingConstructor());//null
		System.out.println(Enclosing.f().getEnclosingConstructor());//null
		System.out.println(Enclosing.class.getEnclosingMethod());//null
		System.out.println(Enclosing.f().getEnclosingMethod());
		//static java.lang.Class xiaofei.library.comparatorgenerator.Test01$Enclosing.f()
		
	}
	
	class GetDeclaringClass {
		
	}
	
	private static void testGetDeclaringClass() {
		System.out.println("testGetDeclaringClass");
		class A {
			
		}
		System.out.println(A.class.getDeclaringClass());//null
		System.out.println(GetDeclaringClass.class.getDeclaringClass());
		//class xiaofei.library.comparatorgenerator.Test01
	}
	
	private static class IsKind {
		
	}
	
	private static void testIsKind() {
		System.out.println("testIsKind");
		class B{}
		class A {
			B b = new B() {//A() {
				
			};
			A f() {
				return new A () {
					
				};
			}
		}
		System.out.println(A.class.isLocalClass());//t
		System.out.println(A.class.isAnonymousClass());//f
		System.out.println(A.class.isMemberClass());//f
		System.out.println(IsKind.class.isLocalClass());//f
		System.out.println(IsKind.class.isAnonymousClass());//f
		System.out.println(IsKind.class.isMemberClass());//t
		A a = new A().f();
		System.out.println(a.getClass().isLocalClass());//f
		System.out.println(a.getClass().isAnonymousClass());//t
		System.out.println(a.getClass().isMemberClass());//f
		System.out.println(a.getClass().getDeclaringClass());//null
		System.out.println(a.getClass().getEnclosingMethod());
		//xiaofei.library.comparatorgenerator.Test01$4A xiaofei.library.comparatorgenerator.Test01$4A.f()
		System.out.println(a.getClass().getEnclosingClass());
		//class xiaofei.library.comparatorgenerator.Test01$4A
		
		B b = new A().b;
		System.out.println(b.getClass().isLocalClass());//f
		System.out.println(b.getClass().isAnonymousClass());//t
		System.out.println(b.getClass().isMemberClass());//f
		System.out.println(b.getClass().getDeclaringClass());//null
		System.out.println(b.getClass().getEnclosingMethod());//null
		System.out.println(b.getClass().getEnclosingClass());
		//class xiaofei.library.comparatorgenerator.Test01$4A
		System.out.println(b.getClass().getName());
		//xiaofei.library.comparatorgenerator.Test01$4A$1
	}
	
	private static void testCastAsSub() {
		System.out.println("testCastAsSub");
		class A {
			
		}
		class B extends A {
			
		}
		B b = new B();
		@SuppressWarnings("unused")
		A a = A.class.cast(b);
		System.out.println("suc");
		System.out.println(A.class);
		System.out.println(B.class);
		System.out.println(B.class.asSubclass(A.class));
		@SuppressWarnings("rawtypes")
		Class<List> clazz = List.class;  
        @SuppressWarnings("rawtypes")
		Class<? extends List> subclazz = ArrayList.class.asSubclass(clazz);  
        System.out.println(subclazz);
        System.out.println(subclazz == List.class);
        System.out.println(subclazz == ArrayList.class);
	}
	
	private interface GetInterfacesA {
		
	}
	
	private interface GetInterfacesB extends GetInterfacesA {
		
	}
	
	private interface GetInterfacesC {
		
	}
	
	private static void testGetInterfaces() {
		System.out.println("testGetInterfaces");
		class A implements GetInterfacesC, GetInterfacesB {
			
		}
		Class<?>[] cs = A.class.getInterfaces();
		for (Class<?> c : cs) {
			System.out.println(c);
		}
	}
	
	private static void testFieldAccess() {
		System.out.println("testFieldAccess");
		ReflectionTest02 t = new ReflectionTest02();
		Field field = t.get();
		System.out.println(t.get2());
		System.out.println(field.isAccessible());
		try {
			field.set(t, 4);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("haha 1");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("haha 2");
			e.printStackTrace();
		}
		System.out.println(t.get2());
		field.setAccessible(true);
		System.out.println(field.isAccessible());
		try {
			field.set(t, 5);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("haha 3");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("haha 4");
			e.printStackTrace();
		}
		field.setAccessible(false);
		System.out.println(field.isAccessible());
		System.out.println(t.get2());
		try {
			field.set(t, 6);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			System.out.println("haha 5");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			System.out.println("haha 7");
			e.printStackTrace();
		}
		Field field2 = null;
		try {
			field2 = ReflectionTest02.class.getDeclaredField("i");
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(field == field2);
		field2.setAccessible(true);
		System.out.println(field.isAccessible());
		System.out.println(field2.isAccessible());
		try {
			field2.set(t, 100);
			field.setAccessible(true);
			System.out.println(field.get(t));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.f();
	}
	
	public static void main(String[] args) {
		testIsInstance();
		testIsPrimitive();
		testIsAssignableFrom();
		testIsSynthetic();
		testGetTypeParameters();
		testGetGenericSuperclass();
		testGetSigners();
		testEnclosing();
		testGetDeclaringClass();
		testIsKind();
		testCastAsSub();
		testGetInterfaces();
		testFieldAccess();
		/**
		 * What the fuck is all the stuff above!!!
		 * 
		 * Almost nobody knows that I, Eric Zhao, have done so much research about these to
		 * make a better understanding about the reflection and make projects better!!!
		 * 
		 * Deep research is necessary.
		 * 
		 * It is indeed this that makes me better than many others since I was a pupil.
		 * 
		 * Do not do repeated fucking work or copy what others do, which is unnecessary
		 * and a waste of time.
		 * 
		 * Now the following remains:
		 * 
		 * 1. Generic type cast, especially <? extends T> and <?>
		 * 
		 * 2. What should be paid more attention to when writing code about reflection?
		 * 
		 * 3. I forget what I wanted to say!!! Fuck it!!!
		 * 
		 * After all, Java is a beautiful language!!! Marvelous!!! Splendid!!!
		 * 
		 */
	}

}
