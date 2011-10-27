// Metawidget
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package org.metawidget.inspector.java5;

import static org.metawidget.inspector.InspectionResultConstants.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.metawidget.inspector.impl.BaseObjectInspector;
import org.metawidget.inspector.impl.BaseObjectInspectorConfig;
import org.metawidget.inspector.impl.propertystyle.Property;
import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

/**
 * Inspector to look for Java 5 language features, such as enums and generics.
 *
 * @author Richard Kennard
 */

// REFACTOR: this should be deployed as standard just like MetawidgetAnnotationInspector is. However this will require rewriting/refilming the tutorial

public class Java5Inspector
	extends BaseObjectInspector {

	//
	// Constructor
	//

	public Java5Inspector() {

		this( new BaseObjectInspectorConfig() );
	}

	public Java5Inspector( BaseObjectInspectorConfig config ) {

		super( config );
	}

	//
	// Protected methods
	//

	@Override
	protected boolean shouldInspectPropertyAsEntity( Property property ) {

		return true;
	}

	@Override
	protected Map<String, String> inspectEntity( String declaredClass, String actualClass )
		throws Exception {

		Map<String, String> attributes = CollectionUtils.newHashMap();

		// Enums - classToInspect may an Enum type or an enum instance type (ie. Foo$1)

		Class<?> actualClazz = ClassUtils.niceForName( actualClass );

		if ( actualClazz != null && Enum.class.isAssignableFrom( actualClazz ) ) {
			// Invoke 'magic' values method
			//
			// This actually proved more reliable than using 'getEnumConstants', as that
			// didn't seem to work in some environments (ie. NetBeans). Also, you can
			// call 'values' from both the Enum class (ie. Gender) and a Enum instance
			// (ie. Gender$Male)

			Method methodValues = actualClazz.getMethod( "values" );
			Enum<?>[] enums = (Enum[]) methodValues.invoke( actualClazz );

			// Construct lookup values

			List<String> lookup = CollectionUtils.newArrayList();
			List<String> lookupLabels = CollectionUtils.newArrayList();

			for ( Enum<?> anEnum : enums ) {
				// Convert enum values to their .name() form, not their .toString()
				// form, so that clients can use .valueOf() to convert them back

				lookup.add( anEnum.name() );
				lookupLabels.add( anEnum.toString() );
			}

			attributes.put( LOOKUP, CollectionUtils.toString( lookup ) );
			attributes.put( LOOKUP_LABELS, CollectionUtils.toString( lookupLabels ) );

			// Put the type in too. This is not strictly necessary, as generally we
			// will be teamed up with PropertyTypeInspector, but we are used standalone
			// in the tutorial so we need to support this (contrived) use case.

			if ( actualClazz.isEnum() ) {
				attributes.put( TYPE, actualClazz.getName() );
			} else {
				attributes.put( TYPE, actualClazz.getSuperclass().getName() );
			}
		}

		return attributes;
	}

	@Override
	protected Map<String, String> inspectProperty( Property property )
		throws Exception {

		Map<String, String> attributes = CollectionUtils.newHashMap();

		// Generics

		attributes.put( PARAMETERIZED_TYPE, property.getGenericType() );

		return attributes;
	}
}
