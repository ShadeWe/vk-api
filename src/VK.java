import com.google.gson.*;
import org.json.simple.parser.ParseException;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class VK extends Auth {

    private String readStream(InputStream stream) throws IOException, ParseException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder data = new StringBuilder();
        String temp = "";

        while ((temp = reader.readLine()) != null)
            data.append(temp);

        return data.toString();
    }

    private boolean requestAccess() {


        return false;
    }


    public VK(String username, String password, String scope) throws IOException, ParseException
    {
        super();

        File token_file = new File("token.json");

        if (token_file.exists()) {

            FileReader fr = new FileReader(token_file);
            Scanner scan = new Scanner(fr);

            String token_json = scan.nextLine();

            JsonObject convertedObject = new Gson().fromJson(token_json, JsonObject.class);

            this.access_token = convertedObject.get("access_token").getAsString();
            this.user_id = convertedObject.get("user_id").toString();

        } else {

        }

//        String auth = "https://oauth.vk.com/token?grant_type=password&scope="+scope+"&client_id=3140623&client_secret=VeWdmVclDCtn6ihuP1nt&username="+ username +"&password="+ password +"&v=5.52";
//
//        URL url = new URL(auth);
//
//        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//
//        connection.setRequestMethod("GET");
//        connection.setDoOutput(true);
//
//        String response = this.readStream(connection.getInputStream());
//
//
//        JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
//
//
//        System.out.println(response);
//
//        this.access_token = convertedObject.get("access_token").getAsString();
//        this.user_id = convertedObject.get("user_id").toString();

//        System.out.println(convertedObject.get("access_token").getAsString());
//        System.out.println(convertedObject.get("user_id").getAsString());
//
//        FileWriter fw = new FileWriter( "token.json" );
//        fw.write(response);
//        fw.close();
//
//        connection.disconnect();
    }

    public String getFriends() throws IOException, ParseException {

        String request = "/method/friends.get?user_id="+ this.user_id +"&fields=1&access_token=" + this.access_token;
        String auth = "https://api.vk.com" + request + "&v=5.52";

        URL url = new URL(auth);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setDoOutput(true);

        connection.disconnect();

        String response = this.readStream(connection.getInputStream());

        JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
        Set<Map.Entry<String, JsonElement>> entries = convertedObject.entrySet();

        int friends_total = 0;

        for (Map.Entry<String, JsonElement> entry: entries) {

            JsonObject sub_row = entry.getValue().getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> sub_row_entries = sub_row.entrySet();

            for (Map.Entry<String, JsonElement> sub_entry: sub_row_entries) {

                if (sub_entry.getKey().toString().equals("items"))
                {
                    JsonArray sub = sub_entry.getValue().getAsJsonArray();

                    sub.forEach(item -> {

                        JsonObject friend = item.getAsJsonObject();

                        System.out.printf(friend.get("first_name").getAsString() + " " + friend.get("last_name").getAsString() + " Online: " + friend.get("online").getAsString() + "\r\n");

                    });

                } else if (sub_entry.getKey().toString().equals("count")) {

                    friends_total = sub_entry.getValue().getAsInt();

                }

            }

        }

        System.out.printf("\r\nFriends found: " + friends_total);


        return null;
    }

    public String sendMessage() throws IOException {

//        String request = "/method/messages.send?user_id="+ this.user_id +"&message=сообщение_из_программы&access_token=" + this.access_token;
//        String auth = "https://api.vk.com" + request + "&v=5.52";
//
//
//        URL url = new URL(auth);
//        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
//
//        connection.setRequestMethod("GET");
//        connection.setDoOutput(true);
//
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        StringBuilder data = new StringBuilder();
//        String temp = "";
//        while ((temp = reader.readLine()) != null) {
//
//            data.append(temp);
//
//        }
//
//        System.out.printf(data.toString());
//
//        connection.disconnect();
//
        return null;


    }

    public String getAccessToken()
    {
        return this.access_token;
    }

    public String getUserId()
    {
        return this.user_id;
    }

}
