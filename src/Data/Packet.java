package Data;

import java.io.Serializable;
import java.util.List;
import java.net.InetAddress;
import java.io.*;
import java.util.ArrayList;
public class Packet implements Serializable {
    public List<Object> Gdata = new ArrayList<>();
    public int packetInt;
    public boolean packetBool;
    public int senderId;
    public PacketType packetType;
    
    public Packet(PacketType type, int senderId){
        this.senderId = senderId;
        this.packetType = type;
    }
    
    public Packet(byte[] packetbytes) throws Exception{
        ByteArrayInputStream st = new ByteArrayInputStream(packetbytes);
        ObjectInputStream ob = new ObjectInputStream(st);
        Packet p =(Packet)ob.readObject();
        ob.close();
        this.Gdata = p.Gdata;
        this.packetInt = p.packetInt;
        this.packetBool = p.packetBool;
        this.senderId = p.senderId;
        this.packetType = p.packetType;        
    }
    
    public byte[] toBytes() throws IOException,ClassNotFoundException{
        ByteArrayOutputStream st = new ByteArrayOutputStream();
        ObjectOutputStream ob = new ObjectOutputStream(st);
        ob.writeObject(this);
        byte[] bytes = new byte[st.size()];
        bytes = st.toByteArray();
        st.close();
        return bytes;
        
    }
    
    public static String getIp4Address(){
        try{
            InetAddress address =InetAddress.getLocalHost();
            String ip = address.getHostAddress();
            return ip;
        }
        catch(Exception e){
            return "127.0.0.1";
        }
        
    }
    
    public enum PacketType
    {
        Registeration,
        Chat
    }
}
