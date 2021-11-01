package cscie55.hw7;

import java.rmi.RemoteException;
import java.util.Set;

public class LinkAnalyzerImpl implements LinkAnalyzer{

    /**
     * Return the Links whose timestamp is between startTime and endTime, searching just the files managed by this node.
     * @param startTime Minimum timestamp to be returned.
     * @param endTime Maximum timestamp to be returned.
     * @return Links whose timestamp is between startTime and endTime.
     * @throws RemoteException
     */
    public Set<Link> linksByTime(long startTime, long endTime) throws RemoteException {
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

    @Override
    public void registerNode(LinkAnalyzerNode node) throws RemoteException {
        // TODO Auto-generated method stub
        
    }

}
