// Muhammad Khizar
// CS2336.0U1
// Professor: Kamran Khan
// Overview: This program creates a bot at a channel at
// webchat.freenode.net and that bot can communicate with
// people in that channel that talk to it.
// It has 2 major functionalities. 
// 1. Displaying weather of desired location.
// 2. Displaying top news in the United States.
// These are done by the bot using an API. The API is called
// the information is given in JSON format. and the JSON is then
// parsed to get and display the information.
// Other than that, it replies to basic greetings and thank you.

import org.jibble.pircbot.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main
{
	// Main method
	
	public static void main(String[] args) throws Exception 
	{
		// creating chatbot
		
		String server = "irc.freenode.net", channel = "#testChannel"; 
		
		bot ChatBot = new bot();
		ChatBot.setVerbose(true);
		ChatBot.connect(server); 
		ChatBot.joinChannel(channel); 		// joining channel called test channel
		
		// sending message upon joining
		
		ChatBot.sendMessage(channel, "Hey this is bot! You can talk to me!");
		
	}
}

class bot extends PircBot 
	{
	
	private static HttpURLConnection connection;

	// pircbot constructor 
	
	public bot() 
	{
		this.setName("KhizarSmartBot");
	}

	public void onMessage(String channel, String sender, String login, String hostname, String message)
	    {
		
		// string to check the location once getting the user
		
			String location = "";
			
			// if the string contains keyword weather and in then it will try to find or else not
			
			if (message.toLowerCase().contains("weather") || message.toLowerCase().contains("temperature")) 
			{
				if (message.contains("in") || message.contains(" at "))
				{
						location = removeTillWord(message, "in");
						location = location.replaceFirst("in", "");
						sendMessage(channel, API(location));
				}
				else
				{
					// if there is no "in " or "at " then it will ask for city name
					
					sendMessage(channel, "Please enter the city name.");
				}
				
			} 
			
			// replying to basic greetings
			
			else if (message.toLowerCase().contains("hello")
					|| message.toLowerCase().contains("hi")
					|| message.toLowerCase().contains("hey")) 
			{
				sendMessage(channel, "Hey " + sender + "! ");
			}
			else if (message.contains("how are you") || message.contains("How are you"))
			{
				sendMessage(channel, "I am good you?");
			}
			
			//checking if user asks for news
			
			else if(message.toLowerCase().contains("news"))
			{
				sendMessage(channel, "Todays Hot 5 News: ");
				sendMessage(channel, newsAPI(0));
				sendMessage(channel, newsAPI(1));
				sendMessage(channel, newsAPI(2));
				sendMessage(channel, newsAPI(3));
				sendMessage(channel, newsAPI(4));		
			}
			
			// basic manners
			
			else if (message.toLowerCase().contains("thanks") || message.toLowerCase().contains("thank you") || message.toLowerCase().contains("love you"))
			{
				sendMessage(channel, "No Problem " + sender + " :)");
			}
			// leave channel command
			
			else if (message.toLowerCase().contains("leave channel") || message.toLowerCase().contains("leave chat"))
			{
				this.disconnect();
			}
			
			// if none of the commands match up then it asks user to write again
			
			else
			{
				// please elaborate
				
				sendMessage(channel, "Please elaborate what I can help you with?");
			}
			
		}
	
		// weather API caller
	
		public String API(String location)
		{
			// reading json string from website
			
			BufferedReader reader;
			String line;
			StringBuffer responseContent = new StringBuffer();

			try 
			{
				// url for api
			
				String URLFULL = "http://api.openweathermap.org/data/2.5/weather?q=" 
								+ location 
								+ "&APPID=aa6a07d4d33a46f7b558c00195a62565";
				
				URL url = new URL(URLFULL);
				connection = (HttpURLConnection) url.openConnection();
				
				// Request connection Setup
				
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				
				int status = connection.getResponseCode();
				
				// checking status of connectivity 
				
				if(status > 299)
				{
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while((line = reader.readLine())!= null)
					{
						responseContent.append(line);
					}
					reader.close();
				}
				else
				{
					// getting json
					
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while((line = reader.readLine()) != null)
					{
						responseContent.append(line);
					}
					reader.close();

				}				
       			
			} 
			// Exceptions
			
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				connection.disconnect();
			}

			// calling parse to parse weather info and returning it
			
			String total = parseWeatherJson(responseContent.toString());
			
			return total;
				
		}
		
		// reomve till word to get important info
		
		public static String removeTillWord(String input, String word) 
		{
		    return input.substring(input.indexOf(word));
		}
		
		// weather json parser using gson
		
		String parseWeatherJson(String json) //parsing the JSON using the GSON
		{
			// getting city name and temp
			
			JsonObject object = new JsonParser().parse(json).getAsJsonObject();
			String cityName = object.get("name").getAsString();
			JsonObject main = object.getAsJsonObject("main");
			double temp = main.get("temp").getAsDouble();
			// F = 9/5 (K - 273) + 32
			double temp2 = temp - 273;
			double temp3 = ((temp2 * 9.0)/5.0) + 32.0;
			temp3 = (double) Math.round(temp3 * 100) / 100;
			
			//returning info
			
			return ("The temperature is " + temp3 + " degrees Fahrenheit in " + cityName); 

		}

		// news API function to call API and return news
		
		public static String newsAPI(int i)
		{
			// using this to store JSON string
			
			BufferedReader reader;
			String line;
			StringBuffer responseContent = new StringBuffer();

			try 
			{
				
				// URL to call news API
			
				String URLFULL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=e606507e20ab46478aa243578a9f14ae";
				
				URL url = new URL(URLFULL);
				connection = (HttpURLConnection) url.openConnection();
				
				// Request Setup
				
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				
				int status = connection.getResponseCode();
				
				// checking connection status
				
				if(status > 299)
				{
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while((line = reader.readLine())!= null)
					{
						responseContent.append(line);
					}
					reader.close();
				}
				else
				{
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					while((line = reader.readLine()) != null)
					{
						responseContent.append(line);
					}
					reader.close();

				}				
	   			
			} 
			
			// exception
			
			catch(IOException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				connection.disconnect();
			}
					
			// string to store main info
			
			String total = "";
			
			// parsing and returning based on call
			
			 try
			 {
				 JSONObject mainObj= new JSONObject(responseContent.toString());
				 int totalResult = (int) mainObj.get("totalResults");
				 JSONArray jsonArray = (JSONArray) mainObj.get("articles");
				 
				 // i is passed in 5 times so 5 headlines are sent
				 
				 if (totalResult > 0)
				 {
					   JSONObject obj= jsonArray.getJSONObject(i);
					   
					   String title = (String) obj.getString("title");
					   			   
					   total = total + ((i + 1) + ". " + title);        
					
				 }
			 }
			 catch(JSONException e)
			 {
				 e.printStackTrace();
			 }
			 		
			 // returning string
			 
			return total;
		}
	}
