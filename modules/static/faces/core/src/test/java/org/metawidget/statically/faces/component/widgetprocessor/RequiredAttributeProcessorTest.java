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

package org.metawidget.statically.faces.component.widgetprocessor;

import static org.metawidget.inspector.InspectionResultConstants.*;

import java.io.StringWriter;
import java.util.Date;
import java.util.Map;

import junit.framework.TestCase;

import org.metawidget.inspector.annotation.UiRequired;
import org.metawidget.statically.faces.StaticFacesMetawidgetTests;
import org.metawidget.statically.faces.component.html.StaticHtmlMetawidget;
import org.metawidget.statically.faces.component.html.widgetbuilder.HtmlInputText;
import org.metawidget.util.CollectionUtils;

/**
 * @author Richard Kennard
 */

public class RequiredAttributeProcessorTest
	extends TestCase {

	//
	// Public methods
	//

	public void testWidgetProcessor()
		throws Exception {

		RequiredAttributeProcessor processor = new RequiredAttributeProcessor();

		HtmlInputText htmlInputText = new HtmlInputText();
		Map<String, String> attributes = CollectionUtils.newHashMap();
		processor.processWidget( htmlInputText, PROPERTY, attributes, null );
		StaticFacesMetawidgetTests.assertWidgetEquals( htmlInputText, "<h:inputText/>\r\n" );

		attributes.put( REQUIRED, TRUE );
		processor.processWidget( htmlInputText, PROPERTY, attributes, null );
		StaticFacesMetawidgetTests.assertWidgetEquals( htmlInputText, "<h:inputText required=\"true\"/>\r\n" );
	}

	public void testSimpleType() {

		StaticHtmlMetawidget metawidget = new StaticHtmlMetawidget();
		metawidget.setValueExpression( "#{foo}" );
		metawidget.setPath( Foo.class.getName() );

		StringWriter writer = new StringWriter();
		metawidget.write( writer );

		String result = "<h:panelGrid columns=\"2\">\r\n" +
				"\t<h:outputLabel for=\"fooBar\" id=\"fooBar-label\" value=\"Bar:\"/>\r\n" +
				"\t<h:inputText id=\"fooBar\" required=\"true\" value=\"#{foo.bar}\">\r\n" +
				"\t\t<f:convertDateTime/>\r\n" +
				"\t</h:inputText>\r\n" +
				"\t<h:outputLabel for=\"fooBaz\" id=\"fooBaz-label\" value=\"Baz:\"/>\r\n" +
				"\t<h:inputText id=\"fooBaz\" value=\"#{foo.baz}\"/>\r\n" +
				"</h:panelGrid>\r\n";

		assertEquals( result, writer.toString() );
	}

	//
	// Inner class
	//

	public static class Foo {

		@UiRequired
		public Date	bar;

		public int	baz;
	}
}