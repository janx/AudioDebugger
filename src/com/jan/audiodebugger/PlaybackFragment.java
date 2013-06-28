package com.jan.audiodebugger;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class PlaybackFragment extends Fragment {
	AudioManager am = null;

	public PlaybackFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		am = (AudioManager) getActivity().getSystemService(
				Context.AUDIO_SERVICE);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.playback_fragment, container,
				false);

		Button testPlayback = (Button) rootView
				.findViewById(R.id.btnTestPlayback);
		testPlayback.setSoundEffectsEnabled(false);
		testPlayback.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(),
						R.raw.sonar);
				mediaPlayer.start();
			}
		});

		return rootView;
	}

}
