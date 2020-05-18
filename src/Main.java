import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;

public class Main {

    public static void get_friends(String token, String user_id) throws IOException, ParseException, NoSuchAlgorithmException {

    }

    public static void main(String[] args) throws IOException, ParseException, NoSuchAlgorithmException {

        String scope = "notify,friends,photos,audio,video,docs,notes,pages,status,offers,questions,wall,groups,messages,notifications,stats,ads,offline";

        VK client = new VK("", "", scope);

        String friends = client.getFriends();


    }
}
