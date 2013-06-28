package com.jan.audiodebugger;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class SystemTestFragment extends Fragment {

	AudioManager am = null;
	OnAudioFocusChangeListener afChangeListener = null;

	public SystemTestFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		am = (AudioManager) getActivity().getSystemService(
				Context.AUDIO_SERVICE);

		afChangeListener = new OnAudioFocusChangeListener() {
			public void onAudioFocusChange(int focusChange) {
				if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
					// Pause playback
				} else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
					// Resume playback
				} else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
					// am.unregisterMediaButtonEventReceiver(RemoteControlReceiver);
					// am.abandonAudioFocus(afChangeListener);
					// Stop playback
				}
			}
		};
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.system_test_fragment,
				container, false);

		setupBtnRequestFocus(rootView);
		setupBtnAbandonFocus(rootView);

		return rootView;
	}

	private void setupBtnRequestFocus(View rootView) {
		Button btn = (Button) rootView.findViewById(R.id.btnRequestFocus);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int result = am
						.requestAudioFocus(afChangeListener,
								AudioManager.STREAM_MUSIC,
								AudioManager.AUDIOFOCUS_GAIN);

				String text = (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) ? "Granted!"
						: "Failed!";
				Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
			}
		});

	}

	private void setupBtnAbandonFocus(View rootView) {
		Button btn = (Button) rootView.findViewById(R.id.btnAbandonFocus);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int result = am.abandonAudioFocus(afChangeListener);

				String text = (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) ? "Granted!"
						: "Failed!";
				Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
			}
		});

	}

}
