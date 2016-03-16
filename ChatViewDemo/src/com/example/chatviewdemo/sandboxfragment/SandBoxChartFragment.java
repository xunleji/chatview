package com.example.chatviewdemo.sandboxfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chatviewdemo.R;
import com.example.chatviewdemo.SandboxFragment;

public class SandBoxChartFragment extends BaseSandBoxFragment {

	private View mLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.sandbox_chart, container, false);
		SandboxFragment.mChartId = R.id.sandbox_chart_line;
		mLayout.findViewById(SandboxFragment.mChartId).setSelected(true);
		return mLayout;
	}

	@Override
	public void onStateRecover() {
		mLayout.findViewById(SandboxFragment.mChartId).setSelected(true);
	}

	@Override
	public void onStateChange(int id) {
		SandboxFragment.mChartId = swapState(mLayout, SandboxFragment.mChartId,
				id, true);
	}
}
