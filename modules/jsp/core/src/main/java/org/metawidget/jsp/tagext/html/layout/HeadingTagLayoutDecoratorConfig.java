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

package org.metawidget.jsp.tagext.html.layout;

import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

import org.metawidget.jsp.tagext.MetawidgetTag;
import org.metawidget.layout.decorator.LayoutDecoratorConfig;
import org.metawidget.layout.iface.Layout;
import org.metawidget.util.simple.ObjectUtils;

/**
 * Configures a HeadingTagLayoutDecorator prior to use. Once instantiated, Layouts are immutable.
 *
 * @author Richard Kennard
 */

public class HeadingTagLayoutDecoratorConfig
	extends LayoutDecoratorConfig<Tag, BodyTag, MetawidgetTag> {

	//
	// Private members
	//

	private String	mStyle;

	private String	mStyleClass;

	//
	// Public methods
	//

	/**
	 * Overridden to use covariant return type.
	 *
	 * @return this, as part of a fluent interface
	 */

	@Override
	public HeadingTagLayoutDecoratorConfig setLayout( Layout<Tag, BodyTag, MetawidgetTag> layout ) {

		super.setLayout( layout );

		return this;
	}

	/**
	 * @return this, as part of a fluent interface
	 */

	public HeadingTagLayoutDecoratorConfig setStyle( String style ) {

		mStyle = style;

		return this;
	}

	/**
	 * @return this, as part of a fluent interface
	 */

	public HeadingTagLayoutDecoratorConfig setStyleClass( String styleClass ) {

		mStyleClass = styleClass;

		return this;
	}

	@Override
	public boolean equals( Object that ) {

		if ( this == that ) {
			return true;
		}

		if ( !ObjectUtils.nullSafeClassEquals( this, that )) {
			return false;
		}

		if ( !ObjectUtils.nullSafeEquals( mStyle, ( (HeadingTagLayoutDecoratorConfig) that ).mStyle ) ) {
			return false;
		}

		if ( !ObjectUtils.nullSafeEquals( mStyleClass, ( (HeadingTagLayoutDecoratorConfig) that ).mStyleClass ) ) {
			return false;
		}

		return super.equals( that );
	}

	@Override
	public int hashCode() {

		int hashCode = super.hashCode();
		hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode( mStyle );
		hashCode = 31 * hashCode + ObjectUtils.nullSafeHashCode( mStyleClass );

		return hashCode;
	}

	//
	// Protected methods
	//

	protected String getStyle() {

		return mStyle;
	}

	protected String getStyleClass() {

		return mStyleClass;
	}
}
