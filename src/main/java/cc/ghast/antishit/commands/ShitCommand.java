package cc.ghast.antishit.commands;

import cc.ghast.antishit.Antishit;
import cc.ghast.antishit.api.checks.Check;
import cc.ghast.antishit.data.PlayerData;
import cc.ghast.antishit.managers.ConfigManager;
import cc.ghast.antishit.utils.chat.Chat;
import cc.ghast.antishit.utils.hastebin.Hastebin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

import java.util.ArrayList;
import java.util.List;

public class ShitCommand implements CommandExecutor {
    public ShitCommand(){
        Antishit.getInstance().getCommand("shit").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return returnInvalidPlayer(sender);
        Player player = (Player) sender;
        if (!player.hasPermission("shit.debug")) return returnNoPerm(player);
        PlayerData data = Antishit.getInstance().getApi().getPlayerDataManager().getData(player);
        switch (args.length){
            case 1: {
                switch (args[0].toLowerCase()){
                    case "alerts": {
                        data.staff.setVerboseAlertable(true);
                        data.staff.setVLAlertable(true);
                        player.sendMessage(Chat.translate( data.    staff.isVerboseAlertable() ? "&7[&c!&7] &6Set alerts on!" : "&7[&c!&7] &6Set alerts off!"));
                        return true;
                    }

                    default: {
                        return returnInfo(player);
                    }
                }
            }
            case 2: {
                switch (args[0].toLowerCase()){
                    case "debug": {
                        switch (args[1].toLowerCase()){
                            case "aim": {
                                data.setDebugAim(!data.isDebugAim());
                                String message = data.isDebugAim() ? "&7[&c!&7] &6Set debug aim on!" : "&7[&c!&7] &6Set debug aim off!";
                                player.sendMessage(Chat.translate(message));
                                return true;
                            }
                            default: {
                                return returnInfo(player);
                            }
                        }
                    }
                    case "paste": {
                        switch (args[1].toLowerCase()){
                            case "gcd": {
                                List<Long> list = data.getPreviousGCDS();
                                String[] payload = new String[list.size()];
                                for (int i = 0; i < list.size(); i++){
                                    payload[i] = list.get(i).toString();
                                }
                                try {
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted hastebin at: " + Hastebin.paste(payload, "")));
                                } catch (Exception e){
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Error when pasting to Hastebin. Check console"));
                                }
                                return true;
                            }
                            case "pitchchange": {
                                List<Float> list = data.getPitchChangePrevious();
                                String[] payload = new String[list.size()];
                                for (int i = 0; i < list.size(); i++){
                                    payload[i] = list.get(i).toString();
                                }
                                try {
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted hastebin at: " + Hastebin.paste(payload, "")));
                                } catch (Exception e){
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Error when pasting to Hastebin. Check console"));
                                }
                                return true;
                            }
                            case "yawchange": {
                                List<Float> list = data.getYawChangePrevious();
                                String[] payload = new String[list.size()];
                                for (int i = 0; i < list.size(); i++){
                                    payload[i] = list.get(i).toString();
                                }
                                try {
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted hastebin at: " + Hastebin.paste(payload, "")));
                                } catch (Exception e){
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Error when pasting to Hastebin. Check console"));
                                }
                                return true;
                            }
                            case "yawdif": {
                                List<Float> list = data.getPreviousYawChangeDif();
                                String[] payload = new String[list.size()];
                                for (int i = 0; i < list.size(); i++){
                                    payload[i] = list.get(i).toString();
                                }
                                try {
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted hastebin at: " + Hastebin.paste(payload, "")));
                                } catch (Exception e){
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Error when pasting to Hastebin. Check console"));
                                }
                                return true;
                            }
                            case "rawgcd": {
                                List<Long> list = data.getPreviousRawGCDS();
                                String[] payload = new String[list.size()];
                                for (int i = 0; i < list.size(); i++){
                                    payload[i] = list.get(i).toString();
                                }
                                try {
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted hastebin at: " + Hastebin.paste(payload, "")));
                                } catch (Exception e){
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Error when pasting to Hastebin. Check console"));
                                }
                                return true;
                            }
                            case "all": {
                                List<Long> gcd = data.getPreviousGCDS();
                                String[] gcdpayload = new String[gcd.size()];
                                for (int i = 0; i < gcd.size(); i++){
                                    gcdpayload[i] = gcd.get(i).toString();
                                }

                                List<Float> pitch = data.getPitchChangePrevious();
                                String[] pitchpayload = new String[pitch.size()];
                                for (int i = 0; i < pitch.size(); i++){
                                    pitchpayload[i] = pitch.get(i).toString();
                                }

                                List<Float> yaw = data.getYawChangePrevious();
                                String[] yawpayload = new String[yaw.size()];
                                for (int i = 0; i < yaw.size(); i++){
                                    yawpayload[i] = yaw.get(i).toString();
                                }
                                try {
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted GCD hastebin at: " + Hastebin.paste(gcdpayload, "")));
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted PITCH hastebin at: " + Hastebin.paste(pitchpayload, "")));
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Pasted YAW hastebin at: " + Hastebin.paste(yawpayload, "")));
                                } catch (Exception e){
                                    player.sendMessage(Chat.translate("&7[&c!&7] &6Error when pasting to Hastebin. Check console"));
                                }
                                return true;
                            }
                            default: {
                                return returnInfo(player);
                            }
                        }
                    }
                    case "clear": {
                        switch (args[1].toLowerCase()){
                            case "gcd": {
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getPreviousGCDS().size() + " GCD logs"));
                                data.getPreviousGCDS().clear();
                                return true;
                            }
                            case "pitchchange": {
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getPitchChangePrevious().size() + " Pitch Difference logs"));
                                data.getPitchChangePrevious().clear();
                                return true;
                            }
                            case "yawchange": {
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getYawChangePrevious().size() + " Yaw Difference logs"));
                                data.getYawChangePrevious().clear();
                                return true;
                            }
                            case "yawdif": {
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getYawChangePrevious().size() + " Yaw Difference logs"));
                                data.getPreviousYawChangeDif().clear();
                                return true;
                            }
                            case "rawgcd": {
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getYawChangePrevious().size() + " Raw GCD logs"));
                                data.getPreviousYawChangeDif().clear();
                                return true;
                            }
                            case "all": {
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getYawChangePrevious().size() + " Yaw Change logs"));
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getPitchChangePrevious().size() + " Pitch Change logs"));
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getPreviousGCDS().size() + " GCD logs"));
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getPreviousYawChangeDif().size() + " Yaw Difference logs"));
                                player.sendMessage(Chat.translate("&7[&c!&7] &aSuccessfully &6cleared " + data.getYawChangePrevious().size() + " Raw GCD logs"));
                                data.getPreviousGCDS().clear();
                                data.getPitchChangePrevious().clear();
                                data.getYawChangePrevious().clear();
                                data.getPreviousYawChangeDif().clear();
                                data.getPreviousRawGCDS().clear();
                                return true;
                            }
                            default: {
                                return returnInfo(player);
                            }
                        }
                    }
                    default: {
                        return returnInfo(player);
                    }
                }
            }

            default: {
                return returnInfo(player);
            }
        }
    }

    private boolean returnInvalidPlayer(CommandSender player){
        player.sendMessage(Chat.translate(ConfigManager.getSettings().getString("message.invalid-player")));
        return false;
    }

    private boolean returnNoPerm(Player player){
        player.sendMessage(Chat.translate(ConfigManager.getSettings().getString("message.no-permission")));
        return false;
    }

    private boolean returnInfo(Player player){
        String[] message = new String[]{
                "&7&m-----------------------------------",
                "&6&lAntishit Debugger &7 made by &6Ghast",
                "&7Version: &6" + Antishit.getInstance().getDescription().getVersion(),
                "&7&m-----------------------------------",
                "&7/&6shit &7-> &6Main shit command",
                "&7/&6shit verbose &7-> &6Toggle verboses",
                "&7/&6shit alerts &7-> &6Toggle alerts",
                "&7/&6shit checks &7-> &6See your current checks",
                "&7/&6shit forcecheck <player> &7-> &6Forcefully start the algorithm",
                "&7&m-----------------------------------"
        };
        for (String s : message){
            player.sendMessage(Chat.translate(s));
        }
        return true;
    }


}
