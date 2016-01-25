package jsec.controller;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import jsec.facade.UserFacade;

@Model
public class Index {
    
    @Inject UserFacade userFacade;
    
    private String userCountMessage;
    
    /**
     * Set UserCountMessage field with the number of registered users
     */
    public void updateUserCount() {
        userCountMessage = String.format("There are %d users", userFacade.userCount() );
    }

    /**
     * UserCountMessage field
     * @return userCountMessage field.
     */
    public String getUserCountMessage() {
        return userCountMessage;
    }

    /**
     * UserCountMessage Field
     * @param userCountMessage field.
     */
    public void setUserCountMessage(String userCountMessage) {
        this.userCountMessage = userCountMessage;
    }

}
