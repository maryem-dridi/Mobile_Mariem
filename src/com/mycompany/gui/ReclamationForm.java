/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Recamation;
import com.mycompany.services.Service_Reclamation;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author dridi
 */
public class ReclamationForm extends BaseForm{
    
    
    
    
    
    
    
    
    Resources theme = UIManager.initFirstTheme("/themeCoHeal");
    public ReclamationForm(Form previous)
    {
     
     
           super("Reclamations",BoxLayout.y());
                 this.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(() -> {
            // this will take a while...

            Display.getInstance().callSerially(() -> {
                this.removeAll();
                
                
             for (Recamation c : new Service_Reclamation().findAll()) {

            this.add(addItem_Coach(c));

        }
               this.revalidate();
            });
        });

        this.getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    mb.setUIIDLine1("libC");
                    mb.setUIIDLine2("btn");
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1
                            || line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
        
     
        
              
           this.getToolbar().addCommandToRightBar("add", null, ev -> {
               try {
                 new AddReclamation(this).show();
               } catch (Exception ex) {
            
               }
               
               
               
        });
           
           
           
                  
  

    }
    
 
//    
    
    
    
    
    
    
     public Container addItem_Cotch_detail(Recamation c) {
  
  
      
  
  
  
        
        Container cn1 = new Container(new BorderLayout());
        Container cn2 = new Container(BoxLayout.y());
        
        Label nom = new Label("Name  : "+c.getMessage());
       
       Label genre = new Label("Type : "+c.getType());

       Label statut = new Label("Status : "+c.getStatut());

        

        Button btn = new Button("Statistique");
        Button screen = new Button("Screen");

        cn2.add(nom).add(genre).add(statut).add(screen).add(btn);
        cn1.add(BorderLayout.WEST, cn2);
        
        
        
        
        
        
        
        
        
        
                
                
                

       
 
        
                 btn.addActionListener((x) -> {                        
        new StatReclamation().createPieChartForm("Reclamation", new Service_Reclamation().getStat(), theme);       
        });
              
                     
                     
   
        
        screen.addActionListener(e -> {
            
             Form form = Display.getInstance().getCurrent();
        if (form != null) {
            
            Image screenshot = Image.createImage(form.getWidth(), form.getHeight());
form.revalidate();
form.setVisible(true);
form.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
        }  
            
            });
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

        return cn1;

    }
     
     
   public MultiButton  addItem_Coach(Recamation c) {
     
          MultiButton m = new MultiButton();
          
/////////////////////////////////////   Notification     /////////////////////
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Liste des Reclamations");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("Hallo");
///////////////////////////////////////////

                   
               

            ImageViewer image_coach;
            Image imge;
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                // image_coach = new ImageViewer(imge);
                
                
        
        Label type = new Label("Nom  : "+c.getType());

              Label statut = new Label("Nom  : "+c.getStatut());

       
       m.setTextLine1(c.getType());
       m.setTextLine2(c.getStatut());
       m.setTextLine3(c.getMessage());

       
          
        m.setEmblem(theme.getImage("round.png"));
      
           // m.setIcon(imge);
              m.addActionListener(l
                -> {

            Form f2 = new Form("Detail",BoxLayout.y());
            
            f2.add(addItem_Cotch_detail(c));
             f2.getToolbar().addCommandToOverflowMenu("back", null, ev -> {
                   new ReclamationForm(this).showBack();
        });
            f2.show(); });
         
        return m;

    }
         
    
}
