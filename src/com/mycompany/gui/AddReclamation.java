/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Recamation;
import com.mycompany.services.Service_Reclamation;
import java.io.IOException;

/**
 *
 * @author youss
 */
public class AddReclamation extends BaseForm{
      String file ;
      String file2 ;
      Resources theme;
    
     public AddReclamation(Form previous) throws IOException {
      super("AddReclamation", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("ADD Reclamation");


     
            this.add(AJOUT);
            

            
            
       ComboBox statut = new ComboBox("Traité","Non Traité");
          
        TextField message = new TextField("", "Message", 20, TextArea.TEXT_CURSOR);

       ComboBox type = new ComboBox("FeedBack","Harcelement","Help");

             
           
   
        Button save = new Button("Ajouter");
      
        
        
        this.add("Message : ").add(message);
        this.add("Type : ").add(type);

 
        this.add(save);
        save.addActionListener(l
                                -> {
                            if (message.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ vide de Message ", "OK", null);

                            } 
               
                            
                            
                            else {
                           
                                try {
                                    
                   
                            
                                    Recamation c = new Recamation();
                                    c.setMessage(message.getText());
                                    
                                    
                                    
                                    
                   if(type.getSelectedIndex() == 1)
                {
                                    c.setType("FeedBack");
                }
                
                if(type.getSelectedIndex() == 2)
                {
                                    c.setType("Harcelement");

                }
                
                if(type.getSelectedIndex() == 3)
                {
                                    c.setType("Help");

                }
                      
                
                
                
                
                     if(statut.getSelectedIndex() == 1)
                {
                                    c.setStatut("Traité");
                }
                
                if(statut.getSelectedIndex() == 2)
                {
                                    c.setStatut("Non Traité");

                }
            

                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                         
                         
           System.out.println("forms.addEvet.addItem()"+c);

           new Service_Reclamation().AddReclamation(c);
           
           
           
           
           com.codename1.ui.Dialog.show("Ajouter Reclamation", "Ajouter Reclamation aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Reclamation de type "+c.getType()+"  ajoute avec succes");
  status.setExpires(4000);  // only show the status for 3 seconds, then have it automatically clear
  status.show();
  System.out.println("hallllllllllllllllllllllllllllllo");
////////////////////////////////////////////
                                        
                                } catch (Exception ex) {
                                       System.out.println("hekllllo");
                                }

                            }

                        }
                        );
        
        
        LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
      
        
        
        
        
        
        
        
        
           
           this.getToolbar().addCommandToLeftSideMenu("Back", null, ev -> {
               try {
                   new ReclamationForm(this).showBack();
               } catch (Exception ex) {
            
               }
               
               
               
        });
        
        
           
           
           
           
           






           
        
 this.getToolbar().addCommandToLeftBar(null, theme.getImage("back.png"), evx -> {
                this.showBack();
            });
        
        this.show();

             
     
     
                            

      
     
     }
     
     
     
     private void addButton(Image img, Resources res) {
         
         
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        add(cnt);  
        
		}
     
     
     
     
     private void addTab(Tabs swipe, Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (image.getHeight() < size) {
            image = image.scaledHeight(size);
        }
        if (image.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overLay = new Label("", "ImageOverLay");

//        Container page1 = LayeredLayout.encloseIn(
//                imageScale,
//                overLay,
//                BorderLayout.south(
//                        BoxLayout.encloseY(
//                                new SpanLabel(text, "LargeWhiteText"),
//                                spacer
//                        )
//                )
//        );

        //swipe.addTab("", res.getImage("back-logo.jpeg"));
    }
}
