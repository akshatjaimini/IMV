import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Main extends JPanel{

    public static int WIDTH = 500,HEIGHT = 500;
    private File f = null;
    static JFrame fl;
    public Color c;
    int dx,dy;
    int x = 0;
    boolean draw = false;
    //boolean call = false;

    private Color random(){
        int r = (int)(Math.random()*255);
        int g = (int)(Math.random()*255);
        int b = (int)(Math.random()*255);
        return new Color(r,g,b);
    }

    public Main(){
        c = random();
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                c = random();
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                draw = true;
                dx = mouseEvent.getX();
                dy = mouseEvent.getY();
                x++;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent mouseEvent) {

            }
        });
        setBackground(Color.BLACK);
    }

    public static void main(String args[]){
        Main cl = new Main();
        fl = new JFrame("IMV");
        fl.setLayout(new BorderLayout());
        fl.add(cl,BorderLayout.CENTER);
        fl.add(new Operations(cl),BorderLayout.SOUTH);
        fl.setDefaultCloseOperation(3);
        fl.setSize(WIDTH,HEIGHT);
        fl.setLocationRelativeTo(null);
        fl.setVisible(true);
    }

    protected void paintComponent(Graphics g){
        if(f != null && x == 0){
            System.out.println("Drawing");
            try {
                Image k = ImageIO.read(f);
                int l = k.getWidth(this);
                int m = k.getHeight(this);
                if(m>4000 || l>4000){
                    re(1100,800);
                    g.drawImage(k,0,0,1100,800,this);
                }else {
                    re(l,m);
                    g.drawImage(k, 0, 0, this);
                }
            } catch (java.lang.NullPointerException e) {
                JOptionPane.showMessageDialog(this,"Cannot Open","Error cannot open file",0);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        if(draw){
            g.setColor(c);
            g.fillOval(dx,dy,20,20);
        }
    }

    public void trigger(File f){
        this.f = f;
        repaint();
    }

    public void re(int x,int y){
        fl.setSize(x,y);
        fl.setLocationRelativeTo(null);
    }

}

class Operations extends JPanel{

    JButton open;
    Main n;

    public Operations(Main m) {
        setBackground(Color.BLACK);
        open = new JButton("Open");
        open.addActionListener(this::actionPerformed);
        open.setBorderPainted(true);
        open.setBackground(Color.BLACK);
        open.setForeground(Color.WHITE);
        n = m;
        add(open);
    }

    private void actionPerformed(ActionEvent e) {
        JFileChooser jfl = new JFileChooser();
        jfl.setToolTipText("Select an image file only");
        jfl.showDialog(n.fl, "Select");
        File f = jfl.getSelectedFile();
        //n.call = true;
        n.x = 0;
        n.trigger(f);
    }

}