package control;

import java.awt.EventQueue;
import ui.GameCanvas;
import ui.GameFrame;
import model.GameState;

/**Sets up each Player with a client, a renderer and a GUI
 * and logs them in to the server. Runs on a seperate main method to the server in order to keep
 * all clients seperate from the server
 * @author lategacuan && mcleankand
 *
 */
public class MainClient {

    public static final int F_WIDTH = 1000;
    public static final int F_HEIGHT = 600;
    public static final int C_WIDTH = 800;
    public static final int C_HEIGHT = 600;
    public static final String TITLE = "ECS BETA";


    public void launchClient(String username, MainServer server) {


        EventQueue.invokeLater(new Runnable() {
            public void run() {

                boolean isServer = false;
                GameFrame gameFrame = new GameFrame(TITLE, F_WIDTH, F_HEIGHT, server, username);
                GameCanvas gameCanvas = new GameCanvas(gameFrame, C_WIDTH, C_HEIGHT);
                gameFrame.getContentPane().add(gameCanvas);
                gameFrame.pack();
                GameState gameState = new GameState(isServer);
                ClientControl socketClient = new ClientControl(GlobalConst.serverIP, gameState, gameCanvas);
                gameCanvas.setSocketClient(socketClient);
                PlayerController playerController = new PlayerController(socketClient);
                gameFrame.addKeyListener(playerController);
                gameFrame.add(socketClient);


                socketClient.start();
                PacketLogin loginPacket = new PacketLogin(("00"+username).getBytes());
                loginPacket.writeData(socketClient);


            }

        });
    }
}


