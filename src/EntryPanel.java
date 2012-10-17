import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
/***************************************************************
* file: LineEntry
* author: Timothy Monh
* class: CS 245
*
* assignment: 1
* date last modified: 3/11
*
* purpose: Paint the LEDs for the timer
*
****************************************************************/


public class EntryPanel {
    private boolean top,topright,topleft,middle,bottomleft,bottomright,bottom;
    public EntryPanel(){

    }
    public EntryPanel(int i) {
        switch(i)
        {
                case 0:
                {
                    top = true;
                    topright = true;
                    topleft = true;
                    middle = false;
                    bottomleft = true;
                    bottomright = true;
                    bottom = true;
                    break;
                }
            case 1:
            {
                top = false;
                topright = true;
                topleft = false;
                middle = false;
                bottomleft = false;
                bottomright = true;
                bottom = false;
                break;
            }
            case 2:
            {
                top = true;
                topright = true;
                topleft = false;
                middle = true;
                bottomleft = true;
                bottomright = false;
                bottom = true;
                break;
            }
            case 3:
            {
                top = true;
                topright = true;
                topleft = false;
                middle = true;
                bottomleft = false;
                bottomright = true;
                bottom = true;
                break;
            }
            case 4:
            {
                top = false;
                topright = true;
                topleft = true;
                middle = true;
                bottomleft = false;
                bottomright = true;
                bottom = false;
                break;
            }
            case 5:
            {
                top = true;
                topright = false;
                topleft = true;
                middle = true;
                bottomleft = false;
                bottomright = true;
                bottom = true;
                break;
            }
            case 6:
            {
                top = true;
                topright = false;
                topleft = true;
                middle = true;
                bottomleft = true;
                bottomright = true;
                bottom = true;
                break;
            }
            case 7:
            {
                top = true;
                topright = true;
                topleft = false;
                middle = false;
                bottomleft = false;
                bottomright = true;
                bottom = false;
                break;
            }
            case 8:
            {
                top = true;
                topright = true;
                topleft = true;
                middle = true;
                bottomleft = true;
                bottomright = true;
                bottom = true;
                break;
            }
            case 9:
            {
                top = true;
                topright = true;
                topleft = true;
                middle = true;
                bottomleft = false;
                bottomright = true;
                bottom = true;
                break;
            }
        }
    }
//Purpose: To paint in the polygons for the LED digits
    public void paintEntryPanel(Graphics g/*allows to draw arbitrary shapes*/)
    {
        Graphics2D g2 =(Graphics2D)g;

        //in order to bring up antialiasing for polygon
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);

        int x = 0;
        int y = 0;
//Creating top
        int xtop[] =
        {
            x + 8,
            x + 8,
            x + 35,
            x + 28,
            x + 15
        };
        int ytop[] = {
            y + 5,
            y + 5,
            y + 5,
            y + 8,
            y + 8
        };
//Creating topleft
        int xtopleft[] =
        {
            x + 8,
            x + 8,
            x + 12,
            x + 12,
            x + 8
        };
        int ytopleft[] = {
            y + 9,
            y + 9,
            y + 12,
            y + 17,
            y + 20
        };
//Creating topright
        int xtopright[] =
        {
            x + 35,
            x + 35,
            x + 31,
            x + 31,
            x + 35
        };
        int ytopright[] = {
            y + 9,
            y + 9,
            y + 12,
            y + 17,
            y + 20
        };
//Creating middle
        int xmiddle[] =
        {
            x + 8,
            x + 8,
            x + 12,
            x + 31,
            x + 35,
            x + 31,
            x + 12,
        };
        int ymiddle[] = {
            y + 24,
            y + 24,
            y + 22,
            y + 22,
            y + 24,
            y + 26,
            y + 26
        };
//Creating bottomleft
        int xbottomleft[] =
        {
            x + 8,
            x + 8,
            x + 12,
            x + 12,
            x + 8
        };
        int ybottomleft[] = {
            y + 27,
            y + 27,
            y + 30,
            y + 35,
            y + 38
        };
//Creating bottomright
        int xbottomright[] =
        {
            x + 35,
            x + 35,
            x + 31,
            x + 31,
            x + 35
        };
        int ybottomright[] = {
            y + 27,
            y + 27,
            y + 30,
            y + 35,
            y + 38
        };
//Creating bottom
        int xbottom[] =
        {
            x + 8,
            x + 8,
            x + 35,
            x + 28,
            x + 15
        };
        int ybottom[] = {
            y + 42,
            y + 42,
            y + 42,
            y + 39,
            y + 39
        };
        
