package com.iliaskomp.highfivecardgame;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
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
// TODO check gameover value when onResume?
public class GameFragment extends Fragment{
    private static final String TAG = "GameFragment";

    private TextView mTextViewMessage;
    private ImageView mCardImageView;
    private TextView mTextViewTimer;

    private Deck mDeck;
    private boolean mGameOver = false;
    private boolean mTurnOver = false;

    private static int mTimerSeconds = 3100;
    private CountDownTimer mCountDownTimer;

    private SoundPool mSoundPool;
    private static boolean mSoundLoadComplete = false;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initSoundPool();
        createCountDownTimer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game, container, false);

        mCardImageView = (ImageView) view.findViewById(R.id.image_view_cards);
        mTextViewMessage = (TextView) view.findViewById(R.id.text_view_message);
        mTextViewTimer = (TextView) view.findViewById(R.id.text_view_timer);

        waitToStart();

        return view;
    }

    private void startGame() {
        Log.d(TAG, "Game starts");

        mGameOver = false;
        mDeck = new Deck();
        drawCardInFragment();

        mCardImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mDeck.numberOfCardsLeft() == 1) {
                    gameOver();
                }

                if (!mGameOver && !mTurnOver) {
                    mCountDownTimer.cancel();
                    drawCardInFragment();
                }
            }
        });

        mTextViewTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTurnOver) {
                    mTurnOver = false;
                    drawCardInFragment();
                }
            }
        });

    }

    private void drawCardInFragment() {
        mDeck.drawCard();
        mCardImageView.setImageResource(getResourceIdForCurrentCard());
        mCountDownTimer.start();
    }

    private int getResourceIdForCurrentCard() {
        return this.getResources().getIdentifier(
                mDeck.getCurrentCard().getDrawableString(),
                "drawable",
                getActivity().getPackageName());
    }

    private void waitToStart() {
        Log.d(TAG, "Game waits to start...");

        mTextViewMessage.setVisibility(View.VISIBLE);
        mTextViewMessage.setText("Tap to start the game!");
        mCardImageView.setVisibility(View.GONE);

        mTextViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewMessage.setVisibility(View.GONE);
                mCardImageView.setVisibility(View.VISIBLE);
                startGame();
            }
        });
    }

    private void gameOver() {
        mGameOver = true;

        mTextViewMessage.setVisibility(View.VISIBLE);
        mTextViewMessage.setText("Deck is over. \nTap to start a new game!");
        mCardImageView.setVisibility(View.GONE);
    }

    private void createCountDownTimer() {
        final int tickSoundId = mSoundPool.load(getActivity(), R.raw.tick, 1);
        final int alarmSoundId = mSoundPool.load(getActivity(), R.raw.alarm, 1);

        mCountDownTimer = new CountDownTimer(mTimerSeconds, 1000) {

            public void onTick(long millisUntilFinished) {
                mTextViewTimer.setText("seconds: " + millisUntilFinished / 999);
                if (mSoundLoadComplete) mSoundPool.play(tickSoundId, 1, 1, 0, 0, 1);
            }

            public void onFinish() {
                mTextViewTimer.setText("Lost! (Tap here to continue)");
                mTurnOver = true;
                if (mSoundLoadComplete) mSoundPool.play(alarmSoundId, 1, 1, 0, 0, 1);
            }
        };
    }

    private void initSoundPool() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            mSoundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttrib)
                    .setMaxStreams(3)
                    .build();
        }
        else {
            mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
        }

        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int i, int i1) {
                mSoundLoadComplete = true;
            }
        });

    }

    public static GameFragment newInstance() {
        return new GameFragment();
    }
}
