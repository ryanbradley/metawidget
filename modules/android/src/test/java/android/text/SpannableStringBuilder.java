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

package android.text;

/**
 * Dummy implementation for unit testing.
 *
 * @author Richard Kennard
 */

public class SpannableStringBuilder
	implements CharSequence {

	//
	// Private members
	//

	private CharSequence	mString;

	//
	// Constructor
	//

	public SpannableStringBuilder( CharSequence string ) {

		mString = string;
	}

	//
	// Supported public methods
	//

	@Override
	public String toString() {

		return mString.toString();
	}

	//
	// Unsupported public methods
	//

	public char charAt( int index ) {

		throw new UnsupportedOperationException();
	}

	public int length() {

		throw new UnsupportedOperationException();
	}

	public CharSequence subSequence( int start, int end ) {

		throw new UnsupportedOperationException();
	}
}
