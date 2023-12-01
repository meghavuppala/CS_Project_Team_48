package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("SetTextI18n")
public class DiscoverRoomsActivity extends AppCompatActivity {
    public WifiP2pManager.PeerListListener peerListListener;
    public WifiP2pManager.ConnectionInfoListener connectionInfoListener;
    public WindowDecorActionBar.TabImpl connectionStatus;
    ServerSocket serverSocket;
    Thread Thread1 = null;
    TextView tvIP, tvPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    Button startGame;
    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 44444;
    public static final int MAX_PLAYERS = 3;
    String message;

    private List<PrintWriter> playerOutputs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_rooms);

        startGame = findViewById(R.id.startGameHost);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentStartHostGame = new Intent(DiscoverRoomsActivity.this, GameView.class);
                startActivity(intentStartHostGame);

                String startMessage = "Host started the game";
                new Thread(new Thread3(startMessage)).start();
            }
        });

        tvIP = findViewById(R.id.tvIP);
        tvPort = findViewById(R.id.tvPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        try {
            SERVER_IP = getLocalIpAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        Thread1 = new Thread(new Thread1());
        Thread1.start();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
            }
        });
    }

    private String getLocalIpAddress() throws UnknownHostException {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        assert wifiManager != null;
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipInt = wifiInfo.getIpAddress();
        return InetAddress.getByAddress(ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()).getHostAddress();
    }

    class Thread1 implements Runnable {
        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                runOnUiThread(() -> {
                    tvMessages.setText("Not connected");
                    tvIP.setText("IP: " + SERVER_IP);
                    tvPort.setText("Port: " + SERVER_PORT);
                });
                int playerCounts = 0;

                while (playerCounts < MAX_PLAYERS) { // Keep listening for new connections
                    Socket socket = serverSocket.accept();
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true); // Auto-flush set to true
                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    playerOutputs.add(output);

                    playerCounts++;
                    int finalPlayerCounts = playerCounts;
                    runOnUiThread(() -> {
                        tvMessages.setText("Connected: " + finalPlayerCounts + " players");
                    });

                    if (playerCounts == MAX_PLAYERS) {
                        // Notify clients that the game can start or perform any other necessary actions
                        // Example: broadcast a message to all players
                        broadcastToAll("All players connected. Game can start!");
                    }

                    new Thread(new Thread2(input)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Method to broadcast a message to all connected players
        private void broadcastToAll(String message) {
            for (PrintWriter playerOutput : playerOutputs) {
                playerOutput.println(message);
                playerOutput.flush();
            }
        }
    }

    private class Thread2 implements Runnable {
        private BufferedReader input;

        Thread2(BufferedReader input) {
            this.input = input;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    if (message != null) {
                        broadcastToAll("client: " + message); // Broadcast the received message to all clients
                        runOnUiThread(() -> {
                            tvMessages.append("client: " + message + "\n");
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Thread3 implements Runnable {
        private String message;

        Thread3(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            broadcastToAll("server: " + message + " ");
            runOnUiThread(() -> {
                tvMessages.append("You: " + message + " ");
                etMessage.setText("");
            });
        }
    }

    // Method to broadcast a message to all connected players
    private void broadcastToAll(String message) {
        for (PrintWriter playerOutput : playerOutputs) {
            playerOutput.println(message);
            playerOutput.flush();
        }
    }
}