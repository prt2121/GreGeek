/*
 * Copyright (c) 2015 Prat Tanapaisankit
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package com.prat.gregeek.fragment;

import com.prat.gregeek.R;
import com.prat.gregeek.activity.MainActivity;
import com.prat.gregeek.db.DbAdapter;
import com.prat.gregeek.view.FlashcardView;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Word of the day fragment
 * Activities that contain this fragment must implement the
 * {@link DailyWordFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DailyWordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyWordFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private OnFragmentInteractionListener mListener;

    private DbAdapter mDbAdapter;

    private Subscription mSubscription;

    public DailyWordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment WordOfTheDayFragment.
     */
    public static DailyWordFragment newInstance(int sectionNumber) {
        DailyWordFragment fragment = new DailyWordFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_word_of_the_day, container, false);
        getRandomWord(getActivity(),
                (TextView) view.findViewById(R.id.word),
                (FlashcardView) view.findViewById(R.id.dailyWordDefinition),
                (FlashcardView) view.findViewById(R.id.dailyWordExample),
                (FlashcardView) view.findViewById(R.id.dailyWordSynonym));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDbAdapter.isOpen()) {
            mDbAdapter.close();
        }
        mSubscription.unsubscribe();
    }

    private void getRandomWord(Context context, TextView word,
            FlashcardView definition, FlashcardView example, FlashcardView synonym) {
        mDbAdapter = new DbAdapter(context);
        mDbAdapter.open();
        // get random index
        Random random = new Random();
        int i = random.nextInt(mDbAdapter.count()) + 1;
        mSubscription = mDbAdapter.getWords()
                .take(i)
                .last()
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(w -> {
                    word.setText(w.getWord());
                    definition.setAnswerText(w.getDefinition());
                    example.setAnswerText(w.getExample());
                    synonym.setAnswerText(w.getSynonyms());
                    mDbAdapter.close();
                });
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {

        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }


}
