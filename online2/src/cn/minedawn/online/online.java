package cn.minedawn.online;
import java.io.*;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class online extends JavaPlugin implements Listener{   //实现接口
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("正版玩家验证插件(验证服插件)成功载入");
		getLogger().info("作者:Minedawn Team，QQ:1649800132，邮箱：team@minedawn.cn");
		getLogger().info("http://www.minedawn.cn");
	}
	@SuppressWarnings({ "unused" })
	@EventHandler
	public void onPlayerJoinEvent(PlayerJoinEvent evt) throws IOException{
		String read;
        FileReader fileread;
        String readStr = "";
        String a = null;
		try { 
            fileread = new FileReader("C:/online.txt");
            BufferedReader bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                	if(read == evt.getPlayer().getName()){
    		    		a = "ok";
    		    	}
                    readStr = readStr + read+ "\r\n";
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block 
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) { 
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
        if(a != null){
			return ;
        }
        else{
        	FileOutputStream out = new FileOutputStream(new File("C:/online.txt"));
        	out.write((readStr + evt.getPlayer().getName() + "\r\n").getBytes());
        	out.flush();
    		String comm = null;
        	if(out!=null){
        		out.close();
        		comm = "ban " + evt.getPlayer().getName()+" 验证成功，您是正版玩家，支持正版，支持MC！";
        		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),comm);
        	}
        	else{
        		getLogger().info("ERROR!");
        		comm = "ban " + evt.getPlayer().getName()+" 验证成功，您是正版玩家。但是发生了一个错误，请重新验证！";
        		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),comm);
        	}
        }
	}
}
