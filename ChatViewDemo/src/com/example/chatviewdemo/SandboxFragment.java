package com.example.chatviewdemo;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.db.chart.view.AxisController;
import com.db.chart.view.ChartView;
import com.example.chatviewdemo.sandboxfragment.BaseSandBoxFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxAnimationFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxAxisFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxBarFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxChartFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxGridFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxLineFragment;
import com.example.chatviewdemo.sandboxfragment.SandBoxPlayFragment;

public class SandboxFragment extends Fragment {

	public static SandboxPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;
	public static BaseSandBoxFragment mCurrFragment;
	private SandBoxPlayFragment sandBoxPlayFragment;

	public static int mChartId;
	public static boolean mIsLineDashed;
	public static float[] mLineDashType;
	public static boolean mIsLineSmooth;
	public static float mLineThickness;
	public static float mPointsSize;
	public static int mPointsSizeId;
	public static int mPointColorId;
	public static int mLineColorId;

	public static int mGridColorId;
	public static boolean mIsGridDashed;
	public static float[] mGridDashType;
	public static float mGridThickness;

	/** Axis */
	public static boolean mHasYAxis;
	public static boolean mHasXAxis;
	public static AxisController.LabelPosition mXLabelPosition,
			mYLabelPosition;
	public static int mAxisColorId;
	public static int mLabelColorId;
	public static String mLabelFormat;

	public static ChartView.GridType mGridType;

	public static int mEasingId;
	public static int mDuration;
	public static int mAlpha;
	public static float mOverlapFactor;
	public static int[] mOverlapOrder;
	public final static int[] mEqualOrder = { 0, 1, 2, 3, 4, 5, 6 };
	public static float mStartX;
	public static float mStartY;

	public static int mBarColorId;
	public static int mBarSpacingId;
	public static int mBarBackgroundId;
	public static int mBarBackgroundColorId;
	public static float mBarCornersSize;
	public static float mBarSpacing;
	public static boolean mHasBarBackground;

	public final static int DEFAULT_COLOR = -9276814;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View mLayout = inflater.inflate(R.layout.sandbox, container, false);
		// Pager
		mSectionsPagerAdapter = new SandboxPagerAdapter(
				this.getChildFragmentManager());

		mViewPager = (ViewPager) mLayout.findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		TabLayout tabLayout = (TabLayout) mLayout.findViewById(R.id.tab_layout);
		tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		tabLayout.setupWithViewPager(mViewPager);

		ActionBar actionBar = ((AppCompatActivity) getActivity())
				.getSupportActionBar();
		if (actionBar != null)
			actionBar.setElevation(0);// actionbar的下阴影线消除

		// Defaults
		mIsLineDashed = false;
		mLineDashType = null;
		mLineThickness = 3;
		mPointsSize = 0;
		mLineColorId = DEFAULT_COLOR;
		mPointsSizeId = -1;
		mPointColorId = DEFAULT_COLOR;

		mGridColorId = DEFAULT_COLOR;
		mIsGridDashed = false;
		mGridDashType = null;
		mGridThickness = 1f;

		mHasYAxis = true;
		mHasXAxis = true;
		mYLabelPosition = mXLabelPosition = AxisController.LabelPosition.OUTSIDE;
		mAxisColorId = DEFAULT_COLOR;
		mLabelColorId = DEFAULT_COLOR;
		mLabelFormat = "";

		mGridType = null;

		mEasingId = 0;
		mAlpha = -1;
		mDuration = 1000;
		mOverlapFactor = 1;
		mOverlapOrder = mEqualOrder;
		mStartX = -1;
		mStartY = 0;

		mBarSpacingId = R.id.sandbox_bar_spacing1;
		mBarColorId = DEFAULT_COLOR;
		mBarBackgroundId = -1;
		mBarBackgroundColorId = DEFAULT_COLOR;
		mBarCornersSize = 0;
		mBarSpacing = 10;
		mHasBarBackground = false;

		return mLayout;
	}

	private class SandboxPagerAdapter extends FragmentStatePagerAdapter {

		public SandboxPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {

			switch (position) {
			case 0:
				return new SandBoxChartFragment();
			case 1:
				return new SandBoxAxisFragment();
			case 2:
				return new SandBoxGridFragment();
			case 3:
				if (SandboxFragment.mChartId == R.id.sandbox_chart_line)
					return new SandBoxLineFragment();
				else
					return new SandBoxBarFragment();
			case 4:
				return new SandBoxAnimationFragment();
			case 5:
				sandBoxPlayFragment = new SandBoxPlayFragment();
				return sandBoxPlayFragment;
			default:
				return new SandBoxChartFragment();
			}
		}

		@Override
		public void setPrimaryItem(ViewGroup container, int position,
				Object object) {
			mCurrFragment = ((BaseSandBoxFragment) object);
			super.setPrimaryItem(container, position, object);
		}

		@Override
		public int getCount() {
			return 6;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "Chart";
			case 1:
				return "Axis";
			case 2:
				return "Grid";
			case 3:
				return "Data";
			case 4:
				return "Animation";
			case 5:
				return "Play";
			default:
				return "";
			}
		}
	}

	public void onMenuClick(View view) {
		mCurrFragment.onStateChange(view.getId());
	}

	public void onPlay(View view) {
		sandBoxPlayFragment.onPlay(view);
	}
}
