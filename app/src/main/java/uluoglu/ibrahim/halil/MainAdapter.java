package uluoglu.ibrahim.halil;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MainAdapter extends FragmentPagerAdapter {
    private Context context;
    int totalTabs;

    public MainAdapter(FragmentManager fragmentManager, Context context, int totalTabs) {
        super(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new SigninTabFragment();
            case 1:
                return new SignupTabFragment();
            default:
                return null;
        }
    }

}
