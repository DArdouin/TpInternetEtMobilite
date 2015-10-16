/******************************************************************************/
/*                                                                            */
/*                                                  FILE: DatagramClient.java */
/*                                                                            */
/*  Demonstrates a simple datagram client                                     */
/*  =====================================                                     */
/*                                                                            */
/*  V1.00   16-DEC-1998 Te                                                    */
/*  V1.10   12-OCT-2009 Te Cleaned up and extended                            */
/*                                                                            */
/******************************************************************************/
package clienthockey;
import Match.Match;
import Match.Equipe;
import java.net.* ;
import protocole.Request;
import protocole.Message;
/**
 *  A simple datagram client
 *  Shows how to send and receive UDP packets in Java
 *
 *  @author  P. Tellenbach,  http://www.heimetli.ch
 *  @version V1.00
 */
public class DatagramClient
{
   private final static int PACKETSIZE = 100 ;

    public DatagramClient() {

      // Check the arguments

      DatagramSocket socket = null ;

      try
      {
         // Convert the arguments first, to ensure that they are valid

         // Construct the socket
         socket = new DatagramSocket() ;

         // Construct the datagram packet
//         byte [] data = "Hello Server".getBytes() ;
         Match MyMatch = new Match(new Equipe("red socks"), new Equipe("Canadian Sherbrooke"));
         Request MyRequest = new Request();
         MyRequest.setMatch(MyMatch); 
         
         byte [] data = Request.marshall(MyRequest);
         
         DatagramPacket packet = new DatagramPacket( data, data.length, InetAddress.getByName("192.168.0.60"), 11111 ) ;
         
         // Send it
         socket.send( packet ) ;

         // Set a receive timeout, 2000 milliseconds
         socket.setSoTimeout( 2000 ) ;

         // Prepare the packet for receive
         packet.setData( new byte[PACKETSIZE] ) ;

         // Wait for a response from the server
         socket.receive( packet ) ;

         // Print the response
         System.out.println( new String(packet.getData()) ) ;

      }
      catch( Exception e )
      {
         System.out.println( e ) ;
      }
      finally
      {
         if( socket != null )
            socket.close() ;
      }
   }
}