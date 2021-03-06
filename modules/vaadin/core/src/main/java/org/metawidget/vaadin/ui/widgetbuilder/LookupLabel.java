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

package org.metawidget.vaadin.ui.widgetbuilder;

import java.util.Map;

/*
 * Label whose values display using a lookup.
 */

public class LookupLabel
	extends com.vaadin.ui.Label {

	//
	// Private members
	//

	private Map<String, String>	mLookup;

	//
	// Constructor
	//

	public LookupLabel( Map<String, String> lookup ) {

		if ( lookup == null ) {
			throw new NullPointerException( "lookup" );
		}

		mLookup = lookup;
	}

	//
	// Public methods
	//

	/**
	 * Overridden to display the value using our lookup. The original value is still available
	 * through <code>getValue</code>.
	 */

	@Override
	public String toString() {

		String toString = super.toString();
		String lookup = mLookup.get( toString );

		if ( lookup != null ) {
			return lookup;
		}

		return toString;
	}
}
