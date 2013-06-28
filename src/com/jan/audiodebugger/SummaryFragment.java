package com.jan.audiodebugger;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class SummaryFragment extends Fragment {

	AudioManager am = null;

	public SummaryFragment() {
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
		View rootView = inflater.inflate(R.layout.summary_fragment, container,
				false);

		TextView ringerMode = (TextView) rootView
				.findViewById(R.id.summaryRingerMode);
		ringerMode.setText(getRingerMode());

		TextView summaryDevice = (TextView) rootView
				.findViewById(R.id.summaryDevice);
		summaryDevice.setText(getDeviceSummary());

		Button btnSetSpeakerPhone = (Button) rootView
				.findViewById(R.id.btnSetSpeakerPhone);
		btnSetSpeakerPhone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				am.setSpeakerphoneOn(true);
			}
		});

		setupStreamButton(rootView, R.id.btnStreamAlarm, AudioManager.STREAM_ALARM);
		setupStreamButton(rootView, R.id.btnStreamMusic, AudioManager.STREAM_MUSIC);
		setupStreamButton(rootView, R.id.btnStreamRing, AudioManager.STREAM_RING);
		setupStreamButton(rootView, R.id.btnStreamSystem, AudioManager.STREAM_SYSTEM);
		setupStreamButton(rootView, R.id.btnStreamVoiceCall, AudioManager.STREAM_VOICE_CALL);
		setupStreamButton(rootView, R.id.btnStreamNotification, AudioManager.STREAM_NOTIFICATION);
		setupStreamButton(rootView, R.id.btnStreamDTMF, AudioManager.STREAM_DTMF);
		
		return rootView;
	}

	private void setupStreamButton(View rootView, int id, final int streamType) {
		ToggleButton btn = (ToggleButton) rootView.findViewById(id);
		btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked) {
					am.setStreamMute(streamType, false);
				} else {
					am.setStreamMute(streamType, true);
				}
			}
		});
		btn.setChecked(true);
	}

	private String getDeviceSummary() {
		String device = "";

		device += "Bluetooth A2dp: " + am.isBluetoothA2dpOn() + "\n";
		device += "Bluetooth Sco: " + am.isBluetoothScoOn() + "\n";
		device += "Bluetooth Sco Available Off Call: "
				+ am.isBluetoothScoAvailableOffCall() + "\n";
		device += "Microphone Mute: " + am.isMicrophoneMute() + "\n";
		device += "Music Active: " + am.isMusicActive() + "\n";
		device += "Speaker Phone: " + am.isSpeakerphoneOn() + "\n";
		device += "Wired Headset: " + am.isWiredHeadsetOn() + "\n";

		return device;
	}

	private String getRingerMode() {
		int result = am.getRingerMode();

		String mode = null;
		if (result == AudioManager.RINGER_MODE_NORMAL) {
			mode = "Normal";
		} else if (result == AudioManager.RINGER_MODE_SILENT) {
			mode = "Silent";
		} else if (result == AudioManager.RINGER_MODE_VIBRATE) {
			mode = "Vibrate";
		} else {
			mode = "Unknown";
		}

		return "Ringer Mode: " + mode;
	}
}
