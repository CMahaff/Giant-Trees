package gianttrees;

import com.nijiko.permissions.PermissionHandler;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.bukkit.entity.Player;

/**
 *
 * @author Connor Mahaffey
 */
public class Settings {

    private int maxTreeHeight, maxTreeWidth;
    private boolean permIn;
    private PermissionHandler permHand;

    public Settings(){
        loadSettings();
    }

    public void loadSettings(){
        String s;
        File file = new File("plugins/Giant Trees/config.txt");
        if(file.exists()){
            try{
                BufferedReader in = new BufferedReader(new FileReader(file));
                s = in.readLine();
                if(!s.contains("Maximum")){
                    makeSettings();
                    in.close();
                }
                else{
                    s = s.replace("Maximum Tree Height:", "");
                    maxTreeHeight = Integer.parseInt(s);
                    s = in.readLine();
                    s = s.replace("Maximum Tree Width:", "");
                    maxTreeWidth = Integer.parseInt(s);
                    in.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            makeSettings();
        }
    }

    private void makeSettings(){
        maxTreeHeight = 128;
        maxTreeWidth = 16;
        File file = new File("plugins/Giant Trees/");
        file.mkdirs();
        file = new File("plugins/Giant Trees/config.txt");
        try{
            BufferedWriter out = new BufferedWriter(new FileWriter(file));
            out.write("Maximum Tree Height:128");
            out.newLine();
            out.write("Maximum Tree Width:16");
            out.newLine();
            out.close();
        }catch(Exception e){
        	System.out.println("[Giant Trees] ERROR: Unable to create configuration file. (Insuffisient permissons?)");
            //e.printStackTrace();
        }
    }

    public int getMaxTreeHeight(){
        return maxTreeHeight;
    }

    public int getMaxTreeWidth(){
        return maxTreeWidth;
    }

    public void setPermissions(boolean pi, PermissionHandler ph){
        permIn = pi;
        permHand = ph;
    }

    public boolean checkPermission(Player player, String thePerm){
        if(permIn){
            return permHand.has(player, thePerm);
        }
        else{
            if(player.isOp()){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
