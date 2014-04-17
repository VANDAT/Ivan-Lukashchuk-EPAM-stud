package com.bionic.socnet.convertors;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import com.bionic.socnet.cdi.MySession;
import com.bionic.socnet.entities.User;

@ManagedBean
@RequestScoped
@FacesConverter("userConvertor")
public class UserConvertor implements Converter {
		
	@Inject
	private MySession mySession;
	
		@Override
		public Object getAsObject(FacesContext context, UIComponent component,
				String value) {
			for(User user : mySession.getFriends()){
				if(user.getEmail().equals(value)){
					return user;
				}
			}
			return null;
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,
				Object value) {			
			return ((User) value).getEmail();
		}
	}