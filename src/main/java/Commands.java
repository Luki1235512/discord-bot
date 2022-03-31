import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.List;


public class Commands extends ListenerAdapter {

    public String prefix = "!";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        TextChannel channel = (TextChannel) event.getChannel();

        if (args[0].equalsIgnoreCase(prefix + "join")) {

//            event.getChannel().sendMessage("mortadela").queue();
            if (!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
                channel.sendMessage("I can't join").queue();
                return;
            }

            VoiceChannel connectedChannel = (VoiceChannel) event.getMember().getVoiceState().getChannel();

            if (connectedChannel == null) {
                channel.sendMessage("You are not connected to a voice channel").queue();
                return;
            }

            AudioManager audioManager = event.getGuild().getAudioManager();
            audioManager.openAudioConnection(connectedChannel);

//            System.out.println(connectedChannel.getId());
//            System.out.println(connectedChannel.getMembers());
            List<Member> testVariable = connectedChannel.getMembers();
            Member choosenOne = testVariable.get(0);
//            System.out.println(choosenOne);

            event.getGuild().moveVoiceMember(choosenOne, null).queue();



        } else if (args[0].equalsIgnoreCase(prefix + "leave")) {
            VoiceChannel connectedChannel = (VoiceChannel) event.getGuild().getSelfMember().getVoiceState().getChannel();
            if (connectedChannel == null) {
                channel.sendMessage("I'm not in vc").queue();
                return;
            }
            event.getGuild().getAudioManager().closeAudioConnection();
        }


    }

}
