import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.JFrame;

public class Game_fr extends JFrame implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Game_play play = new Game_play();
	public static boolean game=false;
	public static final String SUN_JAVA_COMMAND = "sun.java.command";
	public static Thread run = new Thread() {
		public void run() {Movements.runt();}
	};
	
	public Game_fr ()
	{
		addKeyListener(this);
	    addWindowListener(new WindowAdapter()
	    {
	      public void windowClosing(WindowEvent e)
	      {   
	        System.exit(0);    
	      }  
	    });
		this.setTitle("bird");
		this.setSize(450,300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(350,150);
		this.setResizable(false);
		this.setBackground(Color.black);
		this.setLayout(null);
		run.start();
		this.setContentPane(play);
		this.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) {Movements.setmovup();}
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(!game)Movements.setGame(!Movements.getGame());
			if(game) {Runnable runBeforeRestart = null;
			try {
				restartApplication(runBeforeRestart);
			} catch (IOException e1) {
				e1.printStackTrace();
			}};
			}
		else {Movements.setGame(true);}
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {Movements.setmovdown();}
		
	}

	
	
	public static void restartApplication(Runnable runBeforeRestart) throws IOException {
	try {
	// java binary
	String java = System.getProperty("java.home") + "/bin/java";
	// vm arguments
	java.util.List<String> vmArguments = ManagementFactory.getRuntimeMXBean().getInputArguments();
	StringBuffer vmArgsOneLine = new StringBuffer();
	for (String arg : vmArguments) {
	// if it's the agent argument : we ignore it otherwise the
	// address of the old application and the new one will be in conflict
	if (!arg.contains("-agentlib")) {
	vmArgsOneLine.append(arg);
	vmArgsOneLine.append(" ");
	}
	}
	// init the command to execute, add the vm args
	final StringBuffer cmd = new StringBuffer("\"" + java + "\" " + vmArgsOneLine);

	// program main and program arguments
	String[] mainCommand = System.getProperty(SUN_JAVA_COMMAND).split(" ");
	// program main is a jar
	if (mainCommand[0].endsWith(".jar")) {
	// if it's a jar, add -jar mainJar
	cmd.append("-jar " + new File(mainCommand[0]).getPath());
	} else {
	// else it's a .class, add the classpath and mainClass
	cmd.append("-cp \"" + System.getProperty("java.class.path") + "\" " + mainCommand[0]);
	}
	// finally add program arguments
	for (int i = 1; i < mainCommand.length; i++) {
	cmd.append(" ");
	cmd.append(mainCommand[i]);
	}
	// execute the command in a shutdown hook, to be sure that all the
	// resources have been disposed before restarting the application
	Runtime.getRuntime().addShutdownHook(new Thread() {
	@Override
	public void run() {
	try {
	Runtime.getRuntime().exec(cmd.toString());
	} catch (IOException e) {
	e.printStackTrace();
	}
	}
	});
	// execute some custom code before restarting
	if (runBeforeRestart!= null) {
	runBeforeRestart.run();
	}
	System.exit(0);
	} catch (Exception e) {
	// something went wrong
	throw new IOException("Error while trying to restart the application", e);
	}
	}
	
	
	

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DOWN) {Movements.setmovN();}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	@SuppressWarnings("deprecation")
	public static void end()
	{
		run.stop();
		game=true;
	}
}


