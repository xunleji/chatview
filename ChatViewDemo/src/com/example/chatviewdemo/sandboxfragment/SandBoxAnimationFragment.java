package com.example.chatviewdemo.sandboxfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.chatviewdemo.R;
import com.example.chatviewdemo.SandboxFragment;

public class SandBoxAnimationFragment extends BaseSandBoxFragment {

	private View mLayout;
	private static int mOrderId;
	private static int mEnterId;
	private static int mAlphaId;
	private final static int[] mMiddleOrder = { 3, 2, 4, 1, 5, 0, 6 };
	private final static int[] mLastOrder = { 6, 5, 4, 3, 2, 1, 0 };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mOrderId = R.id.sandbox_anim_ordere;
		mEnterId = R.id.sandbox_anim_enterb;
		mAlphaId = -1;
		mLayout = inflater
				.inflate(R.layout.sandbox_animation, container, false);
		Spinner spinner = (Spinner) mLayout
				.findViewById(R.id.sandbox_anim_easing_type);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this.getActivity(), R.array.easing, R.layout.spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);

		/** Easing function **/
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parentView,
					View selectedItemView, int position, long id) {
				SandboxFragment.mEasingId = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
			}

		});

		Spinner spinnerDuration = (Spinner) mLayout
				.findViewById(R.id.sandbox_anim_duration);
		ArrayAdapter<CharSequence> adapterDuration = ArrayAdapter
				.createFromResource(this.getActivity(), R.array.duration,
						R.layout.spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerDuration.setAdapter(adapterDuration);

		/** Duration function **/
		spinnerDuration
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					final String[] durations = getResources().getStringArray(
							R.array.duration);

					@Override
					public void onItemSelected(AdapterView<?> parentView,
							View selectedItemView, int position, long id) {
						SandboxFragment.mDuration = Integer.parseInt(durations[position]
								.replace("ms", ""));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parentView) {
					}

				});

		return mLayout;
	}

	@Override
	public void onStateRecover() {

		mLayout.findViewById(mOrderId).setSelected(true);
		mLayout.findViewById(mEnterId).setSelected(true);
		if (mAlphaId != -1)
			mLayout.findViewById(mAlphaId).setSelected(true);
	}

	public void onStateChange(int id) {

		switch (id) {

		/** Alpha value **/
		case R.id.sandbox_anim_alpha1:
			if (id == mAlphaId)
				SandboxFragment.mAlpha = -1;
			else
				SandboxFragment.mAlpha = 1;
			mAlphaId = swapState(mLayout, mAlphaId, id, false);
			break;
		case R.id.sandbox_anim_alpha2:
			if (id == mAlphaId)
				SandboxFragment.mAlpha = -1;
			else
				SandboxFragment.mAlpha = 2;
			mAlphaId = swapState(mLayout, mAlphaId, id, false);
			break;
		case R.id.sandbox_anim_alpha3:
			if (id == mAlphaId)
				SandboxFragment.mAlpha = -1;
			else
				SandboxFragment.mAlpha = 3;
			mAlphaId = swapState(mLayout, mAlphaId, id, false);
			break;

		/** Animation order **/
		case R.id.sandbox_anim_ordere:
			SandboxFragment.mOverlapFactor = 1;
			SandboxFragment.mOverlapOrder = SandboxFragment.mEqualOrder;
			mOrderId = swapState(mLayout, mOrderId, id, true);
			break;
		case R.id.sandbox_anim_orderf:
			SandboxFragment.mOverlapFactor = .5f;
			SandboxFragment.mOverlapOrder = SandboxFragment.mEqualOrder;
			mOrderId = swapState(mLayout, mOrderId, id, true);
			break;
		case R.id.sandbox_anim_orderl:
			SandboxFragment.mOverlapFactor = .5f;
			SandboxFragment.mOverlapOrder = mLastOrder;
			mOrderId = swapState(mLayout, mOrderId, id, true);
			break;
		case R.id.sandbox_anim_orderm:
			SandboxFragment.mOverlapFactor = .5f;
			SandboxFragment.mOverlapOrder = mMiddleOrder;
			mOrderId = swapState(mLayout, mOrderId, id, true);
			break;

		/** Enter point **/
		case R.id.sandbox_anim_entertl:
			SandboxFragment.mStartX = 0f;
			SandboxFragment.mStartY = 1f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_entert:
			SandboxFragment.mStartX = -1f;
			SandboxFragment.mStartY = 1f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_entertr:
			SandboxFragment.mStartX = 1f;
			SandboxFragment.mStartY = 1f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_entercl:
			SandboxFragment.mStartX = 0f;
			SandboxFragment.mStartY = -1f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_enterc:
			SandboxFragment.mStartX = .5f;
			SandboxFragment.mStartY = .5f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_entercr:
			SandboxFragment.mStartX = 1f;
			SandboxFragment.mStartY = -1f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_enterbl:
			SandboxFragment.mStartX = 0f;
			SandboxFragment.mStartY = 0f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_enterb:
			SandboxFragment.mStartX = -1f;
			SandboxFragment.mStartY = 0f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;
		case R.id.sandbox_anim_enterbr:
			SandboxFragment.mStartX = 1f;
			SandboxFragment.mStartY = 0f;
			mEnterId = swapState(mLayout, mEnterId, id, true);
			break;

		default:
		}
	}
}
