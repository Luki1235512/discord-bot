import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class DiscordBot {

    public static void main(String[] args) throws LoginException, IOException {

        Properties p = new Properties();
        InputStream is = new FileInputStream("bot.properties");
        p.load(is);

        JDA bot = JDABuilder.createDefault(p.getProperty("TOKEN"))
                .setActivity(Activity.watching("you"))
                .addEventListeners(new Commands())
                .build();
    }

}
