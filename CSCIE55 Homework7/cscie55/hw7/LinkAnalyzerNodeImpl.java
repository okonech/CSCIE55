package cscie55.hw7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import cscie55.hw7.Link;

public class LinkAnalyzerNodeImpl implements LinkAnalyzerNode {
    private List<Link> links;

    public LinkAnalyzerNodeImpl (File directory) throws RemoteException {
        List<Link> links = new ArrayList<Link>();
        // loop on every file in directory
        for(File file : directory.listFiles()) {
            try {
                FileInputStream fstream = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                String line;
                int i=0;
                while ((line = br.readLine()) != null) {
                    links.add(Link.parse(line));
                    i++;
                }
                System.out.println(i);
                br.close();
            } catch (FileNotFoundException e) {
                // FOF exception
                e.printStackTrace();
            } catch (IOException e) {
                // file exception
                e.printStackTrace();
            } catch (StringIndexOutOfBoundsException e) {
                // catch parsing error
            }
        }
    }

    /**
     * Return the Links whose timestamp is between startTime and endTime, searching just the files managed by this node.
     * @param startTime Minimum timestamp to be returned.
     * @param endTime Maximum timestamp to be returned.
     * @return Links whose timestamp is between startTime and endTime.
     * @throws RemoteException
     */
    public Set<Link> linksByTime(long startTime, long endTime)
            throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Return the Links with a given URL, searching just the files managed by this node.
     * @param url URL to search for.
     * @return Links with the given URL.
     * @throws RemoteException
     */
    public Set<Link> linksByURL(String url) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Return the Links containing all of the given tags, searching just the files managed by this node.
     * @param tags Set of tags of interest.
     * @return Links containing all of the given tags.
     * @throws RemoteException
     */
    public Set<Link> linksByTag(String... tags) throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

    public static void main(String [] args) {
        //File directory = new File(args[0]);
        String message = "blank";
        // I download server's stubs ==> must set a SecurityManager
        System.setSecurityManager(new RMISecurityManager()); 
        try {
            String currentDir = System.getProperty("user.dir");
            File directory = new File(currentDir+"\\cscie55\\hw7\\links\\files.2");
            System.out.println(directory);

            LinkAnalyzerNodeImpl impl = new LinkAnalyzerNodeImpl(directory);

            System.out.println(directory);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
