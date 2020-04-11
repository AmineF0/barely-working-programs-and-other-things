package environment;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class button extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public button(String string)
	{
		super(string);
		this.setBackground(Color.lightGray);
		this.setForeground(Color.black);
		this.setFont(new Font("Arial",Font.BOLD,23));
		this.setHorizontalAlignment(SwingConstants.LEFT);
	}
}
