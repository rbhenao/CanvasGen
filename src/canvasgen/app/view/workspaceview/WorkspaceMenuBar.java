package canvasgen.app.view.workspaceview;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import canvasgen.app.factory.mvpFactory;

public class WorkspaceMenuBar extends JMenuBar {
	
	protected String[] fileMenu = {"New...", "Open...", 
			"Open Recent(previous project)", "-", "Save",
			"Save As...", "Export Project", "-", "Exit" };
	
	protected String[] EditMenu = { "Undo", "Redo" };
	
	//protected String[] ViewMenu = {"Ruler", "Grid Lines", "Coordinates" };
	
	//protected String[] AnimationMenu = { "Translate", "Scale", "Rotate","Change Color" };
	
	//protected String[] EventsMenu = { "Add Mouse Event", "Add Keyboard Event",
		//	"Add Touch Event", "Add Link Button" };
	
	protected String[] PreferencesMenu = { "Keyboard Shortcuts", "Full Screen" };
	
	private ArrayList<JMenu> menus = new ArrayList<JMenu>();
	
	private ArrayList<JMenuItem> menuItems = new ArrayList<JMenuItem>();
	
	private String[] shortcutFields;
	
	private String[] actionFields;
	
	public WorkspaceMenuBar(){
		
		shortcutFields = (String[])mvpFactory.getModel("Shortcuts").getModelObjects().get("shortcutFields");
		actionFields = (String[])mvpFactory.getModel("Shortcuts").getModelObjects().get("actionFields");
		
		add(initMenuItem("File", fileMenu) );
		
		add(initMenuItem("Edit", EditMenu) );
		
		//add(initMenuItem("View", ViewMenu) );

		//add(initMenuItem("Animation", AnimationMenu) );
		
		//add(initMenuItem("Events", EventsMenu) );

		add(initMenuItem("Preferences", PreferencesMenu) );
	}
	
	private JMenu initMenuItem(String menuItem,String[] itemsArray) {
		JMenu x = new JMenu(menuItem);
		for (String item : itemsArray){
			if (item.equals("-") == true)
				x.addSeparator();
			else if(item.indexOf("(") >= 0){
				String menu = item.substring(0, item.indexOf("("));
				x = getJMenuSettings(menu,x);
				String[] subMenuArray = findArray(item);
				x.add(initMenuItem(menu, subMenuArray));
			}
			else{
				x = getJMenuSettings(menuItem,x);
				x.add(getJMenuItem(item));
			}
				
		}
		menus.add(x);
		return x;
	}
	
	private JMenu getJMenuSettings(String menuName, JMenu j){
		switch(menuName){
			case "Open Recent":
				j.setMnemonic(KeyEvent.VK_S);
			break;
		default:
			break;
		}
		return j;
	}
	
	private JMenuItem getJMenuItem(String itemName){
		JMenuItem m = new JMenuItem(itemName);
		switch(itemName){
			case "New...":
				m = setKeys(m, "New...");
				break;
			case "Open...":
				m = setKeys(m, "Open...");
				break;
			case "Open Recent":
				break;		
			case "Save":
				m = setKeys(m, "Save");
				m.setEnabled(false);
				break;
			case "Save As...":
				m = setKeys(m, "Save As...");
				break;
			case "Export Project": 
				break;
			case "Exit":
				break;
			case "An item in the submenu":				
				m.setAccelerator(KeyStroke.getKeyStroke(
						KeyEvent.VK_2, ActionEvent.ALT_MASK));
				break;
			case "Another item":
				break;
			case "Undo":
				m = setKeys(m, "Undo");
				m.setEnabled(false);
				break;
			case "Redo":
				m = setKeys(m, "Redo");
				break;
			case "Clear":
				m = setKeys(m, "Clear");
			case "Ruler":
				m = setKeys(m, "Ruler");
				break;
			case "Grid Lines":
				m = setKeys(m, "Grid Lines");
				break;
			case "Coordinates":
				m = setKeys(m, "Coordinates");
				break;
			case "Translate":
				m = setKeys(m, "Translate");
				break;
			case "Rotate":
				m = setKeys(m, "Rotate");
				//m.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.SHIFT_MASK+Toolkit
						//.getDefaultToolkit().getMenuShortcutKeyMask()));
				break;
			case "Scale":
				m = setKeys(m, "Scale");
				break;
			case "Change Color":
				m = setKeys(m, "Change Color");
				break;
			case "Add Mouse Event":
				m = setKeys(m, "Add Mouse Event");
				break;
			case "Add Keyboard Event":
				m = setKeys(m, "Add Keyboard Event");
				break;
			case "Add Touch Event":
				m = setKeys(m, "Add Touch Event");		
				break;
			case "Add Link Button":
				m = setKeys(m, "Add Link Button");
				break;
			case "Full Screen":				
				m = setKeys(m, "Full Screen");
				break;
			default:
				if(!itemName.matches("New...|Open...|Exit"))
					//m.setEnabled(false);
				break;		
		}
		//m.addActionListener(menuListener);
		menuItems.add(m);
		return m;
	}
	
