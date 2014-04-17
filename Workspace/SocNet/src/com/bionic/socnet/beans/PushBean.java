package com.bionic.socnet.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import org.richfaces.application.push.MessageException;
import org.richfaces.cdi.push.Push;

@Named  
@SessionScoped  
public class PushBean implements Serializable {  
          private static final long serialVersionUID = 1L;   
          private static final String CDI_PUSH_TOPIC = "pushCdi";          
   
          @Inject  
          @Push(topic=CDI_PUSH_TOPIC, subtopic="qwe")
          private Event<String> pushEvent;  
           
          public void sendMessage() throws MessageException {  
   
                    pushEvent.fire("message"); 
          }       
   
}  