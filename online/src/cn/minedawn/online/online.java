package cn.minedawn.online;
import java.io.*;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class online extends JavaPlugin implements Listener{   //实现接口
	public void onEnable(){
		getServer().getPluginManager().registerEvents(this, this);
		getLogger().info("正版玩家验证插件(普通服插件)成功载入");
		getLogger().info("作者:Minedawn 我的黎明，QQ:1649800132");
		getLogger().info("http://www.minedawn.cn");
	}
	@EventHandler
	public void chat(AsyncPlayerChatEvent evt) throws IOException{
		String read;
        FileReader fileread;
        String readStr = "";
        String a = null;
		try { 
            fileread = new FileReader("C:/online.txt");
            @SuppressWarnings("resource")
			BufferedReader bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                	if(read == evt.getPlayer().getName()){
                		evt.setFormat("&r[&a正版玩家&r]" + evt.getFormat());
                		return;
    		    	}
                	@SuppressWarnings("resource")
					FileOutputStream out = new FileOutputStream(new File("C:/online.txt"));
                	out.write(("read:"+read+",txt:"+a+",player:"+evt.getPlayer().getName()+",byte:"+read.getBytes()+",byte:"+evt.getPlayer().getName().getBytes()).getBytes());
                	out.flush();
                	getLogger().info("read:"+read+",txt:"+a+",player:"+evt.getPlayer().getName()+",byte:"+read.getBytes()+",byte:"+evt.getPlayer().getName().getBytes());
                    readStr = readStr + read+ "\r\n";
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block 
                e.printStackTrace();
            }
        } catch (FileNotFoundException e){
            // TODO Auto-generated catch block 
            e.printStackTrace();
        }
	}
}