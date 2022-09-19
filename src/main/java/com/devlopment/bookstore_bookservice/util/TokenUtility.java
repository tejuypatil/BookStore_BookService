package com.devlopment.bookstore_bookservice.util;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.devlopment.bookstore_bookservice.exception.InvalidTokenException;
import org.springframework.stereotype.Component;



@Component
public class TokenUtility {
    private static final String TOKEN_SECRET = "hubhhubghjb";

    public String createToken(int id) {
        try {
            //to set algorithm
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);

            String token = JWT.create().withClaim("user_id", id).sign(algorithm);
            return token;
        }
        catch (JWTCreationException exception)
        {
            exception.printStackTrace();
            //log Token Signing Failed
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public int decodeToken(String token) {
        try {
            int userid;
            //for verification algorithm
            Verification verification = null;

            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)) ;

            assert verification != null;
            JWTVerifier jwtverifier = verification.build();
            //to decode token
            DecodedJWT decodedjwt = jwtverifier.verify(token);

            Claim claim = decodedjwt.getClaim("user_id");
            userid = claim.asInt();
            return userid;
        }
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch (Exception exception){
            throw new InvalidTokenException(token);
        }

        return -1;
    }

}
