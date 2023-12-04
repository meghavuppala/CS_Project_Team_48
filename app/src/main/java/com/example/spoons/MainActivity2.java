package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import java.net.Socket;
import java.util.ArrayList;

@SuppressLint("SetTextI18n")
public class MainActivity2 extends AppCompatActivity {
    Thread Thread1 = null;
    EditText etIP, etPort;
    TextView tvMessages;
    EditText etMessage;
    Button btnSend;
    String SERVER_IP;
    int SERVER_PORT;
    Button btnConnect;

    private PrintWriter output;
    private BufferedReader input;
    private Handler handler;

    private int playerId;

    //---------------------------------------------------------------------------------

    ImageView iv_card1, iv_card2, iv_card3, iv_card4, cardSwap;
    Button swapButton;
    Button passButton;
    Button spoons;

    ArrayList<Integer> cards;

    int singleCard;

    boolean swapStatus = false;

    int card1 = 0;
    int card2 = 0;
    int card3 = 0;
    int card4 = 0;

    TextView playerDisplay;

    int nextPlayer = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);
        btnConnect = findViewById(R.id.btnConnect);

        handler = new Handler(Looper.getMainLooper());

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMessages.setText("");
                SERVER_IP = etIP.getText().toString().trim();
                SERVER_PORT = Integer.parseInt(etPort.getText().toString().trim());
                Thread1 = new Thread(new Thread1());
                Thread1.start();

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = etMessage.getText().toString().trim();
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
            }
        });

        //---------------------------------------------------------------------------------------------


        iv_card1 = (ImageView) findViewById(R.id.iv_card1);
        iv_card2 = (ImageView) findViewById(R.id.iv_card2);
        iv_card3 = (ImageView) findViewById(R.id.iv_card3);
        iv_card4 = (ImageView) findViewById(R.id.iv_card4);
        cardSwap = (ImageView) findViewById(R.id.cardSwap);
        swapButton = (Button) findViewById(R.id.swapButton);
        passButton = (Button) findViewById(R.id.passButton);
        spoons = (Button) findViewById(R.id.button5);

        playerDisplay = (TextView) findViewById(R.id.textView2);

        swapButton.setVisibility(View.INVISIBLE);
        passButton.setVisibility(View.INVISIBLE);
        spoons.setVisibility(View.INVISIBLE);
        playerDisplay.setVisibility(View.INVISIBLE);


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

        iv_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if its clicked on and swap status is true, then swap the card
                if(swapStatus)
                {
                    assignImages(card1, cardSwap);
                    assignImages(singleCard, iv_card1);
                    new Thread(new MainActivity2.Thread3("Player"+nextPlayer+" cardS " + card1)).start();
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
                    new Thread(new MainActivity2.Thread3("Player"+nextPlayer+" cardS " + card2)).start();
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
                    new Thread(new MainActivity2.Thread3("Player"+nextPlayer+" cardS " + card3)).start();
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
                    new Thread(new MainActivity2.Thread3("Player"+nextPlayer+" cardS " + card4)).start();
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
                new Thread(new MainActivity2.Thread3("Player"+nextPlayer+" cardP " + singleCard)).start();
                cards.remove(cards.indexOf(singleCard));
            }
        });

        spoons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkWinningCondition()) {
                    Toast.makeText(MainActivity2.this, "Congratulations! You won!", Toast.LENGTH_LONG).show();
                    new Thread(new MainActivity2.Thread3("Player"+playerId+" cardW " + singleCard)).start();
                    Intent intentWon = new Intent(MainActivity2.this, WinningPageActivity.class);
                    startActivity(intentWon);
                } else {
                    Toast.makeText(MainActivity2.this, "Not the winning move!", Toast.LENGTH_SHORT).show();

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
        // Toast.makeText(MainActivity2.this, value1 + " " +value2 + " " + value3 + " " + value4 , Toast.LENGTH_LONG).show();



        if ((value1 == value2) && (value2 == value3) && (value3 == value4)) {
            var = true;
        } else {
            var = false;
        }
        return var;
    }

    class Thread1 implements Runnable {
        public void run() {
            Socket socket;
            try {
                socket = new Socket(SERVER_IP, SERVER_PORT);    //creating a new socket for the player, used for communication with host
                output = new PrintWriter(socket.getOutputStream(), true);   //writing data to the socket ouput
                input = new BufferedReader(new InputStreamReader(socket.getInputStream())); //keeps track of the input data from the socket
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tvMessages.setText("Connected\n");  //updating the display to show that the player has been connected to host
                    }
                });
                new Thread(new Thread2()).start();  //creating a thread to listen for activity from host
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Thread listens to activity from host
    class Thread2 implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    final String message = input.readLine();
                    //Listening to see if the host starts the game
                    if(message.equals("server: Host started the game ")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Once the host starts the game, the game functionality buttons are visible for player
                                swapButton.setVisibility(View.VISIBLE);
                                passButton.setVisibility(View.VISIBLE);
                                spoons.setVisibility(View.VISIBLE);
                                playerDisplay.setVisibility(View.VISIBLE);
                                //Once the host starts the game, the connectivity functionality disappears from the screen
                                etIP.setVisibility(View.INVISIBLE);
                                etPort.setVisibility(View.INVISIBLE);
                                tvMessages.setVisibility(View.INVISIBLE);
                                etMessage.setVisibility(View.INVISIBLE);
                                btnSend.setVisibility(View.INVISIBLE);
                                btnConnect.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                    //Listening to see if the host started dealing the four random cards
                    if(message.equals("server: Game Has Started ")){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Making the four cards on screen visible since the host dealt the cards
                                iv_card1.setVisibility(View.VISIBLE);
                                iv_card2.setVisibility(View.VISIBLE);
                                iv_card3.setVisibility(View.VISIBLE);
                                iv_card4.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                    //Listening for game functionality messages that are formatted to start with the word Player
                    if(message.startsWith("server: Player") || message.startsWith("client: Player")) {
                        int playerNum = Integer.parseInt(message.substring(14,15)); //interpretting the message to find which player the message is intended for
                        int cardVal = Integer.parseInt(message.substring(22, message.length() - 1)); //interpretting the message to find the card value sent
                        String msg = message.substring(16, 21); //interpretting the message to find the intented game functionality like card assignment, card pass, etc
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(playerId==playerNum) {
                                    if (msg.equals("card1")) {
                                        //Assigns the first random card to the player received from the host
                                        card1 = cardVal;
                                        assignImages(card1, iv_card1);
                                    } else if (msg.equals("card2")) {
                                        //Assigns the second random card to the player received from the host
                                        card2 = cardVal;
                                        assignImages(card2, iv_card2);
                                    } else if (msg.equals("card3")) {
                                        //Assigns the third random card to the player received from the host
                                        card3 = cardVal;
                                        assignImages(card3, iv_card3);
                                    } else if (msg.equals("card4")) {
                                        //Assigns the fourth random card to the player received from the host
                                        card4 = cardVal;
                                        assignImages(card4, iv_card4);
                                    } else if (msg.equals("cardS") || msg.equals("cardP")) {
                                        //Assigns the main card to the player received from the host to pass or swap
                                         singleCard = cardVal;
                                        cardSwap.setVisibility(View.VISIBLE);
                                        assignImages(singleCard, cardSwap);
                                    }
                                }else if(playerId!=playerNum){
                                    //checking to see if a different player won the game, and redirecting user to the lost game screen
                                    if(msg.equals("cardW")) {
                                        Toast.makeText(MainActivity2.this, "You lost!", Toast.LENGTH_SHORT).show();
                                        Intent intentLoose = new Intent(MainActivity2.this, LoosingPageActivity.class);
                                        startActivity(intentLoose);
                                    }
                                }
                            }
                        });
                    }
                    //Interpretting the message from host to get the unique playerId
                    if (message != null && message.startsWith("server: You are player ")) {
                        playerId = Integer.parseInt(message.substring("server: You are player ".length())); //Interpretting the message to find the playerId sent
                        nextPlayer = playerId+1;    //calculating the value of the next player to send swapped or passed cards to
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Updating the unique player number on screen
                                playerDisplay.setText("Player Number "+playerId);
                            }
                        });
                    }
                    //Adding the received messages to the messages log
                    if (message != null) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvMessages.append(message + " ");
                            }
                        });
                    } else { //No message content has been sent
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

    //Thread sends activity to host
    class Thread3 implements Runnable {
        private String message;
        Thread3(String message) {
            this.message = message; //updating the message to be sent to host, message is passed during thread creation
        }
        @Override
        public void run() {
            output.println(message);    //sending the desired message to the host
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvMessages.append("client: " + message+" ");    //messages are displayed on the connectivity screen
                    etMessage.setText("");  //text box is cleared after message is sent
                }
            });
        }
    }

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