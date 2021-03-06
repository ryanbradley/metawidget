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

package org.metawidget.example.swing.animalraces;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import junit.framework.TestCase;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

import org.metawidget.swing.SwingMetawidget;

/**
 * @author Richard Kennard
 */

public class SwingAnimalRacesTest
	extends TestCase {

	//
	// Public methods
	//

	public void testAnimalRaces()
		throws Exception {

		JFrame mainFrame = AnimalRaces.mainFrame();
		JPanel toolbar = (JPanel) mainFrame.getContentPane().getComponent( 0 );

		// Toolbar

		SwingMetawidget elephantMetawidget = (SwingMetawidget) toolbar.getComponent( 0 );
		SwingMetawidget hippoMetawidget = (SwingMetawidget) toolbar.getComponent( 1 );
		SwingMetawidget pandaMetawidget = (SwingMetawidget) toolbar.getComponent( 2 );

		assertEquals( "Eddie", ( (JTextField) elephantMetawidget.getComponent( 1 ) ).getText() );
		assertTrue( 10 == (Integer) ( (JSpinner) elephantMetawidget.getComponent( 3 ) ).getValue() );
		assertTrue( 0 == (Integer) ( (SpinnerNumberModel) ( (JSpinner) elephantMetawidget.getComponent( 3 ) ).getModel() ).getMinimum() );
		assertEquals( "Animal*:", ( (JLabel) elephantMetawidget.getComponent( 4 ) ).getText() );
		// (6f normally, may be 2f if run from src/examples?)
		float padding = ( (CC) ( (MigLayout) elephantMetawidget.getLayout() ).getComponentConstraints( elephantMetawidget.getComponent( 4 ) ) ).getPadding()[0].getValue();
		assertTrue( 6f == padding || 2f == padding );
		assertEquals( "Elephant", ( (JComboBox) elephantMetawidget.getComponent( 5 ) ).getSelectedItem() );
		assertEquals( 3, ( (JComboBox) elephantMetawidget.getComponent( 5 ) ).getItemCount() );
		assertEquals( "Harry", ( (JTextField) hippoMetawidget.getComponent( 1 ) ).getText() );
		assertTrue( 5 == (Integer) ( (JSpinner) hippoMetawidget.getComponent( 3 ) ).getValue() );
		assertEquals( "Hippo", ( (JComboBox) hippoMetawidget.getComponent( 5 ) ).getSelectedItem() );
		assertEquals( "Paula", ( (JTextField) pandaMetawidget.getComponent( 1 ) ).getText() );
		assertTrue( 2 == (Integer) ( (JSpinner) pandaMetawidget.getComponent( 3 ) ).getValue() );
		assertEquals( "Panda", ( (JComboBox) pandaMetawidget.getComponent( 5 ) ).getSelectedItem() );

		// Racetrack

		JPanel racetrack = (JPanel) mainFrame.getContentPane().getComponent( 1 );
		assertEquals( "Eddie", ( (JLabel) racetrack.getComponent( 0 ) ).getText() );
		assertEquals( 0, ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().x );
		assertEquals( 0, ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().y );
		Icon elephantIcon = ( (JLabel) racetrack.getComponent( 0 ) ).getIcon();
		assertEquals( "Harry", ( (JLabel) racetrack.getComponent( 1 ) ).getText() );
		assertEquals( 0, ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().x );
		assertEquals( 200, ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().y );
		Icon hippoIcon = ( (JLabel) racetrack.getComponent( 1 ) ).getIcon();
		assertEquals( "Paula", ( (JLabel) racetrack.getComponent( 2 ) ).getText() );
		assertEquals( 0, ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().x );
		assertEquals( 400, ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().y );
		Icon pandaIcon = ( (JLabel) racetrack.getComponent( 2 ) ).getIcon();

		// Configure race

		( (JTextField) elephantMetawidget.getComponent( 1 ) ).setText( "Eddie1" );
		( (JSpinner) elephantMetawidget.getComponent( 3 ) ).setValue( 1 );
		( (JComboBox) elephantMetawidget.getComponent( 5 ) ).setSelectedItem( "Hippo" );
		( (JTextField) hippoMetawidget.getComponent( 1 ) ).setText( "Harry1" );
		( (JSpinner) hippoMetawidget.getComponent( 3 ) ).setValue( 1 );
		( (JComboBox) hippoMetawidget.getComponent( 5 ) ).setSelectedItem( "Panda" );
		( (JTextField) pandaMetawidget.getComponent( 1 ) ).setText( "Paula1" );
		( (JSpinner) pandaMetawidget.getComponent( 3 ) ).setValue( 1 );
		( (JComboBox) pandaMetawidget.getComponent( 5 ) ).setSelectedItem( "Elephant" );

		// Start race

		SwingMetawidget statusbar = (SwingMetawidget) mainFrame.getContentPane().getComponent( 2 );
		assertEquals( "Start Race", ( (JButton) statusbar.getComponent( 0 ) ).getText() );
		( (JButton) statusbar.getComponent( 0 ) ).doClick();

		Thread.sleep( 2000 );
		assertEquals( "Eddie1", ( (JLabel) racetrack.getComponent( 0 ) ).getText() );
		assertTrue( ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().x > 20 );
		assertEquals( 0, ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().y );
		assertEquals( hippoIcon, ( (JLabel) racetrack.getComponent( 0 ) ).getIcon() );
		assertEquals( "Harry1", ( (JLabel) racetrack.getComponent( 1 ) ).getText() );
		assertTrue( ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().x > 20 );
		assertEquals( 200, ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().y );
		assertEquals( pandaIcon, ( (JLabel) racetrack.getComponent( 1 ) ).getIcon() );
		assertEquals( "Paula1", ( (JLabel) racetrack.getComponent( 2 ) ).getText() );
		assertTrue( ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().x > 20 );
		assertEquals( 400, ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().y );
		assertEquals( elephantIcon, ( (JLabel) racetrack.getComponent( 2 ) ).getIcon() );

		// Stop race

		assertEquals( "Stop Race", ( (JButton) statusbar.getComponent( 1 ) ).getText() );
		( (JButton) statusbar.getComponent( 1 ) ).doClick();
		assertEquals( ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().x, 0 );
		assertEquals( ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().x, 0 );
		assertEquals( ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().x, 0 );

		// Start race again

		( (JButton) statusbar.getComponent( 0 ) ).doClick();

		Thread.sleep( 1000 );
		assertTrue( ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().x > 20 );
		assertTrue( ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().x > 20 );
		assertTrue( ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().x > 20 );

		// Stop race again

		( (JButton) statusbar.getComponent( 1 ) ).doClick();
		assertEquals( ( (JLabel) racetrack.getComponent( 0 ) ).getLocation().x, 0 );
		assertEquals( ( (JLabel) racetrack.getComponent( 1 ) ).getLocation().x, 0 );
		assertEquals( ( (JLabel) racetrack.getComponent( 2 ) ).getLocation().x, 0 );

		// Close (don't actually click Close, as it calls System.exit)

		assertEquals( "Close", ( (JButton) statusbar.getComponent( 2 ) ).getText() );
	}
}
