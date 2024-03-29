/**
 *
 * Copyright 2016 Xiaofei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xiaofei.library.comparatorgenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TypeUtils {
	
	private TypeUtils() {
		
	}
	
	public static List<Field> getFields(Class<?> clazz) {
		List<Field> result = new ArrayList<Field>();
		for (Class<?> tmp = clazz;
			tmp != null && tmp != Object.class;
				tmp = tmp.getSuperclass()) {
			Field[] fields = tmp.getDeclaredFields();
			for (Field field : fields) {
				if (!field.isSynthetic()) {
					result.add(field);
				}
			}
		}
		return result;
	}
	
	public static Field getField(Class<?> clazz, String fieldName) {
		try {
			for (Class<?> tmp = clazz;
				tmp != null && tmp != Object.class;
					tmp = tmp.getSuperclass()) {
				Field field = clazz.getDeclaredField(fieldName);
				if (field != null) {
					return field;
				}
			}
			return null;
		} catch (NoSuchFieldException e) {
			return null;
		} catch (SecurityException e) {
			return null;
		}
	}
	
	public static void checkField(Field field) {
		Class<?> type = field.getType();
		if (!(Comparable.class.isAssignableFrom(type) || type.isPrimitive())) {
			throw new RuntimeException(
				"The field " + field.getName() + " is not a primitive type or does not implement the Comparable interface. "
					+ "It must be a primitive type or implement the Comparable interface.");
		}
	}
	
}
