package com.lingkai.spannablestringdemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        tvTestPozhenzi.setText(resources.getText(R.string.test_pozhenzi), TextView.BufferType.SPANNABLE)
        getEachParagraph(tvTestPozhenzi)
        tvTestPozhenzi.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getEachParagraph(tvTestPozhenzi: TextView?) {
        /*
                Spannable spans = (Spannable) textView.getText();
        Integer[] indices = getIndices(
        textView.getText().toString().trim(), ',');
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
        * */

        val text = tvTestPozhenzi?.text
        val split = text?.split(",")
        val start = 0
        val end = 0
        split?.forEach {

        }
    }


}


