	private JMenuItem setKeys(JMenuItem m, String input){
		int index;
		
		for(index = 0; index < actionFields.length; index++){
			if(actionFields[index].equals(input))
				break;
			//System.out.println(actionFields[index]);
		}
		//System.out.println();
		//System.out.println(index);
		//System.out.println(actionFields[index]);
		
		String[] keys = shortcutFields[index].split("\\+");
		
		if(keys.length == 1){
			KeyStroke ks = KeyStroke.getKeyStroke(keys[0]);
			m.setAccelerator(ks);
		}else if(keys.length == 2){
			if(keys[0].length() == 1){
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(keys[0].charAt(0));
				int keyCode2 = 0;
				
				if(keys[1].equals("ctrl"))
					keyCode2 = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
				else if(keys[1].equals("shft"))
					keyCode2 = 0; //find out what shft is
				else if(keys[1].equals("alt"))
					keyCode2 = 0; //find out what alt is
				else
					keyCode2 = 0; //find out what good default should be
				
				m.setAccelerator(KeyStroke.getKeyStroke(keyCode,keyCode2));
				
			}else if(keys[1].length() == 1){
				int keyCode = KeyEvent.getExtendedKeyCodeForChar(keys[1].charAt(0));
				int keyCode2 = 0;
				
				if(keys[0].equals("ctrl"))
					keyCode2 = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
				else if(keys[0].equals("shft"))
					keyCode2 = 0; //find out what shft is
				else if(keys[0].equals("alt"))
					keyCode2 = 0; //find out what alt is
				else
					keyCode2 = 0; //find out what good default should be
				
				m.setAccelerator(KeyStroke.getKeyStroke(keyCode,keyCode2));
			}
		}else if(keys.length == 3){
			//System.out.println("havent written this one yet");
		}
		
		return m;
	}
	
	private String[] findArray(String item){
		int count = 1;
		int index = item.indexOf("(");
		
		//find the range from open to closing brackets
		while(count > 0){
			index++;
			if(item.charAt(index) == '(' ){
				count++;
			}
				
			else if(item.charAt(index) == ')')
				count--;					
		}
		String subMenu = item.substring(item.indexOf("(")+1, index);
		int count2 = -1;
		int begin = 0;
		
		//temporarily replace all nested + signs with $ signs to the array can be split properly
		for(int i = 0; i < subMenu.length(); i++){
			if(subMenu.charAt(i) == '(' && count2 == -1){
				begin = i;
				count2 = 1;
			}					
			else if(subMenu.charAt(i) == '('){
				count2++;
				if(count2 == 1){
					begin = i;
				}
			}else if(subMenu.charAt(i) == ')'){
				count2--;
				if(count2 == 0){
					String cleanStr = subMenu.substring(begin,i+1).replaceAll("\\+", "\\$");
					subMenu = subMenu.substring(0,begin) + cleanStr + subMenu.substring(i+1);
				}
			}						
		}
		
		//split array
		String[] subMenuArray = subMenu.split("\\+");
		
		//add + signs back
		for(int j = 0; j < subMenuArray.length; j++){
			if(subMenuArray[j].indexOf("$") >= 0)
				subMenuArray[j] = subMenuArray[j].replaceAll("\\$", "\\+");
		}
		return subMenuArray;
	}

	public ArrayList<JMenu> getMenus() {
		return menus;
	}

	public ArrayList<JMenuItem> getMenuItems() {
		return menuItems;
	}
}
