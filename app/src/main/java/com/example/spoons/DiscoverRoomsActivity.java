package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Collections;
import java.util.List;

@SuppressLint("SetTextI18n")
public class DiscoverRoomsActivity extends AppCompatActivity {
    ServerSocket serverSocket;
    Thread Thread1 = null;
    TextView displayIP, displayPort;
    TextView displayMessages;
    EditText sendMessage;
    Button btnSend;
    Button startGame;
    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 44444;
    public static final int MAX_PLAYERS = 4;
    String message;
    private int playerCounter = 0;

    private List<PrintWriter> playerOutputs = new ArrayList<>();

    //------------------------------------------------------------------------------


    ImageView iv_deck, iv_card1, iv_card2, iv_card3, iv_card4, cardSwap;
    Button startButton;
    Button swapButton;
    Button passButton;
    Button spoons;

    ArrayList<Integer> cards;

    int singleCard;

    boolean swapStatus = false;

    int finalPlayerCount;

    int myId = 0;

    int card1 = 0;
    int card2 = 0;
    int card3 = 0;
    int card4 = 0;

    int card5 = 0;
    int card6 = 0;
    int card7 = 0;
    int card8 = 0;

    int card9 = 0;
    int card10 = 0;
    int card11 = 0;
    int card12 = 0;

