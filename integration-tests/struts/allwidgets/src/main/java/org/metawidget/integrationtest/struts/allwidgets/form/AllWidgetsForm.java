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

package org.metawidget.integrationtest.struts.allwidgets.form;

import org.apache.struts.action.ActionForm;
import org.metawidget.integrationtest.shared.allwidgets.model.AllWidgets;
import org.metawidget.integrationtest.shared.allwidgets.proxy.AllWidgetsProxy.AllWidgets_$$_javassist_1;

/**
 * @author Richard Kennard
 */

public class AllWidgetsForm
	extends ActionForm {

	//
	// Private members
	//

	private AllWidgets			mAllWidgets			= new AllWidgets_$$_javassist_1();

	//
	// Public methods
	//

	public AllWidgets getAllWidgets() {

		return mAllWidgets;
	}
}
