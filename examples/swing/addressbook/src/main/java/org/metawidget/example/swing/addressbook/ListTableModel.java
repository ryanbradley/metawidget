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

package org.metawidget.example.swing.addressbook;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

/**
 * TableModel for Lists of Objects.
 * <p>
 * As well as wrapping Lists of Objects, <code>ListTableModel</code> supports dynamically adding a
 * blank row to the model to accomodate entering new Objects.
 *
 * @author Richard Kennard
 */

public class ListTableModel<T extends Comparable<T>>
	extends AbstractTableModel {

	//
	// Private members
	//

	private Class<T>			mClass;

	private List<T>				mList;

	private String[]			mColumns;

	private boolean				mEditable;

	private boolean				mExtraBlankRow;

	//
	// Constructor
	//

	public ListTableModel( Class<T> clazz, Collection<T> collection, String... columns ) {

		mClass = clazz;
		mColumns = columns;

		importCollection( collection );
	}

	//
	// Public methods
	//

	public void importCollection( Collection<T> collection ) {

		if ( collection == null ) {
			mList = CollectionUtils.newArrayList();
		} else {
			mList = CollectionUtils.newArrayList( collection );
			Collections.sort( mList );
		}

		fireTableDataChanged();
	}

	public List<T> exportList() {

		return CollectionUtils.newArrayList( mList );
	}

	public void setEditable( boolean editable ) {

		mEditable = editable;
	}

	public void setExtraBlankRow( boolean extraBlankRow ) {

		mExtraBlankRow = extraBlankRow;
	}

	@Override
	public boolean isCellEditable( int rowIndex, int columnIndex ) {

		return mEditable;
	}

	public int getColumnCount() {

		// (mColumns can never be null)

		return mColumns.length;
	}

	@Override
	public String getColumnName( int columnIndex ) {

		if ( columnIndex >= getColumnCount() ) {
			return null;
		}

		return mColumns[columnIndex];
	}

	public int getRowCount() {

		// (mList can never be null)

		int rows = mList.size();

		if ( mExtraBlankRow ) {
			rows++;
		}

		return rows;
	}

	@Override
	public Class<?> getColumnClass( int columnIndex ) {

		String column = getColumnName( columnIndex );

		if ( column == null ) {
			return null;
		}

		return ClassUtils.getReadMethod( mClass, column ).getReturnType();
	}

	public T getValueAt( int rowIndex ) {

		// Sanity check

		if ( rowIndex >= mList.size() ) {
			return null;
		}

		return mList.get( rowIndex );
	}

	public Object getValueAt( int rowIndex, int columnIndex ) {

		// Sanity check

		if ( columnIndex >= getColumnCount() ) {
			return null;
		}

		// Fetch the object

		T t = getValueAt( rowIndex );

		if ( t == null ) {
			return null;
		}

		// Inspect it

		return ClassUtils.getProperty( t, getColumnName( columnIndex ) );
	}

	@Override
	public void setValueAt( Object value, int rowIndex, int columnIndex ) {

		// Sanity check

		if ( columnIndex >= getColumnCount() ) {
			return;
		}

		// Just-in-time creation

		if ( rowIndex == ( getRowCount() - 1 ) && mExtraBlankRow ) {
			if ( value == null || "".equals( value ) ) {
				return;
			}

			try {
				mList.add( mClass.newInstance() );
				fireTableRowsInserted( rowIndex, rowIndex );
			} catch ( Exception e ) {
				throw new RuntimeException( e );
			}
		}

		// Fetch the object

		T t = getValueAt( rowIndex );

		if ( t == null ) {
			return;
		}

		// Update it

		ClassUtils.setProperty( t, getColumnName( columnIndex ), value );
	}
}
