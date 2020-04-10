package design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import TST.Files;
import file.FileBar;
import front.home;

public class ScriptPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	MyArrayList step;
	private static JTextPane field;
	JTextPane numbers;
	JPanel container;
	public static String source ;
	
	ScriptPanel(String link){
		step = new MyArrayList();
		source = link;
		setLayout(new BorderLayout());
		setOpaque(true);
		setBackground(Color.white);
		field = new JTextPane();
		container = new JPanel();
		numbers = setNumbers(new JTextPane());
		
		field.getDocument().addDocumentListener(new DocumentListener() {
			public String getText(){
				int caretPosition = getField().getDocument().getLength();
				Element root = getField().getDocument().getDefaultRootElement();
				String text = "1" + System.getProperty("line.separator");
				for(int i = 2 ; i < root.getElementIndex( caretPosition ) + 2; i++){
					text += i + System.getProperty("line.separator");
				}
				return text;
			}
			
			public void changedUpdate(DocumentEvent arg0) {
				numbers.setText(getText());
			}
			
			public void removeUpdate(DocumentEvent arg0) {
				numbers.setText(getText());
				step.AddChangeT(getField().getText());
			    colorer();
			    if(home.special.getAutoSave().equals("YES") && home.special.getLoaded()) {
			    	FileBar.normal_save();
			    }
			}

			public void insertUpdate(DocumentEvent arg0) {
				numbers.setText(getText());
				step.AddChangeT(getField().getText());
			    colorer();
			    if(home.special.getAutoSave().equals("YES") && home.special.getLoaded()) {
			    	FileBar.normal_save();
			    }
			}

			private void colorer() {
				Runnable doAssist = new Runnable() {
                    public void run() {
                    	StyledDocument doc = getField().getStyledDocument();
               	     	Style style = getField().addStyle("I'm a Style", null);
               	     	doc.setCharacterAttributes(0,doc.getLength()-1,style,true);
               	     	StyleConstants.setForeground(style, Color.black);
                    	for(int n=0;n<step.getArrayFunc().size();n++) {
                    		if(!step.getArrayFunc().get(n).equals("scratch")) {
                    			for(int z=0;
                    					z<step.getArrayArray().get(n).size();
                    					z++) {
                    				int num=step.getArrayArray().get(n).get(z).getNum();
                    				
                    				if(Character.isDigit(step.getArrayChar().get(num)))
                    					StyleConstants.setForeground(style, Color.green);
                    				else
                    					StyleConstants.setForeground(style, Color.blue);
                    				doc.setCharacterAttributes(num-n,1,style,true);
                    				StyleConstants.setForeground(style, Color.black);
                    			}
                    		}
                    	}
                    }
                };
                SwingUtilities.invokeLater(doAssist);
			}
		});
		
		field.setOpaque(false);
		field.setText(Files.openTSTfile(link));
		container.setLayout(new BorderLayout());
		container.add(getField(), BorderLayout.CENTER);
		container.add(numbers , BorderLayout.WEST);
		this.add(scrollpane(new JScrollPane(container)),BorderLayout.CENTER);
	}
	
	private JTextPane setNumbers(JTextPane numbers) {
		numbers.setText("1");
		numbers.setBackground(Color.GRAY);
		numbers.setOpaque(true);
		numbers.setPreferredSize(new Dimension(20,290));
		numbers.setEditable(false);
		return numbers;
	}

	public JScrollPane scrollpane(JScrollPane jsp) {
	    jsp.getBackground();
		jsp.setOpaque(false);
		jsp.setBorder(null);
		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.getViewport().setOpaque(false);
		return jsp;
	}

	public static JTextPane getField() {
		return field;
	}

	public static void setField(JTextPane field) {
		ScriptPanel.field = field;
	}

}


