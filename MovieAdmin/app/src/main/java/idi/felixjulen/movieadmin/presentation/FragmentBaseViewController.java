package idi.felixjulen.movieadmin.presentation;

import android.content.ContentValues;
import android.support.design.widget.NavigationView;

public abstract class FragmentBaseViewController extends BaseViewController implements NavigationView.OnNavigationItemSelectedListener {

    public static final String FRAGMENT_NAME = "FRAGMENT_NAME";

    public abstract void onFragmentAction(ContentValues values);

}
