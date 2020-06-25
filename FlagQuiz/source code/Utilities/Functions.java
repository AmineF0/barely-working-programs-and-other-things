package Utilities;

import java.io.File;

public class Functions {

    //for universal program that works cross platform , which java let us do
    //it does that by changing the special file system separator by File.separator()) function
    public static String fileLinkCP(String link, String os){
        String updatedLink = "" ;
        String[] path = new String[0];
        if(os.equals("unix"))
            path = link.split("/");
        for (int n = 0 ; n < path.length ; n++)
            updatedLink+=path[n]+ File.separator;
        updatedLink=updatedLink.substring(0,updatedLink.length()-1);
        return updatedLink;
    }

}
