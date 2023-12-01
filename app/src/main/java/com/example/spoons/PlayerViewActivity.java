package com.example.spoons;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerViewActivity extends AppCompatActivity {

    ImageView iv_card1, iv_card2, iv_card3, iv_card4, cardSwap;
    Button swapButton;
    Button passButton;

    ArrayList<Integer> cards;

    int singleCard;

    boolean swapStatus = false;

    int card1 = 0;
    int card2 = 0;
    int card3 = 0;
    int card4 = 0;

    int playerId;

    TextView playerNumDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_view);

        Intent intent = getIntent();
        playerId = intent.getIntExtra("playerId",0);

        playerNumDisplay = (TextView) findViewById(R.id.textView2);
        playerNumDisplay.setText("Player Number: "+playerId);

        iv_card1 = (ImageView) findViewById(R.id.iv_card1);
        iv_card2 = (ImageView) findViewById(R.id.iv_card2);
        iv_card3 = (ImageView) findViewById(R.id.iv_card3);
        iv_card4 = (ImageView) findViewById(R.id.iv_card4);
        cardSwap = (ImageView) findViewById(R.id.cardSwap);
        swapButton = (Button) findViewById(R.id.swapButton);
        passButton = (Button) findViewById(R.id.passButton);
        //cardFinal = (ImageView) findViewById(R.id.cardFinal);

        iv_card1.setVisibility(View.INVISIBLE);
        iv_card2.setVisibility(View.INVISIBLE);
        iv_card3.setVisibility(View.INVISIBLE);
        iv_card4.setVisibility(View.INVISIBLE);
        cardSwap.setVisibility(View.INVISIBLE);
        //cardFinal.setVisibility(View.INVISIBLE);

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
                    //cardSwap.setVisibility(View.INVISIBLE);
                    //cardSwap.setVisibility(View.VISIBLE);
                    assignImages(card1, cardSwap);
                    assignImages(singleCard, iv_card1);
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
            }
        });
    }

    public void assignImages(int card, ImageView image) {
        switch (card) {
            //Diamonds
            case 1:
                image.setImageResource(R.drawable.ace_of_diamonds);
                break;
            case 2:
                image.setImageResource(R.drawable.two_of_diamonds);
                break;
            case 3:
                image.setImageResource(R.drawable.three_of_diamonds);
                break;
            case 4:
                image.setImageResource(R.drawable.four_of_diamonds);
                break;
            case 5:
                image.setImageResource(R.drawable.five_of_diamonds);
                break;
            case 6:
                image.setImageResource(R.drawable.six_of_diamonds);
                break;
            case 7:
                image.setImageResource(R.drawable.seven_of_diamonds);
                break;
            case 8:
                image.setImageResource(R.drawable.eight_of_diamonds);
                break;
            case 9:
                image.setImageResource(R.drawable.nine_of_diamonds);
                break;
            case 10:
                image.setImageResource(R.drawable.ten_of_diamonds);
                break;
            case 11:
                image.setImageResource(R.drawable.jack_of_diamonds2);
                break;
            case 12:
                image.setImageResource(R.drawable.queen_of_diamonds2);
                break;
            case 13:
                image.setImageResource(R.drawable.king_of_diamonds2);
                break;
            //Spades
            case 14:
                image.setImageResource(R.drawable.ace_of_spades2);
                break;
            case 15:
                image.setImageResource(R.drawable.two_of_spades);
                break;
            case 16:
                image.setImageResource(R.drawable.three_of_spades);
                break;
            case 17:
                image.setImageResource(R.drawable.four_of_spades);
                break;
            case 18:
                image.setImageResource(R.drawable.five_of_spades);
                break;
            case 19:
                image.setImageResource(R.drawable.six_of_spades);
                break;
            case 20:
                image.setImageResource(R.drawable.seven_of_spades);
                break;
            case 21:
                image.setImageResource(R.drawable.eight_of_spades);
                break;
            case 22:
                image.setImageResource(R.drawable.nine_of_spades);
                break;
            case 23:
                image.setImageResource(R.drawable.ten_of_spades);
                break;
            case 24:
                image.setImageResource(R.drawable.jack_of_spades2);
                break;
            case 25:
                image.setImageResource(R.drawable.queen_of_spades2);
                break;
            case 26:
                image.setImageResource(R.drawable.king_of_spades2);
                break;
            //Clubs
            case 27:
                image.setImageResource(R.drawable.ace_of_clubs);
                break;
            case 28:
                image.setImageResource(R.drawable.two_of_clubs);
                break;
            case 29:
                image.setImageResource(R.drawable.three_of_clubs);
                break;
            case 30:
                image.setImageResource(R.drawable.four_of_clubs);
                break;
            case 31:
                image.setImageResource(R.drawable.five_of_clubs);
                break;
            case 32:
                image.setImageResource(R.drawable.six_of_clubs);
                break;
            case 33:
                image.setImageResource(R.drawable.seven_of_clubs);
                break;
            case 34:
                image.setImageResource(R.drawable.eight_of_clubs);
                break;
            case 35:
                image.setImageResource(R.drawable.nine_of_clubs);
                break;
            case 36:
                image.setImageResource(R.drawable.ten_of_clubs);
                break;
            case 37:
                image.setImageResource(R.drawable.jack_of_clubs2);
                break;
            case 38:
                image.setImageResource(R.drawable.queen_of_clubs2);
                break;
            case 39:
                image.setImageResource(R.drawable.king_of_clubs2);
                break;
            case 40:
                image.setImageResource(R.drawable.ace_of_hearts);
                break;
            case 41:
                image.setImageResource(R.drawable.two_of_hearts);
                break;
            case 42:
                image.setImageResource(R.drawable.three_of_hearts);
                break;
            case 43:
                image.setImageResource(R.drawable.four_of_hearts);
                break;
            case 44:
                image.setImageResource(R.drawable.five_of_hearts);
                break;
            case 45:
                image.setImageResource(R.drawable.six_of_hearts);
                break;
            case 46:
                image.setImageResource(R.drawable.seven_of_hearts);
                break;
            case 47:
                image.setImageResource(R.drawable.eight_of_hearts);
                break;
            case 48:
                image.setImageResource(R.drawable.nine_of_hearts);
                break;
            case 49:
                image.setImageResource(R.drawable.ten_of_hearts);
                break;
            case 50:
                image.setImageResource(R.drawable.jack_of_hearts2);
                break;
            case 51:
                image.setImageResource(R.drawable.queen_of_hearts2);
                break;
            case 52:
                image.setImageResource(R.drawable.king_of_hearts2);
                break;
        }
    }
}