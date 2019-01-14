import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * Class that stores and handles a Spotify authorization token
 * Author: Mikael Lindfors
 */

public class Token {
    private String access_token;
    private String token_type;
    private int expires_in;

    public Token() {
    }

    /**
     * Method that return the spotify access token
     * @return String access_token
     */
    public String getAccess_token() {
        return access_token;
    }

    /**
     * Method that sets the spotify authorization token
     * @param access_token String with spotify authorization token
     */
    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    /**
     * Method that returns the token type.
     * @return String with token type
     */
    public String getToken_type() {
        return token_type;
    }

    /**
     * Method that sets the token type.
     * @param token_type String with the token type.
     */
    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    /**
     * Method that returns the amount of time before token will be expired
     * @return return int number of seconds before it expires.
     */
    public int getExpires_in() {
        return expires_in;
    }

    /**
     * Method that sets the amount of time before the token will be expired
     * @param expires_in int number of seconds before it expires.
     */
    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}

