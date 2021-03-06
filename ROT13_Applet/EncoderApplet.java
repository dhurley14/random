
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * Class EncoderApplet - write a description of the class here
 * 
 * @author (your name) 
 * @version (a version number)
 */
public class EncoderApplet extends JApplet implements ActionListener
{
    // instance variables - replace the example below with your own
    private int x;
    private Button button;
    private TextField rot13;
    private TextField input;
    private TextField output;

    private int newLine = 0;
    public static void main(String args[]){
        new EncoderApplet();
    }

    public EncoderApplet(){
        init();
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {
        // this is a workaround for a security conflict with some browsers
        // including some versions of Netscape & Internet Explorer which do 
        // not allow access to the AWT system event queue which JApplets do 
        // on startup to check access. May not be necessary with your browser. 
        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck", Boolean.TRUE);
        // provide any initialisation necessary for your JApplet\
        setBackground(Color.lightGray);
        //add(this.input = new TextArea(3, 10));
        //add(new TextArea("Some\nInitial\nText", 3, 10));
        //theString = JOptionPane.showInputDialog(null,"Enter something...");
        //JOptionPane.showMessageDialog(null,"Hello","title", JOptionPane.PLAIN_MESSAGE);
        //add(new Button("click"),CENTER_ALIGNMENT);
        setLayout(null);
        button = new Button("Click for Cipher!");
        input = new TextField("INPUT", 100);
        button.setBounds(20,20,200,40);

        input.setBounds(20,70,100,40);
        rot13 = new TextField("number to rotate",100);
        rot13.setBounds(20,200,100,80);
        output = new TextField("OUTPUT GOES HERE", 100);
        output.setBounds(20, 300, 100,40);

        add(button);

        add(input);
        add(rot13);
        add(output);
        button.addActionListener(this);
        output.addActionListener(this);
        /*class event extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
        if(SwingUtilities.isLeftMouseButton(e)){
        output.setText("clicked!");
        }
        repaint();
        e.consume();
        }

        }
        addMouseListener(new event());
         */
    }

    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();
        if(action.equals("Click for Cipher!")){
            output.setText("yar");
            TextField out = new TextField(input.getText(), 100);
            out.setBounds(150,200,200,50);
            add(out);
        }
        //repaint();
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        // simple text displayed on applet
        //g.setColor(Color.white);
        //g.fillRect(0, 0, 200, 100);
        //g.setColor(Color.black);
        //g.drawString("Sample Applet", 20, 20);
        //g.setColor(Color.blue);
        //g.drawString("created by BlueJ", 20, 40);
        //g.drawString(theString, 20, 30);
        
        g.setColor(Color.blue);
        g.drawString("Output will appear below",140,190);
        
    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }

    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }

    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                {"firstParameter",    "1-10",    "description of first parameter"},
                {"status", "boolean", "description of second parameter"},
                {"images",   "url",     "description of third parameter"}
            };
        return paramInfo;
    }
}