    int card13 = 0;
    int card14 = 0;
    int card15 = 0;
    int card16 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover_rooms);

        startGame = findViewById(R.id.startGameHost);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startMessage = "Host started the game";
                new Thread(new Thread3(startMessage)).start();

                iv_deck.setVisibility(View.VISIBLE);
                startButton.setVisibility(View.VISIBLE);
                swapButton.setVisibility(View.VISIBLE);
                passButton.setVisibility(View.VISIBLE);
                spoons.setVisibility(View.VISIBLE);

                displayIP.setVisibility(View.INVISIBLE);
                displayPort.setVisibility(View.INVISIBLE);
                displayMessages.setVisibility(View.INVISIBLE);
                sendMessage.setVisibility(View.INVISIBLE);
                btnSend.setVisibility(View.INVISIBLE);
                startGame.setVisibility(View.INVISIBLE);
            }

        });

        displayIP = findViewById(R.id.tvIP);
        displayPort = findViewById(R.id.tvPort);
        displayMessages = findViewById(R.id.tvMessages);
        sendMessage = findViewById(R.id.etMessage);
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
                message = sendMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
            }
        });


        //--------------------------------------------------------------------------

        iv_deck = (ImageView) findViewById(R.id.iv_deck);
        iv_card1 = (ImageView) findViewById(R.id.iv_card1);
        iv_card2 = (ImageView) findViewById(R.id.iv_card2);
        iv_card3 = (ImageView) findViewById(R.id.iv_card3);
        iv_card4 = (ImageView) findViewById(R.id.iv_card4);
        cardSwap = (ImageView) findViewById(R.id.cardSwap);
        startButton = (Button) findViewById(R.id.startButton);
        swapButton = (Button) findViewById(R.id.swapButton);
        passButton = (Button) findViewById(R.id.passButton);
        spoons = (Button) findViewById(R.id.button5);


        iv_deck.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        swapButton.setVisibility(View.INVISIBLE);
        passButton.setVisibility(View.INVISIBLE);
        spoons.setVisibility(View.INVISIBLE);


        iv_card1.setVisibility(View.INVISIBLE);
        iv_card2.setVisibility(View.INVISIBLE);
        iv_card3.setVisibility(View.INVISIBLE);
        iv_card4.setVisibility(View.INVISIBLE);
        cardSwap.setVisibility(View.INVISIBLE);

        cards = new ArrayList<>();

        //Diamonds
        cards.add(0);
        cards.add(1);
        cards.add(2);
        cards.add(3);
        cards.add(4);
        cards.add(5);
        cards.add(6);
        cards.add(7);
        cards.add(8);
        cards.add(9);
        cards.add(10);
        cards.add(11);
        cards.add(12);
        cards.add(13);
        //Spades
        cards.add(14);
        cards.add(15);
        cards.add(16);
        cards.add(17);
        cards.add(18);
        cards.add(19);
        cards.add(20);
        cards.add(21);
        cards.add(22);
        cards.add(23);
        cards.add(24);
        cards.add(25);
        cards.add(26);
        //clubs
        cards.add(27);
        cards.add(28);
        cards.add(29);
        cards.add(30);
        cards.add(31);
        cards.add(32);
        cards.add(33);
        cards.add(34);
        cards.add(35);
        cards.add(36);
        cards.add(37);
        cards.add(38);
        //hearts
        cards.add(39);
        cards.add(40);
        cards.add(41);
        cards.add(42);
        cards.add(43);
        cards.add(44);
        cards.add(45);
        cards.add(46);
        cards.add(47);
        cards.add(48);
        cards.add(49);
        cards.add(50);
        cards.add(51);
        //cards.add(52);




        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.shuffle(cards);

                new Thread(new Thread3("Game Has Started")).start();


                card1 = cards.get(0);
                card2  = cards.get(1);
                card3 = cards.get(2);
                card4 = cards.get(3);

                assignImages(card1, iv_card1);
                assignImages(card2, iv_card2);
                assignImages(card3, iv_card3);
                assignImages(card4, iv_card4);

                iv_card1.setVisibility(View.VISIBLE);
                iv_card2.setVisibility(View.VISIBLE);
                iv_card3.setVisibility(View.VISIBLE);
                iv_card4.setVisibility(View.VISIBLE);

                //remove selected cards from arraylist
                cards.remove(cards.indexOf(card1));
                cards.remove(cards.indexOf(card2));
                cards.remove(cards.indexOf(card3));
                cards.remove(cards.indexOf(card4));

                int tempIndex = 4;
                for(int i = 1; i<= finalPlayerCount; i++){
                    int tempCard1 = cards.get(tempIndex);
                    int tempCard2 = cards.get(tempIndex+1);
                    int tempCard3 = cards.get(tempIndex+2);
                    int tempCard4 = cards.get(tempIndex+3);
                    tempIndex++;

                    new Thread(new Thread3("Player"+i+" card1 "+tempCard1)).start();
                    new Thread(new Thread3("Player"+i+" card2 "+tempCard2)).start();
                    new Thread(new Thread3("Player"+i+" card3 "+tempCard3)).start();
                    new Thread(new Thread3("Player"+i+" card4 "+tempCard4)).start();

                    cards.remove(cards.indexOf(tempCard1));
                    cards.remove(cards.indexOf(tempCard2));
                    cards.remove(cards.indexOf(tempCard3));
                    cards.remove(cards.indexOf(tempCard4));
                }

                startButton.setVisibility(View.INVISIBLE);

            }
        });

        iv_deck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Collections.shuffle(cards);
                //check if there are cards left
                if(cards.isEmpty() == false)
                {
                    singleCard = cards.get(0);
                }

                assignImages(singleCard, cardSwap);
                cardSwap.setVisibility(View.VISIBLE);

            }
        });

        iv_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if its clicked on and swap status is true, then swap the card
                if(swapStatus)
                {
                    assignImages(card1, cardSwap);
                    assignImages(singleCard, iv_card1);
                    new Thread(new Thread3("Player1 cardS "+card1)).start();
                    card1 = singleCard;
                    cards.remove(cards.indexOf(singleCard));
                    cardSwap.setVisibility(View.INVISIBLE);
                    swapStatus = false;


                }


            }
        });

        iv_card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if its clicked on and swap status is true, then swap the card
                if(swapStatus)
                {
                    assignImages(card2, cardSwap);
                    assignImages(singleCard, iv_card2);
                    new Thread(new Thread3("Player1 cardS "+card2)).start();
                    card2 = singleCard;
                    cards.remove(cards.indexOf(singleCard));
                    cardSwap.setVisibility(View.INVISIBLE);
                    swapStatus = false;
                }

            }
        });

        iv_card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if its clicked on and swap status is true, then swap the card
                if(swapStatus)
                {
                    assignImages(card3, cardSwap);
                    assignImages(singleCard, iv_card3);
                    new Thread(new Thread3("Player1 cardS "+card3)).start();
                    card3 = singleCard;
                    cards.remove(cards.indexOf(singleCard));
                    cardSwap.setVisibility(View.INVISIBLE);
                    //card is passed to next person
                    swapStatus = false;
                }

            }
        });

        iv_card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if its clicked on and swap status is true, then swap the card
                if(swapStatus)
                {
                    assignImages(card4, cardSwap);
                    assignImages(singleCard, iv_card4);
                    new Thread(new Thread3("Player1 cardS "+card4)).start();
                    card4 = singleCard;
                    cards.remove(cards.indexOf(singleCard));
                    cardSwap.setVisibility(View.INVISIBLE);
                    //card is passed to next person
                    swapStatus = false;
                }

            }
        });

        swapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //work with single card
                //when swap button is clicked
                swapStatus = true;
                //card is passed to next person

            }
        });

        passButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardSwap.setVisibility(View.INVISIBLE);
                //card is passed to next person
                new Thread(new Thread3("Player1 cardP "+singleCard)).start();
                cards.remove(cards.indexOf(singleCard));
            }
        });

        spoons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkWinningCondition()) {
                    Toast.makeText(DiscoverRoomsActivity.this, "Congratulations! You won!", Toast.LENGTH_LONG).show();
                    new Thread(new DiscoverRoomsActivity.Thread3("Player0 cardW "+singleCard)).start();
                    Intent intentTutorial = new Intent(DiscoverRoomsActivity.this, WinningPageActivity.class);
                    startActivity(intentTutorial);
                } else {
                    Toast.makeText(DiscoverRoomsActivity.this, "Not the winning move!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean checkWinningCondition() {
        // Debug prints
        System.out.println("Card 1: " + card1);
        System.out.println("Card 2: " + card2);
        System.out.println("Card 3: " + card3);
        System.out.println("Card 4: " + card4);
        boolean var = false;
        int value1 = (card1 % 13);
        int value2 = (card2 % 13);
        int value3 = (card3 % 13);
        int value4 = (card4 % 13);
        //  Toast.makeText(DiscoverRoomsActivity.this, value1 + " " +value2 + " " + value3 + " " + value4 , Toast.LENGTH_LONG).show();



        if ((value1 == value2) && (value2 == value3) && (value3 == value4)) {
            var = true;
        } else {
            var = false;
        }
        return var;
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
                serverSocket = new ServerSocket(SERVER_PORT);   //creating a new socket for the host, used for communication with players
                runOnUiThread(() -> {
                    displayMessages.setText("Not connected");    //displaying connectivity status of host and players
                    displayIP.setText("IP: " + SERVER_IP);   //displaying the IP address of the server
                    displayPort.setText("Port: " + SERVER_PORT); //displaying the port to connect to host
                });

                int currPlayerCount = 0;   //variable to keep track of the number of players connected

                while (currPlayerCount < MAX_PLAYERS) {
                    Socket socket = serverSocket.accept();  //creating a socket for each player for communication
                    PrintWriter hostOutput = new PrintWriter(socket.getOutputStream(), true);   //writing data to the socket ouput
                    BufferedReader hostInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));  //keeps track of the input data from the socket
                    playerOutputs.add(hostOutput);  //adds the sent output to the playerOutputs

                    int playerId = ++playerCounter; //variable to create a unique ID for each connected player

                    hostOutput.println("server: You are player " + playerId);   //sending a message to the player to assign their uniqueID

                    currPlayerCount++;
                    finalPlayerCount = currPlayerCount;   //updating the finalPlayerCounts for the host to keep track of total players

                    runOnUiThread(() -> {
                        displayMessages.setText("Connected: " + finalPlayerCount + " players\n");   //updating the display on host screen to show number of connected players
                    });

                    //Sending a message to all players that player limit has reached and host can begin game
                    if (currPlayerCount == MAX_PLAYERS) {
                        broadcastToPlayers("All players connected. Game can start!");
                    }

                    new Thread(new Thread2(hostInput, playerId)).start();   //creating a thread to listen for player activity
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //function to send messages to all connected players
        private void broadcastToPlayers(String message) {
            for (PrintWriter playerOutput : playerOutputs) {
                playerOutput.println(message);
                playerOutput.flush();
            }
        }
    }

    //Thread listens for activity from the players
    private class Thread2 implements Runnable {
        private BufferedReader input;
        private int playerId;

        Thread2(BufferedReader input, int playerId) {
            this.input = input;
            this.playerId = playerId;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    //Checking for game functionality messages that start with player
                    if(message.startsWith("Player")) {
                        int playerNum = Integer.parseInt(message.substring(6,7));   //interpretting the message to find which player the message is intended for
                        String msg = message.substring(8,13);   //interpretting the message to find the intented game functionality like winning move detected
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(myId!=playerNum){
                                    //checking to see if a different player won the game, and redirecting user to the lost game screen
                                    if(msg.equals("cardW")) {
                                        Toast.makeText(DiscoverRoomsActivity.this, "You lost!", Toast.LENGTH_SHORT).show();
                                        Intent intentLoose = new Intent(DiscoverRoomsActivity.this, LoosingPageActivity.class);
                                        startActivity(intentLoose);
                                    }
                                }
                            }
                        });
                    }
                    //Listening for messages from players, and broadcasting the message to all the players connected
                    if (message != null) {
                        broadcastToPlayers("client: " + message+" "); // Broadcast the received message to all clients
                        runOnUiThread(() -> {
                            displayMessages.append("client: " + message + "\n");
                        });
                    } else {    //No message content has been sent
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


    //Thread sends activity to players
    class Thread3 implements Runnable {
        private String message;

        Thread3(String message) {
            this.message = message;  //updating the message to be sent to players, message is passed during thread creation
        }

        @Override
        public void run() {
            broadcastToPlayers("server: " + message + " "); //sending the desired message to all the players
            runOnUiThread(() -> {
                displayMessages.append("You: " + message + " "); //messages are displayed on the connectivity screen
                sendMessage.setText("");  //text box is cleared after message is sent
            });
        }
    }

    // Function to broadcast a message to all connected players
    private void broadcastToPlayers(String message) {
        for (PrintWriter playerOutput : playerOutputs) {
            playerOutput.println(message);
            playerOutput.flush();
        }
    }

    //-----------------------------------------------------------------------------------

    public void assignImages(int card, ImageView image) {
        switch (card) {
            //Diamonds
            case 0:
                image.setImageResource(R.drawable.ace_of_diamonds);
                break;
            case 1:
                image.setImageResource(R.drawable.two_of_diamonds);
                break;
            case 2:
                image.setImageResource(R.drawable.three_of_diamonds);
                break;
            case 3:
                image.setImageResource(R.drawable.four_of_diamonds);
                break;
            case 4:
                image.setImageResource(R.drawable.five_of_diamonds);
                break;
            case 5:
                image.setImageResource(R.drawable.six_of_diamonds);
                break;
            case 6:
                image.setImageResource(R.drawable.seven_of_diamonds);
                break;
            case 7:
                image.setImageResource(R.drawable.eight_of_diamonds);
                break;
            case 8:
                image.setImageResource(R.drawable.nine_of_diamonds);
                break;
            case 9:
                image.setImageResource(R.drawable.ten_of_diamonds);
                break;
            case 10:
                image.setImageResource(R.drawable.jack_of_diamonds2);
                break;
            case 11:
                image.setImageResource(R.drawable.queen_of_diamonds2);
                break;
            case 12:
                image.setImageResource(R.drawable.king_of_diamonds2);
                break;
            //Spades
            case 13:
                image.setImageResource(R.drawable.ace_of_spades2);
                break;
            case 14:
                image.setImageResource(R.drawable.two_of_spades);
                break;
            case 15:
                image.setImageResource(R.drawable.three_of_spades);
                break;
            case 16:
                image.setImageResource(R.drawable.four_of_spades);
                break;
            case 17:
                image.setImageResource(R.drawable.five_of_spades);
                break;
            case 18:
                image.setImageResource(R.drawable.six_of_spades);
                break;
            case 19:
                image.setImageResource(R.drawable.seven_of_spades);
                break;
            case 20:
                image.setImageResource(R.drawable.eight_of_spades);
                break;
            case 21:
                image.setImageResource(R.drawable.nine_of_spades);
                break;
            case 22:
                image.setImageResource(R.drawable.ten_of_spades);
                break;
            case 23:
                image.setImageResource(R.drawable.jack_of_spades2);
                break;
            case 24:
                image.setImageResource(R.drawable.queen_of_spades2);
                break;
            case 25:
                image.setImageResource(R.drawable.king_of_spades2);
                break;
            //Clubs
            case 26:
                image.setImageResource(R.drawable.ace_of_clubs);
                break;
            case 27:
                image.setImageResource(R.drawable.two_of_clubs);
                break;
            case 28:
                image.setImageResource(R.drawable.three_of_clubs);
                break;
            case 29:
                image.setImageResource(R.drawable.four_of_clubs);
                break;
            case 30:
                image.setImageResource(R.drawable.five_of_clubs);
                break;
            case 31:
                image.setImageResource(R.drawable.six_of_clubs);
                break;
            case 32:
                image.setImageResource(R.drawable.seven_of_clubs);
                break;
            case 33:
                image.setImageResource(R.drawable.eight_of_clubs);
                break;
            case 34:
                image.setImageResource(R.drawable.nine_of_clubs);
                break;
            case 35:
                image.setImageResource(R.drawable.ten_of_clubs);
                break;
            case 36:
                image.setImageResource(R.drawable.jack_of_clubs2);
                break;
            case 37:
                image.setImageResource(R.drawable.queen_of_clubs2);
                break;
            case 38:
                image.setImageResource(R.drawable.king_of_clubs2);
                break;
            case 39:
                image.setImageResource(R.drawable.ace_of_hearts);
                break;
            case 40:
                image.setImageResource(R.drawable.two_of_hearts);
                break;
            case 41:
                image.setImageResource(R.drawable.three_of_hearts);
                break;
            case 42:
                image.setImageResource(R.drawable.four_of_hearts);
                break;
            case 43:
                image.setImageResource(R.drawable.five_of_hearts);
                break;
            case 44:
                image.setImageResource(R.drawable.six_of_hearts);
                break;
            case 45:
                image.setImageResource(R.drawable.seven_of_hearts);
                break;
            case 46:
                image.setImageResource(R.drawable.eight_of_hearts);
                break;
            case 47:
                image.setImageResource(R.drawable.nine_of_hearts);
                break;
            case 48:
                image.setImageResource(R.drawable.ten_of_hearts);
                break;
            case 49:
                image.setImageResource(R.drawable.jack_of_hearts2);
                break;
            case 50:
                image.setImageResource(R.drawable.queen_of_hearts2);
                break;
            case 51:
                image.setImageResource(R.drawable.king_of_hearts2);
                break;
        }
    }


}