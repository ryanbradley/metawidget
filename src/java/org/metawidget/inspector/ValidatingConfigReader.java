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

package org.metawidget.inspector;

import java.io.InputStream;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;

import org.metawidget.inspector.iface.InspectorException;
import org.xml.sax.SAXException;

/**
 * ConfigReader that validates the <code>inspector-config.xml</code> file against the
 * <code>inspector-config-1.0.xsd</code> schema.
 * <p>
 * Not all environments (eg. J2SE 1.4, Android) support schema validation.
 *
 * @author Richard Kennard
 */

public class ValidatingConfigReader
	extends ConfigReader
{
	//
	// Constructor
	//

	public ValidatingConfigReader()
	{
		SchemaFactory factory = SchemaFactory.newInstance( XMLConstants.W3C_XML_SCHEMA_NS_URI );
		InputStream in = openResource( "org/metawidget/inspector/inspector-config-1.0.xsd" );

		try
		{
			mFactory.setSchema( factory.newSchema( new StreamSource( in ) ) );
		}
		catch ( SAXException e )
		{
			throw InspectorException.newException( e );
		}
	}
}