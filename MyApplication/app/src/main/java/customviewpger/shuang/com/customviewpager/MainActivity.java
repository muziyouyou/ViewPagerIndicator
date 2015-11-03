package customviewpger.shuang.com.customviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.vp_act)
    android.support.v4.view.ViewPager vp_act;
    @InjectView(R.id.ll_act)
    LinearLayout ll_act;
    @InjectView(R.id.ll_act1)
    LinearLayout ll_act1;
    View  blackdont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注解
        ButterKnife.inject(this);
        //設置點
        setdont();
        //設置adapter
        setadpter();
        Log.e("abc", Integer.MAX_VALUE / 2 - ((Integer.MAX_VALUE / 2) % 4) + "");
        vp_act.setCurrentItem(Integer.MAX_VALUE/2-((Integer.MAX_VALUE/2)%4));
        //設置viewpager滑動事件
        vp_act.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

                // 移动距离 = 起始位置 + 偏移大小
                // 起始位置 = position * 指示器宽度 startX
                // 偏移大小 = 手指划过屏幕的百分比 * 指示器宽度 offsetX
              /*  */
                Log.e("MainActivity", "position=" + position + ",positionOffset=" +
                        positionOffset + "," +
                        "positionOffsetPixels" + positionOffsetPixels);
                Log.e("width", "" + ll_act1.getWidth());

                int offsetX = (int) (positionOffset * ll_act1.getWidth());
                position=position%4;
                int startX = position * ll_act1.getWidth();
                int translationX = startX + offsetX;
                ViewHelper.setTranslationX(ll_act1, translationX);
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:

                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 設置adapter
     */
    private void setadpter() {
        //设置Adapter--->ViewPager
        vp_act.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                //设置参数
              /*  RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams
                                .WRAP_CONTENT);
                ImageView iv=new ImageView(getApplicationContext());*/
                View view = View.inflate(getApplicationContext(), R.layout.item, null);
                ImageView iv_item = (ImageView) view.findViewById(R.id.iv_item);
                //对应位置设置图片
                switch (position%4){

                    case 0:
                        iv_item.setImageResource(R.mipmap.one);
                        break;
                    case 1:
                        iv_item.setImageResource(R.mipmap.two);
                        break;
                    case 2:
                        iv_item.setImageResource(R.mipmap.three);
                        break;
                    case 3:
                        iv_item.setImageResource(R.mipmap.four);
                        break;
                }
                container.addView(view);
                return view;
            }


            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });
    }

    /**
     * 設置點
     */
    private void setdont() {
        //设置ll
        View dotView;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(15, 15);
        for(int i=0;i<=3;i++){
            //设置白点
            dotView = new View(getApplicationContext());
            dotView.setBackgroundResource(R.drawable.dont);
            if(i!=0){
                params.leftMargin = 10;
            }
            dotView.setLayoutParams(params);
            ll_act.addView(dotView);
            //设置黑点
            if(i==0){
                blackdont=new View(getApplication());
                blackdont.setBackgroundResource(R.drawable.dontbalck);
                blackdont.setLayoutParams(params);
                ll_act1.addView(blackdont);
            }
        }
    }
}
