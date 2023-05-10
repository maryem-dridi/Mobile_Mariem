/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.ToastBar;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
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
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Recamation;
import com.mycomany.entities.Reponse;
import com.mycompany.services.Service_Reclamation;
import com.mycompany.services.Service_Reponse;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author youss
 */
public class AddReponse extends BaseForm{
      String file ;
      String file2 ;
      Resources theme;
    
     public AddReponse(Form previous) throws IOException {
      super("Add Reponse", BoxLayout.y());
      theme = UIManager.initFirstTheme("/themeCoHeal");
 
Label AJOUT = new Label("ADD Reponse");


     
            this.add(AJOUT);
            

            
            
          
        TextField message = new TextField("", "Message Reponse", 20, TextArea.TEXT_CURSOR);

         Picker  DateReponse = new Picker ();


      
      
           
   
        Button save = new Button("Ajouter");
      
        
        
        this.add("Message : ").add(message);
        this.add("Date Reponse : ").add(DateReponse);


 
        this.add(save);
        save.addActionListener(l
                                -> {

                            if (message.getText().equals("")) {
                                com.codename1.ui.Dialog.show("Erreur", "Champ Vide de Message ", "OK", null);

                            } 
               
                            
                            
                            else {
                           
                                try {
                                    
                                      
                                    DateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
                                    Date ddebut = DateReponse.getDate();
                                    
                                    String dated = dd.format(ddebut);
                                    
                   
                            
                                    Reponse c = new Reponse();
                                    c.setMessageRep(message.getText());
                                    
                                    
                                        c.setDateRep(dated);

                                       
                      

                
                
             
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                                    
                         
                         
           System.out.println("forms.addEvet.addItem()"+c);

           new Service_Reponse().AddReponse(c);
           
           
           
           
           com.codename1.ui.Dialog.show("Ajouter reponse", "Ajout Reponse aves success ", "OK", null);
                                        
                                        
                                 
                                        
  /////////////////////////////////////   Notification     /////////////////////
  
  ToastBar.Status status = ToastBar.getInstance().createStatus();
  status.setMessage("Reponse  "+c.getMessageRep()+"  ajoute avec succes");
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
                  new ReponseForm(this).showBack();
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
