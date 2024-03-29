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
import java.util.List;
import java.util.TreeMap;

import xiaofei.library.comparatorgenerator.ComparatorGenerator.SortingCriterion;

public class AnnotationUtils {

	private AnnotationUtils() {
		
	}
	
	public static TreeMap<Integer, SortingCriterion> getCriterionAnnotation(Class<?> clazz) {
		TreeMap<Integer, SortingCriterion> map = new TreeMap<Integer, SortingCriterion>();
		List<Field> fields = TypeUtils.getFields(clazz);
		for (Field field : fields) {
			Criterion criterion = field.getAnnotation(Criterion.class);
			if (criterion != null) {
				TypeUtils.checkField(field);
				int priority = criterion.priority();
				Order order = criterion.order();
				SortingCriterion prev = map.put(priority, new SortingCriterion(field, order));
				if (prev != null) {
					throw new RuntimeException(
							"The priority value " + priority + " has already been set to field "
								+ prev.getField().getName() + " of class " + prev.getField().getDeclaringClass().getName()
								+ ". Please specify another priority value.");
				}
			}
		}
		return map;
	}
	
}