        GeneralPath polygon;
        polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD,xtop.length);
        //creating top
        if(top == true)
        {
            polygon.moveTo(xtop[0], ytop[0]);//starting the origin
            for(int index = 1; index < xtop.length; index++)
            {
                polygon.lineTo(xtop[index],ytop[index]);
            }
            polygon.closePath();
        }
        //topleft
        if(topleft == true)
        {
            polygon.moveTo(xtopleft[0], ytopleft[0]);
            for(int index = 1; index < xtopleft.length; index++)
            {
                polygon.lineTo(xtopleft[index],ytopleft[index]);
            }
            polygon.closePath();
            
        }
         //topright
        if(topright == true)
        {
            polygon.moveTo(xtopright[0], ytopright[0]);
             for(int index = 1; index < xtopright.length; index++)
            {
                polygon.lineTo(xtopright[index],ytopright[index]);
            }
            polygon.closePath();
           
        }
        //middle
        if(middle == true)
        {
            polygon.moveTo(xmiddle[0], ymiddle[0]);
             for(int index = 1; index < xmiddle.length; index++)
            {
                polygon.lineTo(xmiddle[index],ymiddle[index]);
            }
            polygon.closePath();
           
        }
        //bottomleft
        if(bottomleft == true)
        {
             polygon.moveTo(xbottomleft[0], ybottomleft[0]);
             for(int index = 1; index < xbottomleft.length; index++)
            {
                polygon.lineTo(xbottomleft[index],ybottomleft[index]);
            }
            polygon.closePath();
           
        }
        //bottomright
        if(bottomright == true)
        {
              polygon.moveTo(xbottomright[0], ybottomright[0]);
             for(int index = 1; index < xbottomleft.length; index++)
            {
                polygon.lineTo(xbottomright[index],ybottomright[index]);
            }
            polygon.closePath();
           
        }
         //bottom
        if(bottom == true)
        {
             polygon.moveTo(xbottom[0], ybottom[0]);
             for(int index = 1; index < xbottom.length; index++)
            {
                polygon.lineTo(xbottom[index],ybottom[index]);
            }
            polygon.closePath();
        }
        g2.setColor(Color.red);
            g2.fill(polygon);//fills color in polygon
            g2.draw(polygon);
}
   public void paintColons(Graphics g)
    {
        Graphics2D g2 =(Graphics2D)g;
        //in order to bring up antialiasing for polygon
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHints(rh);
         //Creating top
        int xDot = 5;
        int yDot = 12;
        int yDot2 = 32;
        GeneralPath A1,A2,B1,B2;

        A1 = new GeneralPath();
        A1.moveTo(xDot, yDot);//starting the origin
        A1.curveTo(5, 12, 8, 5, 13, 12);
        A1.closePath();

        A2 = new GeneralPath();
        A2.moveTo(xDot, yDot);//starting the origin
        A2.curveTo(5, 12, 8, 19, 13, 12);
        A2.closePath();

        B1 = new GeneralPath();
        B1.moveTo(xDot, yDot2);//starting the origin
        B1.curveTo(5, 32, 8, 25, 13, 32);
        B1.closePath();

        B2 = new GeneralPath();
        B2.moveTo(xDot, yDot2);//starting the origin
        B2.curveTo(5, 32, 8, 39, 13, 32);
        B2.closePath();

         g2.setColor(Color.red);
            g2.fill(A1);//fills color in polygon
            g2.draw(A1);
            g2.fill(A2);//fills color in polygon
            g2.draw(A2);
            g2.fill(B1);//fills color in polygon
            g2.draw(B1);
            g2.fill(B2);//fills color in polygon
            g2.draw(B2);
    }
}