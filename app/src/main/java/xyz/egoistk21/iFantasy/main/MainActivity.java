package xyz.egoistk21.iFantasy.main;

import android.content.Intent;
import android.graphics.Point;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import xyz.egoistk21.iFantasy.R;
import xyz.egoistk21.iFantasy.base.BaseActivity;
import xyz.egoistk21.iFantasy.login.LoginActivity;
import xyz.egoistk21.iFantasy.util.UIUtil;

public class MainActivity extends BaseActivity implements MainContract.View {

    public static final int LOGIN_SUCCESS = 351;

    private MainContract.Presenter mPresenter;

    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void initData() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        UIUtil.hideNavi(this);
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        int bottomMargin = UIUtil.px2dip(this, point.y / 3);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) btnLogin.getLayoutParams();
        layoutParams.bottomMargin = bottomMargin;
        btnLogin.setLayoutParams(layoutParams);
    }

    @Override
    protected void initEvent() {

    }

    @OnClick(R.id.btn_login)
    void login() {
        if (!mPresenter.isLogined()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivityForResult(intent, LOGIN_SUCCESS);
        }
    }

    @Override
    protected void onDetachP() {
        mPresenter.detachMV();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case LOGIN_SUCCESS:
                    break;
            }
        } else {
            // ToastUtil根据requestCode够构造的键值对
        }
    }
}
