package com.lingkai.javaspannable;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.Browser;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.*;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvTestPozhenzi;
    private TextView tvForegroundColor;
    private TextView tvBackgroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        init2();
        inint3();
        init4();
        init5();

    }

    private void init5() {
        TextView tvChaolianjie = findViewById(R.id.tvChaolianjie);
        SpannableString spannableString = new SpannableString("为文字设置超链接");
        URLSpan urlSpan = new URLSpan("http://www.baidu.com/");
        spannableString.setSpan(urlSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvChaolianjie.setMovementMethod(LinkMovementMethod.getInstance());
        tvChaolianjie.setHighlightColor(Color.parseColor("#ff0000"));
        tvChaolianjie.setText(spannableString);
    }

    private void init4() {
        TextView tvOnClick = findViewById(R.id.tvOnClick);
        SpannableString spannableString = new SpannableString("为文字设置点击事件");
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "你点击了我", Snackbar.LENGTH_SHORT).show();
                Uri uri = Uri.parse("http://www.baidu.com");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, MainActivity.this.getPackageName());
                try {
                    MainActivity.this.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    Log.e("--ClickableSpan--", "Actvity was not found for intent, " + intent.toString());
                }
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.parseColor("#abc123"));
                ds.setUnderlineText(true);
            }
        }, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvOnClick.setMovementMethod(LinkMovementMethod.getInstance());
        tvOnClick.setHighlightColor(Color.parseColor("#abc123"));
        tvOnClick.setText(spannableString);
    }

    private void inint3() {
        TextView tvStyleSpan = findViewById(R.id.tvStyleSpan);
        SpannableString spannableString = new SpannableString("为文字设置粗体,斜体风格");
        StyleSpan styleSpan_B = new StyleSpan(Typeface.BOLD);
        StyleSpan styleSpan_I = new StyleSpan(Typeface.ITALIC);
        spannableString.setSpan(styleSpan_B, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(styleSpan_I, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvStyleSpan.setText(spannableString);
    }

    private void init2() {
        TextView tvBackgroundColor = findViewById(R.id.tvRelativeSize);
        SpannableString spannableString = new SpannableString("9月22日");
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(2.0f);
        spannableString.setSpan(sizeSpan, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvBackgroundColor.setText(spannableString);
    }

    private void init() {
        tvTestPozhenzi = findViewById(R.id.tvTestPozhenzi);
        tvTestPozhenzi.setText(getResources().getText(R.string.test_pozhenzi), TextView.BufferType.SPANNABLE);
        getEachParagraph(tvTestPozhenzi);
        tvTestPozhenzi.setMovementMethod(LinkMovementMethod.getInstance());

        //设置前景色
        tvForegroundColor = findViewById(R.id.tvForegroundColor);
        SpannableString spannableString = new SpannableString("设置文字的前景色");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor("#0099EE"));
        spannableString.setSpan(colorSpan, 5, spannableString.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvForegroundColor.setText(spannableString);

        //设置背景色
        tvBackgroundColor = findViewById(R.id.tvBackgroundColor);
        SpannableString spannableString2 = new SpannableString("设置文字的背景色");
        BackgroundColorSpan colorSpan2 = new BackgroundColorSpan(Color.parseColor("#AC00FF30"));
        spannableString2.setSpan(colorSpan2, 5, spannableString2.length(), Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvBackgroundColor.setText(spannableString2);

    }

    //paragraph
    public void getEachParagraph(TextView textView) {
        Spannable spans = (Spannable) textView.getText();
        Integer[] indices = getIndices(
                textView.getText().toString().trim(), '，');
        int start = 0;
        int end = 0;
        // recycle
        for (int i = 0; i <= indices.length; i++) {
            ClickableSpan clickSpan = getClickableSpan();
        //setspan
            end = (i < indices.length ? indices[i] : spans.length());
            spans.setSpan(clickSpan, start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            start = end + 1;
        }
        //改变选中文本的高亮颜色
        textView.setHighlightColor(Color.GRAY);
    }

    //click
    private ClickableSpan getClickableSpan() {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                TextView tv = (TextView) widget;
                String s = tv
                        .getText()
                        .subSequence(tv.getSelectionStart(),
                                tv.getSelectionEnd()).toString();
                Log.e("onclick--:", s);
                Toast.makeText(MainActivity.this, "點擊了：" + s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setColor(Color.BLACK);
                ds.setUnderlineText(false);
            }
        };
    }

    //array
    public static Integer[] getIndices(String s, char c) {
        int pos = s.indexOf(c, 0);
        List<Integer> indices = new ArrayList<Integer>();
        while (pos != -1) {
            indices.add(pos);
            pos = s.indexOf(c, pos + 1);
        }
        return (Integer[]) indices.toArray(new Integer[0]);
    }
}
