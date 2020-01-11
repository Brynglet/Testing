package com.annotationz2;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestAnnotation {

	public static void main(String[] args) {

		System.out.println("Start");

		Class<AnnotationExample> obj = AnnotationExample.class;

		if (obj.isAnnotationPresent(CustomTypeAnno.class)) {

			Annotation annotation = obj.getAnnotation(CustomTypeAnno.class);
			CustomTypeAnno custType = (CustomTypeAnno) annotation;

			System.out.println("Prio: " + custType.priority());
			System.out.println("CreatedBy: " + custType.createdBy());
			System.out.println("LastModified: " + custType.lastModified());

			for (String tag : custType.tags()) {

				System.out.println("tag:" + tag);

			}

		}

		for (Method method: obj.getDeclaredMethods()) {
			System.out.println();
			System.out.println("methodName:" + method.getName());
			if (method.isAnnotationPresent(CustomMethodAnno.class)) {
				System.out.println("ANNO Present");
				Annotation annotation = method.getAnnotation(CustomMethodAnno.class);
				CustomMethodAnno custMeth = (CustomMethodAnno) annotation;

				if (custMeth.enabled()) {
					System.out.println("Meth enabled!");
					try {
						System.out.println("invoked...?");
						String invoked = (String)method.invoke(obj.newInstance()); //AnnotationExample
						System.out.println("...->" + invoked);

					} catch (Exception e) {
						System.out.println("OPS:" + e.getCause());
					}
				} else {
					System.out.println("Meth disabled!");
				}
			} else {
				System.out.println("ANNO Not Present");
			}
		}

	}

}
