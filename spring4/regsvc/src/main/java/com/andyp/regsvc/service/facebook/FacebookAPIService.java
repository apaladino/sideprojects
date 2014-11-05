package com.andyp.regsvc.service.facebook;

/**
 * Created by apaladino on 10/18/14.
 */
public interface FacebookAPIService {

    /**
     * Authenticate with Facebook API and return authorization token.
     *
     * @return
     */
    String getAuthToken();
}
