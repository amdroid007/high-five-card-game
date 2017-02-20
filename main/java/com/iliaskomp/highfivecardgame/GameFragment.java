package com.iliaskomp.highfivecardgame;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iliaskomp.highfivecardgame.models.Deck;

/**
 * Created by IliasKomp on 20/02/17.
 */

public class GameFragment extends Fragment{
    private static final String TAG = "GameFragment";

    private TextView mTextView;
    private ImageView mCardImageView;
    private Deck mDeck;

    private boolean mGameOver = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        mCardImageView = (ImageView) view.findViewById(R.id.image_view_cards);
        mTextView = (TextView) view.findViewById(R.id.text_view_message);

        waitToStart();
        checkForGameOver();

        return view;
    }

    private void startGame() {
        Log.d(TAG, "Game starts");

        mDeck = new Deck();
        mDeck.drawCard();
        mCardImageView.setImageResource(getResourceIdForCurrentCard());

        mCardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDeck.numberOfCardsLeft() == 0) {
                    mGameOver = true;
                }

                while (!mGameOver) {
                    mDeck.drawCard();
                    mCardImageView.setImageResource(getResourceIdForCurrentCard());
                }
            }
        });

    }

    private int getResourceIdForCurrentCard() {
        return this.getResources().getIdentifier(
                mDeck.getCurrentCard().getDrawableString(),
                "drawable",
                getActivity().getPackageName());
    }

    private void waitToStart() {
        Log.d(TAG, "Game waits to start...");

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextView.setVisibility(View.GONE);
                mCardImageView.setVisibility(View.VISIBLE);
                startGame();
            }
        });
    }

    private void checkForGameOver() {
        // TODO use handler??
    }

    public static GameFragment newInstance() {
        return new GameFragment();
    }
}
