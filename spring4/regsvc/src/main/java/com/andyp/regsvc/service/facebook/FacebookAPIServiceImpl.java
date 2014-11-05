package com.andyp.regsvc.service.facebook;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.stereotype.Service;

/**
 * Created by apaladino on 10/18/14.
 */
@Service
public class FacebookAPIServiceImpl implements FacebookAPIService{

    @Value("${fb.app.id}")
    private String fbAppId;
    @Value("${fb.app.secret}")
    private String fbSecret;

    private final static Logger logger = Logger.getLogger(FacebookAPIService.class);

    @Override
    public String getAuthToken(){

        try {
            if(logger.isDebugEnabled()){
                logger.debug("Retrieving authorization token from Facebook API");
            }

            OAuth2Operations oauth = new FacebookConnectionFactory(fbAppId, fbSecret).getOAuthOperations();
            return oauth.authenticateClient().getAccessToken();
        }catch (Throwable t){
            logger.error("Unexpected error: " + t.getMessage(), t);
            throw new IllegalStateException("Unable to retrieve facebook authorization token. Error: " + t.getMessage());
        }

    }

}
