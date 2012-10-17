
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
/***************************************************************
* file: LEDTimeAppFrame.java
* author: Timothy L. Monh
* class: CS 245
*
* assignment: LEDTimeAPP
* date last modified: 3/11
*
* purpose: Creating an interface layout for an LED Timer
*
****************************************************************/
public class LEDTimeAppFrame extends javax.swing.JFrame {
    private EntryPanel colons;
    //variables for current time
    private clockform cf;
    private Timer clock;
    private boolean setCheck;
    //Settings for stopwatch and countdown
    private Timer stopWatch;
    private Timer countDown;
    private boolean startStop;
    private SetCountDownNumber cd;//get numbers for countdown Timer
    private int digit1, digit2, digit3,digit4,digit5,digit6;
    private int[] digitb;
    //String hours minutes and seconds for countdown
    private String s, m, h;
    //creating entries for other digits
    private EntryPanel[] ep,a,b,c,d,f;
    private static LEDTimeAppFrame theApp;
    /* Creates new form LEDTimeAppFrame */
    public LEDTimeAppFrame(String title) throws IOException {
        setCheck = false;
        cd = new SetCountDownNumber(0,0,0);
        digitb = new int[6];
        for(int i = 0;i<6;i++){
            digitb[i] = 0;
        }
        startStop = false;
        startingIntegers();
        stopWatchClear();
        initComponents();
        //Set for background
        this.getContentPane().setBackground(new Color(238,221,130));
        clock();
        this.SetKeyPresses();
        try {
            this.setFaviCon();
        } catch (IOException ex) {
            Logger.getLogger(LEDTimeAppFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //set favicon of the application
    private void setFaviCon() throws IOException{
        Image img = ImageIO.read(this.getClass().getResource("angryman.png"));
        this.setIconImage(img);

    }
    //set mnemonics of the esc button
     private void SetKeyPresses()
    {
        JRootPane rootPane = this.getRootPane();
        InputMap iMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escape");

        ActionMap aMap = rootPane.getActionMap();
        aMap.put("escape", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }});
        InputMap fMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Info");

        ActionMap aMapF = rootPane.getActionMap();
        aMapF.put("Info", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0){
                JOptionPane.showMessageDialog(null, "Timothy L. Monh\n#007656116\nLEDTimerApplication\nWinter Quarter 2010");
        }});
        InputMap f2Map = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "Print");

        ActionMap aMapF2 = rootPane.getActionMap();
        aMapF.put("Print", new AbstractAction(){
            public void actionPerformed(ActionEvent arg0){
                PrinterJob job = PrinterJob.getPrinterJob();
                PageFormat pf = job.defaultPage();

        job.setPrintable(new PrinterClassGUI(theApp), pf);

        if(job.printDialog()){
            try {
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(LEDTimeAppFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }});
    }
    //Purpose: To clear the digits back to 0
    public void startingIntegers()
    {
        ep = new EntryPanel[3];
        a = new EntryPanel[3];
        b = new EntryPanel[3];
        c = new EntryPanel[3];
        d = new EntryPanel[3];
        f = new EntryPanel[3];
        colons = new EntryPanel();
        for(int i = 0; i < 3; i++)
        {
            ep[i] = new EntryPanel(0);
            a[i] = new EntryPanel(0);
            b[i] = new EntryPanel(0);
            c[i] = new EntryPanel(0);
            d[i] = new EntryPanel(0);
            f[i] = new EntryPanel(0);
        }
    }
    //Purpose: To center the app
     private void center()
    {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension us = this.getSize();
        int x = (screen.width - us.width) / 2;
        int y = (screen.height - us.height) / 2;
        this.setLocation(x,y);
    }
     //Constructor for allowing LED print for the top clock
     private void  clock(){
        this.clock = new Timer(1000/* 1000 milliseconds per second*/,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cf = new clockform();
                ep[0] = new EntryPanel(cf.getSD1());
                a[0] = new EntryPanel(cf.getSD2());
                b[0] = new EntryPanel(cf.getMD1());
                c[0] = new EntryPanel(cf.getMD2());
                d[0] = new EntryPanel(cf.getHD1());
                f[0] = new EntryPanel(cf.getHD2());
                CurrentTime.repaint();
            }
     });
     this.clock.start();
     }
     //Purpose: To display the regular countdown in the middle
    private void stopWatch()
    {
        this.stopWatch = new Timer(1000/* 1000 milliseconds per second*/,new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("stopWatch incremented");
                if(digit1 > 9)
                {
                    digit1 = 0;
                    
                    digit2++;
                    if(digit2 > 5)
                    {
                        digit2 = 0;
                        digit3++;
                        if(digit3 > 9)
                        {
                            digit3 = 0;
                            digit4++;
                            if(digit4 > 5)
                            {
                                digit4 = 0;
                                digit5++;
                                if(digit5 > 9)
                                {
                                    digit5 = 0;
                                    digit6++;
                                    if(digit6 > 9)
                                    {
                                        digit6 = 0;
                                        System.out.println("Max reached.");
                                        stopWatch.stop();
                                    }
                                }
                            }
                        }
                    }
                }
                ep[1] = new EntryPanel(digit1);
                a[1] = new EntryPanel(digit2);
                b[1] = new EntryPanel(digit3);
                c[1] = new EntryPanel(digit4);
                d[1] = new EntryPanel(digit5);
                f[1] = new EntryPanel(digit6);
                digit1++;
                StopWatch.repaint();
            }
        });
        this.stopWatch.start();//Starts up the timer countdown
    }
    //Clear the stopwatch to 0
    private void stopWatchClear(){
        digit1 = 0;
        digit2 = 0;
        digit3 = 0;
        digit4 = 0;
        digit5 = 0;
        digit6 = 0;
    }
    //set up numbers for countDown timer
    private void countDownSet(){
        digitb[0] = cd.sec1();
        digitb[1] = cd.sec2();
        digitb[2] = cd.min1();
        digitb[3] = cd.min2();
        digitb[4] = cd.hour1();
        digitb[5] = cd.hour2();
    }


    //purpose: To display the countdown on the bottom
     private void countDown()
    {
         CountDown.repaint();
        this.countDown = new Timer(1000/* 1000 milliseconds per second*/,new ActionListener()
        {
            public void actionPerformed(ActionEvent arg0)
            {
                System.out.println("countDown decremented");
                if(digitb[0] < 0)
                {
                    digitb[0] = 9;
                    digitb[1]--;
                    if(digitb[1] < 0)
                    {
                        digitb[1] = 5;
                        digitb[2]--;
                        if(digitb[2] < 0)
                        {
                            digitb[2] = 9;
                            digitb[3]--;
                            if(digitb[3] < 0)
                            {
                                digitb[3] = 5;
                                digitb[4]--;
                                if(digitb[4] < 0)
                                {
                                    digitb[4] = 9;
                                    digitb[5]--;
                                    if(digitb[5] < 0)
                                    {
                                        countDown.stop();
                                        digitb[5] = 0;
                                        System.out.println("Min reached.");
                                        countDown.stop();
                                    }
                                }
                            }
                        }
                    }
                }
                ep[2] = new EntryPanel(digitb[0]);
                a[2] = new EntryPanel(digitb[1]);
                b[2] = new EntryPanel(digitb[2]);
                c[2] = new EntryPanel(digitb[3]);
                d[2] = new EntryPanel(digitb[4]);
                f[2] = new EntryPanel(digitb[5]);
                digitb[0]--;
                CountDown.repaint();
            }
        });
        this.countDown.start();//Starts up the timer countdown
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        CurrentTime = new javax.swing.JLayeredPane();
        tHours = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                f[0].paintEntryPanel(g);
            }

        };
        Hours = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                d[0].paintEntryPanel(g);
            }

        };
        tMinutes = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                c[0].paintEntryPanel(g);
            }

        };
        Seconds = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                ep[0].paintEntryPanel(g);
            }

        };
        Minutes = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                b[0].paintEntryPanel(g);
            }

        };
        tSeconds = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                a[0].paintEntryPanel(g);
            }

        };
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                colons.paintColons(g);
            }

        };
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                colons.paintColons(g);
            }

        };
        jPanel6 = new javax.swing.JPanel();
        StopwatchLabel = new javax.swing.JLabel();
        StartStopwatchButton = new javax.swing.JButton();
        StopStopwatchButton = new javax.swing.JButton();
        ClearStopwatchButton = new javax.swing.JButton();
        CountdownLabel = new javax.swing.JLabel();
        SetHours = new javax.swing.JTextField();
        StartCountdownButton = new javax.swing.JButton();
        StopCountdownButton = new javax.swing.JButton();
        SetCountdownButton = new javax.swing.JButton();
        StopWatch = new javax.swing.JLayeredPane();
        tHours1 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                f[1].paintEntryPanel(g);
            }

        };
        Hours1 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                d[1].paintEntryPanel(g);
            }

        };
        tMinutes1 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                c[1].paintEntryPanel(g);
            }

        };
        Seconds1 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                ep[1].paintEntryPanel(g);
            }

        };
        Minutes1 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                b[1].paintEntryPanel(g);
            }

        };
        tSeconds1 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                a[1].paintEntryPanel(g);
            }

        };
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                colons.paintColons(g);
            }

        };
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                colons.paintColons(g);
            }

        };
        jPanel11 = new javax.swing.JPanel();
        CountDown = new javax.swing.JLayeredPane();
        tHours2 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                f[2].paintEntryPanel(g);
            }

        };
        Hours2 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                d[2].paintEntryPanel(g);
            }

        };
        tMinutes2 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                c[2].paintEntryPanel(g);
            }

        };
        Seconds2 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                ep[2].paintEntryPanel(g);
            }

        };
        Minutes2 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                b[2].paintEntryPanel(g);
            }

        };
        tSeconds2 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                a[2].paintEntryPanel(g);
            }

        };
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                colons.paintColons(g);
            }

        };
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                colons.paintColons(g);
            }

        };
        jPanel16 = new javax.swing.JPanel();
        SetSeconds = new javax.swing.JTextField();
        SetMinutes = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LED Timer Application");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(176, 176, 103));
        jPanel1.setToolTipText("Enter your time here to allow countdown");

        CurrentTime.setBackground(new java.awt.Color(204, 204, 204));
        CurrentTime.setToolTipText("Displays the current time");

        tHours.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tHoursLayout = new javax.swing.GroupLayout(tHours);
        tHours.setLayout(tHoursLayout);
        tHoursLayout.setHorizontalGroup(
            tHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tHoursLayout.setVerticalGroup(
            tHoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tHours.setBounds(10, 20, 40, 50);
        CurrentTime.add(tHours, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Hours.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout HoursLayout = new javax.swing.GroupLayout(Hours);
        Hours.setLayout(HoursLayout);
        HoursLayout.setHorizontalGroup(
            HoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        HoursLayout.setVerticalGroup(
            HoursLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Hours.setBounds(70, 20, 40, 50);
        CurrentTime.add(Hours, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tMinutes.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tMinutesLayout = new javax.swing.GroupLayout(tMinutes);
        tMinutes.setLayout(tMinutesLayout);
        tMinutesLayout.setHorizontalGroup(
            tMinutesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tMinutesLayout.setVerticalGroup(
            tMinutesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tMinutes.setBounds(130, 20, 40, 50);
        CurrentTime.add(tMinutes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Seconds.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout SecondsLayout = new javax.swing.GroupLayout(Seconds);
        Seconds.setLayout(SecondsLayout);
        SecondsLayout.setHorizontalGroup(
            SecondsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        SecondsLayout.setVerticalGroup(
            SecondsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Seconds.setBounds(310, 20, 40, 50);
        CurrentTime.add(Seconds, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Minutes.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout MinutesLayout = new javax.swing.GroupLayout(Minutes);
        Minutes.setLayout(MinutesLayout);
        MinutesLayout.setHorizontalGroup(
            MinutesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        MinutesLayout.setVerticalGroup(
            MinutesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Minutes.setBounds(190, 20, 40, 50);
        CurrentTime.add(Minutes, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tSeconds.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tSecondsLayout = new javax.swing.GroupLayout(tSeconds);
        tSeconds.setLayout(tSecondsLayout);
        tSecondsLayout.setHorizontalGroup(
            tSecondsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tSecondsLayout.setVerticalGroup(
            tSecondsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tSeconds.setBounds(250, 20, 40, 50);
        CurrentTime.add(tSeconds, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.setBounds(290, 20, 20, 50);
        CurrentTime.add(jPanel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.setBounds(230, 20, 20, 50);
        CurrentTime.add(jPanel3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel4.setBounds(170, 20, 20, 50);
        CurrentTime.add(jPanel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel5.setBounds(110, 20, 20, 50);
        CurrentTime.add(jPanel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel6.setBounds(50, 20, 20, 50);
        CurrentTime.add(jPanel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        StopwatchLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        StopwatchLabel.setText("Stopwatch");

        StartStopwatchButton.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        StartStopwatchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StartButton.png"))); // NOI18N
        StartStopwatchButton.setToolTipText("Start the Timer");
        StartStopwatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartStopwatchButtonActionPerformed(evt);
            }
        });

        StopStopwatchButton.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        StopStopwatchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StopButton.png"))); // NOI18N
        StopStopwatchButton.setToolTipText("Stops the Stopwatch Timer");
        StopStopwatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopStopwatchButtonActionPerformed(evt);
            }
        });

        ClearStopwatchButton.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        ClearStopwatchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ClearButton.png"))); // NOI18N
        ClearStopwatchButton.setToolTipText("Clears the Stopwatch Timer down to 0");
        ClearStopwatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearStopwatchButtonActionPerformed(evt);
            }
        });

        CountdownLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        CountdownLabel.setText("Countdown");
        CountdownLabel.setToolTipText("Countdown timer which counts down from user's input");

        SetHours.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        SetHours.setText("00");
        SetHours.setToolTipText("Enter your time here to allow countdown");
        SetHours.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetHoursActionPerformed(evt);
            }
        });

        StartCountdownButton.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        StartCountdownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StartButton.png"))); // NOI18N
        StartCountdownButton.setToolTipText("Starts the countdown time");
        StartCountdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartCountdownButtonActionPerformed(evt);
            }
        });

        StopCountdownButton.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        StopCountdownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/StopButton.png"))); // NOI18N
        StopCountdownButton.setToolTipText("Stops the countdown time");
        StopCountdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopCountdownButtonActionPerformed(evt);
            }
        });

        SetCountdownButton.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        SetCountdownButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SetButton.png"))); // NOI18N
        SetCountdownButton.setToolTipText("Sets the countdown time to that of what is input above");
        SetCountdownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetCountdownButtonActionPerformed(evt);
            }
        });

        StopWatch.setToolTipText("Displays the Stopwatch count");

        tHours1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tHours1Layout = new javax.swing.GroupLayout(tHours1);
        tHours1.setLayout(tHours1Layout);
        tHours1Layout.setHorizontalGroup(
            tHours1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tHours1Layout.setVerticalGroup(
            tHours1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tHours1.setBounds(10, 20, 40, 50);
        StopWatch.add(tHours1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Hours1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Hours1Layout = new javax.swing.GroupLayout(Hours1);
        Hours1.setLayout(Hours1Layout);
        Hours1Layout.setHorizontalGroup(
            Hours1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        Hours1Layout.setVerticalGroup(
            Hours1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Hours1.setBounds(70, 20, 40, 50);
        StopWatch.add(Hours1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tMinutes1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tMinutes1Layout = new javax.swing.GroupLayout(tMinutes1);
        tMinutes1.setLayout(tMinutes1Layout);
        tMinutes1Layout.setHorizontalGroup(
            tMinutes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tMinutes1Layout.setVerticalGroup(
            tMinutes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tMinutes1.setBounds(130, 20, 40, 50);
        StopWatch.add(tMinutes1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Seconds1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Seconds1Layout = new javax.swing.GroupLayout(Seconds1);
        Seconds1.setLayout(Seconds1Layout);
        Seconds1Layout.setHorizontalGroup(
            Seconds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        Seconds1Layout.setVerticalGroup(
            Seconds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Seconds1.setBounds(310, 20, 40, 50);
        StopWatch.add(Seconds1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Minutes1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Minutes1Layout = new javax.swing.GroupLayout(Minutes1);
        Minutes1.setLayout(Minutes1Layout);
        Minutes1Layout.setHorizontalGroup(
            Minutes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        Minutes1Layout.setVerticalGroup(
            Minutes1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Minutes1.setBounds(190, 20, 40, 50);
        StopWatch.add(Minutes1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tSeconds1.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tSeconds1Layout = new javax.swing.GroupLayout(tSeconds1);
        tSeconds1.setLayout(tSeconds1Layout);
        tSeconds1Layout.setHorizontalGroup(
            tSeconds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tSeconds1Layout.setVerticalGroup(
            tSeconds1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tSeconds1.setBounds(250, 20, 40, 50);
        StopWatch.add(tSeconds1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel7.setBounds(290, 20, 20, 50);
        StopWatch.add(jPanel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel8.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel8.setBounds(230, 20, 20, 50);
        StopWatch.add(jPanel8, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel9.setBounds(170, 20, 20, 50);
        StopWatch.add(jPanel9, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel10.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel10.setBounds(110, 20, 20, 50);
        StopWatch.add(jPanel10, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel11.setBounds(50, 20, 20, 50);
        StopWatch.add(jPanel11, javax.swing.JLayeredPane.DEFAULT_LAYER);

        CountDown.setToolTipText("Displays the Countdown count");

        tHours2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tHours2Layout = new javax.swing.GroupLayout(tHours2);
        tHours2.setLayout(tHours2Layout);
        tHours2Layout.setHorizontalGroup(
            tHours2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tHours2Layout.setVerticalGroup(
            tHours2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tHours2.setBounds(10, 20, 40, 50);
        CountDown.add(tHours2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Hours2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Hours2Layout = new javax.swing.GroupLayout(Hours2);
        Hours2.setLayout(Hours2Layout);
        Hours2Layout.setHorizontalGroup(
            Hours2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        Hours2Layout.setVerticalGroup(
            Hours2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Hours2.setBounds(70, 20, 40, 50);
        CountDown.add(Hours2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tMinutes2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tMinutes2Layout = new javax.swing.GroupLayout(tMinutes2);
        tMinutes2.setLayout(tMinutes2Layout);
        tMinutes2Layout.setHorizontalGroup(
            tMinutes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tMinutes2Layout.setVerticalGroup(
            tMinutes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tMinutes2.setBounds(130, 20, 40, 50);
        CountDown.add(tMinutes2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Seconds2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Seconds2Layout = new javax.swing.GroupLayout(Seconds2);
        Seconds2.setLayout(Seconds2Layout);
        Seconds2Layout.setHorizontalGroup(
            Seconds2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        Seconds2Layout.setVerticalGroup(
            Seconds2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Seconds2.setBounds(310, 20, 40, 50);
        CountDown.add(Seconds2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        Minutes2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout Minutes2Layout = new javax.swing.GroupLayout(Minutes2);
        Minutes2.setLayout(Minutes2Layout);
        Minutes2Layout.setHorizontalGroup(
            Minutes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        Minutes2Layout.setVerticalGroup(
            Minutes2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        Minutes2.setBounds(190, 20, 40, 50);
        CountDown.add(Minutes2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tSeconds2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout tSeconds2Layout = new javax.swing.GroupLayout(tSeconds2);
        tSeconds2.setLayout(tSeconds2Layout);
        tSeconds2Layout.setHorizontalGroup(
            tSeconds2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        tSeconds2Layout.setVerticalGroup(
            tSeconds2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        tSeconds2.setBounds(250, 20, 40, 50);
        CountDown.add(tSeconds2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel12.setBounds(290, 20, 20, 50);
        CountDown.add(jPanel12, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel13.setBounds(230, 20, 20, 50);
        CountDown.add(jPanel13, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel14.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel14.setBounds(170, 20, 20, 50);
        CountDown.add(jPanel14, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel15.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel15.setBounds(110, 20, 20, 50);
        CountDown.add(jPanel15, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jPanel16.setBackground(new java.awt.Color(0, 0, 0));
        jPanel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel16MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel16.setBounds(50, 20, 20, 50);
        CountDown.add(jPanel16, javax.swing.JLayeredPane.DEFAULT_LAYER);

        SetSeconds.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        SetSeconds.setText("00");
        SetSeconds.setToolTipText("Enter your time here to allow countdown");
        SetSeconds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetSecondsActionPerformed(evt);
            }
        });

        SetMinutes.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        SetMinutes.setText("00");
        SetMinutes.setToolTipText("Enter your time here to allow countdown");
        SetMinutes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SetMinutesActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        jLabel1.setText(":");

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 18));
        jLabel2.setText(":");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(StartCountdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(StopCountdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SetCountdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(CountdownLabel)
                                .addGap(44, 44, 44)
                                .addComponent(SetHours, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(SetMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(7, 7, 7)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SetSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(StopwatchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StartStopwatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(StopStopwatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ClearStopwatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(CurrentTime, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(StopWatch, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CountDown))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CurrentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(StopWatch, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ClearStopwatchButton, 0, 0, Short.MAX_VALUE)
                    .addComponent(StopStopwatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(StopwatchLabel)
                    .addComponent(StartStopwatchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CountDown, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SetHours, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SetMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SetSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2))
                    .addComponent(CountdownLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SetCountdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(StopCountdownButton, 0, 0, Short.MAX_VALUE)
                    .addComponent(StartCountdownButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Quit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StopStopwatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopStopwatchButtonActionPerformed

        System.out.println("Stopwatch has stopped.");
        stopWatch.stop();
        if(startStop == true)
        {
            startStop = false;
        }
}//GEN-LAST:event_StopStopwatchButtonActionPerformed

    private void StartStopwatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartStopwatchButtonActionPerformed
        if(startStop == false)
        {
            stopWatch();
            System.out.println("Stopwatch has started.");
            startStop = true;
        }
}//GEN-LAST:event_StartStopwatchButtonActionPerformed

    private void ClearStopwatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearStopwatchButtonActionPerformed
    stopWatchClear();
    CurrentTime.repaint();
}//GEN-LAST:event_ClearStopwatchButtonActionPerformed

    private void StartCountdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartCountdownButtonActionPerformed
 if(setCheck == false){
     JOptionPane.showMessageDialog(null, "Nothing has been entered. Please enter a number value on the textfield");
 }
 else if(s.equals("00") && m.equals("00") && h.equals("00"))
 {
     JOptionPane.showMessageDialog(null, "Nothing has been entered. Please enter a number value on the textfield");
 }
 else{
        countDown();
        System.out.println("Countdown has started.");
 }
}//GEN-LAST:event_StartCountdownButtonActionPerformed

    private void StopCountdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopCountdownButtonActionPerformed
        System.out.println("Countdown has stopped");
        countDown.stop();
}//GEN-LAST:event_StopCountdownButtonActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        System.out.println("x=" + evt.getX()+" y="+evt.getY());
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jPanel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel16MouseClicked

    private void SetHoursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetHoursActionPerformed

    }//GEN-LAST:event_SetHoursActionPerformed

    private void SetSecondsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetSecondsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SetSecondsActionPerformed

    private void SetMinutesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetMinutesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SetMinutesActionPerformed

    private void SetCountdownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SetCountdownButtonActionPerformed
        setCheck = true;
        boolean check = true;
       boolean check2 = true;
       s = SetSeconds.getText();
       m = SetMinutes.getText();
       h = SetHours.getText();
       try
       {
             Integer.parseInt(s);
             Integer.parseInt(m);
             Integer.parseInt(h);
       }
       catch(NumberFormatException e)
       {
           JOptionPane.showMessageDialog(null,"This field only accepts numbers.");
           check = false;
       }
       if(check == true)
       {
           int sec = Integer.parseInt(s);
           int min =  Integer.parseInt(m);
           int hour =  Integer.parseInt(h);
           if(sec >= 60 || min >= 60 || hour > 60)
           {
               JOptionPane.showMessageDialog(null,"This field only accepts two digits for each square.\nThere's also a limit of 59 for each box.");
                check2 = false;
           }
           if(check2 == true)
           {
               cd = new SetCountDownNumber(sec,min,hour);
               countDownSet();
               JOptionPane.showMessageDialog(null, "The time has been set, click start to start timer.");
           }
       }
    }//GEN-LAST:event_SetCountdownButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    theApp = new LEDTimeAppFrame("Timer Application");
                } catch (IOException ex) {
                    Logger.getLogger(LEDTimeAppFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                theApp.center();
                theApp.setVisible(true);
                //new LEDTimeAppFrame().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClearStopwatchButton;
    private javax.swing.JLayeredPane CountDown;
    private javax.swing.JLabel CountdownLabel;
    private javax.swing.JLayeredPane CurrentTime;
    private javax.swing.JPanel Hours;
    private javax.swing.JPanel Hours1;
    private javax.swing.JPanel Hours2;
    private javax.swing.JPanel Minutes;
    private javax.swing.JPanel Minutes1;
    private javax.swing.JPanel Minutes2;
    private javax.swing.JPanel Seconds;
    private javax.swing.JPanel Seconds1;
    private javax.swing.JPanel Seconds2;
    private javax.swing.JButton SetCountdownButton;
    private javax.swing.JTextField SetHours;
    private javax.swing.JTextField SetMinutes;
    private javax.swing.JTextField SetSeconds;
    private javax.swing.JButton StartCountdownButton;
    private javax.swing.JButton StartStopwatchButton;
    private javax.swing.JButton StopCountdownButton;
    private javax.swing.JButton StopStopwatchButton;
    private javax.swing.JLayeredPane StopWatch;
    private javax.swing.JLabel StopwatchLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel tHours;
    private javax.swing.JPanel tHours1;
    private javax.swing.JPanel tHours2;
    private javax.swing.JPanel tMinutes;
    private javax.swing.JPanel tMinutes1;
    private javax.swing.JPanel tMinutes2;
    private javax.swing.JPanel tSeconds;
    private javax.swing.JPanel tSeconds1;
    private javax.swing.JPanel tSeconds2;
    // End of variables declaration//GEN-END:variables
    
}
